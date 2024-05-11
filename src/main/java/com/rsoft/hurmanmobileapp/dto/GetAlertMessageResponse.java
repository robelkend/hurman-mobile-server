package com.rsoft.hurmanmobileapp.dto;

import java.util.List;

public class GetAlertMessageResponse extends ReponseBase {
    private List<GetAlertMessage> alertMessages;

    public List<GetAlertMessage> getAlertMessages() {
        return alertMessages;
    }

    public void setAlertMessages(List<GetAlertMessage> alertMessages) {
        this.alertMessages = alertMessages;
    }
}
