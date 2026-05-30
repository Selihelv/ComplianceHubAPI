package com.example.compliancehubapi.model;

import com.example.compliancehubapi.enums.MarketplaceEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Regulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private MarketplaceEnum marketplace;

    //TODO: define relationship!!!

    @ManyToMany
    @JoinTable(
            name= "regulation_documents",
            joinColumns = @JoinColumn(name = "regulation_id"),
            inverseJoinColumns = @JoinColumn(name = "compliance_document_id")
    )
    private List<ComplianceDocument> requiredDocuments = new ArrayList<>();
}
