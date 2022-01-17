package es.atrujillo.sample.funwalletms.domain.ports

import es.atrujillo.sample.funwalletms.domain.model.Account
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AccountRepository {

    fun getByUser(userId: String): Flux<Account>

    fun getPrimaryAccountByUser(userId: String): Mono<Account>

    fun saveAccount(account: Account) : Mono<Account>

}