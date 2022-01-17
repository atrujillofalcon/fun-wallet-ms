package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository

import es.atrujillo.sample.funwalletms.domain.ports.UserRepository
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.UserEntity
import es.atrujillo.sample.funwalletms.domain.model.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserDatabaseRepository: ReactiveCrudRepository<UserEntity, Int>, UserRepository {

    override fun persistUser(user: Mono<User>): Mono<User> {
        return user.map { UserEntity.fromDomain(it) }
            .flatMap { this.save(it) }
            .map { it.toDomain() }
    }
}