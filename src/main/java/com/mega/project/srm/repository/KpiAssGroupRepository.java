package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiAssGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiAssGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiAssGroupRepository extends JpaRepository<KpiAssGroup, Long> {}
