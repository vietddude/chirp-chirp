package viet.io.chirpchirp.security.service;

import viet.io.chirpchirp.security.dto.AuthUser;
import viet.io.chirpchirp.security.dto.RegRequest;
import viet.io.chirpchirp.model.User;

public interface UserService {

    User findByUsername(String username);

    String register(RegRequest regRequest);

    AuthUser findAuthUserByUsername(String username);
}