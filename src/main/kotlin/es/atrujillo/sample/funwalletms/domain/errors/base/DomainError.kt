package es.atrujillo.sample.funwalletms.domain.errors.base

open class DomainError(val errorMessage: String, val errorCode: String?, val httpCode: Int = 500) : RuntimeException(errorMessage)