package com.rsoft.hurmanmobileapp.dto;

import java.util.List;

public class AlertMessageResponse extends ReponseBase{
    private List<AlertMessage> alertMessages;

    public List<AlertMessage> getAlertMessages() {
        return alertMessages;
    }

    public void setAlertMessages(List<AlertMessage> alertMessages) {
        this.alertMessages = alertMessages;
    }
}
