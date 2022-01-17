package es.atrujillo.sample.funwalletms.domain.model

import es.atrujillo.sample.funwalletms.domain.model.base.DomainModel

data class Deposit(
    val accountId: String,
    val amount: Double,
) : DomainModel() {

    fun toTransaction(): Transaction {
        return Transaction(null, accountId, amount, TransactionType.DEPOSIT, TransactionStatusType.PENDING,  null, accountId)
    }

}
