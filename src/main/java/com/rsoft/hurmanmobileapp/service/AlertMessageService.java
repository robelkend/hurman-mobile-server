package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.*;
import com.rsoft.hurmanmobileapp.mapper.AlertMessageMapper;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.Utilities;
import com.rsoft.lib.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class AlertMessageService {

    @Autowired
    private DefaultProxy proxy;

    //@Cacheable(value = "alertMessages", key = "#alertMessageRequest.codeEmploye", unless = "#result == null", cacheManager = "cacheManager")
    public GetAlertMessageResponse getAlertMessages(AlertMessageRequest alertMessageRequest) {
        return getAlertMessagesFromService(alertMessageRequest);
    }

    public GetAlertMessageResponse getAlertMessagesFromService(AlertMessageRequest alertMessageRequest) {
        GetAlertMessageResponse r = new GetAlertMessageResponse();
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen(Utilities.SUPER_SCREEN);
        requestAttributes.setAgent(alertMessageRequest.getCodeEmploye());
        FilterWrapper filterWrapper = new FilterWrapper();
        //Pas besoin de mettre de date ici, c est   limité  à  3 jours au niveau du serveur et malgré tout on en met, elles ne seront pas prises en compte
        requestAttributes.setFilterWrapper(filterWrapper);
        List<OrderField> orderFields = new ArrayList<>();
        orderFields.add(new OrderField("alertDate", OrderField.ORDER_DIR_DESC));
        requestAttributes.setOrderFields(orderFields);
        AlertMessage model = new AlertMessage();
        model.setCodeEmploye(alertMessageRequest.getCodeEmploye());
        requestAttributes.setModel(model);
        List<AlertMessage> alertMessages = proxy.getNotificationAlerts(requestAttributes);
        List<GetAlertMessage> getAlertMessage = new ArrayList<>();
        r.setError("000");
        if (!CollectionUtils.isEmpty(alertMessages)) {
            AlertMessage m1 = alertMessages.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setError(m1.getErrorCode());
                r.setMessage(m1.getErrorMessage());
            } else {
                for (AlertMessage m : alertMessages) {
                    GetAlertMessage message = AlertMessageMapper.alertMessageToDTO(m);
                    getAlertMessage.add(message);
                }
                r.setAlertMessages(getAlertMessage);
            }
        }
        return r;
    }

    public SendMessageResponse persistAlert(SendMessageRequest alertMessageRequest) {
        SendMessageResponse r = new SendMessageResponse();
        RequestAttributes<AlertMessage> requestAttributes = new RequestAttributes();
        requestAttributes.setScreen(Utilities.SUPER_SCREEN);
        requestAttributes.setAgent(alertMessageRequest.getCodeEmploye());
        //Pas besoin de mettre de date ici, c est   limité  à  3 jours au niveau du serveur et malgré tout on en met, elles ne seront pas prises en compte
        AlertMessage model = new AlertMessage();
        model.fireUpdatePendingChanged(true);
        model.setLineNo(0);
        model.setSTATUS("INSERT");
        model.setSource("MOBILE");
        model.setCodeEmploye(alertMessageRequest.getCodeEmploye());
        model.setAlertDate(new Date());
        model.setAgent(alertMessageRequest.getCodeEmploye());
        model.setDescription("Message de l'employé "+ alertMessageRequest.getCodeEmploye()+" ["+alertMessageRequest.getMessage()+"]");
        model.setReference("EMPLOYE="+alertMessageRequest.getCodeEmploye());
        //A remplir
        //model.setGroupId();
        ArrayList<AlertMessage> models = new ArrayList();
        models.add(model);
        requestAttributes.setModels(models);
        List<AlertMessage> alertMessages = proxy.persistAlertMessages(requestAttributes);
        r.setError("000");
        if (!CollectionUtils.isEmpty(alertMessages)) {
            AlertMessage m1 = alertMessages.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setError(m1.getErrorCode());
                r.setMessage(m1.getErrorMessage());
            }
        }
        return r;
    }

    public SendMessageResponse addAlertMessages(SendMessageRequest alertMessageRequest) {
        switch (alertMessageRequest.getType()) {
            case "CONGE MALADIE":
            case "CONGE PATERNITE":
            case "CONGE MATERNITE":
            case "CONGE ANNUEL":
                return persistVacation(alertMessageRequest);
            case "AUTRE":
                return persistAlert(alertMessageRequest);
            case "RETARD":
            case "ABSENCE":
            default:
                SendMessageResponse r = new SendMessageResponse();
                RequestAttributes requestAttributes = new RequestAttributes();
                requestAttributes.setScreen(Utilities.SUPER_SCREEN);
                requestAttributes.setAgent(alertMessageRequest.getCodeEmploye());
                AlertMessage m = new AlertMessage();
                m.setDescription(alertMessageRequest.getMessage());
                m.setMessageCode(alertMessageRequest.getType());
                m.setUserId(alertMessageRequest.getCodeEmploye());
                //m.setCodeEmploye(alertMessageRequest.getCodeEmploye());
                m.setAgent(alertMessageRequest.getCodeEmploye());
                m.setAlertDate(new Date());
                m.setSource("MOBILE");
                m.setSTATUS(Utilities.STATUS_INSERT);
                ArrayList<AlertMessage> messages = new ArrayList<>();
                messages.add(m);
                requestAttributes.setModels(messages);
                List<AlertMessage> alertMessages = proxy.persistAlertMessages(requestAttributes);
                r.setError("000");
                if (!CollectionUtils.isEmpty(alertMessages)) {
                    AlertMessage m1 = alertMessages.get(0);
                    if (!StringUtils.isEmpty(m1.getErrorCode())) {
                        r.setError(m1.getErrorCode());
                        r.setMessage(m1.getErrorMessage());
                    }
                }
                return r;
        }
    }

    private SendMessageResponse persistVacation(SendMessageRequest alertMessageRequest) {
        SendMessageResponse r = new SendMessageResponse();
        Conge c = new Conge();
        c.setDateDepart(alertMessageRequest.getBeginDate());
        c.setDateRetour(alertMessageRequest.getEndDate());
        c.setMemo(alertMessageRequest.getMessage());
        c.setCodeEmploye(alertMessageRequest.getCodeEmploye());
        c.setAgent(alertMessageRequest.getCodeEmploye());
        c.setSTATUS(Utilities.STATUS_INSERT);
        c.setSoldeConge(BigDecimal.ZERO);
        c.setJoursConge(BigDecimal.ZERO);
        c.setJoursEffectif(BigDecimal.ZERO);
        switch (alertMessageRequest.getType()) {
            case "CONGE MALADIE":
                c.setCongeId("CM");
                c.setCongePaye("Y");
                break;
            case "CONGE PATERNITE":
                c.setCongeId("CP");
                c.setCongePaye("Y");
                break;
            case "CONGE MATERNITE":
                c.setCongeId("CMD");
                c.setCongePaye("Y");
                break;
            default:
                RequestAttributes ra = new RequestAttributes();
                ra.setScreen(Utilities.SUPER_SCREEN);
                ra.setAgent(alertMessageRequest.getCodeEmploye());
                FilterWrapper filterWrapper = new FilterWrapper();
                filterWrapper.addFilter(new XFilter("eq", "codeEmploye", "string", alertMessageRequest.getCodeEmploye()));
                ra.setFilterWrapper(filterWrapper);
                TypeConge typeConge = proxy.getTypeCongeEmploye(ra);
                if (typeConge != null && typeConge.getErrorCode() == null) {
                    c.setCongeId(typeConge.getCongeId());
                    c.setCongePaye("Y");
                } else {
                    if (typeConge != null) {
                        r.setError(typeConge.getErrorCode());
                        r.setMessage(typeConge.getErrorMessage());
                    }
                    return r;
                }
        }
        ArrayList<Conge> conges = new ArrayList<>();
        conges.add(c);
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen(Utilities.SUPER_SCREEN);
        requestAttributes.setAgent(alertMessageRequest.getCodeEmploye());
        requestAttributes.setModels(conges);
        List<Conge> results = proxy.persistConges(requestAttributes);
        r.setError("000");
        if (!CollectionUtils.isEmpty(results)) {
            Conge m1 = results.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setError(m1.getErrorCode());
                r.setMessage(m1.getErrorMessage());
            }
        }
        return r;
    }
}
