package es.atrujillo.sample.funwalletms.domain.errors

import es.atrujillo.sample.funwalletms.domain.errors.base.DomainError

class UnsupportedTransactionType() : DomainError("Transaction type is not supported yed", "UNSUPPORTED_TRANSACTION_TYPE", 400)