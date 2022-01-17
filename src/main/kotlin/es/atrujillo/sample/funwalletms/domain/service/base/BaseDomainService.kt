package es.atrujillo.sample.funwalletms.domain.service.base

import es.atrujillo.sample.funwalletms.domain.events.DomainEventData
import es.atrujillo.sample.funwalletms.domain.events.DomainEventPublisher
import es.atrujillo.sample.funwalletms.domain.events.DomainEventPublisherSink
import es.atrujillo.sample.funwalletms.domain.model.base.DomainModel
import reactor.core.publisher.Flux

open abstract class BaseDomainService<T : DomainModel>: DomainEventPublisher<T> {

    private val publisherSink: DomainEventPublisherSink<T>
    private val publisher: Flux<DomainEventData<T>>

    constructor() {
        publisherSink = DomainEventPublisherSink()
        publisher = Flux.create(publisherSink)
    }

    fun publishDomainEvent(event: DomainEventData<T>) {
        publisherSink.emitDomainEvent(event)
    }

    override fun getPublisherDataStream(): Flux<DomainEventData<T>> = publisher

}