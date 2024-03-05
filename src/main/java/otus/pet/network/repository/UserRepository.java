package otus.pet.network.repository;

import org.springframework.stereotype.Repository;
import otus.pet.network.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository {

    /**
     * Поиск пользователя по email
     *
     * @param email - почта пользователя
     * @return - Optional<User>
     */
    Optional<User> findByEmail(String email);

    /**
     * Поиск пользователя по идентификатору
     *
     * @param id - идентификатор пользователя
     * @return - Optional<User>
     */
    Optional<User> findById(Long id);

    /**
     * Сохранение пользователя в бд
     *
     * @param user - доменная модель пользователя
     */
    void save(User user);

    /**
     *
     * @param email - почта пользователя
     * @return - boolean
     */
    boolean existsByEmail(String email);
}
