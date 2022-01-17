package es.atrujillo.sample.funwalletms.domain.ports.`in`

import es.atrujillo.sample.funwalletms.domain.model.User
import reactor.core.publisher.Mono

interface UserCreationUseCase {

    fun createUser(user: User): Mono<User>

}