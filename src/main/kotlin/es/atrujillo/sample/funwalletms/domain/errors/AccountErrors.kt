package es.atrujillo.sample.funwalletms.domain.errors

import es.atrujillo.sample.funwalletms.domain.errors.base.DomainError

class AccountNotFoundError(accountId: String) : DomainError("Account with id ${accountId} not found", "ACCOUNT_NOT_FOUND", 404)

class ExistingPrimaryAccountError() : DomainError("The user can only have one primary account", "EXISTING_PRIMARY_ACCOUNT", 400)
