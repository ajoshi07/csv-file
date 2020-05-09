package com.sape.feecalculator.calculator;

import com.sape.feecalculator.beans.Transaction;
import com.sape.feecalculator.beans.TransactionSummary;

import java.text.ParseException;

public class FeeCalculation {

    TransactionCalculator transactionCalculator;

    public FeeCalculation(TransactionCalculator transactionCalculator)
    {
         this.transactionCalculator=transactionCalculator;
    }

    public TransactionSummary doCalculation(Transaction transaction) throws ParseException {
        return transactionCalculator.calculateCharge(transaction);
    }
}
