package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.GetSoldeRequest;
import com.rsoft.hurmanmobileapp.dto.GetSoldeResponse;
import com.rsoft.hurmanmobileapp.dto.Solde;
import com.rsoft.hurmanmobileapp.mapper.PretMapper;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.Utilities;
import com.rsoft.lib.model.FilterWrapper;
import com.rsoft.lib.model.OrderField;
import com.rsoft.lib.model.Pret;
import com.rsoft.lib.model.XFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class SoldeService {

    @Autowired
    private DefaultProxy proxy;

    public GetSoldeResponse getSoldeFromService(GetSoldeRequest getSoldeRequest) {
        GetSoldeResponse r = new GetSoldeResponse();
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen(Utilities.SUPER_SCREEN);
        requestAttributes.setAgent(getSoldeRequest.getCodeEmploye());
        FilterWrapper filterWrapper = new FilterWrapper();
        filterWrapper.addFilter(new XFilter("eq", "codeEmploye", "string", getSoldeRequest.getCodeEmploye()));
        filterWrapper.addFilter(new XFilter("eq", "statut", "string", "ACTIF"));
        requestAttributes.setFilterWrapper(filterWrapper);
        List<OrderField> orderFields = new ArrayList<>();
        orderFields.add(new OrderField("datePret", OrderField.ORDER_DIR_DESC));
        requestAttributes.setOrderFields(orderFields);
        List<Pret> prets = proxy.getPrets(requestAttributes);
        List<Solde> soldes = new ArrayList<>();
        r.setError("000");
        if (!CollectionUtils.isEmpty(prets)) {
            Pret m1 = prets.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setError(m1.getErrorCode());
                r.setMessage(m1.getErrorMessage());
            } else {
                for (Pret p : prets) {
                    Solde solde = PretMapper.salaireEmployeToDTO(p);
                    soldes.add(solde);
                }
                r.setSoldeList(soldes);
            }
        }
        return r;
    }
}
