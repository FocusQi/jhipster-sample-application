package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiAssTemplateInfo;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiAssTemplateInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KpiAssTemplateInfoRepository extends JpaRepository<KpiAssTemplateInfo, Long> {
    @Query(
        "select kpiAssTemplateInfo from KpiAssTemplateInfo kpiAssTemplateInfo where kpiAssTemplateInfo.user.login = ?#{principal.username}"
    )
    List<KpiAssTemplateInfo> findByUserIsCurrentUser();
}
