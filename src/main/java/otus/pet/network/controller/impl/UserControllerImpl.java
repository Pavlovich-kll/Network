package otus.pet.network.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otus.pet.network.controller.UserController;
import otus.pet.network.dto.UserDto;
import otus.pet.network.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> getUser(Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);

    }

    @Override
    public ResponseEntity<?> searchUsers(String firstName,
                                         String lastName) {
        // Ваша логика поиска пользователей здесь
        // Пример: возвращаем список пользователей, удовлетворяющих условиям поиска
        return ResponseEntity.ok(new UserDto[0]);
    }
}