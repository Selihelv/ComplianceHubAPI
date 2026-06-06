package com.example.compliancehubapi.repository;

import com.example.compliancehubapi.enums.DocumentStatusEnum;
import com.example.compliancehubapi.enums.DocumentTypeEnum;
import com.example.compliancehubapi.model.ComplianceDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplianceDocumentRepository extends JpaRepository<ComplianceDocument, Long> {

    List<ComplianceDocument> findComplianceDocumentsByDocumentStatus(DocumentStatusEnum status);
    List<ComplianceDocument> findComplianceDocumentsByDocumentType(DocumentTypeEnum documentType);
    List<ComplianceDocument> findComplianceDocumentsByUser_Id(Long userId);
}
