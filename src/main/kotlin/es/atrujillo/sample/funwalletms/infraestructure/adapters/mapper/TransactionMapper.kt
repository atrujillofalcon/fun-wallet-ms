package es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper

import es.atrujillo.sample.funwalletms.domain.model.Transaction
import es.atrujillo.sample.funwalletms.model.CreateTransactionRequest
import es.atrujillo.sample.funwalletms.model.CreateTransactionResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface TransactionMapper {

    @Mapping(target = "id", expression = "java(null)")
    fun createTransactionRequestToDomain(request: CreateTransactionRequest): Transaction

    @Mapping(target = "data.id", source = "id")
    @Mapping(target = "data.type", source = "type")
    @Mapping(target = "data.amount", source = "amount")
    @Mapping(target = "data.fee", source = "fee")
    @Mapping(target = "data.status", source = "status")
    @Mapping(target = "data.accountId", source = "accountId")
    @Mapping(target = "metadata", expression = "java(MapperMockUtility.Companion.metadataMockCreator())")
    fun domainToCreateTransactionResponse(transaction: Transaction): CreateTransactionResponse


}