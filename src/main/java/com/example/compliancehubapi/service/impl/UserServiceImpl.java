package com.example.compliancehubapi.service.impl;

import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.example.compliancehubapi.model.User;
import com.example.compliancehubapi.repository.UserRepository;
import com.example.compliancehubapi.service.RoleService;
import com.example.compliancehubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j // use this for logging (log.info, log.error...)
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user with the given username
        User user = userRepository.findByUsername(username);
        // Check if user exists
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            // Create a collection of SimpleGrantedAuthority objects from the user's roles
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            });
            // Return the user details, including the username, password, and authorities
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }


    @Override
    public User saveUser(User user) {
        log.info("Checking if user with username {} already exists", user.getUsername());
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            log.error("User with username {} already exists", user.getUsername());
            throw new IllegalArgumentException("User with username " + user.getUsername() + " already exists");
        }
        log.info("Saving new user {} to the database", user.getUsername());
        // Encode the user's password for security before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    public Optional<User> getUserById (Long id) {
        log.info("Fetching user by id {}", id);
       var user = userRepository.findById(id).orElseThrow(
               () -> new RuntimeException("User with id: " + id + " not found.")
       );
       return userRepository.findById(id);
    }

    public List<User> getUsersByComplianceStatus(ComplianceStatusEnum complianceStatus){
        log.info("Fetching users by compliance status {}", complianceStatus);
        return userRepository.getUsersByComplianceStatus(complianceStatus);
    }

    public User updateUser(Long id, User user){
       log.info("Updating user with id {}", id);
        var userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id: " + id + " not found.")
        );
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        userToUpdate.setComplianceStatus(user.getComplianceStatus());
        userToUpdate.setRoles(user.getRoles());
        return userRepository.save(userToUpdate);
    }

    @Transactional
    public void deleteUserById(Long id) {
       log.info("Deleting user with id {}", id);
       var user = userRepository.findById(id).orElseThrow(
               () -> new RuntimeException("User with id: " + id + " not found.")
       );
       user.getComplianceDocuments().forEach(complianceDocument ->
               complianceDocument.getRegulations().forEach(regulation ->
                       regulation.getRequired_documents().remove(complianceDocument)));
        userRepository.delete(user);
    }

}
