package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetPayRequest;
import com.rsoft.hurmanmobileapp.dto.GetPayResponse;
import com.rsoft.hurmanmobileapp.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/v1/mobile")
public class PayrollController {
    @Autowired
    private PayrollService payrollService;
    @RequestMapping(value = "/GetEmployeePays", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetPayResponse getEmployeePays(GetPayRequest payRequest) throws JAXBException {
        return payrollService.getEmployeePays(payRequest);
    }
}
