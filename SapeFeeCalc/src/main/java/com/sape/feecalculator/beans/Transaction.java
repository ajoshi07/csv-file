package com.sape.feecalculator.beans;


import com.opencsv.bean.CsvBindByPosition;


public class Transaction {

    @CsvBindByPosition(position = 0)
    private String externalTransactionId;
    @CsvBindByPosition(position = 1)
    private String clientId;
    @CsvBindByPosition(position = 2)
    private String securityId;
    @CsvBindByPosition(position = 3)
    private String transactionType;
    @CsvBindByPosition(position = 4)
    private String   transactionDate;
    @CsvBindByPosition(position = 5)
    private String marketValue;
    @CsvBindByPosition(position = 6)
    private String priorityFlag;

    public String getExternalTransactionId() {
        return externalTransactionId;
    }

    public void setExternalTransactionId(String externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
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

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getPriorityFlag() {
        return priorityFlag;
    }

    public void setPriorityFlag(String priorityFlag) {
        this.priorityFlag = priorityFlag;
    }
}
