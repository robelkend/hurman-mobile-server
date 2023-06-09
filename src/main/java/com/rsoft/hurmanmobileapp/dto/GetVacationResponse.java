package com.rsoft.hurmanmobileapp.dto;

import java.util.List;

public class GetVacationResponse extends ReponseBase {
    private List<Vacation> vacationList;

    public List<Vacation> getVacationList() {
        return vacationList;
    }

    public void setVacationList(List<Vacation> vacationList) {
        this.vacationList = vacationList;
    }
}
