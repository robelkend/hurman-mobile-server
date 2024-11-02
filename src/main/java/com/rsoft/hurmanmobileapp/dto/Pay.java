package com.rsoft.hurmanmobileapp.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class Pay  {
    private String payType;
    private String currencyId;
    private String currencySymbole;
    private BigDecimal baseSalary;
    private BigDecimal overtimeSalary;
    private BigDecimal absenceAmount;
    private BigDecimal lateAmount;
    private BigDecimal sanctionAmount;
    private BigDecimal totalTaxAmount;
    private BigInteger nbPresences;
    private BigDecimal overtimeHourSalary;
    private BigDecimal nbOvertimeHour;
    private BigDecimal overtimeOffSalary;
    private BigDecimal nbOvertimeOff;
    private BigDecimal overtimeNightSalary;
    private BigDecimal nbOvertimeNight;
    private BigDecimal overtimeVacationSalary;
    private BigDecimal nbOvertimeVacation;
    private BigDecimal otherFees;
    private BigDecimal grossSalary;
    private BigDecimal netSalary;
    private Date payDate;
    private Date beginDate;
    private List<Deduction> deductionList;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencySymbole() {
        return currencySymbole;
    }

    public void setCurrencySymbole(String currencySymbole) {
        this.currencySymbole = currencySymbole;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getOvertimeSalary() {
        return overtimeSalary;
    }

    public void setOvertimeSalary(BigDecimal overtimeSalary) {
        this.overtimeSalary = overtimeSalary;
    }

    public BigDecimal getAbsenceAmount() {
        return absenceAmount;
    }

    public void setAbsenceAmount(BigDecimal absenceAmount) {
        this.absenceAmount = absenceAmount;
    }

    public BigDecimal getLateAmount() {
        return lateAmount;
    }

    public void setLateAmount(BigDecimal lateAmount) {
        this.lateAmount = lateAmount;
    }

    public BigDecimal getSanctionAmount() {
        return sanctionAmount;
    }

    public void setSanctionAmount(BigDecimal sanctionAmount) {
        this.sanctionAmount = sanctionAmount;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public BigDecimal getOtherFees() {
        return otherFees;
    }

    public void setOtherFees(BigDecimal otherFees) {
        this.otherFees = otherFees;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public BigDecimal getOvertimeHourSalary() {
        return overtimeHourSalary;
    }

    public void setOvertimeHourSalary(BigDecimal overtimeHourSalary) {
        this.overtimeHourSalary = overtimeHourSalary;
    }

    public BigDecimal getNbOvertimeHour() {
        return nbOvertimeHour;
    }

    public void setNbOvertimeHour(BigDecimal nbOvertimeHour) {
        this.nbOvertimeHour = nbOvertimeHour;
    }

    public BigDecimal getOvertimeOffSalary() {
        return overtimeOffSalary;
    }

    public void setOvertimeOffSalary(BigDecimal overtimeOffSalary) {
        this.overtimeOffSalary = overtimeOffSalary;
    }

    public BigDecimal getNbOvertimeOff() {
        return nbOvertimeOff;
    }

    public void setNbOvertimeOff(BigDecimal nbOvertimeOff) {
        this.nbOvertimeOff = nbOvertimeOff;
    }

    public BigDecimal getOvertimeNightSalary() {
        return overtimeNightSalary;
    }

    public void setOvertimeNightSalary(BigDecimal overtimeNightSalary) {
        this.overtimeNightSalary = overtimeNightSalary;
    }

    public BigDecimal getNbOvertimeNight() {
        return nbOvertimeNight;
    }

    public void setNbOvertimeNight(BigDecimal nbOvertimeNight) {
        this.nbOvertimeNight = nbOvertimeNight;
    }

    public BigDecimal getOvertimeVacationSalary() {
        return overtimeVacationSalary;
    }

    public void setOvertimeVacationSalary(BigDecimal overtimeVacationSalary) {
        this.overtimeVacationSalary = overtimeVacationSalary;
    }

    public BigDecimal getNbOvertimeVacation() {
        return nbOvertimeVacation;
    }

    public void setNbOvertimeVacation(BigDecimal nbOvertimeVacation) {
        this.nbOvertimeVacation = nbOvertimeVacation;
    }

    public BigInteger getNbPresences() {
        return nbPresences;
    }

    public void setNbPresences(BigInteger nbPresences) {
        this.nbPresences = nbPresences;
    }

    public List<Deduction> getDeductionList() {
        return deductionList;
    }

    public void setDeductionList(List<Deduction> deductionList) {
        this.deductionList = deductionList;
    }

    public static class Deduction {
        private String code;
        private BigDecimal amount;
        private BigDecimal balance;
        private BigDecimal employerAmount;
        private BigDecimal percentage;
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }

        public BigDecimal getEmployerAmount() {
            return employerAmount;
        }

        public void setEmployerAmount(BigDecimal employerAmount) {
            this.employerAmount = employerAmount;
        }

        public BigDecimal getPercentage() {
            return percentage;
        }

        public void setPercentage(BigDecimal percentage) {
            this.percentage = percentage;
        }
    }
}