package es.atrujillo.sample.funwalletms.domain.model

data class Transaction(
    val id: String,
    val accountId: String,
    val amount: Double,
    val type: TransactionType,
    val status: TransactionStatusType = TransactionStatusType.PENDING,
    val fee: Double = 0.0,
    val from: String?,
    val to: String?
)

enum class TransactionType {
    DEPOSIT, WITHDRAWAL, TRANSFER, OPERATION
}

enum class TransactionStatusType {
    PENDING, DONE, CANCELLED, FAILED
}