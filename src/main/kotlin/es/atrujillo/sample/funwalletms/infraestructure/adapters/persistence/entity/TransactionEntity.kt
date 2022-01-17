package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity

import es.atrujillo.sample.funwalletms.domain.model.Transaction
import es.atrujillo.sample.funwalletms.domain.model.TransactionStatusType
import es.atrujillo.sample.funwalletms.domain.model.TransactionType
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class TransactionEntity(
    @Indexed
    val id: String?,
    @Indexed
    val accountId: String,
    val amount: Double,
    val type: TransactionType,
    val status: TransactionStatusType = TransactionStatusType.PENDING,
    val from: String?,
    val to: String?,
    val fee: Double? = null,
) {

    fun toDomain(): Transaction {
        return Transaction(id, accountId, amount, type, status, from, to, fee)
    }

    companion object {
        fun fromDomain(transaction: Transaction): TransactionEntity {
            return TransactionEntity(
                transaction.id, transaction.accountId, transaction.amount, transaction.type, transaction.status,
                transaction.from, transaction.to, transaction.fee
            )
        }
    }

}

