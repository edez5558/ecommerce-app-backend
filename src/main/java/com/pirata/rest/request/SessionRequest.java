package com.pirata.rest.request;

public class SessionRequest {
    private long id;
    private String sessionId;

    public SessionRequest(long id, String sessionId) {
        this.id = id;
        this.sessionId = sessionId;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


}
