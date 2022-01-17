package es.atrujillo.sample.funwalletms.domain.errors

import es.atrujillo.sample.funwalletms.domain.errors.base.DomainError

class UserNotFoundError(userId: String) : DomainError("Username with userId ${userId} not found", "USER_NOT_FOUND", 404)

class UserAlreadyExistsError(username: String) : DomainError("Username ${username} already exists", "USER_ALREADY_EXISTS", 400)

