package com.rsoft.hurmanmobileapp.dto;

public class GetMessageTypeRequest extends RequestBase{
    private String codeEmploye;

    public String getCodeEmploye() {
        return codeEmploye;
    }

    public void setCodeEmploye(String codeEmploye) {
        this.codeEmploye = codeEmploye;
    }
}
