package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MaterialRound;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MaterialRound entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaterialRoundRepository extends JpaRepository<MaterialRound, Long> {
}
