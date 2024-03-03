package otus.pet.network.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import otus.pet.network.domain.User;
import otus.pet.network.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    @Mapping(target = "id", ignore = true) // Не копируем поле id из UserDto в User
    @Mapping(target = "password", ignore = true) // Не копируем поле id из UserDto в User
    User userDtoToUser(UserDto userDto);
}
