package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BomTemplateHeader;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BomTemplateHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BomTemplateHeaderRepository extends JpaRepository<BomTemplateHeader, Long> {
}
