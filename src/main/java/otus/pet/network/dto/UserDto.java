package otus.pet.network.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String age;
    private String gender;
    private LocalDate birthdate;
    private String biography;
    private String city;
}
