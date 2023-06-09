package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.GetCalendarRequest;
import com.rsoft.hurmanmobileapp.dto.GetCalendarResponse;
import com.rsoft.hurmanmobileapp.dto.Schedule;
import com.rsoft.lib.Utilities;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/v1/mobile")
public class ScheduleController {
    @RequestMapping(value = "/GetSchedules", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetCalendarResponse getSchedules(GetCalendarRequest calendarRequest) throws JAXBException {
        GetCalendarResponse response = new GetCalendarResponse();
        response.setErrorCode("000");
        int r = new Random().nextInt(10);
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            Schedule s2 = new Schedule();
            Calendar c2 = Calendar.getInstance();
            Utilities.resetCalendarTime(c2);
            c2.add(Calendar.DAY_OF_YEAR, -1 * (i));
            s2.setDateOfDay(c2.getTime());
            s2.setBeginHour("06:00");
            s2.setEndHour("14:00");
            List<String> taks = new ArrayList<>();
            taks.add("Faire la vaiselle " + (i + 1));
            taks.add("Laver les voitures " + (i + 1));
            taks.add("Faire bouillir la marmite " + (i + 1));
            s2.setTaskList(taks);
            schedules.add(s2);
        }
        response.setScheduleList(schedules);
        return response;
    }
}
