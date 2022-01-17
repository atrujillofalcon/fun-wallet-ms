package es.atrujillo.sample.funwalletms.domain.ports.out

import es.atrujillo.sample.funwalletms.domain.model.Account
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AccountRepository {

    fun getAccountById(accountId: String): Mono<Account>

    fun getAccountsByUser(userId: String): Flux<Account>

    fun getPrimaryAccountByUser(userId: String): Mono<Account>

    fun getAccounts(): Flux<Account>

    fun saveAccount(account: Account) : Mono<Account>

}