package com.example.compliancehubapi.tools;

import com.example.compliancehubapi.model.User;
import com.example.compliancehubapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserTools {

    private final UserRepository userRepository;

    @Tool(description = "Get all users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Tool(description = "Get user by id")
    public Optional<User> getUserById(@ToolParam(description = "The id of the user to search") Long id){
        return userRepository.findById(id);
    }



}
