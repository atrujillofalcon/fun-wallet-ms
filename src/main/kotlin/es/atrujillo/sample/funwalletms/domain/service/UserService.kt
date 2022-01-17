package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.errors.UserAlreadyExistsError
import es.atrujillo.sample.funwalletms.domain.model.User
import es.atrujillo.sample.funwalletms.domain.ports.`in`.UserCreationUseCase
import es.atrujillo.sample.funwalletms.domain.ports.out.UserRepository
import reactor.core.publisher.Mono

class UserService(private val userRepository: UserRepository) : UserCreationUseCase {

    override fun createUser(user: User): Mono<User> {

        logInfo("EXECUTING BUSINESS LOGIC IN USER CREATION")

        logInfo("VALIDATING THAT USERNAME IS UNIQUE")
        return userRepository.getByUsername(user.username)
            .count()
            .filter { usernamesCount -> usernamesCount == 0L }
            .flatMap { userRepository.saveUser(user) }
            .switchIfEmpty(Mono.error(UserAlreadyExistsError(user.username)))
    }
}