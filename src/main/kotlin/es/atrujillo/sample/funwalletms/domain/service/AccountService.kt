package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.errors.base.DomainError
import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.ports.`in`.AccountCreationUseCase
import es.atrujillo.sample.funwalletms.domain.ports.`in`.DepositMoneyToAccountUseCase
import es.atrujillo.sample.funwalletms.domain.ports.out.AccountRepository
import es.atrujillo.sample.funwalletms.domain.ports.out.UserRepository
import reactor.core.publisher.Mono

class AccountService(private val accountRepository: AccountRepository, private val userRepository: UserRepository) : AccountCreationUseCase, DepositMoneyToAccountUseCase {

    override fun createAccount(account: Account): Mono<Account> {

        logInfo("EXECUTING BUSINESS LOGIC IN ACCOUNT CREATION")

        return validateThatAccountUserExists(account)
            .flatMap(this::validatePrimaryAccountIsPossible)
            .flatMap { accountRepository.saveAccount(it) }
    }

    override fun depositMoney() {
        TODO("Not yet implemented")
    }

    private fun validateThatAccountUserExists(account: Account): Mono<Account> {

        logInfo("VALIDATING THAT USER EXIST")

        return userRepository.get(account.userId)
            .filter { it != null }
            .map { account }
            .switchIfEmpty(Mono.error(DomainError("Username with userId ${account.userId} not found", "USER_NOT_FOUND", 400)))
    }

    private fun validatePrimaryAccountIsPossible(account: Account): Mono<Account> {

        logInfo("VALIDATING THAT PRIMARY ACCOUNT IS POSSIBLE")

            return accountRepository.getPrimaryAccountByUser(account.userId)
                .filter { account.primary }
                .flatMap{ Mono.error<DomainError>(DomainError("The user can only have one primary account", "EXISTING_PRIMARY_ACCOUNT", 400))}
                .cast(Account::class.java)
                .switchIfEmpty(Mono.just(account))
    }

}