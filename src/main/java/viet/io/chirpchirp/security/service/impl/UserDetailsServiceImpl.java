package viet.io.chirpchirp.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import viet.io.chirpchirp.security.dto.AuthUser;
import viet.io.chirpchirp.security.service.UserService;

import java.util.Collections;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        final AuthUser authUser = userService.findAuthUserByUsername(username);

        if (Objects.isNull(authUser)) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        final String authUsername = authUser.getUsername();
        final String authPassword = authUser.getPassword();

        return new User(authUsername, authPassword, Collections.emptyList());
    }
}