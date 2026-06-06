package com.example.compliancehubapi.service;


import com.example.compliancehubapi.enums.DocumentStatusEnum;
import com.example.compliancehubapi.enums.DocumentTypeEnum;
import com.example.compliancehubapi.model.ComplianceDocument;

import java.util.List;
import java.util.Optional;

public interface ComplianceDocumentService {

     ComplianceDocument saveComplianceDocument(ComplianceDocument complianceDocument);
     List<ComplianceDocument> getAllComplianceDocuments();
     Optional<ComplianceDocument> getComplianceDocumentById(Long id);

     List<ComplianceDocument> getComplianceDocumentsByDocumentStatus(DocumentStatusEnum status);
     List<ComplianceDocument> getComplianceDocumentsByDocumentType(DocumentTypeEnum documentType);
     List<ComplianceDocument> getComplianceDocumentsByUserId(Long userId);

     ComplianceDocument updateComplianceDocument(Long id, ComplianceDocument complianceDocument);
     void deleteComplianceDocumentById(Long id);
}
