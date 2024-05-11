package com.rsoft.hurmanmobileapp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class Solde implements Serializable {
    private Date soldeDate;
    private BigDecimal soldeAmount;
    private BigDecimal interestRate;
    private BigInteger disbursementNb;
    private BigDecimal disbursementAmount;
    private BigDecimal balance;
    private Date nextDisbursement;
    private String status;
    private String message;
    private String label;
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSoldeDate() {
        return soldeDate;
    }

    public void setSoldeDate(Date soldeDate) {
        this.soldeDate = soldeDate;
    }

    public BigDecimal getSoldeAmount() {
        return soldeAmount;
    }

    public void setSoldeAmount(BigDecimal soldeAmount) {
        this.soldeAmount = soldeAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigInteger getDisbursementNb() {
        return disbursementNb;
    }

    public void setDisbursementNb(BigInteger disbursementNb) {
        this.disbursementNb = disbursementNb;
    }

    public BigDecimal getDisbursementAmount() {
        return disbursementAmount;
    }

    public void setDisbursementAmount(BigDecimal disbursementAmount) {
        this.disbursementAmount = disbursementAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Date getNextDisbursement() {
        return nextDisbursement;
    }

    public void setNextDisbursement(Date nextDisbursement) {
        this.nextDisbursement = nextDisbursement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
