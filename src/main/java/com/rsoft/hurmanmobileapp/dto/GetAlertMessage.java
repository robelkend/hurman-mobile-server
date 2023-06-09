package com.rsoft.hurmanmobileapp.dto;

import java.io.Serializable;
import java.util.Date;

public class GetAlertMessage implements Serializable {
    private String messageCode;
    private String messageCategory;
    private String messageType;
    private String message;
    private Date startDateValidity;
    private Date endDateValidity;
    private String status;
    private String alertImage;
    public String getAlertImage() {
        return alertImage;
    }

    public void setAlertImage(String alertImage) {
        this.alertImage = alertImage;
    }

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStartDateValidity() {
        return startDateValidity;
    }

    public void setStartDateValidity(Date startDateValidity) {
        this.startDateValidity = startDateValidity;
    }

    public Date getEndDateValidity() {
        return endDateValidity;
    }

    public void setEndDateValidity(Date endDateValidity) {
        this.endDateValidity = endDateValidity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
