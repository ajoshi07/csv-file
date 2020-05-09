package com.sape.feecalculator.beans;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;


public class TransactionDTO {

    @CsvBindByName(column ="Client Id")
    @CsvBindByPosition(position = 0)
    private String clientId;
    @CsvBindByName(column ="Transaction Type")
    @CsvBindByPosition(position = 1)
    private String transactionType;
    @CsvBindByName(column ="Transaction Date")
    @CsvBindByPosition(position = 2)
    private String transactionDate;
    @CsvBindByName(column ="Priority Flag")
    @CsvBindByPosition(position = 3)
    private String priorityFlag;
    @CsvBindByName(column ="Processing Fee")
    @CsvBindByPosition(position = 4)
    private String processingFee;

    public TransactionDTO(String clientId, String transactionType, String transactionDate, String priorityFlag, String processingFee) {
        this.clientId = clientId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.priorityFlag = priorityFlag;
        this.processingFee = processingFee;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPriorityFlag() {
        return priorityFlag;
    }

    public void setPriorityFlag(String priorityFlag) {
        this.priorityFlag = priorityFlag;
    }

    public String getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(String processingFee) {
        this.processingFee = processingFee;
    }
}
