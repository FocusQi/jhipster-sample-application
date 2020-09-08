package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiAssGroupInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiAssGroupInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiAssGroupInfoRepository extends JpaRepository<KpiAssGroupInfo, Long> {}
