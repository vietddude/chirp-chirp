package viet.io.chirpchirp.security.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String accessToken;
    private String refreshToken;
}
