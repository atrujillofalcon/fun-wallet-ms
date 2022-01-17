package es.atrujillo.sample.funwalletms.domain.ports.out

import es.atrujillo.sample.funwalletms.domain.model.Transaction
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface TransactionRepository {

    fun saveTransaction(transaction: Transaction) : Mono<Transaction>

    fun getByAccount(accountId: String): Flux<Transaction>

}