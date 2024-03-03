package otus.pet.network.dto.login;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
