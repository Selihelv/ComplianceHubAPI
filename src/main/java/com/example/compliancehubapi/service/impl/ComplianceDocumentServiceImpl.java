package com.example.compliancehubapi.service.impl;

import com.example.compliancehubapi.enums.DocumentStatusEnum;
import com.example.compliancehubapi.enums.DocumentTypeEnum;
import com.example.compliancehubapi.model.ComplianceDocument;
import com.example.compliancehubapi.repository.ComplianceDocumentRepository;
import com.example.compliancehubapi.service.ComplianceDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j // use this for logging (log.info, log.error...)
public class ComplianceDocumentServiceImpl implements ComplianceDocumentService {

    private final ComplianceDocumentRepository complianceDocumentRepository;

    public ComplianceDocument saveComplianceDocument(ComplianceDocument complianceDocument){
        log.info("Saving compliance document: {}", complianceDocument);
        return complianceDocumentRepository.save(complianceDocument);
    }


    public List<ComplianceDocument> getAllComplianceDocuments(){
        log.info("Fetching all compliance documents");
        return complianceDocumentRepository.findAll();
    }


    public Optional<ComplianceDocument> getComplianceDocumentById(Long id){
        log.info("Fetching compliance document by id {}", id);
        return complianceDocumentRepository.findById(id);
    }

    public List<ComplianceDocument> getComplianceDocumentsByDocumentStatus(DocumentStatusEnum status){
        log.info("Fetching Compliance documents by status {}", status);
        return complianceDocumentRepository.findComplianceDocumentsByDocumentStatus(status);
    }


    public List<ComplianceDocument> getComplianceDocumentsByDocumentType(DocumentTypeEnum documentType){
       log.info("Fetching Compliance documents by document type {}", documentType);
        return complianceDocumentRepository.findComplianceDocumentsByDocumentType(documentType);
    }

    public List<ComplianceDocument> getComplianceDocumentsByUserId(Long userId){
        log.info("Fetching Compliance documents by user id {}", userId);
        return complianceDocumentRepository.findComplianceDocumentsByUser_Id(userId);
    }

    public ComplianceDocument updateComplianceDocument(Long id, ComplianceDocument complianceDocument){
        log.info("Updating compliance document with id {}", id);
        var complianceDocumentToUpdate = complianceDocumentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Compliance document with id: " + id + " not found.")
        );
        complianceDocumentToUpdate.setDocumentName(complianceDocument.getDocumentName());
        complianceDocumentToUpdate.setDocumentType(complianceDocument.getDocumentType());
        complianceDocumentToUpdate.setDocumentStatus(complianceDocument.getDocumentStatus());

        if(complianceDocument.getRegulations() != null){
            complianceDocumentToUpdate.setRegulations(complianceDocument.getRegulations());
            complianceDocumentToUpdate.getRegulations().forEach( regulation ->
                    regulation.getRequired_documents().add(complianceDocumentToUpdate));
        }

        return complianceDocumentRepository.save(complianceDocumentToUpdate);
    }


    public void deleteComplianceDocumentById(Long id){
        log.info("Deleting compliance document with id {}", id);
        complianceDocumentRepository.deleteById(id);
    }
}
