package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.*;
import com.rsoft.lib.Utilities;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/mobile")
public class AlertMessageController {
    @RequestMapping(value = "/GetAlerts", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetAlertMessageResponse authenticate(AlertMessageRequest authenticationRequest) throws JAXBException {
        GetAlertMessageResponse response = new GetAlertMessageResponse();
        response.setErrorCode("000");
        List<GetAlertMessage> alertMessages = new ArrayList<>();
        GetAlertMessage alert1 = new GetAlertMessage();
        alert1.setAlertImage(null);
        Calendar c = Calendar.getInstance();
        Utilities.resetCalendarTime(c);
        alert1.setStartDateValidity(c.getTime());
        alert1.setMessageCategory("anniversaire");
        alert1.setMessage("Bonne fête à toutes les mamans de la terre spécialement les mamans HAÏTIENNES.");
        alert1.setMessageCode("maman");
        alert1.setMessageType("ANNONCE");
        alertMessages.add(alert1);
        GetAlertMessage alert2 = new GetAlertMessage();
        alert2.setStartDateValidity(c.getTime());
        alert2.setMessageCategory("catastrophe");
        alert2.setMessage("L'Unité Hydrométéorologique d'Haïti (UHM) informe la population haïtienne ; les prestataires de service des produits de la météo ; les institutions étatiques ou gouvernementales que ce Jeudi 01 Juin marque l'ouverture officielle de la saison cyclonique de l'année 2023 et qui prendra fin jusqu'au 30 novembre dans le bassin Caribéen, l'Atlantique Nord et le Golfe du Mexique.");
        alertMessages.add(alert2);
        GetAlertMessage alert3 = new GetAlertMessage();
        alert3.setStartDateValidity(c.getTime());
        alert3.setMessageCategory("catastrophe");
        alert3.setMessage("Message important, à cause de la forte pluie qui s'est derversée sur la capitale hier soir, on decide de garder fermée le bureau central, vous prié de rester chez vous");
        alertMessages.add(alert3);
        GetAlertMessage alert4 = new GetAlertMessage();
        alert4.setStartDateValidity(c.getTime());
        Calendar d = Calendar.getInstance();
        d.add(Calendar.WEEK_OF_YEAR,1);
        alert4.setEndDateValidity(d.getTime());
        alert4.setStatus("ACTIF");
        alert4.setMessageCategory("sanction");
        alert4.setMessageCode("sanction");
        alert4.setMessage("Vous etes sanctionné, parceque vous etes trop gourmand");
        alertMessages.add(alert4);
        response.setAlertMessages(alertMessages);
        return response;
    }

    @RequestMapping(value = "/SendMessage", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SendMessageResponse sendMessage(SendMessageRequest vacationRequest) throws JAXBException {
        SendMessageResponse response = new SendMessageResponse();
        response.setErrorCode("000");
        return response;
    }

    @RequestMapping(value = "/GetMessagesType", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetMessageTypeResponse getMessagesType(GetMessageTypeRequest getMessageTypeRequest) throws JAXBException {
        GetMessageTypeResponse response = new GetMessageTypeResponse();
        response.setErrorCode("000");
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
