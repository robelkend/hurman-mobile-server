package com.rsoft.hurmanmobileapp.mapper;

import com.rsoft.hurmanmobileapp.dto.Position;
import com.rsoft.lib.model.SalaireEmploye;

import java.math.BigDecimal;

public class SalaireEmployeMapper {

    public static Position salaireEmployeToDTO(SalaireEmploye salaireEmploye, String poste) {
        Position position = new Position();
        position.setDescription(poste+"// "+salaireEmploye.getTypePayroll().getDescription());
        position.setEndDate(salaireEmploye.getPosteEmploye().getDateFin());
        position.setBeginDate(salaireEmploye.getPosteEmploye().getDateDebut());
        position.setSalaryBase("MENSUEL");
        if ("FIXE".equalsIgnoreCase(salaireEmploye.getTypePayroll().getBaseCalculSalaire())) {
            switch (salaireEmploye.getTypePayroll().getPeriodicite()) {
                case "HEBDOMADAIRE":
                    position.setGrossSalary(salaireEmploye.getMontant().multiply(new BigDecimal(4)));
                    break;
                case "QUINZAINE":
                    position.setGrossSalary(salaireEmploye.getMontant().multiply(new BigDecimal(26)).divide(new BigDecimal(12)));
                    break;
                case "QUINZOMADAIRE":
                case "BIMENSUEL":
                    position.setGrossSalary(salaireEmploye.getMontant().multiply(new BigDecimal(2)));
                    break;
                case "MENSUEL":
                    position.setGrossSalary(salaireEmploye.getMontant().multiply(new BigDecimal(1)));
                    break;
                case "TRIMESTRIEL":
                    position.setGrossSalary(salaireEmploye.getMontant().divide(new BigDecimal(3)));
                    break;
                case "SEMESTRIEL":
                    position.setGrossSalary(salaireEmploye.getMontant().divide(new BigDecimal(6)));
                    break;
                case "ANNUEL":
                    position.setGrossSalary(salaireEmploye.getMontant().divide(new BigDecimal(12)));
                    break;
                default:
                    position.setGrossSalary(salaireEmploye.getMontant().multiply(new BigDecimal(30)));
                    break;
            }

        } else if ("JOURNALIER".equalsIgnoreCase(salaireEmploye.getTypePayroll().getBaseCalculSalaire()) || "PIECE".equalsIgnoreCase(salaireEmploye.getTypePayroll().getBaseCalculSalaire()) || "PIECE-FIXE".equalsIgnoreCase(salaireEmploye.getTypePayroll().getBaseCalculSalaire())) {
            position.setGrossSalary(salaireEmploye.getMontant().multiply(new BigDecimal(30)));
        } else {
            //Horaire
        }
        return position;
    }
}