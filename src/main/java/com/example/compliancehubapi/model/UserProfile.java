package com.example.compliancehubapi.model;

import com.example.compliancehubapi.enums.MarketplaceEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private MarketplaceEnum marketplace;

    @Embedded
    private BusinessInfo businessInfo;

    public UserProfile(String firstName, String lastName, String email, MarketplaceEnum marketplace) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.marketplace = marketplace;
    }
}
