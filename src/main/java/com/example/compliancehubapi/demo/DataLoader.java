package com.example.compliancehubapi.demo;

import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.example.compliancehubapi.enums.MarketplaceEnum;
import com.example.compliancehubapi.model.*;
import com.example.compliancehubapi.service.*;
import com.example.compliancehubapi.service.impl.UserProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final RegulationService regulationService;
    private final ComplianceDocumentService complianceDocumentService;
    private final UserProfileServiceImpl userProfileService;

    @Override
    public void run(String... args) throws Exception {
        roleService.save(new Role("Seller"));
        roleService.save(new Role("Agent"));
        roleService.save(new Role("Regulation Manager"));

        userService.saveUser(new User("john", "jd34+"));
        userService.saveUser(new User("jean", "jdp31+"));
        userService.saveUser(new User("marie", "ml37+"));
        userService.saveUser(new User("james", "js35+"));
        userService.saveUser(new User("jane", "jc08+"));



        roleService.addRoleToUser("john", "Seller");
        roleService.addRoleToUser("jean", "Seller");
        roleService.addRoleToUser("marie", "Seller");
        roleService.addRoleToUser("james", "Agent");
        roleService.addRoleToUser("jane", "Regulation Manager");

        //TODO: check how to integrate ComplianceDocument required_documents
        regulationService.saveRegulation( new Regulation("title", "desc", MarketplaceEnum.BE));
        userProfileService.saveUserProfile(new UserProfile("first", "lastname", "test@email.com", MarketplaceEnum.BE));

    }
}
