package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.domain.ports.out.AccountRepository
import es.atrujillo.sample.funwalletms.domain.ports.out.TransactionRepository

class TransactionService(private val accountRepository: AccountRepository, private val transactionRepository: TransactionRepository) {


}