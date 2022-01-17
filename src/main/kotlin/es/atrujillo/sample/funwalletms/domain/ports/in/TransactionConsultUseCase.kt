package es.atrujillo.sample.funwalletms.domain.ports.`in`

import es.atrujillo.sample.funwalletms.domain.model.Transaction
import reactor.core.publisher.Flux

interface TransactionConsultUseCase {

    fun getTransactionsByAccount(accountId: String): Flux<Transaction>

}