package com.mega.project.srm.repository;

import com.mega.project.srm.domain.BomTemplateInfoColumn;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BomTemplateInfoColumn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BomTemplateInfoColumnRepository extends JpaRepository<BomTemplateInfoColumn, Long> {}
