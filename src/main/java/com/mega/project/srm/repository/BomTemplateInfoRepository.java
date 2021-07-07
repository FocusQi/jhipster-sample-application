package com.mega.project.srm.repository;

import com.mega.project.srm.domain.BomTemplateInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BomTemplateInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BomTemplateInfoRepository extends JpaRepository<BomTemplateInfo, Long> {}
