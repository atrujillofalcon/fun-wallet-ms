package es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper

import es.atrujillo.sample.funwalletms.domain.model.Account
import es.atrujillo.sample.funwalletms.model.CreateAccountRequest
import es.atrujillo.sample.funwalletms.model.CreateAccountResponse
import es.atrujillo.sample.funwalletms.model.GetAccountDetailResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface AccountMapper {

    @Mapping(target = "id", expression = "java(null)")
    fun createAccountRequestToDomain(request: CreateAccountRequest): Account

    @Mapping(target = "data.id", source = "id")
    @Mapping(target = "data.balance", source = "balance")
    @Mapping(target = "data.userId", source = "userId")
    @Mapping(target = "data.alias", source = "alias")
    @Mapping(target = "data.currency", source = "currency")
    @Mapping(target = "data.primary", source = "primary")
    @Mapping(target = "data.type", source = "type")
    @Mapping(target = "metadata", expression = "java(MapperMockUtility.Companion.metadataMockCreator())")
    fun domainToCreateAccountResponse(account: Account): CreateAccountResponse

    @Mapping(target = "data.id", source = "id")
    @Mapping(target = "data.balance", source = "balance")
    @Mapping(target = "data.userId", source = "userId")
    @Mapping(target = "data.alias", source = "alias")
    @Mapping(target = "data.currency", source = "currency")
    @Mapping(target = "data.primary", source = "primary")
    @Mapping(target = "data.type", source = "type")
    @Mapping(target = "data.transactionsResource", expression = "java(\"/accounts/\" + account.getId() + \"/transaction\")")
    @Mapping(target = "metadata", expression = "java(MapperMockUtility.Companion.metadataMockCreator())")
    fun domainToGetAccountDetailResponse(account: Account): GetAccountDetailResponse

    fun domainToWebAccount(account: Account) : es.atrujillo.sample.funwalletms.model.Account


}