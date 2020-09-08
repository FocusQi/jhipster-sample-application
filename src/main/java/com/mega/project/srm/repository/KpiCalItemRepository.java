package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiCalItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiCalItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiCalItemRepository extends JpaRepository<KpiCalItem, Long> {}
