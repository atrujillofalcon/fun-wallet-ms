package es.atrujillo.sample.funwalletms.infraestructure.adapters.web

import es.atrujillo.sample.funwalletms.api.AccountsApi
import es.atrujillo.sample.funwalletms.model.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RestController
class AccountController : AccountsApi {

    override fun createAccount(createAccountRequest: Mono<CreateAccountRequest>?, exchange: ServerWebExchange?): Mono<ResponseEntity<CreateAccountResponse>> {
        TODO("Not yet implemented")
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