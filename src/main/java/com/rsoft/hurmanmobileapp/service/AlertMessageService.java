package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.AlertMessageRequest;
import com.rsoft.hurmanmobileapp.dto.GetAlertMessage;
import com.rsoft.hurmanmobileapp.dto.GetAlertMessageResponse;
import com.rsoft.hurmanmobileapp.mapper.AlertMessageMapper;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.Utilities;
import com.rsoft.lib.model.AlertMessage;
import com.rsoft.lib.model.FilterWrapper;
import com.rsoft.lib.model.OrderField;
import com.rsoft.lib.model.XFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
        List<AlertMessage> alertMessages = proxy.getAlertMessages(requestAttributes);
        List<GetAlertMessage> getAlertMessage = new ArrayList<>();
        r.setErrorCode("000");
        if (!CollectionUtils.isEmpty(alertMessages)) {
            AlertMessage m1 = alertMessages.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setErrorCode("001");
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
}
