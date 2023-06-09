package com.rsoft.hurmanmobileapp.dto;

import java.util.Date;

public class SendMessageRequest extends RequestBase{
    private String codeEmploye;
    private Date beginDate;
    private Date endDate;
    private String message;
    private String type;

    public String getCodeEmploye() {
        return codeEmploye;
    }

    public void setCodeEmploye(String codeEmploye) {
        this.codeEmploye = codeEmploye;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
