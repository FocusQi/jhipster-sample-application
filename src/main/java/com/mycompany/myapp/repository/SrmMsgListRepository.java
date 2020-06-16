package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SrmMsgList;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SrmMsgList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SrmMsgListRepository extends JpaRepository<SrmMsgList, Long> {
}
