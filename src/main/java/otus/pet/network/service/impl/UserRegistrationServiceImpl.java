package otus.pet.network.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import otus.pet.network.domain.User;
import otus.pet.network.dto.login.LoginRequestDto;
import otus.pet.network.dto.UserRegistrationRequestDto;
import otus.pet.network.error.UserAlreadyExistsException;
import otus.pet.network.error.UserNotFoundException;
import otus.pet.network.repository.UserRepository;
import otus.pet.network.service.UserRegistrationService;
import otus.pet.network.service.security.JwtTokenProvider;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public void register(UserRegistrationRequestDto registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с таким email = " + registrationRequest.getEmail() + " уже зарегистрирован");
        }

        // Хешируем пароль
        var encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        // Создаем нового пользователя
        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setAge(registrationRequest.getAge());
        user.setBirthdate(LocalDate.parse(registrationRequest.getBirthdate()));
        user.setGender(registrationRequest.getGender());
        user.setCity(registrationRequest.getCity());
        user.setBiography(registrationRequest.getBiography());

        // Сохраняем пользователя в базе данных
        userRepository.save(user);
    }

    public String login(LoginRequestDto loginRequest) {
        var authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // Находим пользователя по email
        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        // Проверяем правильность пароля
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Неверные учетные данные");
        }

        var email = authentication.getName();
        return jwtTokenProvider.createToken(email);
    }
}
