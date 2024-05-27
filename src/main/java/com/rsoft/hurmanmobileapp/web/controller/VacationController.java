package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetVacationRequest;
import com.rsoft.hurmanmobileapp.dto.GetVacationResponse;
import com.rsoft.hurmanmobileapp.dto.PlanVacationRequest;
import com.rsoft.hurmanmobileapp.dto.PlanVacationResponse;
import com.rsoft.hurmanmobileapp.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/v1/mobile")
public class VacationController {
    @Autowired
    private VacationService vacationService;

    @RequestMapping(value = "/GetVacations", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetVacationResponse getVacations(GetVacationRequest vacationRequest) throws JAXBException {
        return vacationService.getVacations(vacationRequest);
    }

    @RequestMapping(value = "/PlanVacations", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanVacationResponse planVacations(PlanVacationRequest vacationRequest) throws JAXBException {
        return vacationService.persistVacation(vacationRequest);
    }
}
