package otus.pet.network.service;

import otus.pet.network.dto.UserDto;

import java.util.List;

public interface UserService {

    /**
     * Поиск пользователя по его идентификатору
     *
     * @param id - идентификатор пользователя
     * @return UserDto
     */
    UserDto getUserById(Long id);

    /**
     * Поиск пользователей по префиксу имени и префиксу фамилии
     * @param partFirstName - префикс имени
     * @param partSecondName - префикс фамилии
     * @return List<UserDto> - список анкет
     */
    List<UserDto> searchUsersByFirstAndSecondNamesPrefix(String partFirstName, String partSecondName);
}
