package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetSoldeRequest;
import com.rsoft.hurmanmobileapp.dto.GetSoldeResponse;
import com.rsoft.hurmanmobileapp.service.SoldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

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
