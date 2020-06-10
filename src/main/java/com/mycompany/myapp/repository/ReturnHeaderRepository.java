package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ReturnHeader;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ReturnHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReturnHeaderRepository extends JpaRepository<ReturnHeader, Long> {
}
