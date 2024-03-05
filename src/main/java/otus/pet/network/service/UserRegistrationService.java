package otus.pet.network.service;

import otus.pet.network.dto.UserRegistrationRequestDto;
import otus.pet.network.dto.login.LoginRequestDto;

public interface UserRegistrationService {

    /**
     * Регистрирует нового пользователя
     *
     * @param registrationRequest - json с информацией регистрируемого пользователя
     */
    void register(UserRegistrationRequestDto registrationRequest);

    /**
     * Авторизирует пользователя и выдает токен
     *
     * @param loginRequest - json с информацией для авторизации
     * @return jwt token
     */
    String login(LoginRequestDto loginRequest);
}
