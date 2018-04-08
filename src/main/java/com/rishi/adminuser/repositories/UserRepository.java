package com.rishi.adminuser.repositories;

/**
 * @author raushanr
 *
 */

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rishi.adminuser.entity.Authority;
import com.rishi.adminuser.entity.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    //User findByName(String name);
	
	Optional<User> findOneById(@Param("userId") Long userId);
	
	Optional<User> findOneByEmail(@Param("email") String email);

    Optional<User> findOneByLogin(@Param("login") String login);

    Optional<User> findById(@Param("userId") Long userId);
    
    @Override
    @Query(value = "SELECT u FROM User u order by u.createdDate DESC")
    Page<User> findAll(Pageable pageable);
    
    Page<User> findByLoginContaining(@Param("login") String login, Pageable pageable);
    
    @Query(value = "SELECT u FROM User u join u.authorities u1 WHERE u1 IN (:authorities)")
    List<User> findOneByAuthority(@Param("authorities") Set<Authority> authorities);
    
    @Query(value = "SELECT u FROM User u join u.authorities u1 WHERE u1.name =:authorities)")
	List<User> findAllAdmin(@Param("authorities") String authority);
    
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByLogin(@Param("login") String login);
    
    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
    
    @Query(value = "SELECT u FROM User u WHERE u.login =:login AND u.password =:password")
    List<User> checkByLoginAndPassword(@Param("login") String login, @Param("password") String password);
    
    long count();
}
