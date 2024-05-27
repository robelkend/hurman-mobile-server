package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.*;
import com.rsoft.hurmanmobileapp.mapper.CongeMapper;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.Utilities;
import com.rsoft.lib.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class VacationService {

    @Autowired
    private DefaultProxy proxy;

    public GetVacationResponse getVacations(GetVacationRequest vacationRequest) {
        GetVacationResponse r = new GetVacationResponse();
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen(Utilities.SUPER_SCREEN);
        requestAttributes.setAgent(vacationRequest.getCodeEmploye());
        FilterWrapper filterWrapper = new FilterWrapper();
        filterWrapper.addFilter(new XFilter("eq", "congePk.codeEmploye", "string", vacationRequest.getCodeEmploye()));
        filterWrapper.addFilter(new XFilter("gt", "congePk.dateDepart", "date", "01/01/" + vacationRequest.getYear()));
        filterWrapper.addFilter(new XFilter("lt", "congePk.dateDepart", "date", "31/12/" + vacationRequest.getYear()));
        requestAttributes.setFilterWrapper(filterWrapper);
        List<OrderField> orderFields = new ArrayList<>();
        orderFields.add(new OrderField("congePk.dateDepart", OrderField.ORDER_DIR_DESC));
        requestAttributes.setOrderFields(orderFields);
        List<Conge> conges = proxy.getConges(requestAttributes);
        List<Vacation> vacationList = new ArrayList<>();
        r.setErrorCode("000");
        if (!CollectionUtils.isEmpty(conges)) {
            Conge m1 = conges.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setErrorCode(m1.getErrorCode());
                r.setErrorMessage(m1.getErrorMessage());
            } else {
                for (Conge m : conges) {
                    Vacation vacation = CongeMapper.congeToDTO(m);
                    if (m.getCongeId().startsWith("CA")) {
                        vacation.setType("CONGE ANNUEL");
                    } else if (m.getCongeId().equalsIgnoreCase("CM")) {
                        vacation.setType("CONGE MALADIE");
                    } else if (m.getCongeId().equalsIgnoreCase("CMD")) {
                        vacation.setType("CONGE MATERNITE");
                    } else if (m.getCongeId().equalsIgnoreCase("CP")) {
                        vacation.setType("CONGE PATERNITE");
                    }
                    vacationList.add(vacation);
                }
                r.setVacationList(vacationList);
            }
        }
        return r;
    }

    public PlanVacationResponse persistVacation(PlanVacationRequest vacationRequest) {
        PlanVacationResponse r = new PlanVacationResponse();
        Conge c = new Conge();
        c.setDateDepart(vacationRequest.getBeginDate());
        c.setDateRetour(vacationRequest.getEndDate());
        c.setMemo(vacationRequest.getMemo());
        c.setCodeEmploye(vacationRequest.getCodeEmploye());
        c.setAgent(vacationRequest.getCodeEmploye());
        c.setSTATUS(Utilities.STATUS_INSERT);
        c.setSoldeConge(BigDecimal.ZERO);
        c.setJoursConge(BigDecimal.ZERO);
        c.setJoursEffectif(BigDecimal.ZERO);
        switch (vacationRequest.getType()) {
            case "CONGE MALADIE":
                c.setCongeId("CM");
                c.setCongePaye("Y");
                break;
            case "CONGE PATERNITE":
                c.setCongeId("CP");
                c.setCongePaye("Y");
                break;
            case "CONGE MATERNITE":
                c.setCongeId("CMD");
                c.setCongePaye("Y");
                break;
            default:
                RequestAttributes ra = new RequestAttributes();
                ra.setScreen(Utilities.SUPER_SCREEN);
                ra.setAgent(vacationRequest.getCodeEmploye());
                FilterWrapper filterWrapper = new FilterWrapper();
                filterWrapper.addFilter(new XFilter("eq", "codeEmploye", "string", vacationRequest.getCodeEmploye()));
                ra.setFilterWrapper(filterWrapper);
                TypeConge typeConge = proxy.getTypeCongeEmploye(ra);
                if (typeConge != null && typeConge.getErrorCode() == null) {
                    c.setCongeId(typeConge.getCongeId());
                    c.setCongePaye("Y");
                } else {
                    if (typeConge != null) {
                        r.setErrorCode(typeConge.getErrorCode());
                        r.setErrorMessage(typeConge.getErrorMessage());
                    }
                    return r;
                }
        }
        ArrayList<Conge> conges = new ArrayList<>();
        conges.add(c);
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen(Utilities.SUPER_SCREEN);
        requestAttributes.setAgent(vacationRequest.getCodeEmploye());
        requestAttributes.setModels(conges);
        List<Conge> results = proxy.persistConges(requestAttributes);
        r.setErrorCode("000");
        if (!CollectionUtils.isEmpty(results)) {
            Conge m1 = results.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setErrorCode(m1.getErrorCode());
                r.setErrorMessage(m1.getErrorMessage());
            }
        }
        return r;
    }
}
