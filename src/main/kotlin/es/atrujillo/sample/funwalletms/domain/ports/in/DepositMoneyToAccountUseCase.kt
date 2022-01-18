package es.atrujillo.sample.funwalletms.domain.ports.`in`

import es.atrujillo.sample.funwalletms.domain.model.Deposit
import es.atrujillo.sample.funwalletms.domain.model.Transaction
import reactor.core.publisher.Mono

interface DepositMoneyToAccountUseCase {

    fun depositMoney(deposit: Deposit): Mono<Transaction>

}