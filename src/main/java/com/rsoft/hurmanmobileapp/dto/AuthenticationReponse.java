package com.rsoft.hurmanmobileapp.dto;

import java.util.Date;

public class AuthenticationReponse extends ReponseBase{
    private String firstName;
    private String lastName;
    private Date currentAccessTime;
    private Date lastAccessTime;
    private String language;
    private String enterpriseName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCurrentAccessTime() {
        return currentAccessTime;
    }

    public void setCurrentAccessTime(Date currentAccessTime) {
        this.currentAccessTime = currentAccessTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
