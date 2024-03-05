package otus.pet.network.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otus.pet.network.controller.AuthController;
import otus.pet.network.dto.UserRegistrationRequestDto;
import otus.pet.network.dto.login.LoginRequestDto;
import otus.pet.network.dto.login.LoginResponseDto;
import otus.pet.network.service.UserRegistrationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final UserRegistrationService registrationService;

    @Override
    public ResponseEntity<?> login(LoginRequestDto loginRequest) {
        String token = registrationService.login(loginRequest);
        return ResponseEntity.ok(new LoginResponseDto(loginRequest.getEmail(), token));
    }

    @Override
    public ResponseEntity<?> register(UserRegistrationRequestDto registrationRequest) {
        registrationService.register(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
