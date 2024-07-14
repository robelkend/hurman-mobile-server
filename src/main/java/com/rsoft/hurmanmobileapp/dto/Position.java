package com.rsoft.hurmanmobileapp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Position implements Serializable {
    private Date beginDate;
    private Date endDate;
    private String description;
    private BigDecimal grossSalary;
    private BigDecimal overtimeRate;
    private Date lastPayDate;
    private Date nextPayDate;
    private String salaryBase;
    private String currency;
    private String dayOff1;
    private String dayOff2;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public String getSalaryBase() {
        return salaryBase;
    }

    public void setSalaryBase(String salaryBase) {
        this.salaryBase = salaryBase;
    }

    public String getDayOff1() {
        return dayOff1;
    }

    public void setDayOff1(String dayOff1) {
        this.dayOff1 = dayOff1;
    }

    public String getDayOff2() {
        return dayOff2;
    }

    public void setDayOff2(String dayOff2) {
        this.dayOff2 = dayOff2;
    }

    public BigDecimal getOvertimeRate() {
        return overtimeRate;
    }

    public void setOvertimeRate(BigDecimal overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public Date getLastPayDate() {
        return lastPayDate;
    }

    public void setLastPayDate(Date lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public Date getNextPayDate() {
        return nextPayDate;
    }

    public void setNextPayDate(Date nextPayDate) {
        this.nextPayDate = nextPayDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
