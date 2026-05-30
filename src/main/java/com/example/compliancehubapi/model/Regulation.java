package com.example.compliancehubapi.model;

import com.example.compliancehubapi.enums.MarketplaceEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Regulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private MarketplaceEnum marketplace;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name= "regulation_documents",
            joinColumns = @JoinColumn(name = "regulation_id"),
            inverseJoinColumns = @JoinColumn(name = "compliance_document_id")
    )
    private List<ComplianceDocument> required_documents = new ArrayList<>();

    public Regulation(String title, String description, MarketplaceEnum marketplace) {
        this.title = title;
        this.description = description;
        this.marketplace = marketplace;
    }
}
