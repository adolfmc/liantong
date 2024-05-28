package com.crb.jscard.entity;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BalanceAndCardNumberEntity {
    private String balance;
    private String cardNumber;
    private String code;
    private String endDate;
    private String info;
    private String startDate;

    public String getBalance() {
        return this.balance;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getCode() {
        return this.code;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getInfo() {
        return this.info;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public void setCardNumber(String str) {
        this.cardNumber = str;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String toString() {
        return "BalanceAndCardNumberEntity{code='" + this.code + "', info='" + this.info + "', balance='" + this.balance + "', cardNumber='" + this.cardNumber + "', startDate='" + this.startDate + "', endDate='" + this.endDate + "'}";
    }
}
