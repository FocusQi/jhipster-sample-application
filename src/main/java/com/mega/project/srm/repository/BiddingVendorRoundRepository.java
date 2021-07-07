package com.mega.project.srm.repository;

import com.mega.project.srm.domain.BiddingVendorRound;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BiddingVendorRound entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiddingVendorRoundRepository extends JpaRepository<BiddingVendorRound, Long> {}
