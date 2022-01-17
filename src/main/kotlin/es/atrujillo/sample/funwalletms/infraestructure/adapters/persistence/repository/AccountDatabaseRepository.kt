package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.repository

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.ports.AccountRepository
import es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity.AccountEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface AccountDatabaseRepository : ReactiveCrudRepository<AccountEntity, Int>, AccountRepository {

    fun findAccountEntitiesByUser(userId: Int) : Flux<AccountEntity>

    override fun saveAccount(user: Mono<Account>): Mono<Account> {
        return user.map { AccountEntity.fromDomain(it) }
            .flatMap { this.save(it) }
            .map { it.toDomain() }
    }

    override fun getByUser(userId: Int): Flux<Account> {
        return findAccountEntitiesByUser(userId)
            .map { it.toDomain() }
    }
}