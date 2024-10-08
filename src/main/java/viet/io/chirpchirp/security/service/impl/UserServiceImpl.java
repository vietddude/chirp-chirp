package viet.io.chirpchirp.security.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import viet.io.chirpchirp.security.dto.AuthUser;
import viet.io.chirpchirp.security.dto.RegRequest;
import viet.io.chirpchirp.model.User;
import viet.io.chirpchirp.repository.UserRepository;
import viet.io.chirpchirp.security.mapper.UserMapper;
import viet.io.chirpchirp.security.service.UserService;
import viet.io.chirpchirp.service.UserValidationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String register(RegRequest regRequest) {
        userValidationService.validateUser(regRequest);
        final User user = UserMapper.INSTANCE.INSTANCE.convertToUser(regRequest);
        user.setPassword(passwordEncoder.encode(regRequest.getPassword()));
        System.out.println(user);
        userRepository.save(user);

        final String username = regRequest.getUsername();

        log.info("{} registered successfully!", username);

        return "User " + username + " has been registered successfully!";
    }

    @Override
    public AuthUser findAuthUserByUsername(String username) {
        final User user = findByUsername(username);
        return UserMapper.INSTANCE.INSTANCE.convertToAuthUser(user);
    }

}
