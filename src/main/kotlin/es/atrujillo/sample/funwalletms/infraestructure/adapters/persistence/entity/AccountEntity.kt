package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.model.AccountType

data class AccountEntity(
    val id: Int,
    val user: UserEntity,
    val primary: Boolean,
    val type: AccountType,
    val currency: String,
    val balance: Double
) {

    fun toDomain(): Account {
        return Account(id, user.toDomain(), primary, type, currency, balance)
    }

    companion object {
        fun fromDomain(account: Account): AccountEntity {
            return AccountEntity(
                account.id, UserEntity.fromDomain(account.user), account.primary, account.type,
                account.currency, account.balance
            )
        }
    }

}

