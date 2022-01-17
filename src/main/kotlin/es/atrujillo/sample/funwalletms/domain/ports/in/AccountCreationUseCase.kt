package es.atrujillo.sample.funwalletms.domain.ports.`in`

import es.atrujillo.sample.funwalletms.domain.model.Account
import reactor.core.publisher.Mono

interface AccountCreationUseCase {

    fun createAccount(account: Account): Mono<Account>

}