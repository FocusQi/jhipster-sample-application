package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.VendorRound;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendorRound entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorRoundRepository extends JpaRepository<VendorRound, Long> {
}
