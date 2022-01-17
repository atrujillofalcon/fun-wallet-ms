package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.errors.base.DomainError
import es.atrujillo.sample.funwalletms.domain.model.User
import es.atrujillo.sample.funwalletms.domain.ports.UserRepository
import es.atrujillo.sample.funwalletms.domain.ports.`in`.UserCreationUseCase
import reactor.core.publisher.Mono

class UserService(val userRepository: UserRepository) : UserCreationUseCase {

    override fun createUser(user: User): Mono<User> {

        logInfo("EXECUTING BUSINESS LOGIC IN USER CREATION")

        logInfo("VALIDATING THAT USERNAME IS UNIQUE")
        return userRepository.getByUsername(user.username)
            .count()
            .filter { usernamesCount -> usernamesCount == 0L }
            .flatMap { userRepository.persistUser(user) }
            .switchIfEmpty(Mono.error(DomainError("Username ${user.username} already exists", "USER_ALREADY_EXISTS", 400)))
    }
}