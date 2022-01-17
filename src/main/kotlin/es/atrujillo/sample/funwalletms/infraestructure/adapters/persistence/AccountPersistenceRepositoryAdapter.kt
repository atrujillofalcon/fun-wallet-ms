package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.model.User
import es.atrujillo.sample.funwalletms.domain.ports.AccountRepository
import es.atrujillo.sample.funwalletms.domain.ports.UserRepository
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.AccountEntity
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.UserEntity
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository.AccountDatabaseRepository
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository.UserDatabaseRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class AccountPersistenceRepositoryAdapter(val databaseRepository: AccountDatabaseRepository) : AccountRepository {

    override fun getByUser(userId: Int): Flux<Account> {
        return databaseRepository.findAccountEntitiesByUser(userId)
            .map { it.toDomain() }
    }

    override fun saveAccount(account: Mono<Account>): Mono<Account> {
        return account.map { AccountEntity.fromDomain(it) }
            .flatMap { databaseRepository.save(it) }
            .map { it.toDomain() }
    }
}