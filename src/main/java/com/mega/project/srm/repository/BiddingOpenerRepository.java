package com.mega.project.srm.repository;

import com.mega.project.srm.domain.BiddingOpener;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BiddingOpener entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiddingOpenerRepository extends JpaRepository<BiddingOpener, Long> {}
