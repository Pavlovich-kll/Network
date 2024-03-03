package otus.pet.network.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otus.pet.network.dto.UserDto;
import otus.pet.network.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);

    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestParam("first_name") String firstName,
                                         @RequestParam("last_name") String lastName) {
        // Ваша логика поиска пользователей здесь
        // Пример: возвращаем список пользователей, удовлетворяющих условиям поиска
        return ResponseEntity.ok(new UserDto[0]);
    }
}