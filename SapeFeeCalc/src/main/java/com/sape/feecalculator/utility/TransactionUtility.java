package com.sape.feecalculator.utility;

import com.sape.feecalculator.beans.Transaction;
import com.sape.feecalculator.beans.TransactionSummary;
import com.sape.feecalculator.calculator.FeeCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Component
public class TransactionUtility {

    @Autowired
    @Qualifier(value = "intraDayFeeCalc")
    FeeCalculation intraTransactionCalc;

    @Autowired
    @Qualifier(value = "normalDayFeeCalc")
    FeeCalculation normalTransactionCalc;

    BiPredicate<Transaction,Transaction> clientPredicate=(t1,t2)->t1.getClientId().equals(t2.getClientId());
    BiPredicate<Transaction,Transaction> securityPredicate=(t1,t2)->t1.getSecurityId().equals(t2.getSecurityId());
    BiPredicate<Transaction,Transaction> datePredicate=(t1,t2)->t1.getTransactionDate().equals(t2.getTransactionDate());
    BiPredicate<Transaction,Transaction> transactionBiPredicate=(t1,t2)->t1.getTransactionType().equals(t2.getTransactionType());

    BiPredicate<TransactionSummary,TransactionSummary> clientSummaryPre=(t1,t2)->t1.getClientId().equals(t2.getClientId());
    BiPredicate<TransactionSummary,TransactionSummary> prioritySummaryPred=(t1,t2)->t1.getPriorityFlag().equals(t2.getPriorityFlag());
    BiPredicate<TransactionSummary,TransactionSummary> dateSummaryPre=(t1,t2)->t1.getTransactionDate().equals(t2.getTransactionDate());
    BiPredicate<TransactionSummary,TransactionSummary> transactionSummaryPre=(t1,t2)->t1.getTransactionType().equals(t2.getTransactionType());

    Comparator<TransactionSummary> compByCliendId=Comparator.comparing(TransactionSummary::getClientId);
    Comparator<TransactionSummary> compByTransactionType=Comparator.comparing(TransactionSummary::getTransactionType);
    Comparator<TransactionSummary> compByTransactionDate=Comparator.comparing(TransactionSummary::getTransactionDate);
    Comparator<TransactionSummary> compByPriority=Comparator.comparing(TransactionSummary::getPriorityFlag);

    public  List<TransactionSummary> getSummary(List<Transaction> list) throws ParseException {
        List<TransactionSummary> summaryList=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            boolean flag=false;
            int nonRead=0;
            for(int j=i+1;j<list.size();j++)
            {
                if(clientPredicate.and(securityPredicate)
                        .and(datePredicate)
                        .test(list.get(i),list.get(j))
                        &&transactionBiPredicate.negate().
                        test(list.get(i),list.get(j))&&!list1.contains(j))
                {
                    flag=true;
                    nonRead=j;
                    break;
                }
            }
            if(!list1.contains(i)) {
                if (flag) {
                    summaryList.add(intraTransactionCalc.doCalculation(list.get(i)));
                    summaryList.add(intraTransactionCalc.doCalculation(list.get(nonRead)));
                } else {
                    summaryList.add(normalTransactionCalc.doCalculation(list.get(i)));
                }
                list1.add(nonRead);
            }
        }
        return summaryList;

    }

    public  List<TransactionSummary> groupDataOnField(List<TransactionSummary> list)
    {
        List<TransactionSummary> summaryList=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            boolean flag=false;
            int nonRead=0;
            for(int j=i+1;j<list.size();j++)
            {
                if(clientSummaryPre
                        .and(dateSummaryPre)
                        .and(transactionSummaryPre)
                        .and(prioritySummaryPred)
                        .test(list.get(i),list.get(j))
                                &&!list1.contains(j))
                {
                    flag=true;
                    nonRead=j;
                    break;
                }
            }
            if(!list1.contains(i)) {
                if (flag) {
                    TransactionSummary transactionSummary=list.get(i);
                    transactionSummary.setProcessingFee(transactionSummary.getProcessingFee().add(list.get(nonRead).getProcessingFee()));
                    summaryList.add(transactionSummary);
                } else {
                    summaryList.add(list.get(i));
                }
                list1.add(nonRead);
            }
        }
        return summaryList;

    }

    public  List<TransactionSummary> sortDataOnField(List<TransactionSummary> list)
    {

        return list
                .stream()
                .sorted(compByCliendId.thenComparing(compByTransactionType).thenComparing(compByTransactionDate).thenComparing(compByPriority))
                .collect(Collectors.toList());

    }


}
