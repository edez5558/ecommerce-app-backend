package com.pirata.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pirata.rest.exceptions.ProductException;
import com.pirata.rest.exceptions.UserException;
import com.pirata.rest.model.Product;
import com.pirata.rest.model.User;
import com.pirata.rest.repository.ProductRepository;
import com.pirata.rest.request.BuyRequest;
import com.pirata.rest.request.ModifyRequest;
import com.pirata.rest.request.SessionRequest;


@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRespository;

    @Autowired
    private UserService userService;

    public Product create(Product product){
        return productRespository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRespository.findAllinStock();
    }

    public void delete(Product product){
        productRespository.delete(product);
    }

    public Optional<Product> findById(Long id){
        Optional<Product> product = productRespository.findById(id);
        System.out.println(product.get().getStock());
        return product;
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

    public String sellerByUserId(BuyRequest request){
        Optional<User> userTmp = userService.sessionVerify(new SessionRequest(request.getClientId(), request.getSessionId()));

        if(!userTmp.isPresent())
            throw new UserException("No se ha podido completar la compra");
        
        Optional<Product> productTmp = productRespository.findById(request.getProductId());

        if(!productTmp.isPresent())
            throw new ProductException("El producto no existe");
        
        Product product = productTmp.get();
        
        Long finalStock = product.getStock() - request.getAmount();

        if(finalStock < 0)
            throw new ProductException("Sin disponibilidad");
        
        productRespository.updateStockById(finalStock, request.getProductId());
    
        return "Compra completada";
    }

    public String updateProduct(ModifyRequest re){
        Product product = re.getProduct();
        SessionRequest request = re.getRequest();

        Optional<User> userTmp = userService.sessionVerify(new SessionRequest(request.getId(), request.getSessionId()));

        if(!userTmp.isPresent())
            throw new UserException("No se ha podido verificar la cuenta");
        
        Optional<Product> productTmp = productRespository.findById(product.getId());

        if(!productTmp.isPresent())
            throw new ProductException("El producto no existe");
        
        product.setClient(userTmp.get());
        
        productRespository.save(product);

        return "Modificacion completada";
    }
}
