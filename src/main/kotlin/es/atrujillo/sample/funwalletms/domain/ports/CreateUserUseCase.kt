package es.atrujillo.sample.funwalletms.domain.ports

import es.atrujillo.sample.funwalletms.domain.model.User
import reactor.core.publisher.Mono

interface CreateUserUseCase {

    fun createUser(user: User): Mono<User>


}