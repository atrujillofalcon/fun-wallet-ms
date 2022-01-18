package es.atrujillo.sample.funwalletms.domain.ports.`in`

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.model.Transaction
import reactor.core.publisher.Mono

interface AccountUpdateBalanceUseCase {

    fun updateAccountBalance(transaction: Transaction): Mono<Account>

}