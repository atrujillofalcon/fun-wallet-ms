package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.domain.model.*
import es.atrujillo.sample.funwalletms.domain.ports.out.TransactionRepository
import es.atrujillo.sample.funwalletms.helpers.MockitoKotlinHelper.Companion.any
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.Duration

@ExtendWith(MockitoExtension::class)
internal class TransactionServiceTest {

    lateinit var transactionService: TransactionService

    @Mock
    lateinit var transactionRepository: TransactionRepository

    @Mock
    lateinit var accountService: AccountService

    @BeforeEach
    fun prepareTest() {
        transactionService = TransactionService(transactionRepository, accountService)
    }

    @Test
    fun givenNewDepositShouldCreateAndProcessTransaction() {

        val deposit = Deposit("12345", 100.0)
        val depositTransaction = Transaction(null, "12345", 100.0, TransactionType.DEPOSIT, TransactionStatusType.PENDING, null, "12345", null);
        val account = Account("5", "111", "PRUEBA", true, AccountType.WALLET, "EUR", 1100.0)

        Assertions.assertEquals(deposit.toTransaction(), depositTransaction)

        val withFeeDeposito = Transaction("789", "12345", 100.0, TransactionType.DEPOSIT, TransactionStatusType.PENDING, null, "12345", 10.0);

        Mockito.`when`(transactionRepository.saveTransaction(any(Transaction::class.java))).thenReturn(Mono.just(withFeeDeposito))
        Mockito.`when`(accountService.updateAccountBalance(any(Transaction::class.java))).thenReturn(Mono.just(account))

        val transaction = transactionService.depositMoney(deposit)

        StepVerifier.create(transactionService.getPublisherDataStream()).expectSubscription().verifyTimeout(Duration.ofMillis(1000))

        StepVerifier.create(transaction)
            .expectNext(transaction.block())
            .expectComplete()
            .verify()


    }

}