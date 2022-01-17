package es.atrujillo.sample.funwalletms.domain.model

data class Account(
    val id: String?,
    val userId: String,
    val alias: String?,
    val primary: Boolean,
    val type: AccountType,
    val currency: String,
    val balance: Double
)

enum class AccountType {
    WALLET, FIAT
}