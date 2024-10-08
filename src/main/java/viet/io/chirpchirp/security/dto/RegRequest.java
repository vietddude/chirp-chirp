package viet.io.chirpchirp.security.dto;

import lombok.Data;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

@Data
public class RegRequest {
    @NotNull(message = "Username is mandatory")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "Display name is mandatory")
    @Size(min = 3, max = 50, message = "Display name must be between 3 and 50 characters")
    private String displayName;

    private String profilePictureUrl;
    private String coverPhotoUrl;
}