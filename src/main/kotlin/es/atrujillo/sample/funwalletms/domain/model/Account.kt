package es.atrujillo.sample.funwalletms.domain.model

data class Account(
    val id: Int,
    val user: User,
    val primary: Boolean,
    val type: AccountType,
    val currency: String,
    val balance: Double
)

enum class AccountType {
    WALLET, FIAT
}