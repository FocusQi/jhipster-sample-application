package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiScoreCalConfigItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiScoreCalConfigItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiScoreCalConfigItemRepository extends JpaRepository<KpiScoreCalConfigItem, Long> {}
