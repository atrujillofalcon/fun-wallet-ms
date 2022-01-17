package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.model.Transaction
import es.atrujillo.sample.funwalletms.domain.ports.`in`.TransactionCreationUseCase
import es.atrujillo.sample.funwalletms.domain.ports.out.AccountRepository
import es.atrujillo.sample.funwalletms.domain.ports.out.TransactionRepository
import reactor.core.publisher.Mono

class TransactionService(private val transactionRepository: TransactionRepository, private val accountRepository: AccountRepository) : TransactionCreationUseCase {

    override fun createTransaction(transaction: Transaction): Mono<Transaction> {

        logInfo("EXECUTING BUSINESS LOGIC IN TRANSACTION CREATION")

        transaction.calculateFee()

        return transactionRepository.saveTransaction(transaction)

    }

}