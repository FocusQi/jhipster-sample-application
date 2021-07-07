package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiTemplateGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiTemplateGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiTemplateGroupRepository extends JpaRepository<KpiTemplateGroup, Long> {}
