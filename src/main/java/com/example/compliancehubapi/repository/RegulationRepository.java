package com.example.compliancehubapi.repository;

import com.example.compliancehubapi.enums.MarketplaceEnum;
import com.example.compliancehubapi.model.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegulationRepository extends JpaRepository<Regulation, Long> {

    List<Regulation> findRegulationsByMarketplace(MarketplaceEnum marketplace);
}
