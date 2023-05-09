package com.pirata.rest.request;

public class BuyRequest {
    private Long clientdId;
    private Long productId;
    private String sessionId;
    private long amount;

    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    public Long getClientdId() {
        return clientdId;
    }
    public void setClientdId(Long clientdId) {
        this.clientdId = clientdId;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


}
