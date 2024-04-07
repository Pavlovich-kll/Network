package otus.pet.network.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import otus.pet.network.domain.User;
import otus.pet.network.repository.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password, age, gender, birthdate, biography, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getAge(),
                user.getGender(),
                user.getBirthdate(),
                user.getBiography(),
                user.getCity());
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        var count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public List<User> findByFirstAndSecondNamesPrefix(String firstNamePrefix, String secondNamePrefix) {
        String sql = "SELECT * FROM users WHERE first_name LIKE ? and last_name LIKE ? ORDER BY id";
        firstNamePrefix = firstNamePrefix + "%";
        secondNamePrefix = secondNamePrefix + "%";

        return jdbcTemplate.query(
                sql,
                new Object[]{firstNamePrefix, secondNamePrefix},
                (rs, rowNum) -> new User()
                        .setId(rs.getLong("id"))
                        .setFirstName(rs.getString("first_name"))
                        .setLastName(rs.getString("last_name"))
                        .setEmail(rs.getString("email"))
                        .setAge(rs.getString("age"))
                        .setPassword(rs.getString("password"))
                        .setGender(rs.getString("gender"))
                        .setBiography(rs.getString("biography"))
                        .setCity(rs.getString("city"))
                        .setBirthdate(rs.getDate("birthdate").toLocalDate()));
    }

    @Override
    public void fulfillTableFromCsv(String firstName, String lastName, String birthDate, String city) {
        var password = "password123";
        String sql = "INSERT INTO users (first_name, last_name, email, password, age, gender, birthdate, biography, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                firstName,
                lastName,
                generateEmail(),
                password,
                generateRandomAge(),
                generateRandomGender(),
                convertStringToLocalDate(birthDate),
                generateRandomCharacteristic(),
                city);
    }

    private static String generateRandomString(int length) {
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    private static String generateEmail() {
        int name = 12;
        String userName = generateRandomString(name);
        String domain = "gmail.com";
        return userName + "@" + domain;
    }

    private static String generateRandomAge() {
        Random random = new Random();
        int minAge = 12;
        int maxAge = 60;
        var age = random.nextInt(maxAge - minAge + 1) + minAge;
        return String.valueOf(age);
    }

    private static String generateRandomGender() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return "male";
        } else {
            return "female";
        }
    }

    private static LocalDate convertStringToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private static String generateRandomCharacteristic() {
        var length = 8;
        Random random = new Random();
        String characteristics = "умный, весёлый, общительный, добрый, отзывчивый, дружелюбный, заботливый, креативный, талантливый";
        String[] strings = characteristics.trim().split(",");
        var index = random.nextInt(0, length);

        var characteristic1 = strings[index];
        var characteristic2 = strings[index == 8 ? index - 1 : index + 1];

        return characteristic1 + ", " + characteristic2;
    }
}
