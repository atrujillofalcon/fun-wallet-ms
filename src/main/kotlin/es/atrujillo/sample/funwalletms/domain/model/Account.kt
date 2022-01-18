package es.atrujillo.sample.funwalletms.domain.model

import es.atrujillo.sample.funwalletms.domain.model.base.DomainModel

data class Account(
    val id: String?,
    val userId: String,
    val alias: String?,
    val primary: Boolean,
    val type: AccountType,
    val currency: String,
    var balance: Double
) : DomainModel()

enum class AccountType {
    WALLET, FIAT
}