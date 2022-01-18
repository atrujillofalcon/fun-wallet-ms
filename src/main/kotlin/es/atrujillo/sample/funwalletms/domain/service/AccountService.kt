package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.errors.AccountNotFoundError
import es.atrujillo.sample.funwalletms.domain.errors.ExistingPrimaryAccountError
import es.atrujillo.sample.funwalletms.domain.errors.UnsupportedTransactionType
import es.atrujillo.sample.funwalletms.domain.errors.UserNotFoundError
import es.atrujillo.sample.funwalletms.domain.errors.base.DomainError
import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.model.Transaction
import es.atrujillo.sample.funwalletms.domain.model.TransactionType
import es.atrujillo.sample.funwalletms.domain.ports.`in`.AccountConsultUseCase
import es.atrujillo.sample.funwalletms.domain.ports.`in`.AccountCreationUseCase
import es.atrujillo.sample.funwalletms.domain.ports.`in`.AccountUpdateBalanceUseCase
import es.atrujillo.sample.funwalletms.domain.ports.out.AccountRepository
import es.atrujillo.sample.funwalletms.domain.ports.out.UserRepository
import es.atrujillo.sample.funwalletms.domain.service.base.BaseDomainService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class AccountService(private val accountRepository: AccountRepository, private val userRepository: UserRepository) : BaseDomainService<Account>(), AccountCreationUseCase, AccountConsultUseCase,
    AccountUpdateBalanceUseCase {

    override fun getAccountById(accountId: String): Mono<Account> {

        logInfo("EXECUTING BUSINESS LOGIC IN ACCOUNT CONSULTING BY ID")

        return accountRepository.getAccountById(accountId)
            .switchIfEmpty(Mono.error(AccountNotFoundError(accountId)))
    }

    override fun getAccountByUser(userId: String?): Flux<Account> {

        logInfo("EXECUTING BUSINESS LOGIC IN ACCOUNTS CONSULTING BY USER")

        return if (userId != null) {
            accountRepository.getAccountsByUser(userId)
        } else {
            accountRepository.getAccounts()
        }
    }

    override fun createAccount(account: Account): Mono<Account> {

        logInfo("EXECUTING BUSINESS LOGIC IN ACCOUNT CREATION")

        return validateThatAccountUserExists(account)
            .flatMap(this::validatePrimaryAccountIsPossible)
            .flatMap { accountRepository.saveAccount(it) }
            .doOnNext { createdAccount -> publishDomainEvent("ACCOUNT_CREATED", createdAccount) }
    }

    override fun updateAccountBalance(transaction: Transaction): Mono<Account> {

        logInfo("EXECUTING BUSINESS LOGIC IN UPDATING BALANCE BY TRANSACTION")

        if (transaction.type == TransactionType.DEPOSIT) {
            return getAccountById(transaction.accountId)
                .map { calculateBalanceResultByTransaction(it, transaction, true) }
                .flatMap(accountRepository::saveAccount)
        }

        if (transaction.type == TransactionType.TRANSFER && transaction.to != null) {
            return getAccountById(transaction.accountId)
                .map { calculateBalanceResultByTransaction(it, transaction, false) }
                .flatMap(accountRepository::saveAccount)
                .flatMap{ getAccountById(transaction.to) }
                .map { calculateBalanceResultByTransaction(it, transaction, true) }
                .flatMap(accountRepository::saveAccount)
        }

        throw UnsupportedTransactionType()
    }

    private fun validateThatAccountUserExists(account: Account): Mono<Account> {

        logInfo("VALIDATING THAT USER EXIST")

        return userRepository.get(account.userId)
            .filter { it != null }
            .map { account }
            .switchIfEmpty(Mono.error(UserNotFoundError(account.userId)))
    }

    private fun validatePrimaryAccountIsPossible(account: Account): Mono<Account> {

        logInfo("VALIDATING THAT PRIMARY ACCOUNT IS POSSIBLE")

        return accountRepository.getPrimaryAccountByUser(account.userId)
            .filter { account.primary }
            .flatMap { Mono.error<DomainError>(ExistingPrimaryAccountError()) }
            .cast(Account::class.java)
            .switchIfEmpty(Mono.just(account))
    }

    private fun calculateBalanceResultByTransaction(account: Account, transaction: Transaction, positiveSign: Boolean): Account {
        val partialBalance = (account.balance - transaction.fee!!)
        account.balance = if (positiveSign) {
            partialBalance.plus(transaction.amount)
        } else {
            partialBalance.minus(transaction.amount)
        }
        return account;
    }

}