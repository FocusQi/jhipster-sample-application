package com.mega.project.srm.repository;

import com.mega.project.srm.domain.BomTemplateHeader;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BomTemplateHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BomTemplateHeaderRepository extends JpaRepository<BomTemplateHeader, Long> {}
