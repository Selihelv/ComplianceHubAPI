package com.example.compliancehubapi.controller;


import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.example.compliancehubapi.model.User;
import com.example.compliancehubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User user /* , @RequestParam String role*/) {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/users/compliance-status/{complianceStatus}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByComplianceStatus(@PathVariable ComplianceStatusEnum complianceStatus){
        return userService.getUsersByComplianceStatus(complianceStatus);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }


    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
