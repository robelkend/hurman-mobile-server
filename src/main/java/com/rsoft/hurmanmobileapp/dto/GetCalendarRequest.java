package com.rsoft.hurmanmobileapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GetCalendarRequest {
    private String codeEmploye;
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date beginDate;
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date endDate;

    public String getCodeEmploye() {
        return codeEmploye;
    }

    public void setCodeEmploye(String codeEmploye) {
        this.codeEmploye = codeEmploye;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
