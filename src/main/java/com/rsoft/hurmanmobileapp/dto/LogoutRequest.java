package com.rsoft.hurmanmobileapp.dto;

public class LogoutRequest extends RequestBase{
    private String enterprise;
    private String userId;

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
