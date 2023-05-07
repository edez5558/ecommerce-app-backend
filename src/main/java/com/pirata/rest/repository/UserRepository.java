package com.pirata.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pirata.rest.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    Optional<User> findByUsername(String username);


    @Query(value = "UPDATE User AS u SET u.sessionId = ?2 WHERE u.id = ?1")
    void updateSessionIdById(Long id,String sessionId);

    @Query(value = "SELECT u.sessionId FROM User AS u WHERE u.id = ?1")
    Optional<String> selectSessionId(Long id);
}
