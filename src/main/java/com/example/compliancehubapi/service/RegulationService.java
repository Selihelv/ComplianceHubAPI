package com.example.compliancehubapi.service;

import com.example.compliancehubapi.enums.MarketplaceEnum;
import com.example.compliancehubapi.model.Regulation;

import java.util.List;
import java.util.Optional;

public interface RegulationService {

    Regulation saveRegulation(Regulation regulation);

    List<Regulation> getAllRegulations();

    Optional<Regulation> getRegulationById(Long id);

    List<Regulation> getRegulationsByMarketplace(MarketplaceEnum marketplace);

    Regulation updateRegulation(Long id, Regulation regulation);

    void deleteRegulationById(Long id);
}
