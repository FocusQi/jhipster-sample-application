package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiScoreGradeConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiScoreGradeConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiScoreGradeConfigRepository extends JpaRepository<KpiScoreGradeConfig, Long> {}
