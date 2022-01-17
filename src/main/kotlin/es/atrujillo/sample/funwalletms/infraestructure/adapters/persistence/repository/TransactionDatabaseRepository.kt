package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository

import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.TransactionEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TransactionDatabaseRepository : ReactiveCrudRepository<TransactionEntity, String> {

    fun findTransactionEntitiesByAccountId(accountId: String): Flux<TransactionEntity>

}