package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.model.User
import es.atrujillo.sample.funwalletms.domain.ports.UserRepository
import es.atrujillo.sample.funwalletms.domain.ports.`in`.UserCreationUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(val userRepository: UserRepository) : UserCreationUseCase {

    override fun createUser(user: User): Mono<User> {

        // TODO aquí iría la lógica de negocio
        logInfo("EXECUTING BUSINESS LOGIC IN USER CREATION")

        return userRepository.persistUser(user)
    }
}