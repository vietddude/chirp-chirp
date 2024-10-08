package viet.io.chirpchirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viet.io.chirpchirp.dto.BaseResponse;
import viet.io.chirpchirp.security.dto.RefreshRequest;
import viet.io.chirpchirp.security.dto.RegRequest;
import viet.io.chirpchirp.security.dto.LoginRequest;
import viet.io.chirpchirp.security.dto.LoginResponse;
import viet.io.chirpchirp.security.jwt.JwtTokenService;
import viet.io.chirpchirp.security.service.UserService;
import viet.io.chirpchirp.util.enums.ResponseStatus;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication API")
public class AuthController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/login")
    @Operation(summary = "Login to the application")
    public BaseResponse<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        final LoginResponse res = jwtTokenService.login(req);
        return new BaseResponse<>(ResponseStatus.SUCCESS, res);
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public BaseResponse<String> register(@Valid @RequestBody RegRequest req) {
        final String res = userService.register(req);
        return new BaseResponse<>(ResponseStatus.SUCCESS, res);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh the JWT token")
    public ResponseEntity<LoginResponse> refresh(@Valid @RequestBody RefreshRequest req) {
        final LoginResponse res = jwtTokenService.refresh(req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout from the application")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        final String response = jwtTokenService.logout(token.substring(7));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}