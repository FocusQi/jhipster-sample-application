package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BiddingRoundInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BiddingRoundInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiddingRoundInfoRepository extends JpaRepository<BiddingRoundInfo, Long> {
}
