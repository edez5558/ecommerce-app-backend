package com.pirata.rest.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pirata.rest.model.Product;
import com.pirata.rest.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductResource {
    
    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    private ResponseEntity<Product> save(@RequestBody Product product){
        Product tmp = productService.create(product);

        try{
            return ResponseEntity.created(new URI("/api/product/" + tmp.getId())).body(tmp);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/list")
    private ResponseEntity<List<Product>> listAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Void> deleteProduct(@RequestBody Product product){
        productService.delete(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/get/{id}")
    private ResponseEntity<Optional<Product>> listAllProduct(@PathVariable ("id") Long id){
        return ResponseEntity.ok(productService.findById(id));
    }
}