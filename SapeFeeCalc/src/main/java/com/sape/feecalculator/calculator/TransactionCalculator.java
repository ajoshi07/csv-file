package com.sape.feecalculator.calculator;

import com.sape.feecalculator.beans.Transaction;
import com.sape.feecalculator.beans.TransactionSummary;

import java.text.ParseException;


public interface TransactionCalculator {

     TransactionSummary calculateCharge(Transaction transaction) throws ParseException;
}
