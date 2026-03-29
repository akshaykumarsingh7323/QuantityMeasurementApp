package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for user profile management.
 *
 * <p>Exposes an endpoint that returns the currently authenticated user's
 * profile information. Requires a valid JWT in the Authorization header.</p>
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns the profile of the currently authenticated user.
     *
     * <p>The user ID is extracted from the JWT via the {@link UserPrincipal}
     * populated in the security context by the {@code JwtAuthenticationFilter}.</p>
     *
     * @param currentUser the authenticated user principal from the security context
     * @return a {@link ResponseEntity} with the user's details
     */
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserPrincipal currentUser) {
        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new com.app.quantitymeasurement.exception.QuantityMeasurementException("User not found"));
        return ResponseEntity.ok(user);
    }
}
