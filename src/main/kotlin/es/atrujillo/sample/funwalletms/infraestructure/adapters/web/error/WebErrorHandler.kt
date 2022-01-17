package es.atrujillo.sample.funwalletms.infraestructure.adapters.web.error

import es.atrujillo.sample.funwalletms.config.extensions.logError
import es.atrujillo.sample.funwalletms.domain.errors.base.DomainError
import es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper.ErrorMapper
import org.mapstruct.factory.Mappers
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class WebErrorHandler {

    private final val mapper: ErrorMapper = Mappers.getMapper(ErrorMapper::class.java)

    @ExceptionHandler
    fun handleDomainError(domainError: DomainError): ResponseEntity<ErrorResponse> {

        logError(domainError.errorMessage, domainError)

        val errorResponse = mapper.createUserRequestToDomain(domainError)

        return ResponseEntity
            .status(domainError.httpCode)
            .body(errorResponse)
    }

}