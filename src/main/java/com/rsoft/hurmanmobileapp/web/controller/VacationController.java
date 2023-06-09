package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.*;
import com.rsoft.lib.Utilities;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/v1/mobile")
public class VacationController {
    @RequestMapping(value = "/GetVacations", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetVacationResponse getVacations(GetVacationRequest vacationRequest) throws JAXBException {
        GetVacationResponse response = new GetVacationResponse();
        response.setErrorCode("000");
        List<Vacation> vacations = new ArrayList<>();
        int r = new Random().nextInt(12);
        String typeConge;
        if (r < 4) {
            typeConge = "CONGE MALADIE";
        } else if (r < 8) {
            typeConge = "CONGE PATERNITE";
        } else {
            typeConge = "CONGE MATERNITE";
        }

        Vacation v1 = new Vacation();
        Calendar c1 = Calendar.getInstance();
        Utilities.resetCalendarTime(c1);
        c1.add(Calendar.DAY_OF_YEAR, 60);
        Calendar c2 = Calendar.getInstance();
        Utilities.resetCalendarTime(c2);
        c2.add(Calendar.DAY_OF_YEAR, 50);
        v1.setBeginDate(c1.getTime());
        v1.setEndDate(c2.getTime());
        v1.setStatus("FINALISE");
        v1.setBalance(new BigDecimal(7));
        v1.setType("CONGE ANNUEL");
        vacations.add(v1);

        Vacation v2 = new Vacation();
        Calendar c3 = Calendar.getInstance();
        Utilities.resetCalendarTime(c3);
        c1.add(Calendar.DAY_OF_YEAR, 30);
        Calendar c4 = Calendar.getInstance();
        Utilities.resetCalendarTime(c4);
        c2.add(Calendar.DAY_OF_YEAR, 30 - (r + 15));
        v2.setBeginDate(c3.getTime());
        v2.setEndDate(c4.getTime());
        v2.setStatus("ENCOURS");
        v2.setBalance(new BigDecimal(r));
        v2.setType(typeConge);
        vacations.add(v2);
        response.setVacationList(vacations);
        return response;
    }

    @RequestMapping(value = "/PlanVacations", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanVacationResponse planVacations(PlanVacationRequest vacationRequest) throws JAXBException {
        PlanVacationResponse response = new PlanVacationResponse();
        response.setErrorCode("000");
        return response;
    }
}
