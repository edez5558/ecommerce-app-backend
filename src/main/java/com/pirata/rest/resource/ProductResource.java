package com.pirata.rest.resource;

import java.io.Console;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pirata.rest.model.Product;
import com.pirata.rest.model.User;
import com.pirata.rest.request.BuyRequest;
import com.pirata.rest.request.ModifyRequest;
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
            return ResponseEntity.ok(tmp);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/get/{id}/user")
    private ResponseEntity<Optional<User>> getUser(@PathVariable ("id") Long id){
        return ResponseEntity.ok(productService.getUserById(id));
    }

    @GetMapping("/get/{id}/user/name")
    private ResponseEntity<Optional<String>> getUserName(@PathVariable ("id") Long id){
        return ResponseEntity.ok(productService.getUserNameById(id));
    }

    @GetMapping("/list")
    private ResponseEntity<List<Product>> listAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/random")
    private ResponseEntity<List<Product>> randomListProduct(){
        return ResponseEntity.ok(productService.getProductRandom());
    }

    @GetMapping("/random-principal")
    private ResponseEntity<List<Product>> randomPrincipalProduct(){
        return ResponseEntity.ok(productService.getProductPrincipal());
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Void> deleteProduct(@RequestBody Product product){
        productService.delete(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    private ResponseEntity<String> updateProduct(@RequestBody ModifyRequest request){
        return ResponseEntity.ok(productService.updateProduct(request));
    }

    @GetMapping(value = "/get/{id}")
    private ResponseEntity<Optional<Product>> listAllProduct(@PathVariable ("id") Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping(value = "/page/{number}")
    private Page<Product> getProductsPage(@PathVariable("number") int number,@RequestParam("size") int size){
        return productService.getProduct(number, size);
    }

    @GetMapping(value = "/page-select/{number}")
    private Page<Product> getProductsSelectPage(@PathVariable("number") int number,@RequestParam("size") int size,@RequestParam("peluche") Boolean peluche){
        return productService.getProductSelect(number, size, peluche);
    }

    @PostMapping("/buy")
    private ResponseEntity<String> buyProduct(@RequestBody BuyRequest request){
        return ResponseEntity.ok(productService.sellerByUserId(request));
    }

}
