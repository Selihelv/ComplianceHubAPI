package com.example.compliancehubapi.demo;

import com.example.compliancehubapi.enums.DocumentStatusEnum;
import com.example.compliancehubapi.enums.DocumentTypeEnum;
import com.example.compliancehubapi.enums.MarketplaceEnum;
import com.example.compliancehubapi.model.BusinessInfo;
import com.example.compliancehubapi.model.ComplianceDocument;
import com.example.compliancehubapi.model.Regulation;
import com.example.compliancehubapi.model.Role;
import com.example.compliancehubapi.model.User;
import com.example.compliancehubapi.model.UserProfile;
import com.example.compliancehubapi.service.ComplianceDocumentService;
import com.example.compliancehubapi.service.RegulationService;
import com.example.compliancehubapi.service.RoleService;
import com.example.compliancehubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final RegulationService regulationService;
    private final ComplianceDocumentService complianceDocumentService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        roleService.save(new Role("Seller"));
        roleService.save(new Role("Agent"));
        roleService.save(new Role("Regulation Manager"));

        userService.saveUser(createUser(
                "john",
                "jd34+",
                "John",
                "Doe",
                "john.doe@example.com",
                MarketplaceEnum.BE,
                "Doe Home Goods",
                "Rue Neuve 12, 1000 Brussels, Belgium"
        ));
        userService.saveUser(createUser(
                "jean",
                "jdp31+",
                "Jean",
                "Dupont",
                "jean.dupont@example.com",
                MarketplaceEnum.FR,
                "Dupont Electronics",
                "15 Rue de Rivoli, 75001 Paris, France"
        ));
        userService.saveUser(createUser(
                "marie",
                "ml37+",
                "Marie",
                "Lambert",
                "marie.lambert@example.com",
                MarketplaceEnum.NL,
                "Lambert Textiles",
                "Damrak 45, 1012 Amsterdam, Netherlands"
        ));
        userService.saveUser(createUser(
                "james",
                "js35+",
                "James",
                "Smith",
                "james.smith@example.com",
                MarketplaceEnum.UK,
                "Compliance Ops Ltd",
                "10 King Street, London SW1A 1AA, United Kingdom"
        ));
        userService.saveUser(createUser(
                "jane",
                "jc08+",
                "Jane",
                "Carvalho",
                "jane.carvalho@example.com",
                MarketplaceEnum.PT,
                "Marketplace Governance",
                "Avenida da Liberdade 120, 1250-096 Lisbon, Portugal"
        ));

        roleService.addRoleToUser("john", "Seller");
        roleService.addRoleToUser("jean", "Seller");
        roleService.addRoleToUser("marie", "Seller");
        roleService.addRoleToUser("james", "Agent");
        roleService.addRoleToUser("jane", "Regulation Manager");

        ComplianceDocument johnId = complianceDocumentService.saveComplianceDocument(
                createComplianceDocument("John Doe passport",
                        DocumentStatusEnum.APPROVED,
                        DocumentTypeEnum.ID),
                "john"
        );
        ComplianceDocument johnVat = complianceDocumentService.saveComplianceDocument(
                createComplianceDocument("Doe Home Goods VAT registration",
                        DocumentStatusEnum.PENDING,
                        DocumentTypeEnum.VAT),
                "john"
        );
        ComplianceDocument jeanCertificate = complianceDocumentService.saveComplianceDocument(
                createComplianceDocument(
                        "Dupont Electronics company certificate",
                        DocumentStatusEnum.APPROVED,
                        DocumentTypeEnum.BUSINESS_CERTIFICATE),
                "jean"
        );
        ComplianceDocument marieVat = complianceDocumentService.saveComplianceDocument(
                createComplianceDocument("Lambert Textiles VAT registration",
                        DocumentStatusEnum.REJECTED,
                        DocumentTypeEnum.VAT),
                "marie"
        );
        ComplianceDocument marieId = complianceDocumentService.saveComplianceDocument(
                createComplianceDocument("Marie Lambert national ID",
                        DocumentStatusEnum.PENDING,
                        DocumentTypeEnum.ID),
                "marie"
        );

        Regulation belgianVatRegulation = new Regulation(
                "Belgian VAT seller verification",
                "Belgian marketplace sellers must provide identity and VAT registration documents before selling regulated goods.",
                MarketplaceEnum.BE
        );
        belgianVatRegulation.getRequired_documents().add(johnId);
        belgianVatRegulation.getRequired_documents().add(johnVat);
        regulationService.saveRegulation(belgianVatRegulation);

        Regulation frenchBusinessRegulation = new Regulation(
                "French business registration check",
                "French sellers must provide proof of company registration and a valid VAT record for marketplace onboarding.",
                MarketplaceEnum.FR
        );
        frenchBusinessRegulation.getRequired_documents().add(jeanCertificate);
        frenchBusinessRegulation.getRequired_documents().add(marieVat);
        regulationService.saveRegulation(frenchBusinessRegulation);

        Regulation dutchIdentityRegulation = new Regulation(
                "Dutch seller identity review",
                "Dutch marketplace accounts require a reviewed identity document before payout activation.",
                MarketplaceEnum.NL
        );
        dutchIdentityRegulation.getRequired_documents().add(marieId);
        regulationService.saveRegulation(dutchIdentityRegulation);
    }

    private User createUser(
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            MarketplaceEnum marketplace,
            String businessName,
            String businessAddress
    ) {
        User user = new User(username, password);
        UserProfile userProfile = new UserProfile(firstName, lastName, email, marketplace);
        userProfile.setBusinessInfo(new BusinessInfo(businessName, businessAddress));
        user.setUserProfile(userProfile);
        return user;
    }

    private ComplianceDocument createComplianceDocument(
            String documentName,
            DocumentStatusEnum documentStatus,
            DocumentTypeEnum documentType
    ) {
        ComplianceDocument complianceDocument = new ComplianceDocument();
        complianceDocument.setDocumentName(documentName);
        complianceDocument.setDocumentStatus(documentStatus);
        complianceDocument.setDocumentType(documentType);
        return complianceDocument;
    }
}
