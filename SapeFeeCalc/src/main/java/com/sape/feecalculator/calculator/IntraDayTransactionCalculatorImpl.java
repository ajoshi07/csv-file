package com.sape.feecalculator.calculator;

import com.sape.feecalculator.beans.Transaction;
import com.sape.feecalculator.beans.TransactionSummary;
import com.sape.feecalculator.utility.DateUtility;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;

@Component(value ="intraTransactionCalc")
public class IntraDayTransactionCalculatorImpl implements TransactionCalculator {
    @Override
    public TransactionSummary calculateCharge(Transaction transaction) throws ParseException {
        TransactionSummary summary=new TransactionSummary();
        summary.setClientId(transaction.getClientId());
        summary.setPriorityFlag(transaction.getPriorityFlag());
        summary.setTransactionDate(DateUtility.getDate(transaction.getTransactionDate()));
        summary.setTransactionType(transaction.getTransactionType());
        summary.setProcessingFee(new BigDecimal(10));
        return summary;
    }
}
