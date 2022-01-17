package es.atrujillo.sample.funwalletms.infraestructure.adapters.mapper

import es.atrujillo.sample.funwalletms.domain.model.User
import es.atrujillo.sample.funwalletms.model.CreateUserRequest
import es.atrujillo.sample.funwalletms.model.CreateUserResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface UserMapper {

    @Mapping(target = "id", expression = "java(null)")
    fun createUserRequestToDomain(request: CreateUserRequest):User

    @Mapping(target = "data.id", source = "id")
    @Mapping(target = "data.firstName", source = "firstName")
    @Mapping(target = "data.lastName", source = "lastName")
    @Mapping(target = "data.username", source = "username")
    @Mapping(target = "metadata", expression = "java(MapperMockUtility.Companion.metadataMockCreator())")
    fun domainUserToResponse(user: User):CreateUserResponse

}