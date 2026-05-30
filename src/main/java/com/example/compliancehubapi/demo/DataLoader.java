package com.example.compliancehubapi.demo;

import com.example.compliancehubapi.model.Role;
import com.example.compliancehubapi.model.User;
import com.example.compliancehubapi.service.RoleService;
import com.example.compliancehubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        roleService.save(new Role("Seller"));
        roleService.save(new Role("Agent"));
        roleService.save(new Role("Regulation Manager"));

//        userService.saveUser(new User("john", "jd34+"));
//        userService.saveUser(new User("jean", "jdp31+"));
//        userService.saveUser(new User("marie", "ml37+"));
//        userService.saveUser(new User("james", "js35+"));
//        userService.saveUser(new User("jane", "jc08+"));
        // test test test

        roleService.addRoleToUser("john", "Seller");
        roleService.addRoleToUser("jean", "Seller");
        roleService.addRoleToUser("marie", "Seller");
        roleService.addRoleToUser("james", "Agent");
        roleService.addRoleToUser("jane", "Regulation Manager");

    }
}
