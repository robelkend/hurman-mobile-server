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
        filterWrapper.addFilter(new XFilter("eq", "codeEmploye", "string", alertMessageRequest.getCodeEmploye()));
        requestAttributes.setFilterWrapper(filterWrapper);
        List<OrderField> orderFields = new ArrayList<>();
        orderFields.add(new OrderField("alertDate", OrderField.ORDER_DIR_DESC));
        requestAttributes.setOrderFields(orderFields);
        List<AlertMessage> alertMessages = proxy.getNotificationAlerts(requestAttributes);
        List<GetAlertMessage> getAlertMessage = new ArrayList<>();
        r.setErrorCode("000");
        if (!CollectionUtils.isEmpty(alertMessages)) {
            AlertMessage m1 = alertMessages.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setErrorCode(m1.getErrorCode());
                r.setErrorMessage(m1.getErrorMessage());
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

    public SendMessageResponse addAlertMessages(SendMessageRequest alertMessageRequest) {
        switch (alertMessageRequest.getType()) {
            case "CONGE MALADIE":
            case "CONGE PATERNITE":
            case "CONGE MATERNITE":
            case "CONGE ANNUEL":
                return persistVacation(alertMessageRequest);
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
                m.setCodeEmploye(alertMessageRequest.getCodeEmploye());
                m.setAgent(alertMessageRequest.getCodeEmploye());
                m.setAlertDate(new Date());
                m.setSTATUS(Utilities.STATUS_INSERT);
                ArrayList<AlertMessage> messages = new ArrayList<>();
                messages.add(m);
                requestAttributes.setModels(messages);
                List<AlertMessage> alertMessages = proxy.persistAlertMessages(requestAttributes);
                r.setErrorCode("000");
                if (!CollectionUtils.isEmpty(alertMessages)) {
                    AlertMessage m1 = alertMessages.get(0);
                    if (!StringUtils.isEmpty(m1.getErrorCode())) {
                        r.setErrorCode(m1.getErrorCode());
                        r.setErrorMessage(m1.getErrorMessage());
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
                        r.setErrorCode(typeConge.getErrorCode());
                        r.setErrorMessage(typeConge.getErrorMessage());
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
        r.setErrorCode("000");
        if (!CollectionUtils.isEmpty(results)) {
            Conge m1 = results.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setErrorCode(m1.getErrorCode());
                r.setErrorMessage(m1.getErrorMessage());
            }
        }
        return r;
    }
}
