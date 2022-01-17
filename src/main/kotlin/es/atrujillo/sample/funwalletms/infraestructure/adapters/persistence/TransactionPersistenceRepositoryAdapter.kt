package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence

import es.atrujillo.sample.funwalletms.domain.model.Transaction
import es.atrujillo.sample.funwalletms.domain.ports.out.TransactionRepository
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.TransactionEntity
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository.TransactionDatabaseRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class TransactionPersistenceRepositoryAdapter(val databaseRepository: TransactionDatabaseRepository) : TransactionRepository {

    override fun saveTransaction(transaction: Transaction): Mono<Transaction> {
        return databaseRepository.save(TransactionEntity.fromDomain(transaction))
            .map { it.toDomain() }
    }

    override fun getByAccount(accountId: String): Flux<Transaction> {
        return databaseRepository.findTransactionEntitiesByAccountId(accountId)
            .map { it.toDomain() }
    }


}