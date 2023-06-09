package com.rsoft.hurmanmobileapp.dto;

import java.util.Date;

public class PlanVacationRequest extends RequestBase {
    private String type;
    private String codeEmploye;
    private Date beginDate;
    private Date endDate;
    private String memo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
