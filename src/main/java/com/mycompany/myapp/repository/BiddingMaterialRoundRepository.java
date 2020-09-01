package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BiddingMaterialRound;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BiddingMaterialRound entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiddingMaterialRoundRepository extends JpaRepository<BiddingMaterialRound, Long> {
}
