package otus.pet.network.repository;

import org.springframework.stereotype.Repository;
import otus.pet.network.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    void save(User user);

    boolean existsByEmail(String email);
}
