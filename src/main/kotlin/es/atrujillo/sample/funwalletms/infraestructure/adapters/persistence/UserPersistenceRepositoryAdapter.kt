package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.model.User
import es.atrujillo.sample.funwalletms.domain.ports.UserRepository
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.UserEntity
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository.UserDatabaseRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class UserPersistenceRepositoryAdapter(val databaseRepository: UserDatabaseRepository) : UserRepository {

    override fun get(id: String): Mono<User> {
        return databaseRepository.findById(id)
            .map { it.toDomain() }
    }

    override fun getByUsername(username: String): Flux<User> {
        return databaseRepository.findUserEntitiesByUsername(username)
            .map { it.toDomain() }
    }

    override fun persistUser(user: User): Mono<User> {

        logInfo("PERSISTING USER INTO DB")

        return databaseRepository.save(UserEntity.fromDomain(user))
            .map { it.toDomain() }
    }
}