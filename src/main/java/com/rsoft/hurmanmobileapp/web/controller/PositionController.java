package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetPositionRequest;
import com.rsoft.hurmanmobileapp.dto.GetPositionResponse;
import com.rsoft.hurmanmobileapp.dto.Position;
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

@RestController
@RequestMapping(value = "/v1/mobile")
public class PositionController {

    @RestController
    @RequestMapping(value = "/v1/mobile")
    public class SoldeController {
        @RequestMapping(value = "/GetEmployeePositions", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public GetPositionResponse getPositions(GetPositionRequest getPositionRequest) throws JAXBException {
            GetPositionResponse response = new GetPositionResponse();
            response.setErrorCode("000");
            List<Position> positions = new ArrayList<>();
            Position p2 = new Position();
            p2.setDescription("LOREM IPSUM IS SIMPLY");
            Calendar c1 = Calendar.getInstance();
            Utilities.resetCalendarTime(c1);
            p2.setBeginDate(c1.getTime());
            p2.setEndDate(c1.getTime());
            p2.setSalaryBase("QUINZAINE");
            p2.setGrossSalary(new BigDecimal("15000"));
            positions.add(p2);

            Position p1 = new Position();
            p1.setDescription("DIRECTEUR DES RESSOURCES HUMAINES");
            p1.setBeginDate(c1.getTime());
            p1.setEndDate(null);
            p1.setSalaryBase("MENSUEL");
            p1.setGrossSalary(new BigDecimal("150000"));
            positions.add(p1);
            response.setPositionList(positions);
            return response;
        }
    }
}
