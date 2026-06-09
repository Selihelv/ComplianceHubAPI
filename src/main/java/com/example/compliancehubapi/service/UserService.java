package com.example.compliancehubapi.service;

import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.example.compliancehubapi.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {


    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById (Long id);

    List<User> getUsersByComplianceStatus (ComplianceStatusEnum complianceStatus);

    User updateUser(Long id, User user);

    void deleteUserById(Long id);
}

