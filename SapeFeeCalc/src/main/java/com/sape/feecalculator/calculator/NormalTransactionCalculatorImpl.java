package com.sape.feecalculator.calculator;

import com.sape.feecalculator.beans.Transaction;
import com.sape.feecalculator.beans.TransactionSummary;
import com.sape.feecalculator.utility.DateUtility;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.function.Predicate;

@Component(value ="normalTransactionCalc")
public class NormalTransactionCalculatorImpl implements TransactionCalculator {

    Predicate<Transaction> hPriorityPredicate=s->s.getPriorityFlag().equals("Y");
    Predicate<Transaction> nPriorityPredicate=s->s.getPriorityFlag().equals("N");
    Predicate<Transaction> predicate1=s->s.getTransactionType().equals("SELL")||s.getTransactionType().equals("WITHDRAW");
    Predicate<Transaction> predicate2=s->s.getTransactionType().equals("BUY")||s.getTransactionType().equals("DEPOSIT");

    @Override
    public TransactionSummary calculateCharge(Transaction transaction) throws ParseException {
        TransactionSummary summary=new TransactionSummary();
        summary.setClientId(transaction.getClientId());
        summary.setTransactionDate(DateUtility.getDate(transaction.getTransactionDate()));
        summary.setTransactionType(transaction.getTransactionType());
        summary.setPriorityFlag(transaction.getPriorityFlag());
        if(hPriorityPredicate.test(transaction))
        {
            summary.setProcessingFee(new BigDecimal(500));
        }
        else if(nPriorityPredicate.and(predicate1).test(transaction))
        {
            summary.setProcessingFee(new BigDecimal(100));
        }
        else if(nPriorityPredicate.and(predicate2).test(transaction))
        {
            summary.setProcessingFee(new BigDecimal(50));
        }
        else {
            summary.setProcessingFee(new BigDecimal(0));
        }

        return summary;
    }
}
