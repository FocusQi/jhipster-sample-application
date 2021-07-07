package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PriceCompare;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PriceCompare entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriceCompareRepository extends JpaRepository<PriceCompare, Long> {
}
