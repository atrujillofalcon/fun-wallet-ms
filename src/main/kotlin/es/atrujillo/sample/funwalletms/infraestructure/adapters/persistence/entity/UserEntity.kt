package es.atrujillo.sample.funwalletms.infraestructure.adapters.persistence.entity

import es.atrujillo.sample.funwalletms.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class UserEntity(@Id val id: String?,
                      val firstName: String, val lastName: String, @Indexed val username: String) {

    fun toDomain() : User {
        return User(id, firstName, lastName, username)
    }

    companion object {
        fun fromDomain(user:User) : UserEntity {
            return UserEntity(user.id, user.firstName, user.lastName, user.username)
        }
    }

}