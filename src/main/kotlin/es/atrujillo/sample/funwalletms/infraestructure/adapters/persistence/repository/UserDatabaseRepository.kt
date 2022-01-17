package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository

import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.UserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDatabaseRepository : ReactiveCrudRepository<UserEntity, Int>