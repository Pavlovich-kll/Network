package otus.pet.network.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import otus.pet.network.dto.UserRegistrationRequestDto;
import otus.pet.network.dto.login.LoginRequestDto;
import otus.pet.network.dto.login.LoginResponseDto;
import otus.pet.network.service.UserRegistrationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRegistrationService registrationService;

    @ApiOperation(value = "Авторизация")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешная аутентификация", response = LoginResponseDto.class),
            @ApiResponse(code = 400, message = "Невалидные данные"),
            @ApiResponse(code = 404, message = "Пользователь не найден"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервера", response = ErrorResponse.class),
            @ApiResponse(code = 503, message = "Сервис временно недоступен", response = ErrorResponse.class)
    })
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        String token = registrationService.login(loginRequest);
        return ResponseEntity.ok(new LoginResponseDto(loginRequest.getEmail(), token));
    }

    @ApiOperation(value = "Аутентификация")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешная регистрация"),
            @ApiResponse(code = 400, message = "Невалидные данные"),
            @ApiResponse(code = 404, message = "Пользователь с таким email уже зарегистрирован"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервера"),
            @ApiResponse(code = 503, message = "Сервис временно недоступен")
    })
    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequestDto registrationRequest) {
        registrationService.register(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
