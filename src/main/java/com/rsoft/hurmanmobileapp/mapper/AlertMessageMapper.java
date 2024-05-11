package com.rsoft.hurmanmobileapp.mapper;

import com.rsoft.hurmanmobileapp.dto.GetAlertMessage;
import com.rsoft.lib.model.AlertMessage;

public class AlertMessageMapper {

    public static GetAlertMessage alertMessageToDTO(AlertMessage alertMessage) {
        GetAlertMessage message = new GetAlertMessage();
        message.setMessage(alertMessage.getDescription());
        message.setMessageCategory(alertMessage.getReference().substring(0, alertMessage.getReference().indexOf("=")).trim());
        message.setMessageType(alertMessage.getReference().substring(0, alertMessage.getReference().indexOf("=")).trim());
        message.setStatus(alertMessage.getStatus());
        message.setStartDateValidity(alertMessage.getCreatedOn());
        message.setEndDateValidity(alertMessage.getAlertDate());
        message.setAlertImage(null);
        return message;
    }
}