package com.app.quantitymeasurement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA Entity representing an authenticated User in the system.
 * A user is created or updated every time a successful Google OAuth2 login occurs.
 * The entity stores the user's profile information obtained from the OAuth2 provider.
 */
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /** Auto-generated primary key. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** User's display name from the OAuth2 provider. */
    private String name;

    /** User's email address; must be unique and cannot be null. */
    @Email
    @NotNull
    @Column(nullable = false)
    private String email;

    /** URL of the user's profile picture from the OAuth2 provider. */
    private String imageUrl;

    /** Whether the email address has been verified by the OAuth2 provider. */
    @Column(nullable = false)
    private Boolean emailVerified = false;

    /** Password field — not used for OAuth2 users; may be used for local auth in future. */
    @Column
    private String password;

    /** The authentication provider used (e.g., google, local). */
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    /** The ID assigned to the user by the OAuth2 provider (e.g., Google subject ID). */
    private String providerId;

    /** User's mobile number. */
    private String mobileNo;
}
