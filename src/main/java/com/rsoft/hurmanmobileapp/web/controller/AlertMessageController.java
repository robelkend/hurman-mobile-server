package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.AlertMessage;
import com.rsoft.hurmanmobileapp.dto.AlertMessageRequest;
import com.rsoft.hurmanmobileapp.dto.AlertMessageResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.JAXBException;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AlertMessageController {
    @RequestMapping(value = "/GetAlerts", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AlertMessageResponse authenticate(AlertMessageRequest authenticationRequest) throws JAXBException {
        AlertMessageResponse response = new AlertMessageResponse();
        List<AlertMessage> alertMessages = new ArrayList<>();
        AlertMessage alert1 = new AlertMessage();

        alert1.setStartDateValidity(new Date());
        alert1.setMessageCategory("anniversaire");
        alert1.setMessage("Bonne fête à toutes les mamans de la terre spécialement les mamans HAÏTIENNES.");
        alert1.setMessageCode("maman");
        alert1.setMessageType("ANNONCE");
        alertMessages.add(alert1);
        AlertMessage alert2 = new AlertMessage();
        alert2.setStartDateValidity(new Date());
        alert2.setMessageCategory("catastrophe");
        alert2.setMessage("L'Unité Hydrométéorologique d'Haïti (UHM) informe la population haïtienne ; les prestataires de service des produits de la météo ; les institutions étatiques ou gouvernementales que ce Jeudi 01 Juin marque l'ouverture officielle de la saison cyclonique de l'année 2023 et qui prendra fin jusqu'au 30 novembre dans le bassin Caribéen, l'Atlantique Nord et le Golfe du Mexique.");
        alertMessages.add(alert2);
        AlertMessage alert3 = new AlertMessage();
        alert3.setStartDateValidity(new Date());
        alert3.setMessageCategory("catastrophe");
        alert3.setMessage("Message important, à cause de la forte pluie qui s'est derversée sur la capitale hier soir, on decide de garder fermée le bureau central, vous prié de rester chez vous");
        alertMessages.add(alert3);
        AlertMessage alert4 = new AlertMessage();
        alert4.setStartDateValidity(new Date());
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
}
