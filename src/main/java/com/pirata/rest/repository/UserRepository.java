package com.pirata.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pirata.rest.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    Optional<User> findByUsername(String username);

    @Modifying (clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE client AS u SET session_id = ?2 WHERE id = ?1",nativeQuery = true)
    void updateSessionIdById(Long id,String sessionId);

    @Query(value = "SELECT u.sessionId FROM User AS u WHERE u.id = ?1")
    Optional<String> selectSessionId(Long id);
}
