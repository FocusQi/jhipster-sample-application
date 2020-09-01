package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BiddingOpener;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BiddingOpener entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiddingOpenerRepository extends JpaRepository<BiddingOpener, Long> {
}
