package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetCalendarRequest;
import com.rsoft.hurmanmobileapp.dto.GetCalendarResponse;
import com.rsoft.hurmanmobileapp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/v1/mobile")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/GetSchedules", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCalendarResponse getSchedules(GetCalendarRequest calendarRequest) throws JAXBException {
        return scheduleService.getSchedules(calendarRequest);
    }
}
