package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.ports.AccountRepository
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.AccountEntity
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository.AccountDatabaseRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class AccountPersistenceRepositoryAdapter(val databaseRepository: AccountDatabaseRepository) : AccountRepository {

    override fun getByUser(userId: String): Flux<Account> {
        return databaseRepository.findAccountEntitiesByUserId(userId)
            .map { it.toDomain() }
    }

    override fun saveAccount(account: Account): Mono<Account> {
        return databaseRepository.save(AccountEntity.fromDomain(account))
            .map { it.toDomain() }
    }

}