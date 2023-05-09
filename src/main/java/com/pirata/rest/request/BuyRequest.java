package com.pirata.rest.request;

public class BuyRequest {
    private Long clientId;
    private Long productId;
    private String sessionId;
    private long amount;

    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    public Long getClientId() {
        return clientId;
    }
    public void setClientId(Long clientdId) {
        this.clientId = clientdId;
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
