package com.mega.project.srm.repository;

import com.mega.project.srm.domain.KpiTemplateGroupInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KpiTemplateGroupInfo entity.
 */
@Repository
public interface KpiTemplateGroupInfoRepository extends JpaRepository<KpiTemplateGroupInfo, Long> {
    @Query(
        value = "select distinct kpiTemplateGroupInfo from KpiTemplateGroupInfo kpiTemplateGroupInfo left join fetch kpiTemplateGroupInfo.users",
        countQuery = "select count(distinct kpiTemplateGroupInfo) from KpiTemplateGroupInfo kpiTemplateGroupInfo"
    )
    Page<KpiTemplateGroupInfo> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct kpiTemplateGroupInfo from KpiTemplateGroupInfo kpiTemplateGroupInfo left join fetch kpiTemplateGroupInfo.users")
    List<KpiTemplateGroupInfo> findAllWithEagerRelationships();

    @Query(
        "select kpiTemplateGroupInfo from KpiTemplateGroupInfo kpiTemplateGroupInfo left join fetch kpiTemplateGroupInfo.users where kpiTemplateGroupInfo.id =:id"
    )
    Optional<KpiTemplateGroupInfo> findOneWithEagerRelationships(@Param("id") Long id);
}
