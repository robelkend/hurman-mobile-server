package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.*;
import com.rsoft.lib.Utilities;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/v1/mobile")
public class PayrollController {
    @RequestMapping(value = "/GetEmployeePays", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GetPayResponse getEmployeePays(GetPayRequest payRequest) throws JAXBException {
        GetPayResponse response = new GetPayResponse();
        int r = new Random().nextInt(4);
        List<Pay> payrolls = new ArrayList<>();
        for (int i = 1; i <= r; i++) {
            Pay pay = new Pay();
            response.setErrorCode("000");
            pay.setCurrencyId("HTG");
            Calendar c = Calendar.getInstance();
            Utilities.resetCalendarTime(c);
            c.add(Calendar.DAY_OF_WEEK, -1*(3*i));
            pay.setBeginDate(c.getTime());
            Calendar c1 = Calendar.getInstance();
            c.add(Calendar.DAY_OF_WEEK, -1*(2*i));
            Utilities.resetCalendarTime(c1);
            pay.setPayDate(c1.getTime());
            pay.setOtherFees(new BigDecimal(200));
            pay.setNetSalary(new BigDecimal(120000));
            pay.setOvertimeSalary(new BigDecimal(2000));
            pay.setPayType("WORKER1");
            pay.setGrossSalary(new BigDecimal(150000 + 200 + 50000 + 2000));
            pay.setCurrencySymbole("G");
            List<Pay.Deduction> deductionList = new ArrayList<>();
            Pay.Deduction d1 = new Pay.Deduction();
            d1.setCode("IRI");
            d1.setAmount(new BigDecimal(17000));
            d1.setBalance(BigDecimal.ZERO);
            deductionList.add(d1);
            Pay.Deduction d2 = new Pay.Deduction();
            d2.setCode("ONA");
            d2.setAmount(new BigDecimal(12000));
            d2.setBalance(BigDecimal.ZERO);
            deductionList.add(d2);

            Pay.Deduction d3 = new Pay.Deduction();
            d3.setCode("CFGCT");
            d3.setAmount(new BigDecimal(1345.34));
            d3.setBalance(BigDecimal.ZERO);
            deductionList.add(d3);

            Pay.Deduction d4 = new Pay.Deduction();
            d4.setCode("CAS");
            d4.setAmount(new BigDecimal(1234.64));
            d4.setBalance(BigDecimal.ZERO);
            deductionList.add(d4);

            Pay.Deduction d5 = new Pay.Deduction();
            d5.setCode("CAFETERIA");
            d5.setAmount(new BigDecimal(5670.45));
            d5.setBalance(BigDecimal.ZERO);
            deductionList.add(d5);

            Pay.Deduction d6 = new Pay.Deduction();
            d6.setCode("PRETONA");
            d6.setAmount(new BigDecimal(3670.45));
            d6.setBalance(BigDecimal.ZERO);
            deductionList.add(d6);

            pay.setDeductionList(deductionList);
            pay.setBaseSalary(new BigDecimal("150000"));
            payrolls.add(pay);
        }


        response.setPayList(payrolls);
        return response;
    }
}
