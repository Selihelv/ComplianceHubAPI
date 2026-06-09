package com.example.compliancehubapi.service.impl;

import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.example.compliancehubapi.model.Role;
import com.example.compliancehubapi.model.User;
import com.example.compliancehubapi.repository.RoleRepository;
import com.example.compliancehubapi.repository.UserRepository;
import com.example.compliancehubapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final List<String> nonSellerRoles = List.of("Agent", "Regulation Manager", "ADMIN");


    @Override
    public Role save(Role role) {
        log.info("Saving new role {} to the database", role.getRoleName());
        return roleRepository.save(role);
    }


    @Override
    @Transactional
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRoleName(roleName);

        if (user == null) {
            throw new IllegalArgumentException("Cannot add role '" + roleName + "' because user '" + username + "' does not exist");
        }
        if (role == null) {
            throw new IllegalArgumentException("Cannot add role '" + roleName + "' to user '" + username + "' because the role does not exist");
        }

        user.getRoles().add(role);
        if (nonSellerRoles.contains(roleName)) {
            user.setComplianceStatus(ComplianceStatusEnum.NOT_APPLICABLE);
        }

        userRepository.save(user);
    }

    public void deleteRole(String roleName){
        log.info("Deleting role {} from the database", roleName);
        roleRepository.deleteRoleByRoleName(roleName);
    }
}
