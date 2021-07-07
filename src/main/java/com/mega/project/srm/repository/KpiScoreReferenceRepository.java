package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiScoreReference;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiScoreReference entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiScoreReferenceRepository extends JpaRepository<KpiScoreReference, Long> {}
