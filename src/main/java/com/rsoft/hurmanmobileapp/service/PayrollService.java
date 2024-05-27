package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.GetPayRequest;
import com.rsoft.hurmanmobileapp.dto.GetPayResponse;
import com.rsoft.hurmanmobileapp.dto.Pay;
import com.rsoft.hurmanmobileapp.dto.Position;
import com.rsoft.hurmanmobileapp.mapper.PayrollDtMapper;
import com.rsoft.hurmanmobileapp.mapper.SalaireEmployeMapper;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.Utilities;
import com.rsoft.lib.model.FilterWrapper;
import com.rsoft.lib.model.PayrollDt;
import com.rsoft.lib.model.Poste;
import com.rsoft.lib.model.SalaireEmploye;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayrollService {
    @Autowired
    private DefaultProxy proxy;

    public GetPayResponse getEmployeePays(GetPayRequest payRequest) throws JAXBException {
        GetPayResponse r = new GetPayResponse();
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen(Utilities.SUPER_SCREEN);
        requestAttributes.setAgent(payRequest.getCodeEmploye());
        requestAttributes.setField1(payRequest.getCodeEmploye());
        String dateSuffix = "/" + payRequest.getMonth() + "/" + payRequest.getYear();
        requestAttributes.setField2("01" + dateSuffix);
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(payRequest.getYear()), Integer.parseInt(payRequest.getMonth()));
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
        requestAttributes.setField3(lastDayOfMonth.getDayOfMonth() + dateSuffix);
        List<PayrollDt> payrollDts = proxy.getPayrollsEmployee(requestAttributes);
        List<Pay> payList = new ArrayList<>();
        r.setErrorCode("000");
        if (!CollectionUtils.isEmpty(payrollDts)) {
            PayrollDt m1 = payrollDts.get(0);
            if (!StringUtils.isEmpty(m1.getErrorCode())) {
                r.setErrorCode(m1.getErrorCode());
                r.setErrorMessage(m1.getErrorMessage());
            } else {
                for (PayrollDt m : payrollDts) {
                    Pay pay = PayrollDtMapper.payrollDtToDTO(m);
                    payList.add(pay);
                }
            }
            r.setPayList(payList);
        }
        return r;
    }
}
