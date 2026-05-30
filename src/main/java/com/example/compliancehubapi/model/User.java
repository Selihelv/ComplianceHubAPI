package com.example.compliancehubapi.model;

import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

/**
 * Entity class for representing a User in the database
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private ComplianceStatusEnum complianceStatus = ComplianceStatusEnum.NON_COMPLIANT;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "userProfile_id")
    private UserProfile userProfile;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "role_id")
    private Role role;

   @OneToMany( mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<ComplianceDocument> complianceDocuments;

    // TODO: eksik kalan arg ları ekle

    public User( String username, String password, ComplianceStatusEnum complianceStatus,
                UserProfile userProfile, Role role, List<ComplianceDocument> complianceDocuments) {
        this.username = username;
        this.password = password;
        this.complianceStatus = complianceStatus;
        this.userProfile = userProfile;
        this.role = role;
        this.complianceDocuments = complianceDocuments;
    }
}
