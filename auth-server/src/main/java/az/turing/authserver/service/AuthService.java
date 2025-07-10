package az.turing.authserver.service;

import az.turing.authserver.auth.JwtTokenProvider;
import az.turing.authserver.dto.AuthResponse;
import az.turing.authserver.dto.LoginRequest;
import az.turing.authserver.dto.RegisterRequest;
import az.turing.authserver.dto.UserRegisteredEvent;
import az.turing.authserver.entity.Role;
import az.turing.authserver.entity.User;
import az.turing.authserver.exception.AuthenticationException;

import az.turing.authserver.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RabbitMQProducer rabbitMQProducer;
    public AuthResponse register(RegisterRequest request) throws AuthenticationException{
        User user=User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        String token = jwtTokenProvider.generateToken(user.getEmail());
        rabbitMQProducer.sendUserRegisteredEvent(
                new UserRegisteredEvent(user.getEmail())
        );
        return AuthResponse.builder()
                .token(token)
                .build();


    }
    public AuthResponse login(LoginRequest request){
        User user= userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthenticationException("User not found"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new AuthenticationException("Invalid password");
        }
        String token = jwtTokenProvider.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .build();

    }

}
