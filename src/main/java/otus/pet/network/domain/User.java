package otus.pet.network.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String age;
    private String gender;
    private LocalDate birthdate;
    private String biography;
    private String city;
}
