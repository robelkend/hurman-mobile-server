package com.rsoft.hurmanmobileapp.dto;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Schedule implements Serializable {
    private Date dateOfDay;
    private String beginHour;
    private String endHour;
    private Date presenceDepartureDate;
    private String presenceBeginHour;
    private String presenceEndHour;
    private String nightShiftPlanningBeginHour;
    private String nightShiftPlanningEndHour;

    public List<String> getTaskList() {
        return taskList;
    }

    public void addTask(String task) {
        if (!StringUtils.isBlank(task)) {
            if (taskList == null) {
                taskList = new ArrayList<>();
            }
            taskList.add(task);
        }
    }

    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }

    private List<String> taskList;

    public Date getDateOfDay() {
        return dateOfDay;
    }

    public void setDateOfDay(Date dateOfDay) {
        this.dateOfDay = dateOfDay;
    }

    public String getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(String beginHour) {
        this.beginHour = beginHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public Date getPresenceDepartureDate() {
        return presenceDepartureDate;
    }

    public void setPresenceDepartureDate(Date presenceDepartureDate) {
        this.presenceDepartureDate = presenceDepartureDate;
    }

    public String getPresenceBeginHour() {
        return presenceBeginHour;
    }

    public void setPresenceBeginHour(String presenceBeginHour) {
        this.presenceBeginHour = presenceBeginHour;
    }

    public String getPresenceEndHour() {
        return presenceEndHour;
    }

    public void setPresenceEndHour(String presenceEndHour) {
        this.presenceEndHour = presenceEndHour;
    }

    public String getNightShiftPlanningBeginHour() {
        return nightShiftPlanningBeginHour;
    }

    public void setNightShiftPlanningBeginHour(String nightShiftPlanningBeginHour) {
        this.nightShiftPlanningBeginHour = nightShiftPlanningBeginHour;
    }

    public String getNightShiftPlanningEndHour() {
        return nightShiftPlanningEndHour;
    }

    public void setNightShiftPlanningEndHour(String nightShiftPlanningEndHour) {
        this.nightShiftPlanningEndHour = nightShiftPlanningEndHour;
    }
}
