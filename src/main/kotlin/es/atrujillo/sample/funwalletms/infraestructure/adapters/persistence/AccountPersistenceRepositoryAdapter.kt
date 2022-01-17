package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.ports.out.AccountRepository
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.AccountEntity
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository.AccountDatabaseRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class AccountPersistenceRepositoryAdapter(val databaseRepository: AccountDatabaseRepository) : AccountRepository {

    override fun getAccountById(accountId: String): Mono<Account> {
        return databaseRepository.findById(accountId)
            .map { it.toDomain() }
    }

    override fun getAccountsByUser(userId: String): Flux<Account> {
        return databaseRepository.findAccountEntitiesByUserId(userId)
            .map { it.toDomain() }
    }

    override fun getPrimaryAccountByUser(userId: String): Mono<Account> {
        return databaseRepository.findAccountEntitiesByPrimaryIsTrue()
            .map { it.toDomain() }
    }

    override fun getAccounts(): Flux<Account> {
        return databaseRepository.findAll()
            .map { it.toDomain() }
    }

    override fun saveAccount(account: Account): Mono<Account> {
        return databaseRepository.save(AccountEntity.fromDomain(account))
            .map { it.toDomain() }
    }

}