package es.atrujillo.sample.funwalletms.domain.model

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.model.base.DomainModel

data class Transaction(
    val id: String?,
    val accountId: String,
    val amount: Double,
    val type: TransactionType,
    var status: TransactionStatusType = TransactionStatusType.PENDING,
    val from: String?,
    val to: String?,
    var fee: Double? = null,
) : DomainModel() {

    fun calculateFee() {
        logInfo("CALCULATING FEE FOR TRANSACTION BUSINESS LOGIC")
        fee = amount.div((1..10).random())
        logInfo("FEE SETTLED TO $fee of total amount $amount")
    }
}

enum class TransactionType {
    DEPOSIT, WITHDRAWAL, TRANSFER, PAYMENT
}

enum class TransactionStatusType {
    PENDING, DONE, CANCELLED, FAILED
}