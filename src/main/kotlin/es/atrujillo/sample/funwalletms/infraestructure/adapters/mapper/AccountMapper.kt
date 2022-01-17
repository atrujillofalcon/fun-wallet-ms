package es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.model.CreateAccountRequest
import es.atrujillo.sample.funwalletms.model.CreateAccountResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface AccountMapper {

    @Mapping(target = "id", expression = "java(null)")
    fun createAccountRequestToDomain(request: CreateAccountRequest): Account

    @Mapping(target = "data.id", source = "id")
    @Mapping(target = "data.balance", source = "balance")
    @Mapping(target = "data.userId", source = "userId")
    @Mapping(target = "data.currency", source = "currency")
    @Mapping(target = "data.primary", source = "primary")
    @Mapping(target = "data.type", source = "type")
    fun domainAccountToResponse(user: Account): CreateAccountResponse

}