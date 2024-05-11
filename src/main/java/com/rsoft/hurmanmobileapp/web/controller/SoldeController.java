package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.*;
import com.rsoft.hurmanmobileapp.service.PositionService;
import com.rsoft.hurmanmobileapp.service.SoldeService;
import com.rsoft.lib.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private SoldeService soldeService;
    @RequestMapping(value = "/GetSoldes", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetSoldeResponse getSoldes(GetSoldeRequest getSoldeRequest) throws JAXBException {
        return soldeService.getSoldeFromService(getSoldeRequest);
    }
}
