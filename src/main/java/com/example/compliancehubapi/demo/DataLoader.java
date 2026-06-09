package com.example.compliancehubapi.demo;

import org.springframework.boot.CommandLineRunner;
import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.example.compliancehubapi.enums.DocumentStatusEnum;
import com.example.compliancehubapi.enums.DocumentTypeEnum;
import com.example.compliancehubapi.enums.MarketplaceEnum;
import com.example.compliancehubapi.model.*;
import com.example.compliancehubapi.service.ComplianceDocumentService;
import com.example.compliancehubapi.service.RegulationService;
import com.example.compliancehubapi.service.RoleService;
import com.example.compliancehubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final RegulationService regulationService;
    private final ComplianceDocumentService complianceDocumentService;

    @Override
    public void run(String... args) throws Exception {

        createRoles();

        // STAFF CREATION

        User adminAvery = createUser(
                "administrator", "admin123",
                ComplianceStatusEnum.NOT_APPLICABLE,
                "Avery", "Adams", "admin@compliancehub.test",
                MarketplaceEnum.UK, "Compliance Hub", "1 King William Street, London"
        );
        roleService.addRoleToUser(adminAvery.getUsername(), "ADMIN");

        User agentNoah = createUser(
                "Agent_NB", "agent123",
                ComplianceStatusEnum.NOT_APPLICABLE,
                "Noah", "Brown", "noah@compliancehub.test",
                MarketplaceEnum.NL, "Compliance Hub", "Prinsengracht 263, Amsterdam"
        );
        roleService.addRoleToUser(agentNoah.getUsername(), "Agent");

        User agentChloe = createUser(
                "Agent_CD", "agent123",
                ComplianceStatusEnum.NOT_APPLICABLE,
                "Chloe", "Dupont", "chloe@compliancehub.test",
                MarketplaceEnum.FR, "Compliance Hub", "14 Rue de la Paix, Paris"
        );
        roleService.addRoleToUser(agentChloe.getUsername(), "Agent");

        User regulationManager = createUser(
                "RegulationManager_MR", "manager123",
                ComplianceStatusEnum.NOT_APPLICABLE,
                "Mila", "Rossi", "mila@compliancehub.test",
                MarketplaceEnum.IT, "Compliance Hub", "Via Torino 21, Milan"
        );
        roleService.addRoleToUser(regulationManager.getUsername(), "Regulation Manager");

        User regulationManagerDE = createUser(
                "RegulationManager_HM", "manager123",
                ComplianceStatusEnum.NOT_APPLICABLE,
                "Hans", "Müller", "hans@compliancehub.test",
                MarketplaceEnum.DE, "Compliance Hub", "Unter den Linden 10, Berlin"
        );
        roleService.addRoleToUser(regulationManagerDE.getUsername(), "Regulation Manager");

        // SELLERS CREATION

        // Spain – PENDING_REVIEW: VAT approved, business certificate still pending
        User textileSeller = createUser(
                "textile-seller", "seller123",
                ComplianceStatusEnum.PENDING_REVIEW,
                "Sofia", "Martinez", "sofia@iberia-textiles.test",
                MarketplaceEnum.ES, "Iberia Textiles SL", "Carrer de Mallorca 401, Barcelona"
        );
        roleService.addRoleToUser(textileSeller.getUsername(), "Seller");

        // Germany – COMPLIANT: both documents approved
        User electronicsSeller = createUser(
                "electronics-seller", "seller123",
                ComplianceStatusEnum.COMPLIANT,
                "Lukas", "Schmidt", "lukas@rhein-electronics.test",
                MarketplaceEnum.DE, "Rhein Electronics GmbH", "Friedrichstrasse 68, Berlin"
        );
        roleService.addRoleToUser(electronicsSeller.getUsername(), "Seller");

        // France – NON_COMPLIANT: identity rejected, VAT pending
        User homewareSeller = createUser(
                "homeware-seller", "seller123",
                ComplianceStatusEnum.NON_COMPLIANT,
                "Emma", "Dubois", "emma@nord-homeware.test",
                MarketplaceEnum.FR, "Nord Homeware SARL", "12 Rue de Rivoli, Paris"
        );
        roleService.addRoleToUser(homewareSeller.getUsername(), "Seller");

        // Netherlands – COMPLIANT: all documents approved
        User cosmeticsSeller = createUser(
                "cosmetics-seller", "seller123",
                ComplianceStatusEnum.COMPLIANT,
                "Lotte", "de Vries", "lotte@bloem-cosmetics.test",
                MarketplaceEnum.NL, "Bloem Cosmetics BV", "Kalverstraat 152, Amsterdam"
        );
        roleService.addRoleToUser(cosmeticsSeller.getUsername(), "Seller");

        // Poland – PENDING_REVIEW: business certificate approved, insurance pending
        User furnitureSeller = createUser(
                "furniture-seller", "seller123",
                ComplianceStatusEnum.PENDING_REVIEW,
                "Piotr", "Kowalski", "piotr@wisla-furniture.test",
                MarketplaceEnum.PL, "Wisła Furniture Sp. z o.o.", "ul. Nowy Świat 22, Warsaw"
        );
        roleService.addRoleToUser(furnitureSeller.getUsername(), "Seller");

        // Italy – NON_COMPLIANT: CE marking rejected
        User electronicsSellerIT = createUser(
                "electronics-seller-it", "seller123",
                ComplianceStatusEnum.NON_COMPLIANT,
                "Marco", "Ferrari", "marco@roma-tech.test",
                MarketplaceEnum.IT, "Roma Tech SRL", "Via Condotti 8, Rome"
        );
        roleService.addRoleToUser(electronicsSellerIT.getUsername(), "Seller");

        // Portugal – PENDING_REVIEW: only national ID submitted
        User foodSeller = createUser(
                "food-seller", "seller123",
                ComplianceStatusEnum.PENDING_REVIEW,
                "Ana", "Silva", "ana@lisboa-foods.test",
                MarketplaceEnum.PT, "Lisboa Foods Lda", "Rua Augusta 47, Lisbon"
        );
        roleService.addRoleToUser(foodSeller.getUsername(), "Seller");

        // Sweden – COMPLIANT
        User fashionSeller = createUser(
                "fashion-seller", "seller123",
                ComplianceStatusEnum.COMPLIANT,
                "Erik", "Lindqvist", "erik@nordic-fashion.test",
                MarketplaceEnum.SE, "Nordic Fashion AB", "Drottninggatan 55, Stockholm"
        );
        roleService.addRoleToUser(fashionSeller.getUsername(), "Seller");

        //COMPLIANCE_DOCUMENT CREATION

        // --- Spain (textileSeller) ---
        ComplianceDocument textileTaxId = createDocument(textileSeller,
                "Iberia Textiles Tax Identification Number",
                DocumentTypeEnum.TAX_IDENTIFICATION_NUMBER, DocumentStatusEnum.APPROVED);

        ComplianceDocument textileCertificate = createDocument(textileSeller,
                "Iberia Textiles Business Certificate",
                DocumentTypeEnum.BUSINESS_CERTIFICATE, DocumentStatusEnum.PENDING);

        // --- Germany (electronicsSeller) ---
        ComplianceDocument electronicsVatExemption = createDocument(electronicsSeller,
                "Rhein Electronics VAT Exemption Certificate",
                DocumentTypeEnum.VAT_EXEMPTION_CERTIFICATE, DocumentStatusEnum.APPROVED);

        ComplianceDocument electronicsCertificate = createDocument(electronicsSeller,
                "Rhein Electronics Business Certificate",
                DocumentTypeEnum.BUSINESS_CERTIFICATE, DocumentStatusEnum.APPROVED);

        ComplianceDocument electronicsCE = createDocument(electronicsSeller,
                "Rhein Electronics CE Marking Certificate",
                DocumentTypeEnum.CE_MARKING_CERTIFICATE, DocumentStatusEnum.APPROVED);

        // --- France (homewareSeller) ---
        ComplianceDocument homewarePassport = createDocument(homewareSeller,
                "Emma Dubois Passport",
                DocumentTypeEnum.PASSPORT, DocumentStatusEnum.REJECTED);

        ComplianceDocument homewareTaxId = createDocument(homewareSeller,
                "Nord Homeware Tax Identification Number",
                DocumentTypeEnum.TAX_IDENTIFICATION_NUMBER, DocumentStatusEnum.PENDING);

        ComplianceDocument homewareGdpr = createDocument(homewareSeller,
                "Nord Homeware GDPR Compliance Certificate",
                DocumentTypeEnum.GDPR_CERTIFICATE, DocumentStatusEnum.PENDING);

        // --- Netherlands (cosmeticsSeller) ---
        ComplianceDocument cosmeticsNationalId = createDocument(cosmeticsSeller,
                "Lotte de Vries National ID",
                DocumentTypeEnum.NATIONAL_ID, DocumentStatusEnum.APPROVED);

        ComplianceDocument cosmeticsCertificate = createDocument(cosmeticsSeller,
                "Bloem Cosmetics Business Certificate",
                DocumentTypeEnum.BUSINESS_CERTIFICATE, DocumentStatusEnum.APPROVED);

        ComplianceDocument cosmeticsProductSafety = createDocument(cosmeticsSeller,
                "Bloem Cosmetics Product Safety Certificate",
                DocumentTypeEnum.PRODUCT_SAFETY_CERTIFICATE, DocumentStatusEnum.APPROVED);

        ComplianceDocument cosmeticsDataProtection = createDocument(cosmeticsSeller,
                "Bloem Cosmetics Data Protection Certificate",
                DocumentTypeEnum.DATA_PROTECTION_CERTIFICATE, DocumentStatusEnum.APPROVED);

        // --- Poland (furnitureSeller) ---
        ComplianceDocument furnitureCertificate = createDocument(furnitureSeller,
                "Wisła Furniture Business Certificate",
                DocumentTypeEnum.BUSINESS_CERTIFICATE, DocumentStatusEnum.APPROVED);

        ComplianceDocument furnitureInsurance = createDocument(furnitureSeller,
                "Wisła Furniture Insurance Certificate",
                DocumentTypeEnum.INSURANCE_CERTIFICATE, DocumentStatusEnum.PENDING);

        ComplianceDocument furnitureProductionLicense = createDocument(furnitureSeller,
                "Wisła Furniture Production License",
                DocumentTypeEnum.PRODUCTION_LICENSE, DocumentStatusEnum.PENDING);

        // --- Italy (electronicsSellerIT) ---
        ComplianceDocument romaTechCE = createDocument(electronicsSellerIT,
                "Roma Tech CE Marking Certificate",
                DocumentTypeEnum.CE_MARKING_CERTIFICATE, DocumentStatusEnum.REJECTED);

        ComplianceDocument romaTechTaxId = createDocument(electronicsSellerIT,
                "Roma Tech Tax Identification Number",
                DocumentTypeEnum.TAX_IDENTIFICATION_NUMBER, DocumentStatusEnum.APPROVED);

        // --- Portugal (foodSeller) ---
        ComplianceDocument foodSellerNationalId = createDocument(foodSeller,
                "Ana Silva National ID",
                DocumentTypeEnum.NATIONAL_ID, DocumentStatusEnum.PENDING);

        ComplianceDocument foodSellerProductionLicense = createDocument(foodSeller,
                "Lisboa Foods Production License",
                DocumentTypeEnum.PRODUCTION_LICENSE, DocumentStatusEnum.PENDING);

        // --- Sweden (fashionSeller) ---
        ComplianceDocument fashionPassport = createDocument(fashionSeller,
                "Erik Lindqvist Passport",
                DocumentTypeEnum.PASSPORT, DocumentStatusEnum.APPROVED);

        ComplianceDocument fashionCertificate = createDocument(fashionSeller,
                "Nordic Fashion Business Certificate",
                DocumentTypeEnum.BUSINESS_CERTIFICATE, DocumentStatusEnum.APPROVED);

        ComplianceDocument fashionTrademark = createDocument(fashionSeller,
                "Nordic Fashion Trademark Certificate",
                DocumentTypeEnum.TRADEMARK_CERTIFICATE, DocumentStatusEnum.APPROVED);

        // REGULATION CREATION

        // Spain
        createRegulation(
                "Spain Tax Identification Requirement",
                "Requires all Spanish marketplace sellers to provide a valid Tax Identification Number (NIF) before activation.",
                MarketplaceEnum.ES,
                List.of(textileTaxId, textileCertificate)
        );

        // Germany
        createRegulation(
                "Germany Business Registration Requirement",
                "Requires German sellers to provide a business certificate and VAT exemption evidence.",
                MarketplaceEnum.DE,
                List.of(electronicsVatExemption, electronicsCertificate)
        );
        createRegulation(
                "Germany CE Marking Requirement",
                "Requires German electronics sellers to hold a valid CE Marking Certificate for all listed products.",
                MarketplaceEnum.DE,
                List.of(electronicsCE)
        );

        // France
        createRegulation(
                "EU Marketplace Seller Identity Verification",
                "Requires sellers to provide a valid passport or national identity document before marketplace activation.",
                MarketplaceEnum.FR,
                List.of(homewarePassport)
        );
        createRegulation(
                "France Tax Registration Requirement",
                "Requires French sellers to submit a valid Tax Identification Number for VAT compliance review.",
                MarketplaceEnum.FR,
                List.of(homewareTaxId)
        );
        createRegulation(
                "France GDPR Compliance Requirement",
                "Requires French sellers handling customer data to provide a valid GDPR compliance certificate.",
                MarketplaceEnum.FR,
                List.of(homewareGdpr)
        );

        // Netherlands
        createRegulation(
                "Netherlands Seller Identity and Business Verification",
                "Requires Dutch sellers to submit a national ID and a valid business certificate.",
                MarketplaceEnum.NL,
                List.of(cosmeticsNationalId, cosmeticsCertificate)
        );
        createRegulation(
                "Netherlands Product Safety and Data Protection Requirement",
                "Requires cosmetics sellers in the Netherlands to hold product safety and data protection certificates.",
                MarketplaceEnum.NL,
                List.of(cosmeticsProductSafety, cosmeticsDataProtection)
        );

        // Poland
        createRegulation(
                "Poland Business and Insurance Requirement",
                "Requires Polish sellers to provide a business certificate and a valid insurance certificate.",
                MarketplaceEnum.PL,
                List.of(furnitureCertificate, furnitureInsurance)
        );
        createRegulation(
                "Poland Production License Requirement",
                "Requires Polish manufacturing sellers to hold a valid production license.",
                MarketplaceEnum.PL,
                List.of(furnitureProductionLicense)
        );

        // Italy
        createRegulation(
                "Italy CE Marking and Tax Identification Requirement",
                "Requires Italian electronics sellers to provide CE marking certification and a valid tax identification number.",
                MarketplaceEnum.IT,
                List.of(romaTechCE, romaTechTaxId)
        );

        // Portugal
        createRegulation(
                "Portugal Seller Identity Verification",
                "Requires Portuguese sellers to submit a valid national ID prior to marketplace activation.",
                MarketplaceEnum.PT,
                List.of(foodSellerNationalId)
        );
        createRegulation(
                "Portugal Food Production License Requirement",
                "Requires Portuguese food sellers to hold a valid production license issued by a recognised authority.",
                MarketplaceEnum.PT,
                List.of(foodSellerProductionLicense)
        );

        // Sweden
        createRegulation(
                "Sweden Seller Identity and Business Verification",
                "Requires Swedish sellers to submit a valid passport and a business registration certificate.",
                MarketplaceEnum.SE,
                List.of(fashionPassport, fashionCertificate)
        );
        createRegulation(
                "Sweden Trademark Registration Requirement",
                "Requires Swedish fashion sellers to provide a trademark certificate for branded goods.",
                MarketplaceEnum.SE,
                List.of(fashionTrademark)
        );
    }


    private void createRoles() {
        roleService.save(new Role("ADMIN"));
        roleService.save(new Role("Seller"));
        roleService.save(new Role("Agent"));
        roleService.save(new Role("Regulation Manager"));

    }

    private User createUser(String username,
                            String password,
                            ComplianceStatusEnum complianceStatus,
                            String firstName,
                            String lastName,
                            String email,
                            MarketplaceEnum marketplace,
                            String businessName,
                            String businessAddress) {
        User user = new User(username, password);
        user.setComplianceStatus(complianceStatus);
        user.setUserProfile(new UserProfile(firstName, lastName, email, marketplace));
        user.getUserProfile().setBusinessInfo(new BusinessInfo(businessName, businessAddress));
        return userService.saveUser(user);
    }

    private ComplianceDocument createDocument(User user,
                                              String documentName,
                                              DocumentTypeEnum documentType,
                                              DocumentStatusEnum documentStatus) {
        ComplianceDocument document = new ComplianceDocument();
        document.setDocumentName(documentName);
        document.setDocumentType(documentType);

        ComplianceDocument savedDocument = complianceDocumentService.saveComplianceDocument(document, user.getUsername());
        savedDocument.setDocumentStatus(documentStatus);

        return complianceDocumentService.updateComplianceDocument(savedDocument.getId(), savedDocument);
    }

    private void createRegulation(String title,
                                  String description,
                                  MarketplaceEnum marketplace,
                                  List<ComplianceDocument> requiredDocuments) {
        Regulation regulation = new Regulation(title, description, marketplace);
        regulation.setRequired_documents(requiredDocuments);
        regulationService.saveRegulation(regulation);
    }
}
