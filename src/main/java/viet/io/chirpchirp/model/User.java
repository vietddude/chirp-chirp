package viet.io.chirpchirp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private String profilePictureUrl;

    @Column(nullable = false)
    private String coverPhotoUrl;

    // Flags
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isVerified;         // Verification badge for famous users
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isPrivate;          // Private account
}
