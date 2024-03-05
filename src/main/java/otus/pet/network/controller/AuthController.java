package otus.pet.network.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import otus.pet.network.dto.UserRegistrationRequestDto;
import otus.pet.network.dto.login.LoginRequestDto;
import otus.pet.network.dto.login.LoginResponseDto;

public interface AuthController {

    @ApiOperation(value = "Авторизация")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешная аутентификация", response = LoginResponseDto.class),
            @ApiResponse(code = 400, message = "Невалидные данные"),
            @ApiResponse(code = 404, message = "Пользователь не найден"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервера", response = ErrorResponse.class),
            @ApiResponse(code = 503, message = "Сервис временно недоступен", response = ErrorResponse.class)
    })
    @PostMapping(value = "/login")
    ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest);

    @ApiOperation(value = "Аутентификация")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешная регистрация"),
            @ApiResponse(code = 400, message = "Невалидные данные"),
            @ApiResponse(code = 404, message = "Пользователь с таким email уже зарегистрирован"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервера"),
            @ApiResponse(code = 503, message = "Сервис временно недоступен")
    })
    @PostMapping("/user/register")
    ResponseEntity<?> register(@RequestBody UserRegistrationRequestDto registrationRequest);
}
