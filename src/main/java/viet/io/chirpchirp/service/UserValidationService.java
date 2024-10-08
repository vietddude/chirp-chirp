package viet.io.chirpchirp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import viet.io.chirpchirp.repository.UserRepository;
import viet.io.chirpchirp.security.dto.RegRequest;

/**
 * Service class for validating user registration details.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final UserRepository userRepo;

    /**
     * Validates the user registration request.
     *
     * @param req the registration request containing username and email
     * @throws RuntimeException if the username or email already exists
     */
    public void validateUser(RegRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) {
            log.warn("Username {} already exists", req.getUsername());
            throw new RuntimeException("Username already exists");
        }
        if (userRepo.existsByEmail(req.getEmail())) {
            log.warn("Email {} already exists", req.getEmail());
            throw new RuntimeException("Email already exists");
        }
    }
}