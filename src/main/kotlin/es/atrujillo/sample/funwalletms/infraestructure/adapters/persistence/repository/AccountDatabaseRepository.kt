package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository

import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.AccountEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface AccountDatabaseRepository : ReactiveCrudRepository<AccountEntity, Int> {

    fun findAccountEntitiesByUser(userId: Int): Flux<AccountEntity>

}