package com.pirata.rest.repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pirata.rest.model.Product;
import com.pirata.rest.model.User;


public interface ProductRepository extends JpaRepository<Product, Long>{

    @Modifying (clearAutomatically = true)
    @Transactional
    @Query(value = "update product set image_url = ?1 where id = ?2", nativeQuery = true)
    void updateSetImageUrlForId(String newurl,long id);

    @Query(value = "SELECT c FROM Product as p INNER JOIN User as c ON p.client = c.id WHERE p.id = ?1")
    Optional<User> selectUserById(Long id);

    @Query(value = "SELECT c.username FROM Product as p INNER JOIN User as c ON p.client = c.id WHERE p.id = ?1")
    Optional<String> selectUserNameById(Long id);
}
