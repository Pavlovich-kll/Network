package otus.pet.network.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otus.pet.network.dto.UserDto;

import java.util.List;

public interface UserController {

    @GetMapping("/get/{id}")
    ResponseEntity<UserDto> getUser(@PathVariable("id") Long id);

    @GetMapping("/search")
    ResponseEntity<List<UserDto>> searchUsers(@RequestParam("first_name") String firstName,
                                              @RequestParam("last_name") String lastName);
}
