package es.atrujillo.sample.funwalletms.domain.events

import es.atrujillo.sample.funwalletms.config.extensions.logDebug
import es.atrujillo.sample.funwalletms.domain.model.base.DomainModel
import reactor.core.publisher.FluxSink
import java.util.function.Consumer

class DomainEventPublisherSink<T : DomainModel> : Consumer<FluxSink<DomainEventData<T>>> {

    private lateinit var domainEventSink: FluxSink<DomainEventData<T>>

    override fun accept(incomingSink: FluxSink<DomainEventData<T>>) {
        logDebug("Accepting incoming flux sink")
        domainEventSink = incomingSink
    }

    fun emitDomainEvent(event: DomainEventData<T>) {
        domainEventSink.next(event)
    }

}