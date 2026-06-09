package com.example.compliancehubapi.model;

import com.example.compliancehubapi.enums.ComplianceStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private ComplianceStatusEnum complianceStatus = ComplianceStatusEnum.NON_COMPLIANT;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "user_profile_id")
    private UserProfile userProfile;


    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles = new ArrayList<>();

   @OneToMany( mappedBy = "user",cascade = CascadeType.ALL)
   @JsonIgnore
   private List<ComplianceDocument> complianceDocuments = new ArrayList<>();



    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }
}
