package es.atrujillo.sample.funwalletms.domain.ports.out

import es.atrujillo.sample.funwalletms.domain.model.Transaction

interface BlockchainPublisherPort {

    fun publishTransactionToBlockchain(transaction: Transaction)

}