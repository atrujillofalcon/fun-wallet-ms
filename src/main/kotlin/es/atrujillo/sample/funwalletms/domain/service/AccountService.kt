package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.ports.AccountRepository
import es.atrujillo.sample.funwalletms.domain.ports.UserRepository
import es.atrujillo.sample.funwalletms.domain.ports.`in`.AccountCreationUseCase
import es.atrujillo.sample.funwalletms.domain.ports.`in`.DepositMoneyToAccountUseCase
import reactor.core.publisher.Mono

class AccountService(private val accountRepository: AccountRepository, private val userRepository: UserRepository) : AccountCreationUseCase, DepositMoneyToAccountUseCase {

    override fun createAccount(account: Account): Mono<Account> {

        logInfo("EXECUTING BUSINESS LOGIC IN ACCOUNT CREATION")

        return validateThatAccountUserExists(account)
            .filter { it }
            .flatMap { accountRepository.saveAccount(account) }

    }

    override fun depositMoney() {
        TODO("Not yet implemented")
    }

    private fun validateThatAccountUserExists(account: Account): Mono<Boolean> {

        logInfo("VALIDATING THAT USER EXIST")

        return userRepository.get(account.userId).map { it != null }
    }

}