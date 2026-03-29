package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link User} entity.
 * Provides standard CRUD operations and custom finders for user lookup.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their email address.
     *
     * @param email the email to search for
     * @return an Optional containing the found User, or empty if not found
     */
    Optional<User> findByEmail(String email);

    /**
     * Check whether a user with the given email already exists.
     *
     * @param email the email to check
     * @return true if a user with this email exists, false otherwise
     */
    Boolean existsByEmail(String email);
}
