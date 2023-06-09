package com.rsoft.hurmanmobileapp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Position implements Serializable {
    private Date beginDate;
    private Date endDate;
    private String description;
    private BigDecimal grossSalary;
    private String salaryBase;

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
}
