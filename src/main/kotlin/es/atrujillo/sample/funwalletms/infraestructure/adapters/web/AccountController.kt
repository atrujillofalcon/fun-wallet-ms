package es.atrujillo.sample.funwalletms.infraestructure.adapters.web

import es.atrujillo.sample.funwalletms.api.AccountsApi
import es.atrujillo.sample.funwalletms.domain.ports.`in`.AccountCreationUseCase
import es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper.AccountMapper
import es.atrujillo.sample.funwalletms.model.*
import org.mapstruct.factory.Mappers
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RestController
class AccountController(val createAccountUseCase: AccountCreationUseCase) : AccountsApi {

    private final val mapper: AccountMapper = Mappers.getMapper(AccountMapper::class.java)

    override fun createAccount(createAccountRequest: Mono<CreateAccountRequest>, exchange: ServerWebExchange?): Mono<ResponseEntity<CreateAccountResponse>> {

        return createAccountRequest.map { mapper.createAccountRequestToDomain(it) }
            .flatMap { createAccountUseCase.createAccount(it) }
            .map { mapper.domainAccountToResponse(it) }
            .map { ResponseEntity.ok(it) }

    }

    override fun createAccountTransaction(accountId: String?, createTransactionRequest: Mono<CreateTransactionRequest>?, exchange: ServerWebExchange?):
            Mono<ResponseEntity<CreateTransactionResponse>> {

        TODO("Not yet implemented")
    }

    override fun getAccountTransactions(accountId: String?, exchange: ServerWebExchange?): Mono<ResponseEntity<GetAccountTransactionsResponse>> {
        TODO("Not yet implemented")
    }

    override fun getAccounts(exchange: ServerWebExchange?): Mono<ResponseEntity<GetAccountsResponse>> {
        TODO("Not yet implemented")
    }

}