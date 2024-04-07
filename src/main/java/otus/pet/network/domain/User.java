package otus.pet.network.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
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
