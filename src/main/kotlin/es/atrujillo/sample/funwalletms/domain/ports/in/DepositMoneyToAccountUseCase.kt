package es.atrujillo.sample.funwalletms.domain.ports.`in`

import es.atrujillo.sample.funwalletms.domain.model.Deposit

interface DepositMoneyToAccountUseCase {

    fun depositMoney(deposit: Deposit)

}