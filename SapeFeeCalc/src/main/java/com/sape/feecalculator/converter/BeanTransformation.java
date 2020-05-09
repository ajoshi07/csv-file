package com.sape.feecalculator.converter;

import com.sape.feecalculator.beans.TransactionDTO;
import com.sape.feecalculator.beans.TransactionSummary;
import com.sape.feecalculator.utility.DateUtility;
import java.util.List;
import java.util.stream.Collectors;

public class BeanTransformation {
    public static List<TransactionDTO> getSummaryDTO(List<TransactionSummary> transactionSummaries) {
        return transactionSummaries.stream()
                .collect(
                        Collectors.mapping(
                                t -> new TransactionDTO(t.getClientId(), t.getTransactionType(),
                                        DateUtility.getStringDate(t.getTransactionDate()),t.getPriorityFlag(),t.getProcessingFee().toString()),
                                Collectors.toList()));
    }
}
