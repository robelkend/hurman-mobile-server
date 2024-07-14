package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.*;
import com.rsoft.hurmanmobileapp.service.AlertMessageService;
import com.rsoft.lib.Utilities;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/mobile")
public class AlertMessageController {
    @Autowired
    private AlertMessageService alertMessageService;
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(AlertMessageController.class);
    @RequestMapping(value = "/GetAlerts", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetAlertMessageResponse getAlertMessages(AlertMessageRequest authenticationRequest) throws JAXBException {
        GetAlertMessageResponse r = new GetAlertMessageResponse();
        try {
            r = alertMessageService.getAlertMessages(authenticationRequest);
        } catch (RedisConnectionFailureException | QueryTimeoutException e) {
            r = alertMessageService.getAlertMessagesFromService(authenticationRequest);
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r);
        }
        return r;
    }

    @RequestMapping(value = "/SendMessage", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SendMessageResponse sendMessage(@RequestBody SendMessageRequest sendMessageRequest) throws JAXBException {
        logger.info("Sending messages informations for employe / dates: {} / {} / {}", sendMessageRequest.getCodeEmploye(), Utilities.dateToString(sendMessageRequest.getBeginDate()), Utilities.dateToString(sendMessageRequest.getEndDate()));
        return alertMessageService.addAlertMessages(sendMessageRequest);
    }

    @RequestMapping(value = "/GetMessagesType", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetMessageTypeResponse getMessagesType(GetMessageTypeRequest getMessageTypeRequest) throws JAXBException {
        GetMessageTypeResponse response = new GetMessageTypeResponse();
        response.setError("000");
        List<String> messagesType = new ArrayList<>();
        messagesType.add("CONGE ANNUEL");
        messagesType.add("CONGE MALADIE");
        messagesType.add("CONGE PATERNITE");
        messagesType.add("CONGE MATERNITE");
        messagesType.add("RETARD");
        messagesType.add("ABSENCE");
        messagesType.add("AUTRE");
        response.setMessageTypeList(messagesType);
        return response;
    }
}
