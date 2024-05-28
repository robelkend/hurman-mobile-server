package com.rsoft.hurmanmobileapp.dto;

import java.util.Date;

public class AuthenticationResponse extends ReponseBase {
    private String codeEmploye;
    private String firstName;
    private String lastName;
    private Date currentAccessTime;
    private Date lastAccessTime;
    private String language;
    private String enterpriseName;
    private String photo;
    private String jwt;
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCodeEmploye() {
        return codeEmploye;
    }

    public void setCodeEmploye(String codeEmploye) {
        this.codeEmploye = codeEmploye;
    }

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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
