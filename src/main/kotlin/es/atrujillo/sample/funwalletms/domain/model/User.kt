package es.atrujillo.sample.funwalletms.domain.model

import es.atrujillo.sample.funwalletms.domain.model.base.DomainModel

data class User(val id: String?, val firstName: String, val lastName: String, val username: String) : DomainModel()