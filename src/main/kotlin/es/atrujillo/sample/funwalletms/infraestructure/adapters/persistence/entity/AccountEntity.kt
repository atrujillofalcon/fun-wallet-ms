package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.model.AccountType

data class AccountEntity(
    val id: String?,
    val userId: String,
    val alias: String?,
    val primary: Boolean,
    val type: AccountType,
    val currency: String,
    val balance: Double
) {

    fun toDomain(): Account {
        return Account(id, userId, alias, primary, type, currency, balance)
    }

    companion object {
        fun fromDomain(account: Account): AccountEntity {
            return AccountEntity(
                account.id, account.userId, account.alias, account.primary,
                account.type, account.currency, account.balance
            )
        }
    }

}

