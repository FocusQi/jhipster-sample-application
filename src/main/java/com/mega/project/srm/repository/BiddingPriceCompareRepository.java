package com.mega.project.srm.repository;

import com.mega.project.srm.domain.BiddingPriceCompare;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BiddingPriceCompare entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiddingPriceCompareRepository extends JpaRepository<BiddingPriceCompare, Long> {}
