package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.AlertMessageRequest;
import com.rsoft.hurmanmobileapp.dto.GetAlertMessageResponse;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.model.AlertMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AlertMessageServiceTest {

    @InjectMocks
    private AlertMessageService alertMessageService;
    @Mock
    private DefaultProxy defaultProxy;
    private AlertMessageRequest alertMessageRequest;
    @BeforeEach
    void setUp() {
        alertMessageRequest = new AlertMessageRequest();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAlertMessagesFromService_reponse_must_not_null() {
        //Arrange
        List<AlertMessage> alertMessages  = new ArrayList<>();
        AlertMessage a = new AlertMessage();
        a.setErrorCode("0001");
        alertMessages.add(a);
        alertMessageRequest.setCodeEmploye("000111");
        Mockito.doReturn(alertMessages).when(defaultProxy).getNotificationAlerts(any());
        //Act
        GetAlertMessageResponse getAlertMessageResponse = alertMessageService.getAlertMessagesFromService(alertMessageRequest);
        //Assert
        assertNotNull(getAlertMessageResponse);
        assertEquals("001", getAlertMessageResponse.getErrorCode());
    }

    @Test
    void getAlertMessagesFromService_reponse_values() {
        //Arrange
        List<AlertMessage> alertMessages  = new ArrayList<>();
        AlertMessage a1 = new AlertMessage();
        a1.setMessageCode("code_payroll");
        a1.setReference("PAYROLL=1");
        a1.setDescription("Un payroll");
        alertMessages.add(a1);
        AlertMessage a2 = new AlertMessage();
        a2.setMessageCode("code_tache");
        a2.setReference("CONGE=1");
        a2.setDescription("Un conge");
        alertMessages.add(a2);
        alertMessageRequest.setCodeEmploye("000111");
        Mockito.doReturn(alertMessages).when(defaultProxy).getNotificationAlerts(any());
        //Act
        GetAlertMessageResponse getAlertMessageResponse = alertMessageService.getAlertMessagesFromService(alertMessageRequest);
        //Assert
        assertNotNull(getAlertMessageResponse);
        assertNotNull(getAlertMessageResponse.getAlertMessages());
        assertEquals("000",getAlertMessageResponse.getErrorCode());
        assertEquals(2, getAlertMessageResponse.getAlertMessages().size());
        assertEquals("PAYROLL", getAlertMessageResponse.getAlertMessages().get(0).getMessageCategory());
        assertEquals("PAYROLL", getAlertMessageResponse.getAlertMessages().get(0).getMessageType());
        assertEquals("Un payroll", getAlertMessageResponse.getAlertMessages().get(0).getMessage());

        assertEquals("CONGE", getAlertMessageResponse.getAlertMessages().get(1).getMessageCategory());
        assertEquals("CONGE", getAlertMessageResponse.getAlertMessages().get(1).getMessageType());
        assertEquals("Un conge", getAlertMessageResponse.getAlertMessages().get(1).getMessage());
    }
}