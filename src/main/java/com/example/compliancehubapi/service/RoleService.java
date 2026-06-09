package com.example.compliancehubapi.service;

import com.example.compliancehubapi.model.Role;

public interface RoleService {


    Role save(Role role);

    void addRoleToUser(String username, String roleName);

    void deleteRole(String roleName);
}
