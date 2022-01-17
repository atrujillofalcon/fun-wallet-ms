package es.atrujillo.sample.funwalletms.domain.ports.`in`

import es.atrujillo.sample.funwalletms.domain.model.Account
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AccountConsultUseCase {

    fun getAccountById(accountId: String): Mono<Account>

    fun getAccountByUser(userId: String?): Flux<Account>

}