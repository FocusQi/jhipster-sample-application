package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiAssTemplateGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiAssTemplateGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiAssTemplateGroupRepository extends JpaRepository<KpiAssTemplateGroup, Long> {}
