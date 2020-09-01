package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Opener;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Opener entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpenerRepository extends JpaRepository<Opener, Long> {
}
