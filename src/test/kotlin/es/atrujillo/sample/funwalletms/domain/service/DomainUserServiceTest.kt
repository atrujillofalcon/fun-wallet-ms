package es.atrujillo.sample.funwalletms.domain.service

import es.atrujillo.sample.funwalletms.domain.errors.UserAlreadyExistsError
import es.atrujillo.sample.funwalletms.domain.model.User
import es.atrujillo.sample.funwalletms.domain.ports.out.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.Duration

@ExtendWith(MockitoExtension::class)
internal class DomainUserServiceTest {

    lateinit var userService: UserService

    @Mock
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun prepareTest() {
        userService = UserService(userRepository)
    }

    @Test
    fun givenExistingUserShouldThrowUserAlreadyExistsError() {

        val userToCreate = User("456", "Fulano", "De Tal", "fulanito")
        val userInDatabase = User("123", "Mariano", "Rajoy", "fulanito")

        Mockito.`when`(userRepository.getByUsername("fulanito")).thenReturn(Flux.fromArray(arrayOf(userInDatabase)))

        val user = userService.createUser(userToCreate)
        StepVerifier.create(user)
            .expectError(UserAlreadyExistsError::class.java)
            .log()
            .verify()

    }

    @Test
    fun givenDomainUserShouldCompleteCreationAndGenerateDomainEvent() {

        val userToCreate = User("456", "Fulano", "De Tal", "fulanito")

        Mockito.`when`(userRepository.getByUsername("fulanito")).thenReturn(Flux.empty())
        Mockito.`when`(userRepository.saveUser(userToCreate)).thenReturn(Mono.just(userToCreate))

        StepVerifier.create(userService.getPublisherDataStream()).expectSubscription().verifyTimeout(Duration.ofMillis(1000))

        val user = userService.createUser(userToCreate)
        StepVerifier.create(user)
            .expectNext(userToCreate)
            .expectComplete()
            .verify()

    }


}