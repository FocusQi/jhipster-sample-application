package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.QuotationInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QuotationInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotationInfoRepository extends JpaRepository<QuotationInfo, Long> {
}
