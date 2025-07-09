package az.turing.authserver.contoller;

import az.turing.authserver.auth.JwtTokenProvider;
import az.turing.authserver.dto.AuthResponse;
import az.turing.authserver.dto.LoginRequest;
import az.turing.authserver.dto.RegisterRequest;
import az.turing.authserver.service.AuthService;

import az.turing.authserver.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        long expiration = jwtTokenProvider.getExpirationInSeconds(token);
        redisService.blacklistToken(token, expiration);
        return ResponseEntity.ok().build();
    }

}
