package es.atrujillo.sample.funwalletms.infraestructure.adapters.web

import org.springframework.web.bind.annotation.RestController
import es.atrujillo.sample.funwalletms.api.UsersApi;
import es.atrujillo.sample.funwalletms.model.CreateUserRequest
import es.atrujillo.sample.funwalletms.model.CreateUserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RestController
class UserController: UsersApi {

    override fun createUser(createUserRequest: Mono<CreateUserRequest>?, exchange: ServerWebExchange?): Mono<ResponseEntity<CreateUserResponse>> {
        TODO("Not yet implemented")
    }

}