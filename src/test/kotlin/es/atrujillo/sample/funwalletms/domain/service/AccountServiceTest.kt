package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.domain.errors.AccountNotFoundError
import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.model.AccountType
import es.atrujillo.sample.funwalletms.domain.ports.out.AccountRepository
import es.atrujillo.sample.funwalletms.domain.ports.out.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.Duration

@ExtendWith(MockitoExtension::class)
internal class AccountServiceTest {

    lateinit var accountService: AccountService

    @Mock
    lateinit var accountRepository: AccountRepository

    @Mock
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun prepareTest() {
        accountService = AccountService(accountRepository, userRepository)

        StepVerifier.create(accountService.getPublisherDataStream()).expectSubscription().verifyTimeout(Duration.ofMillis(1000))
    }

    @Test
    fun givenNotSupportedTransactionShouldThrowError() {

    }

    @Test
    fun givenEmptyResponseFromRepositoryShouldThrowNotFoundAccountError() {

        val accountIdToFind = "123456"

        Mockito.`when`(accountRepository.getAccountById(accountIdToFind)).thenReturn(Mono.empty())

        val accountResult = accountService.getAccountById(accountIdToFind)

        StepVerifier.create(accountResult)
            .expectError(AccountNotFoundError::class.java)
            .verify()

    }

    @Test
    fun givenExistingAccountIdShouldReturnAccountFromRepository() {

        val accountIdToFind = "123456"
        val account = Account("123456", "555", "testing", true, AccountType.WALLET, "EUR", 10.0)

        Mockito.`when`(accountRepository.getAccountById(accountIdToFind)).thenReturn(Mono.just(account))

        val accountResult = accountService.getAccountById(accountIdToFind)

        StepVerifier.create(accountResult)
            .expectNext(account)
            .expectComplete()
            .verify()

    }

    @Test
    fun givenExistingUserWithAccountsShouldReturnAccountFindingByUserId() {

        val userIdToFind = "555"
        val primaryAccount = Account("123456", "555", "testing", true, AccountType.WALLET, "EUR", 1000.0)
        val secundaryAccount = Account("678901", "555", "secundary", false, AccountType.WALLET, "EUR", 25.0)

        val accounts = Flux.fromArray(arrayOf(primaryAccount, secundaryAccount))

        Mockito.`when`(accountRepository.getAccountsByUser(userIdToFind)).thenReturn(accounts)

        val accountsResult = accountService.getAccountByUser(userIdToFind)

        StepVerifier.create(accountsResult)
            .expectNext(primaryAccount, secundaryAccount)
            .expectComplete()
            .verify()

    }

    @Test
    fun givenNullUserIdParamShouldReturnAllAccounts() {

        val userIdToFind = null
        val primaryAccount = Account("123456", "555", "testing", true, AccountType.WALLET, "EUR", 1000.0)
        val secundaryAccount = Account("678901", "555", "secundary", false, AccountType.WALLET, "EUR", 25.0)

        val accounts = Flux.fromArray(arrayOf(primaryAccount, secundaryAccount))

        Mockito.`when`(accountRepository.getAccounts()).thenReturn(accounts)

        val accountsResult = accountService.getAccountByUser(userIdToFind)

        StepVerifier.create(accountsResult)
            .expectNext(primaryAccount, secundaryAccount)
            .expectComplete()
            .verify()

    }

}

