package com.example.compliancehubapi.tools;

import com.example.compliancehubapi.enums.DocumentStatusEnum;
import com.example.compliancehubapi.enums.DocumentTypeEnum;
import com.example.compliancehubapi.model.ComplianceDocument;
import com.example.compliancehubapi.repository.ComplianceDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ComplianceDocumentTools {

    private final ComplianceDocumentRepository complianceDocumentRepository;

    @Tool(description = "Get all Compliance Documents")
    public List<ComplianceDocument> getAllComplianceDocuments(){
        return complianceDocumentRepository.findAll();
    }

    @Tool(description = "Get Compliance Document by id")
    public Optional<ComplianceDocument> getComplianceDocumentById(Long id){
        return complianceDocumentRepository.findById(id);
    }

    @Tool(description = "Get Compliance Documents by DocumentStatus")
    public List<ComplianceDocument> getComplianceDocumentsByDocumentStatus(DocumentStatusEnum status){
        return complianceDocumentRepository.findComplianceDocumentsByDocumentStatus(status);
    }

    @Tool(description = "Get Compliance Documents by DocumentType")
    public List<ComplianceDocument> getComplianceDocumentsByDocumentType(DocumentTypeEnum documentType){
        return complianceDocumentRepository.findComplianceDocumentsByDocumentType(documentType);
    }

    @Tool(description = "Get Compliance Documents by UserId")
    public List<ComplianceDocument> getComplianceDocumentsByUserId(Long userId){
        return complianceDocumentRepository.findComplianceDocumentsByUser_Id(userId);
    }
}
