package es.atrujillo.sample.funwalletms.domain.events

import es.atrujillo.sample.funwalletms.domain.model.base.DomainModel
import reactor.core.publisher.Flux

interface DomainEventPublisher<T : DomainModel> {

    fun getPublisherDataStream() : Flux<DomainEventData<T>>

}