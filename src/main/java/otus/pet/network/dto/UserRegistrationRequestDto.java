package otus.pet.network.dto;

import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String gender;
    private String biography;
    private String city;
    private String email;
    private String password;
    private String age;
}
