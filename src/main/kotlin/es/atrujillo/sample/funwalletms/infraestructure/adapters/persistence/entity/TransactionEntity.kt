package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity

import es.atrujillo.sample.funwalletms.domain.model.TransactionStatusType
import es.atrujillo.sample.funwalletms.domain.model.TransactionType

data class TransactionEntity(
    val id: Int,
    val account: AccountEntity,
    val amount: Double,
    val type: TransactionType,
    val status: TransactionStatusType = TransactionStatusType.PENDING,
    val fee: Double = 0.0,
    val from: AccountEntity?,
    val to: AccountEntity?
)

