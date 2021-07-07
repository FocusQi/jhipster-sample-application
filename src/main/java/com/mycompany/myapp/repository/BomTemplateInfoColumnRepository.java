package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BomTemplateInfoColumn;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BomTemplateInfoColumn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BomTemplateInfoColumnRepository extends JpaRepository<BomTemplateInfoColumn, Long> {
}
