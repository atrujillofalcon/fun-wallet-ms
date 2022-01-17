package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.model.AccountType
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class AccountEntity(
    val id: String?,
    @Indexed val userId: String,
    val alias: String?,
    @Indexed val primary: Boolean,
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

