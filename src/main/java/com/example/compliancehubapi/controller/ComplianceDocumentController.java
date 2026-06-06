package com.example.compliancehubapi.controller;

import com.example.compliancehubapi.enums.DocumentStatusEnum;
import com.example.compliancehubapi.enums.DocumentTypeEnum;
import com.example.compliancehubapi.model.ComplianceDocument;
import com.example.compliancehubapi.service.ComplianceDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compliance-document")
@RequiredArgsConstructor
public class ComplianceDocumentController {

    private final ComplianceDocumentService complianceDocumentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComplianceDocument saveComplianceDocument(@RequestBody ComplianceDocument complianceDocument){
    return complianceDocumentService.saveComplianceDocument(complianceDocument);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ComplianceDocument> getAllComplianceDocuments(){
    return complianceDocumentService.getAllComplianceDocuments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ComplianceDocument> getComplianceDocumentById(@PathVariable Long id){
        return complianceDocumentService.getComplianceDocumentById(id);
    }

    @GetMapping("/document-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<ComplianceDocument> getComplianceDocumentsByDocumentStatus(@PathVariable DocumentStatusEnum status){
        return complianceDocumentService.getComplianceDocumentsByDocumentStatus(status);
    }

    @GetMapping("/document-type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<ComplianceDocument> getComplianceDocumentsByDocumentType(@PathVariable DocumentTypeEnum type){
        return complianceDocumentService.getComplianceDocumentsByDocumentType(type);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ComplianceDocument> getComplianceDocumentsByUserId(@PathVariable Long userId){
        return complianceDocumentService.getComplianceDocumentsByUserId(userId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComplianceDocument updateComplianceDocument(@PathVariable Long id, @RequestBody ComplianceDocument complianceDocument){
        return complianceDocumentService.updateComplianceDocument(id, complianceDocument);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComplianceDocumentById(@PathVariable Long id){
        complianceDocumentService.deleteComplianceDocumentById(id);
    }

}
