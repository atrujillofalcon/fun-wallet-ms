package es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper

import es.atrujillo.sample.funwalletms.domain.errors.base.DomainError
import es.atrujillo.sample.funwalletms.infraestructure.adapters.web.error.ErrorResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface ErrorMapper {

    @Mapping(target = "cause", source = "errorMessage")
    @Mapping(target = "code", source = "errorCode")
    fun createUserRequestToDomain(domainError: DomainError): ErrorResponse

}