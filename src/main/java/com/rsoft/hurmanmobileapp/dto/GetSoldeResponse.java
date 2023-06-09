package com.rsoft.hurmanmobileapp.dto;

import java.util.List;

public class GetSoldeResponse extends ReponseBase{
    private List<Solde> soldeList;

    public List<Solde> getSoldeList() {
        return soldeList;
    }

    public void setSoldeList(List<Solde> soldeList) {
        this.soldeList = soldeList;
    }
}
