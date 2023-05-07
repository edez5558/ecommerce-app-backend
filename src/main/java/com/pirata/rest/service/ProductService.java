package com.pirata.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pirata.rest.model.Product;
import com.pirata.rest.model.User;
import com.pirata.rest.repository.ProductRepository;


@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRespository;

    public Product create(Product product){
        return productRespository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRespository.findAll();
    }

    public void delete(Product product){
        productRespository.delete(product);
    }

    public Optional<Product> findById(Long id){
        return productRespository.findById(id);
    }

    public void updateUrlById(String url,long id){
        productRespository.updateSetImageUrlForId(url, id);
    }

    public Optional<User> getUserById(Long id){
        return productRespository.selectUserById(id);
    }

    public Optional<String> getUserNameById(Long id){
        return productRespository.selectUserNameById(id);
    }
}
