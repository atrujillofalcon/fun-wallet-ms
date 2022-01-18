package es.atrujillo.sample.funwalletms.infraestructure.adapters.notifications

import es.atrujillo.sample.funwalletms.config.extensions.logInfo
import es.atrujillo.sample.funwalletms.domain.events.DomainEventData
import es.atrujillo.sample.funwalletms.domain.events.DomainEventPublisher
import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.domain.model.User
import org.springframework.stereotype.Component

@Component
class DummyNotificatorAdapter(
    userDomainEventPublisher: DomainEventPublisher<User>,
    accountDomainEventPublisher: DomainEventPublisher<Account>,
) {

    init {
        logInfo("SUBSCRIBING TO DOMAIN EVENTS TO GENERATE NOTIFICATIONS")
        userDomainEventPublisher.getPublisherDataStream().subscribe(this::notifyLogUserEvent)
        accountDomainEventPublisher.getPublisherDataStream().subscribe(this::notifyLogAccountEvent)
    }


    private fun notifyLogUserEvent(event: DomainEventData<User>) {
        logInfo("RECEIVED USER DOMAIN EVENT WITH NAME ${event.headers.eventName} AND PAYLOAD: \n ${event.payload}")
    }

    private fun notifyLogAccountEvent(event: DomainEventData<Account>) {
        logInfo("RECEIVED ACCOUNT DOMAIN EVENT WITH NAME ${event.headers.eventName} AND PAYLOAD: \n ${event.payload}")
    }


}