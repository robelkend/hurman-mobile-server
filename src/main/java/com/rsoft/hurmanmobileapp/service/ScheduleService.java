package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.GetCalendarRequest;
import com.rsoft.hurmanmobileapp.dto.GetCalendarResponse;
import com.rsoft.hurmanmobileapp.dto.Schedule;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.Utilities;
import com.rsoft.lib.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class ScheduleService {
    @Autowired
    private DefaultProxy proxy;

    public GetCalendarResponse getSchedules(GetCalendarRequest calendarRequest) throws JAXBException {
        GetCalendarResponse response = new GetCalendarResponse();
        response.setErrorCode("000");
        //Horaire de la semaine
        Calendar premiereDate = Calendar.getInstance();
        Utilities.resetCalendarTime(premiereDate);
        premiereDate.set(Calendar.DAY_OF_WEEK, premiereDate.getFirstDayOfWeek());

        Calendar derniereDate = Calendar.getInstance();
        // Set the calendar to the last day of the week
        derniereDate.add(Calendar.DAY_OF_WEEK, 6);

        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen(Utilities.SUPER_SCREEN);
        requestAttributes.setAgent(calendarRequest.getCodeEmploye());
        FilterWrapper filterWrapper = new FilterWrapper();
        requestAttributes.setField1(calendarRequest.getCodeEmploye());
        requestAttributes.setFilterWrapper(filterWrapper);
        List<HoraireDt> horaireDts = proxy.getHorairesEmploye(requestAttributes);
        if (!CollectionUtils.isEmpty(horaireDts)) {
            HoraireDt m1 = horaireDts.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                response.setErrorCode(m1.getErrorCode());
                response.setErrorMessage(m1.getErrorMessage());
                return response;
            }
        }

        List<Schedule> schedules = new ArrayList<>();
        requestAttributes.setField1(null);
        filterWrapper.addFilter(new XFilter("eq", "travailNocturnePk.codeEmploye", "string", calendarRequest.getCodeEmploye()));
        filterWrapper.addFilter(new XFilter("gt", "travailNocturnePk.dateJour", "date", premiereDate.getTime()));
        filterWrapper.addFilter(new XFilter("lt", "travailNocturnePk.dateJour", "date", derniereDate.getTime()));

        requestAttributes.setFilterWrapper(filterWrapper);
        List<OrderField> orderFields = new ArrayList<>();
        orderFields.add(new OrderField("travailNocturnePk.dateJour", OrderField.ORDER_DIR_ASC));
        requestAttributes.setOrderFields(orderFields);

        List<TravailNocturne> travailNocturnes = proxy.getTravailNocturnes(requestAttributes);
        if (!CollectionUtils.isEmpty(travailNocturnes)) {
            TravailNocturne m1 = travailNocturnes.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                response.setErrorCode(m1.getErrorCode());
                response.setErrorMessage(m1.getErrorMessage());
                return response;
            }
        }

        requestAttributes.setField1(null);
        filterWrapper = new FilterWrapper();
        filterWrapper.addFilter(new XFilter("eq", "codeEmploye", "string", calendarRequest.getCodeEmploye()));
        filterWrapper.addFilter(new XFilter("gt", "dateJour", "date", premiereDate.getTime()));
        filterWrapper.addFilter(new XFilter("lt", "dateJour", "date", derniereDate.getTime()));
        requestAttributes.setFilterWrapper(filterWrapper);
        orderFields = new ArrayList<>();
        orderFields.add(new OrderField("dateJour", OrderField.ORDER_DIR_ASC));
        orderFields.add(new OrderField("heureDepart", OrderField.ORDER_DIR_ASC));

        requestAttributes.setOrderFields(orderFields);
        List<Presence> presences = proxy.getPresences(requestAttributes);

        if (!CollectionUtils.isEmpty(presences)) {
            Presence p = presences.get(0);
            if (!StringUtils.isEmpty(p.getErrorCode())) {
                response.setErrorCode(p.getErrorCode());
                response.setErrorMessage(p.getErrorMessage());
                return response;
            }
        }


        List<Date> dates = new ArrayList<>();
        while (derniereDate.after(premiereDate) || derniereDate.equals(premiereDate)) {
            dates.add(Utilities.toDate(Utilities.dateToString(premiereDate.getTime())));
            premiereDate.add(Calendar.DAY_OF_YEAR, 1);
        }

        date:
        for (int i = 0; i < dates.size(); i++) {
            Date date = dates.get(i);
            boolean hasNigth = false;
            Schedule schedule = null;

            if (!CollectionUtils.isEmpty(travailNocturnes)) {
                travail_nocturne:
                for (TravailNocturne travailNocturne : travailNocturnes) {
                    if (travailNocturne.getDateJour().equals(date)) {
                        schedule = new Schedule();
                        schedule.setDateOfDay(travailNocturne.getDateJour());
                        schedule.setBeginHour(travailNocturne.getHeureDebut());
                        schedule.setEndHour(travailNocturne.getHeureFin());
                        hasNigth = true;
                        break travail_nocturne;
                    }
                }
            }
            if (!hasNigth && !CollectionUtils.isEmpty(horaireDts)) {
                horaire_dt:
                for (HoraireDt horaireDt : horaireDts) {
                    int jour = Integer.parseInt(horaireDt.getHoraireDtPk().getJour());
                    if (jour - 1 == i) {
                        schedule = new Schedule();
                        schedule.setDateOfDay(date);
                        schedule.setBeginHour(horaireDt.getHeureDebut());
                        schedule.setEndHour(horaireDt.getHeureFin());
                        break horaire_dt;
                    }
                }
            }
            if (!CollectionUtils.isEmpty(presences)) {
                for (Presence presence : presences) {
                    Calendar c = new GregorianCalendar();
                    c.setTime(presence.getDateJour());
                    int jour = c.get(Calendar.DAY_OF_WEEK);
                    if (jour - 1 == i) {
                        if (schedule == null) {
                            schedule = new Schedule();
                        }
                        schedule.setPresenceDepartureDate(presence.getDateDepart());
                        if (schedule.getPresenceBeginHour() == null) {
                            schedule.setPresenceBeginHour(presence.getHeureArrivee());
                        }
                        schedule.setPresenceEndHour(presence.getHeureDepart());
                    }
                }
            }
            if (schedule != null) {
                List<String> taks = new ArrayList<>();
                taks.add("Faire la vaiselle " + (i + 1));
                taks.add("Laver les voitures " + (i + 1));
                taks.add("Faire bouillir la marmite " + (i + 1));
                schedule.setTaskList(taks);

                schedules.add(schedule);
            }
        }
        response.setScheduleList(schedules);
        return response;
    }
}
