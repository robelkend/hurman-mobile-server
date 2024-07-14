package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetPositionRequest;
import com.rsoft.hurmanmobileapp.dto.GetPositionResponse;
import com.rsoft.hurmanmobileapp.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/v1/mobile")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @RequestMapping(value = "/GetEmployeePositions", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetPositionResponse getPositions(GetPositionRequest getPositionRequest) throws JAXBException {
        return positionService.getPositionFromService(getPositionRequest);
    }
}
