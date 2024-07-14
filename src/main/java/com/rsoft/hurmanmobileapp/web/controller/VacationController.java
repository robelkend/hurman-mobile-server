package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetVacationRequest;
import com.rsoft.hurmanmobileapp.dto.GetVacationResponse;
import com.rsoft.hurmanmobileapp.dto.PlanVacationRequest;
import com.rsoft.hurmanmobileapp.dto.PlanVacationResponse;
import com.rsoft.hurmanmobileapp.service.VacationService;
import com.rsoft.lib.Utilities;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/mobile")
public class VacationController {
    @Autowired
    private VacationService vacationService;
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(VacationController.class);
    @RequestMapping(value = "/GetVacations", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetVacationResponse getVacations(GetVacationRequest vacationRequest) {
        logger.info("Getting vacation informations for employe / dates: {} / {}", vacationRequest.getCodeEmploye(), vacationRequest.getYear());
        return vacationService.getVacations(vacationRequest);
    }

    @RequestMapping(value = "/PlanVacations", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanVacationResponse planVacations(@RequestBody PlanVacationRequest vacationRequest) {
        logger.info("Planning vacation informations for employe / dates: {} / {} / {}", vacationRequest.getCodeEmploye(), Utilities.dateToString(vacationRequest.getBeginDate()), Utilities.dateToString(vacationRequest.getEndDate()));
        return vacationService.persistVacation(vacationRequest);
    }
}
