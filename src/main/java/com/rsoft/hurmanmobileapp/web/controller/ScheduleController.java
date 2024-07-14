package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetCalendarRequest;
import com.rsoft.hurmanmobileapp.dto.GetCalendarResponse;
import com.rsoft.hurmanmobileapp.service.ScheduleService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rsoft.lib.*;
import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/v1/mobile")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    @RequestMapping(value = "/GetSchedules", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCalendarResponse getSchedules(GetCalendarRequest calendarRequest) throws JAXBException {
        logger.info("Retrieving schedules informations for employe / dates: {} / {} / {}", calendarRequest.getCodeEmploye(), Utilities.dateToString(calendarRequest.getBeginDate()), Utilities.dateToString(calendarRequest.getEndDate()));
        return scheduleService.getSchedules(calendarRequest);
    }
}
