package es.atrujillo.sample.funwalletms.domain.ports.`in`

import es.atrujillo.sample.funwalletms.domain.model.Transaction
import reactor.core.publisher.Mono

interface TransactionCreationUseCase {

    fun createTransaction(transaction: Transaction): Mono<Transaction>

}