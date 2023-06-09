package com.rsoft.hurmanmobileapp.dto;

import java.math.BigInteger;

public class GetVacationRequest extends RequestBase {
    private String codeEmploye;
    private BigInteger year;

    public BigInteger getYear() {
        return year;
    }

    public void setYear(BigInteger year) {
        this.year = year;
    }

    public String getCodeEmploye() {
        return codeEmploye;
    }

    public void setCodeEmploye(String codeEmploye) {
        this.codeEmploye = codeEmploye;
    }
}
