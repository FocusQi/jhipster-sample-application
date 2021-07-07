package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PoHeader;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PoHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PoHeaderRepository extends JpaRepository<PoHeader, Long> {
}
