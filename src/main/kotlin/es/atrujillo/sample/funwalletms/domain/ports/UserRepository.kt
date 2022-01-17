package es.atrujillo.sample.funwalletms.domain.ports

import es.atrujillo.sample.funwalletms.domain.model.User
import reactor.core.publisher.Mono

interface UserRepository {

    fun get(id: Int): Mono<User>

    fun persistUser(user: User) : Mono<User>

}