package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiImproveReportInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiImproveReportInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiImproveReportInfoRepository extends JpaRepository<KpiImproveReportInfo, Long> {}
