package com.pirata.rest.request;

import com.pirata.rest.model.Product;

public class ModifyRequest {
    private Product product;
    private SessionRequest request;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public SessionRequest getRequest() {
        return request;
    }
    public void setRequest(SessionRequest request) {
        this.request = request;
    }


}
