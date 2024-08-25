package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetPositionRequest;
import com.rsoft.hurmanmobileapp.dto.GetPositionResponse;
import com.rsoft.hurmanmobileapp.dto.Position;
import com.rsoft.hurmanmobileapp.service.PositionService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/v1/mobile")
public class PositionController {
    @Autowired
    private PositionService positionService;
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(PositionController.class);

    @RequestMapping(value = "/GetEmployeePositions", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetPositionResponse getPositions(GetPositionRequest getPositionRequest) throws JAXBException {
        logger.info("Recupererations des postes de l'employé: {}", getPositionRequest.getCodeEmploye());
        GetPositionResponse r = positionService.getPositionFromService(getPositionRequest);
        if (r != null && !CollectionUtils.isEmpty(r.getPositionList())) {
            logger.info("Postes de l'employé {} recuperés", getPositionRequest.getCodeEmploye());
            for (Position position : r.getPositionList()) {
                logger.info("Postes de l'employé {} recuperés, Postes: {}, {}, {}, {}", getPositionRequest.getCodeEmploye(), position.getDescription(), position.getBeginDate(), position.getGrossSalary().doubleValue());
            }
        }
        return r;
    }
}
