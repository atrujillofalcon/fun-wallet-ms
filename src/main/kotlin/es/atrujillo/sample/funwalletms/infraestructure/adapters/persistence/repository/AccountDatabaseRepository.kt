package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository

import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.AccountEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface AccountDatabaseRepository : ReactiveCrudRepository<AccountEntity, String> {

    fun findAccountEntitiesByUserId(userId: String): Flux<AccountEntity>

    fun findAccountEntitiesByPrimaryIsTrue(): Mono<AccountEntity>

}