package es.atrujillo.sample.funwalletms.infraestructure.adapters.web

import es.atrujillo.sample.funwalletms.api.AccountsApi
import es.atrujillo.sample.funwalletms.domain.ports.`in`.*
import es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper.AccountMapper
import es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper.MapperMockUtility
import es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper.TransactionMapper
import es.atrujillo.sample.funwalletms.model.*
import org.mapstruct.factory.Mappers
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class AccountController(
    val createAccountUseCase: AccountCreationUseCase,
    val accountConsultUseCase: AccountConsultUseCase,
    val createDepositMoneyUseCase: DepositMoneyToAccountUseCase,
    val createTransactionUseCase: TransactionCreationUseCase,
    val transactionConsultUseCase: TransactionConsultUseCase,
) : AccountsApi {

    private final val accountMapper: AccountMapper = Mappers.getMapper(AccountMapper::class.java)
    private final val transactionMapper = Mappers.getMapper(TransactionMapper::class.java)

    override fun createAccount(createAccountRequest: Mono<CreateAccountRequest>): Mono<ResponseEntity<CreateAccountResponse>> {

        return createAccountRequest.map(accountMapper::createAccountRequestToDomain)
            .flatMap(createAccountUseCase::createAccount)
            .map(accountMapper::domainToCreateAccountResponse)
            .map { ResponseEntity.ok(it) }

    }

    override fun createAccountTransaction(accountId: String, createTransactionRequest: Mono<CreateTransactionRequest>):
            Mono<ResponseEntity<CreateTransactionResponse>> {

        return createTransactionRequest.map { transactionMapper.createTransactionRequestToDomain(accountId, it) }
            .flatMap(createTransactionUseCase::createTransaction)
            .map(transactionMapper::domainToCreateTransactionResponse)
            .map { ResponseEntity.ok(it) }
    }

    override fun createAccountDeposit(accountId: String, createDepositRequest: Mono<CreateDepositRequest>): Mono<ResponseEntity<Void>> {

        return createDepositRequest.map { transactionMapper.createDepositRequestToDomain(accountId, it) }
            .doOnNext(createDepositMoneyUseCase::depositMoney)
            .map { ResponseEntity.ok().build() }
    }

    override fun getAccountTransactions(accountId: String): Mono<ResponseEntity<GetAccountTransactionsResponse>> {
        return transactionConsultUseCase.getTransactionsByAccount(accountId)
            .map(transactionMapper::domainToWebAccount)
            .collectList()
            .map { transactionList -> GetAccountTransactionsResponse().data(transactionList).metadata(MapperMockUtility.metadataMockCreator()) }
            .map { ResponseEntity.ok(it) }
    }

    override fun getAccounts(userId: String?): Mono<ResponseEntity<GetAccountsResponse>> {
        return accountConsultUseCase.getAccountByUser(userId)
            .map(accountMapper::domainToWebAccount)
            .collectList()
            .map { contractList -> GetAccountsResponse().data(contractList).metadata(MapperMockUtility.metadataMockCreator()) }
            .map { ResponseEntity.ok(it) }
    }

    override fun getAccountDetail(accountId: String): Mono<ResponseEntity<GetAccountDetailResponse>> {
        return accountConsultUseCase.getAccountById(accountId)
            .map(accountMapper::domainToGetAccountDetailResponse)
            .map { ResponseEntity.ok(it) }
    }

}