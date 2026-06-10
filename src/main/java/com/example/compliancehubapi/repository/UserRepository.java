package com.example.compliancehubapi.repository;

import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.example.compliancehubapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> getUsersByComplianceStatus(ComplianceStatusEnum complianceStatus);


}
