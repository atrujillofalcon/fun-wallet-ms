package es.atrujillo.sample.funwalletms.infraestructure.adapters.blockchain

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.events.DomainEventPublisher
import es.atrujillo.sample.funwalletms.domain.model.Transaction
import es.atrujillo.sample.funwalletms.domain.ports.out.BlockchainPublisherPort
import org.springframework.stereotype.Component

@Component
class BlockchainPublisherAdapter(transactionDomainEventPublisher: DomainEventPublisher<Transaction>) : BlockchainPublisherPort {

    init {
        logInfo("SUBSCRIBING TO TRANSACTIONS DOMAIN EVENTS TO PUBLISH IT TO BLOCKCHAIN")
        transactionDomainEventPublisher.getPublisherDataStream().map { it.payload }.subscribe(this::publishTransactionToBlockchain)
    }


    override fun publishTransactionToBlockchain(transaction: Transaction) {
        logInfo("PUBLISHING TRANSACTION TO BLOCKCHAIN. DATA $transaction")

        // here the blockchain logic
    }
}