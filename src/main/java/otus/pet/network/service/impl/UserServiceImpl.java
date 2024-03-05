package otus.pet.network.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import otus.pet.network.dto.UserDto;
import otus.pet.network.error.UserNotFoundException;
import otus.pet.network.mapper.UserMapper;
import otus.pet.network.repository.UserRepository;
import otus.pet.network.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(Long id) {
        var userDomain = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        return userMapper.userToUserDto(userDomain);
    }
}