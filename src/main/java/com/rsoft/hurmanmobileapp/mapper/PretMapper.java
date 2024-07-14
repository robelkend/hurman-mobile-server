package com.rsoft.hurmanmobileapp.mapper;

import com.rsoft.hurmanmobileapp.dto.Solde;
import com.rsoft.lib.model.Pret;

public class PretMapper {
    private PretMapper() {
    }

    public static Solde salaireEmployeToDTO(Pret pret) {
        Solde solde = new Solde();
        solde.setDisbursementNb(pret.getNbPrevu());
        solde.setLabel(pret.getLibelle());
        solde.setSoldeDate(pret.getDatePret());
        solde.setMessage(pret.getNote());
        solde.setSoldeAmount(pret.getMontantPret());
        solde.setBalance(pret.getMontantPret().subtract(pret.getMontantVerse()));
        solde.setInterestRate(pret.getTauxInteret());
        solde.setDisbursementAmount(pret.getMontantPeriode());
        solde.setNextDisbursement(pret.getDatePremierPrelevement());
        solde.setCurrency(pret.getIdMonnaie());
        solde.setStatus(pret.getStatut());
        solde.setDisbursementNb(pret.getNbPrevu());
        return solde;
    }
}