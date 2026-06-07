package com.example.compliancehubapi.service.impl;

import com.example.compliancehubapi.enums.MarketplaceEnum;
import com.example.compliancehubapi.model.Regulation;
import com.example.compliancehubapi.repository.RegulationRepository;
import com.example.compliancehubapi.service.RegulationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j // use this for logging (log.info, log.error...)
public class RegulationServiceImpl implements RegulationService {

    private final RegulationRepository regulationRepository;

    public Regulation saveRegulation(Regulation regulation){
        log.info("Saving regulation: {}", regulation);
        return regulationRepository.save(regulation);
    }

    public List<Regulation> getAllRegulations(){
        log.info("Fetching all regulations");
        return regulationRepository.findAll();
    }

    public Optional<Regulation> getRegulationById(Long id){
        log.info("Fetching regulation by id {}", id);
        var regulation = regulationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Regulation with id: " + id + " not found.")
        );
        return regulationRepository.findById(id);
    }

    public List<Regulation> getRegulationsByMarketplace(MarketplaceEnum marketplace){
        log.info("Fetching regulations by marketplace {}", marketplace);
        return regulationRepository.findRegulationsByMarketplace(marketplace);
    }

    public Regulation updateRegulation(Long id, Regulation regulation){
        log.info("Updating regulation with id {}", id);
       var regulationToUpdate = regulationRepository.findById(id).orElseThrow(
               () -> new RuntimeException("Regulation with id: " + id + " not found.")
       );
       regulationToUpdate.setDescription(regulation.getDescription());
       regulationToUpdate.setMarketplace(regulation.getMarketplace());
       regulationToUpdate.setTitle(regulation.getTitle());

        return regulationRepository.save(regulationToUpdate);
    }

    public void deleteRegulationById(Long id){
        log.info("Deleting regulation with id {}", id);
        regulationRepository.deleteById(id);
    }

}
