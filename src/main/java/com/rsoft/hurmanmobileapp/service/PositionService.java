package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.GetPositionRequest;
import com.rsoft.hurmanmobileapp.dto.GetPositionResponse;
import com.rsoft.hurmanmobileapp.dto.Position;
import com.rsoft.hurmanmobileapp.mapper.SalaireEmployeMapper;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.Utilities;
import com.rsoft.lib.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class PositionService {

    @Autowired
    private DefaultProxy proxy;

    public GetPositionResponse getPositionFromService(GetPositionRequest getPositionRequest) {
        GetPositionResponse r = new GetPositionResponse();
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen(Utilities.SUPER_SCREEN);
        requestAttributes.setAgent(getPositionRequest.getCodeEmploye());
        FilterWrapper filterWrapper = new FilterWrapper();
        filterWrapper.addFilter(new XFilter("eq", "codeEmploye", "string", getPositionRequest.getCodeEmploye()));
        filterWrapper.addFilter(new XFilter("eq", "actif", "string", "Y"));
        requestAttributes.setFilterWrapper(filterWrapper);
        List<OrderField> orderFields = new ArrayList<>();
        orderFields.add(new OrderField("dateStatut", OrderField.ORDER_DIR_DESC));
        requestAttributes.setOrderFields(orderFields);
        List<SalaireEmploye> salaireEmployes = proxy.getSalaireEmployes(requestAttributes);
        List<Position> positions = new ArrayList<>();
        r.setErrorCode("000");
        if (!CollectionUtils.isEmpty(salaireEmployes)) {
            SalaireEmploye m1 = salaireEmployes.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setErrorCode(m1.getErrorCode());
                r.setErrorMessage(m1.getErrorMessage());
            } else {
                for (SalaireEmploye m : salaireEmployes) {
                    RequestAttributes ra = new RequestAttributes();
                    ra.setScreen(Utilities.SUPER_SCREEN);
                    ra.setAgent(getPositionRequest.getCodeEmploye());
                    filterWrapper = new FilterWrapper();
                    filterWrapper.addFilter(new XFilter("eq", "codePoste", "string", m.getPosteEmploye().getCodeEmploye()));
                    requestAttributes.setFilterWrapper(filterWrapper);
                    List<Poste> poste = proxy.getPostes(ra);
                    Position position = SalaireEmployeMapper.salaireEmployeToDTO(m, poste.get(0).getDescription());
                    positions.add(position);
                }
                r.setPositionList(positions);
            }
        }
        return r;
    }
}
