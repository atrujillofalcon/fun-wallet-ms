package es.atrujillo.sample.funwalletms.infraestructure.adapters.web

import es.atrujillo.sample.funwalletms.api.UsersApi
import es.atrujillo.sample.funwalletms.domain.ports.`in`.UserCreationUseCase
import es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper.UserMapper
import es.atrujillo.sample.funwalletms.model.CreateUserRequest
import es.atrujillo.sample.funwalletms.model.CreateUserResponse
import org.mapstruct.factory.Mappers
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RestController
class UserController(val createUserUseCase: UserCreationUseCase) : UsersApi {

    private final val mapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

    override fun createUser(createUserRequest: Mono<CreateUserRequest>?, exchange: ServerWebExchange?): Mono<ResponseEntity<CreateUserResponse>> {

        if (createUserRequest != null) {

            return createUserRequest.map { mapper.createUserRequestToDomain(it) }
                .flatMap { createUserUseCase.createUser(it) }
                .map { mapper.domainUserToResponse(it) }
                .map { ResponseEntity.ok(it) }

        } else {
            return Mono.just(ResponseEntity.badRequest().build())
        }
    }

}