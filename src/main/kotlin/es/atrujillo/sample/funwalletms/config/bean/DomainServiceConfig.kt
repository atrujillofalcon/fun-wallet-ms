package es.atrujillo.sample.funwalletms.config.bean

import es.atrujillo.sample.funwalletms.domain.ports.out.AccountRepository
import es.atrujillo.sample.funwalletms.domain.ports.out.TransactionRepository
import es.atrujillo.sample.funwalletms.domain.ports.out.UserRepository
import es.atrujillo.sample.funwalletms.domain.service.AccountService
import es.atrujillo.sample.funwalletms.domain.service.TransactionService
import es.atrujillo.sample.funwalletms.domain.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainServiceConfig {

    @Bean
    fun domainUserService(userRepository: UserRepository) : UserService {
        return UserService(userRepository)
    }

    @Bean
    fun domainAccountService(accountRepository: AccountRepository, userRepository: UserRepository, transactionService: TransactionService) : AccountService {
        return AccountService(accountRepository, userRepository, transactionService)
    }

    @Bean
    fun domainTransactionService(transactionRepository: TransactionRepository, accountRepository: AccountRepository) : TransactionService {
        return TransactionService(transactionRepository, accountRepository)
    }

}