package com.rsoft.hurmanmobileapp.dto;

import java.util.List;

public class GetCalendarResponse  extends ReponseBase{
    private List<Schedule> scheduleList;

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
