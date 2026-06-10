package com.example.compliancehubapi.model;

import com.example.compliancehubapi.enums.DocumentStatusEnum;
import com.example.compliancehubapi.enums.DocumentTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ComplianceDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;
    private LocalDate documentUploadDate;

    @Enumerated(EnumType.STRING)
    private DocumentStatusEnum documentStatus = DocumentStatusEnum.PENDING;

    @Enumerated(EnumType.STRING)
    private DocumentTypeEnum documentType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "required_documents")
    private List<Regulation>  regulations = new ArrayList<>();
}
