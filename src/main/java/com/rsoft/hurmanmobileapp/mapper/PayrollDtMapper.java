package com.rsoft.hurmanmobileapp.mapper;

import com.rsoft.hurmanmobileapp.dto.Pay;
import com.rsoft.lib.Utilities;
import com.rsoft.lib.model.PayrollDeduction;
import com.rsoft.lib.model.PayrollDt;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class PayrollDtMapper {
    private PayrollDtMapper() {
    }

    public static Pay payrollDtToDTO(PayrollDt payrollDt) {
        Pay pay = null;
        if (payrollDt != null) {
            pay = new Pay();
            pay.setBaseSalary(payrollDt.getSalairePeriode());
            pay.setPayType(payrollDt.getPayroll().getTypePayrollId());
            pay.setCurrencyId(payrollDt.getPayroll().getIdMonnaie());
            pay.setCurrencySymbole("$");
            pay.setOvertimeSalary(payrollDt.getSalaireSupplementaire());
            pay.setOtherFees(payrollDt.getMontantAutreRevenu().add(payrollDt.getMontantFrais()));
            pay.setGrossSalary(getMontantBrut(payrollDt));
            pay.setAbsenceAmount(payrollDt.getMontantAbsence());
            pay.setLateAmount(payrollDt.getMontantRetard());
            pay.setSanctionAmount(payrollDt.getMontantSanction());
            pay.setNetSalary(payrollDt.getSalaireNet());
            pay.setTotalTaxAmount(payrollDt.getTotalPrelevement().add(payrollDt.getMontantSanction()).add(payrollDt.getMontantRetard()).add(payrollDt.getMontantAbsence()).add(payrollDt.getMontantDepenseEmploye()));
            pay.setNbPresences(payrollDt.getNbPresences());
            pay.setOvertimeVacationSalary(payrollDt.getCongeSupplementaire());
            pay.setNbOvertimeVacation(payrollDt.getNbCongeSupplementaire());
            pay.setOvertimeNightSalary(payrollDt.getSoirSupplementaire());
            pay.setNbOvertimeNight(payrollDt.getNbSoirSupplementaire());
            pay.setOvertimeOffSalary(payrollDt.getJourOffSupplementaire());
            pay.setNbOvertimeOff(payrollDt.getNbJourOffSupplementaire());
            pay.setOvertimeHourSalary(payrollDt.getHeureSupplementaire());
            pay.setNbOvertimeHour(payrollDt.getNbHeureSupplementaire());

            Calendar c1 = new GregorianCalendar();
            c1.setTime(payrollDt.getPayroll().getDatePayroll());
            Utilities.resetCalendarTime(c1);
            pay.setPayDate(c1.getTime());
            Calendar c2 = new GregorianCalendar();
            c2.setTime(payrollDt.getPayroll().getDateDebut());
            Utilities.resetCalendarTime(c2);
            pay.setBeginDate(c2.getTime());
            List<Pay.Deduction> deductionList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(payrollDt.getPayrollDeductions())) {
                for (PayrollDeduction pd : payrollDt.getPayrollDeductions()) {
                    Pay.Deduction deduction = payrollDeductionToDTO(pd);
                    if (deduction != null) {
                        deductionList.add(deduction);
                    }
                }
            }
            if (payrollDt.getMontantRetard() != null && payrollDt.getMontantRetard().doubleValue() > 0) {
                Pay.Deduction d = new Pay.Deduction();
                d.setCode("late");
                d.setAmount(payrollDt.getMontantRetard());
                deductionList.add(d);
            }
            if (payrollDt.getMontantAbsence() != null && payrollDt.getMontantAbsence().doubleValue() > 0) {
                Pay.Deduction d = new Pay.Deduction();
                d.setCode("absence");
                d.setAmount(payrollDt.getMontantAbsence());
                deductionList.add(d);
            }
            if (payrollDt.getMontantSanction() != null && payrollDt.getMontantSanction().doubleValue() > 0) {
                Pay.Deduction d = new Pay.Deduction();
                d.setCode("sanction");
                d.setAmount(payrollDt.getMontantSanction());
                deductionList.add(d);
            }
            if (payrollDt.getMontantDepenseEmploye() != null && payrollDt.getMontantDepenseEmploye().doubleValue() > 0) {
                Pay.Deduction d = new Pay.Deduction();
                d.setCode("Refund");
                d.setAmount(payrollDt.getMontantDepenseEmploye());
                deductionList.add(d);
            }
            if (!CollectionUtils.isEmpty(deductionList)) {
                pay.setDeductionList(deductionList);
            }
        }
        return pay;
    }

    private static Pay.Deduction payrollDeductionToDTO(PayrollDeduction payrollDeduction) {
        Pay.Deduction deduction = null;
        if (payrollDeduction != null && payrollDeduction.getMontant().doubleValue() > 0) {
            deduction = new Pay.Deduction();
            deduction.setCode(payrollDeduction.getDeductionId());
            deduction.setAmount(payrollDeduction.getMontant());
            deduction.setBalance(payrollDeduction.getBalance());
        }
        return deduction;
    }

    private static BigDecimal getMontantBrut(PayrollDt payrollDt) {
        BigDecimal salaireNet = BigDecimal.ZERO;
        if (payrollDt.getSalairePeriode() != null) {
            salaireNet = salaireNet.add(payrollDt.getSalairePeriode());
        }
        if (payrollDt.getSalaireSupplementaire() != null) {
            salaireNet = salaireNet.add(payrollDt.getSalaireSupplementaire());
        }
        if (payrollDt.getMontantAutreRevenu() != null) {
            salaireNet = salaireNet.add(payrollDt.getMontantAutreRevenu());
        }
        if (payrollDt.getMontantFrais() != null) {
            salaireNet = salaireNet.add(payrollDt.getMontantFrais());
        }
        if (payrollDt.getReliquat() != null) {
            salaireNet = salaireNet.add(payrollDt.getReliquat());
        }
        if (payrollDt.getMontantCommission() != null) {
            salaireNet = salaireNet.add(payrollDt.getMontantCommission());
        }
        return salaireNet;
    }
}
