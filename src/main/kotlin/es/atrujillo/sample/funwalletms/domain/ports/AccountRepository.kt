package es.atrujillo.sample.funwalletms.domain.ports

import es.atrujillo.sample.funwalletms.domain.model.Account
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AccountRepository {

    fun getByUser(userId: Int): Flux<Account>

    fun saveAccount(user: Mono<Account>) : Mono<Account>

}