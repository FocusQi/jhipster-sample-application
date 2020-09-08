package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiImproveReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiImproveReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiImproveReportRepository extends JpaRepository<KpiImproveReport, Long> {}
