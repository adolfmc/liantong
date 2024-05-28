package com.crb.jscard.entity;

import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TransactionRecordsEntity {
    private String code;
    private String info;
    private List<TradeInfo> tradelist;

    public String getCode() {
        return this.code;
    }

    public String getInfo() {
        return this.info;
    }

    public List<TradeInfo> getTradelist() {
        return this.tradelist;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public void setTradelist(List<TradeInfo> list) {
        this.tradelist = list;
    }

    public String toString() {
        return "TransactionRecordsEntity{code='" + this.code + "', info='" + this.info + "', tradelist=" + this.tradelist + '}';
    }
}
