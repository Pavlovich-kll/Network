package otus.pet.network.service;

import otus.pet.network.dto.UserDto;

public interface UserService {

    /**
     * Поиск пользователя по его идентификатору
     *
     * @param id - идентификатор пользователя
     * @return UserDto
     */
    UserDto getUserById(Long id);
}
