package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.model.*;
import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for authentication operations (login and registration).
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    /**
     * Authenticates a user with email and password and returns a JWT.
     *
     * @param loginRequest the login credentials
     * @return a JWT wrapped in an {@link AuthResponse}
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponse(jwt, "Bearer"));
    }

    /**
     * Registers a new local user account.
     *
     * @param signUpRequest the registration details
     * @return a success message or a 400 if the email is already taken
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email Address already in use!");
        }

        User user = new User();
        user.setName(signUpRequest.getFullName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setMobileNo(signUpRequest.getMobileNo());
        user.setProvider(AuthProvider.local);
        user.setEmailVerified(false);

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
