package es.atrujillo.sample.funwalletms.domain.events

import es.atrujillo.sample.funwalletms.domain.model.base.DomainModel

data class DomainEventData<T : DomainModel>(val headers: DomainEventHeaders, val payload: T)