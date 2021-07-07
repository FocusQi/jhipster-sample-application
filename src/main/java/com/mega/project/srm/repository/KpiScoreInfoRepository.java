package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiScoreInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiScoreInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiScoreInfoRepository extends JpaRepository<KpiScoreInfo, Long> {}
