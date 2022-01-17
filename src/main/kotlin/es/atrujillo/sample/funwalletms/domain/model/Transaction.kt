package es.atrujillo.sample.funwalletms.domain.model

data class Transaction(
    val id: Int,
    val account: Account,
    val amount: Double,
    val type: TransactionType,
    val status: TransactionStatusType = TransactionStatusType.PENDING,
    val fee: Double = 0.0,
    val from: Account?,
    val to: Account?
)

enum class TransactionType {
    DEPOSIT, WITHDRAWAL, TRANSFER, OPERATION
}

enum class TransactionStatusType {
    PENDING, DONE, CANCELLED, FAILED
}