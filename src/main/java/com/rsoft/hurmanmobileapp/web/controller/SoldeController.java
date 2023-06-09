package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.*;
import com.rsoft.lib.Utilities;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/mobile")
public class SoldeController {
    @RequestMapping(value = "/GetSoldes", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetSoldeResponse getSoldes(GetSoldeRequest getSoldeRequest) throws JAXBException {
        GetSoldeResponse response = new GetSoldeResponse();
        response.setErrorCode("000");
        List<Solde> soldes = new ArrayList<>();
        Solde solde1 = new Solde();
        solde1.setBalance(new BigDecimal(13000));
        solde1.setSoldeAmount(new BigDecimal(13000));
        Calendar c2 = Calendar.getInstance();
        Utilities.resetCalendarTime(c2);
        solde1.setSoldeDate(c2.getTime());
        solde1.setDisbursementAmount(new BigDecimal(0));
        solde1.setMessage("Passez payer s'il vous plait");
        solde1.setInterestRate(BigDecimal.ZERO);
        solde1.setLabel("CAFETERIA");
        solde1.setDisbursementNb(BigInteger.ONE);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR,15);
        solde1.setNextDisbursement(c.getTime());
        soldes.add(solde1);


        Solde solde2 = new Solde();
        solde2.setBalance(new BigDecimal(35000));
        solde2.setSoldeAmount(new BigDecimal(50000));
        solde2.setSoldeDate(c2.getTime());
        solde2.setDisbursementAmount(new BigDecimal(5000));
        solde2.setMessage("Passez payer s'il vous plait");
        solde2.setInterestRate(BigDecimal.ZERO);
        solde2.setLabel("PRETONA");
        solde2.setDisbursementNb(BigInteger.TEN);
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_YEAR,15);
        solde1.setNextDisbursement(c.getTime());
        soldes.add(solde1);
        response.setSoldeList(soldes);
        return response;
    }
}
