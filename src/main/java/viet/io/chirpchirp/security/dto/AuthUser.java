package viet.io.chirpchirp.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthUser {
    private String username;
    private String email;
    private String password;
}
