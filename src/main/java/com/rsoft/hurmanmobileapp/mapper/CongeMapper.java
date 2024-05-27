package com.rsoft.hurmanmobileapp.mapper;

import com.rsoft.hurmanmobileapp.dto.Vacation;
import com.rsoft.lib.model.Conge;

public class CongeMapper {
    private CongeMapper() {
    }

    public static Vacation congeToDTO(Conge conge) {
        Vacation vacation = new Vacation();
        vacation.setType(conge.getCongeId());
        vacation.setBalance(conge.getSoldeConge());
        vacation.setStatus(conge.getStatut());
        if (conge.getDateDepartReel() != null) {
            vacation.setBeginDate(conge.getDateDepartReel());
        } else {
            vacation.setBeginDate(conge.getDateDepart());
        }
        if (conge.getDateRetourReel() != null) {
            vacation.setEndDate(conge.getDateRetourReel());
        } else {
            vacation.setEndDate(conge.getDateRetour());
        }
        return vacation;
    }
}
