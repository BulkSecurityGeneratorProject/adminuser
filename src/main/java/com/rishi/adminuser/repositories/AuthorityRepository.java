package com.rishi.adminuser.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rishi.adminuser.entity.Authority;


/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Optional<Authority> findOneByName(@Param("name") String authorityName);

    @Override
    @Query(value = "SELECT u FROM Authority u order by u.createdDate DESC")
    Page<Authority> findAll(Pageable pageable);
    
    Page<Authority> findByNameContaining(@Param("name") String name, Pageable p);
    
}
