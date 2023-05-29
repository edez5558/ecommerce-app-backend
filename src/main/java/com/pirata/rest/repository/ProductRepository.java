package com.pirata.rest.repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pirata.rest.model.Product;
import com.pirata.rest.model.User;



public interface ProductRepository extends JpaRepository<Product, Long>{

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByPelucheTrue(Pageable pageable);

    Page<Product> findByPelucheFalse(Pageable pageable);

    @Query(value = "SELECT p FROM Product as p ORDER BY RAND() LIMIT 10")
    List<Product> findRandomProducts();

    @Query(value = "SELECT p FROM Product as p ORDER BY RAND() LIMIT 1")
    List<Product> findRandomPrincipal();

    @Modifying (clearAutomatically = true)
    @Transactional
    @Query(value = "update product set image_url = ?1 where id = ?2", nativeQuery = true)
    void updateSetImageUrlForId(String newurl,long id);

    @Query(value = "SELECT c FROM Product as p INNER JOIN User as c ON p.client = c.id WHERE p.id = ?1")
    Optional<User> selectUserById(Long id);

    @Query(value = "SELECT c.id FROM Product as p INNER JOIN User as c ON p.client = c.id WHERE p.id = ?1")
    Optional<Long> selectUserIdById(Long id);

    @Query(value = "SELECT c.username FROM Product as p INNER JOIN User as c ON p.client = c.id WHERE p.id = ?1")
    Optional<String> selectUserNameById(Long id);

    @Modifying (clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Product set stock = ?1 where id = ?2")
    void updateStockById(long stock, Long id);

    @Query(value = "SELECT p FROM Product AS p WHERE p.stock >= 1")
    List<Product> findAllinStock();

}
