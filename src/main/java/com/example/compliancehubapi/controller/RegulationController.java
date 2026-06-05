package com.example.compliancehubapi.controller;

import com.example.compliancehubapi.enums.MarketplaceEnum;
import com.example.compliancehubapi.model.Regulation;
import com.example.compliancehubapi.service.RegulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regulation")
@RequiredArgsConstructor
public class RegulationController {

    private final RegulationService regulationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Regulation saveRegulation(@RequestBody Regulation regulation){
        return regulationService.saveRegulation(regulation);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Regulation> getAllRegulations(){
        return regulationService.getAllRegulations();
    }

    @GetMapping("/api/regulation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Regulation> getRegulationById(@PathVariable Long id){
        return regulationService.getRegulationById(id);
    }

    @GetMapping("/api/regulation/{marketplace}")
    @ResponseStatus(HttpStatus.OK)
    public List<Regulation> getRegulationsByMarketplace (@PathVariable MarketplaceEnum marketplace){
        return regulationService.getRegulationsByMarketplace(marketplace);
    }

    @PutMapping("/api/regulation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Regulation updateRegulation(@PathVariable Long id, @RequestBody Regulation regulation){
        return regulationService.updateRegulation(id, regulation);
    }

    @DeleteMapping("/api/regulation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRegulationById(@PathVariable Long id){
        regulationService.deleteRegulationById(id);
    }
}
