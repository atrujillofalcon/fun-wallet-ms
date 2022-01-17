package es.atrujillo.sample.funwalletms.infraestructure.adapters.notifications

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.events.DomainEventData
import es.atrujillo.sample.funwalletms.domain.events.DomainEventPublisher
import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.model.Transaction
import es.atrujillo.sample.funwalletms.domain.model.User
import org.springframework.stereotype.Component

@Component
class DummyNotificatorAdapter(
    userDomainEventPublisher: DomainEventPublisher<User>,
    accountDomainEventPublisher: DomainEventPublisher<Account>,
    transactionDomainEventPublisher: DomainEventPublisher<Transaction>
) {

    init {
        logInfo("SUBSCRIBING TO DOMAIN EVENTS TO GENERATE NOTIFICATIONS")
        userDomainEventPublisher.getPublisherDataStream().subscribe(this::notifyLogUserEvent)
        accountDomainEventPublisher.getPublisherDataStream().subscribe(this::notifyLogAccountEvent)
        transactionDomainEventPublisher.getPublisherDataStream().subscribe(this::notifyLogTransactionEvent)
    }


    private fun notifyLogUserEvent(event: DomainEventData<User>) {
        logInfo("RECEIVED USER DOMAIN EVENT WITH NAME ${event.headers.eventName} AND PAYLOAD: \n ${event.payload}")
    }

    private fun notifyLogAccountEvent(event: DomainEventData<Account>) {
        logInfo("RECEIVED ACCOUNT DOMAIN EVENT WITH NAME ${event.headers.eventName} AND PAYLOAD: \n ${event.payload}")
    }

    private fun notifyLogTransactionEvent(event: DomainEventData<Transaction>) {
        logInfo("RECEIVED TRANSACTION DOMAIN EVENT WITH NAME ${event.headers.eventName} AND PAYLOAD: \n ${event.payload}")
    }


}