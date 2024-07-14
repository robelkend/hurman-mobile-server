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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private DefaultProxy proxy;

    public GetCalendarResponse getSchedules(GetCalendarRequest calendarRequest) throws JAXBException {
        GetCalendarResponse response = new GetCalendarResponse();
        response.setError("000");
        //Horaire de la semaine
        Calendar premiereDate = Calendar.getInstance();
        premiereDate.setTime(calendarRequest.getBeginDate());
        Utilities.resetCalendarTime(premiereDate);

        Calendar derniereDate = Calendar.getInstance();
        // Set the calendar to the last day of the week
        derniereDate.setTime(calendarRequest.getEndDate());

        Calendar dateCourante = Calendar.getInstance();
        dateCourante.setTime(calendarRequest.getBeginDate());
        Utilities.resetCalendarTime(dateCourante);

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
                response.setError(m1.getErrorCode());
                response.setMessage(m1.getErrorMessage());
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
                response.setError(m1.getErrorCode());
                response.setMessage(m1.getErrorMessage());
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
                response.setError(p.getErrorCode());
                response.setMessage(p.getErrorMessage());
                return response;
            }
        }

        filterWrapper = new FilterWrapper();
        filterWrapper.addFilter(new XFilter("gt", "dateDebut", "date", premiereDate.getTime()));
        filterWrapper.addFilter(new XFilter("lt", "dateFin", "date", derniereDate.getTime()));
        filterWrapper.addFilter(new XFilter("eq", "codeEmploye", "string", calendarRequest.getCodeEmploye()));
        requestAttributes.setFilterWrapper(filterWrapper);
        requestAttributes.setOrderFields(null);
        List<Taches> taches = proxy.getTaches(requestAttributes);
        if (!CollectionUtils.isEmpty(taches)) {
            Taches p = taches.get(0);
            if (!StringUtils.isEmpty(p.getErrorCode())) {
                response.setError(p.getErrorCode());
                response.setMessage(p.getErrorMessage());
                return response;
            }
        }
        List<Date> dates = new ArrayList<>();
        while (derniereDate.after(premiereDate) || derniereDate.equals(premiereDate)) {
            dates.add(Utilities.toDate(Utilities.dateToString(premiereDate.getTime())));
            premiereDate.add(Calendar.DAY_OF_YEAR, 1);
        }
        do {
            Date date = dateCourante.getTime();
            boolean hasNigth = false;
            Schedule schedule = new Schedule();
            if (!CollectionUtils.isEmpty(travailNocturnes)) {
                travail_nocturne:
                for (TravailNocturne travailNocturne : travailNocturnes) {
                    if (travailNocturne.getDateJour().equals(date)) {
                        schedule.setDateOfDay(date);
                        schedule.setNightShiftPlanningBeginHour(travailNocturne.getHeureDebut());
                        schedule.setNightShiftPlanningEndHour(travailNocturne.getHeureFin());
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
                    int dayOfWeek = dateCourante.get(Calendar.DAY_OF_WEEK);
                    if (jour == dayOfWeek) {
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
                    if (presence.getDateJour().equals(date)) {
                        schedule.setDateOfDay(date);
                        schedule.setPresenceDepartureDate(presence.getDateDepart());
                        if (schedule.getPresenceBeginHour() == null) {
                            schedule.setPresenceBeginHour(presence.getHeureArrivee());
                        }
                        schedule.setPresenceEndHour(presence.getHeureDepart());
                        break;
                    }
                }
            }
            if (!CollectionUtils.isEmpty(taches)) {
                for (Taches tache : taches) {
                    if (tache.getDateDebut().equals(date)) {
                        schedule.setDateOfDay(date);
                        schedule.addTask(tache.getDescription());
                    }
                }
            }
            if (schedule.getDateOfDay() != null) {
                schedules.add(schedule);
            }
            dateCourante.add(Calendar.DAY_OF_YEAR, 1);
        } while (dateCourante.before(derniereDate));
        response.setScheduleList(schedules);
        return response;
    }
}
