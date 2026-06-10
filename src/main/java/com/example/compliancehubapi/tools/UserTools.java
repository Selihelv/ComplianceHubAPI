package com.example.compliancehubapi.tools;

import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.example.compliancehubapi.model.User;
import com.example.compliancehubapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserTools {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Tool(description = "Get all Users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Tool(description = "Get User by id")
    public Optional<User> getUserById(@ToolParam(description = "The id of the user to search") Long id){
        return userRepository.findById(id);
    }

    @Tool(description = "Get User by Username")
    public User getUserByUsername(@ToolParam(description = "The username of the user to search") String username){
        return userRepository.findByUsername(username);
    }

    @Tool(description = "Get Users by ComplianceStatus")
    public List<User> getUsersByComplianceStatus(@ToolParam(description = "The Compliance Status of the user to search")ComplianceStatusEnum complianceStatus){
        return userRepository.getUsersByComplianceStatus(complianceStatus);
    }

    @Tool(description = "Update Username by userId")
    public User updateUsernameByUserId(@ToolParam(description = "The id of the user to update") Long userId,
                                       @ToolParam(description = "The new username") String username){
        var user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id: " + userId + " not found.")
        );
        user.setUsername(username);
        return userRepository.save(user);
    }

    @Tool(description = "Update User password by userId")
    public User updateUserPasswordByUserId(@ToolParam(description = "The id of the user to update") Long userId,
                                           @ToolParam(description = "The new password") String password){
        var user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id: " + userId + " not found.")
        );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
