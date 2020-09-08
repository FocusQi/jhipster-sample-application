package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiAssTemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiAssTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiAssTemplateRepository extends JpaRepository<KpiAssTemplate, Long> {}
