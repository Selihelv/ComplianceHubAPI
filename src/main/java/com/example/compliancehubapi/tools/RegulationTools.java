package com.example.compliancehubapi.tools;

import com.example.compliancehubapi.enums.MarketplaceEnum;
import com.example.compliancehubapi.model.Regulation;
import com.example.compliancehubapi.repository.RegulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegulationTools {

    private final RegulationRepository regulationRepository;

    @Tool(description = "Get all Regulations")
    public List<Regulation> getAllRegulations(){
        return regulationRepository.findAll();
    }

    @Tool(description = "Get Regulation by id")
    public Optional<Regulation> getRegulationById(Long id){
        var regulation = regulationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Regulation with id: " + id + " not found.")
        );
        return regulationRepository.findById(id);
    }

    @Tool(description = "Get Regulations by marketplace")
    public List<Regulation> getRegulationsByMarketplace(MarketplaceEnum marketplace){
        return regulationRepository.findRegulationsByMarketplace(marketplace);
    }

    @Tool(description = "Update Regulation by id")
    public Regulation updateRegulation(Long id, Regulation regulation){
        var regulationToUpdate = regulationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Regulation with id: " + id + " not found.")
        );
        regulationToUpdate.setDescription(regulation.getDescription());
        regulationToUpdate.setMarketplace(regulation.getMarketplace());
        regulationToUpdate.setTitle(regulation.getTitle());

        return regulationRepository.save(regulationToUpdate);
    }


}
