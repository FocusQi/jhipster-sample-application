package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BiddingQuotationHeader;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BiddingQuotationHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiddingQuotationHeaderRepository extends JpaRepository<BiddingQuotationHeader, Long> {
}
