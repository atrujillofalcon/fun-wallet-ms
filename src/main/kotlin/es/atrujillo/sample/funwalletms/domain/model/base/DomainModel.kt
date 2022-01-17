package es.atrujillo.sample.funwalletms.domain.model.base

abstract class DomainModel {

    open fun publishable(): Boolean = true

}