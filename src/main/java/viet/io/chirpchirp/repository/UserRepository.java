package viet.io.chirpchirp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viet.io.chirpchirp.model.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}