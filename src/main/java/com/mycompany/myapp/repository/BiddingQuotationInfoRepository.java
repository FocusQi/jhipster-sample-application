package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BiddingQuotationInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BiddingQuotationInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiddingQuotationInfoRepository extends JpaRepository<BiddingQuotationInfo, Long> {
}
