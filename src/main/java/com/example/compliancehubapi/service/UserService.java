package com.example.compliancehubapi.service;

import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.example.compliancehubapi.model.User;

import java.util.List;
import java.util.Optional;

/**
 * The UserServiceInterface is an interface that defines the methods that are available to perform operations on User entities.
 */
public interface UserService {

    /**
     * This method is used to save a User entity to the database.
     *
     * @param user the User entity to be saved.
     * @return the saved User entity.
     */
    User saveUser(User user);

    /**
     * This method is used to retrieve a User from the database by its username.
     *
     * @param username the username of the User to be retrieved.
     * @return the retrieved User entity.
     */
    User getUser(String username);

    /**
     * This method is used to retrieve all User entities from the database.
     *
     * @return a List of all User entities.
     */
    List<User> getAllUsers();

    Optional<User> getUserById (Long id);

    List<User> getUsersByComplianceStatus (ComplianceStatusEnum complianceStatus);

    User updateUser(Long id, User user);

    void deleteUserById(Long id);
}

