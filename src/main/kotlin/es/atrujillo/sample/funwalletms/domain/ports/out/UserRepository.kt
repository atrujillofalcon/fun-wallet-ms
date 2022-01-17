package es.atrujillo.sample.funwalletms.domain.ports.out

import es.atrujillo.sample.funwalletms.domain.model.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepository {

    fun get(id: String): Mono<User>

    fun getByUsername(username: String): Flux<User>

    fun saveUser(user: User) : Mono<User>

}