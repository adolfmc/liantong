package com.crb.jscard.entity;

import com.crb.jscard.http.bean.Apdu;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TransmitEntity {
    private List<Apdu> rApduList;

    public List<Apdu> getrApduList() {
        return this.rApduList;
    }

    public void setrApduList(List<Apdu> list) {
        this.rApduList = list;
    }

    public String toString() {
        return "TransmitEntity{apduList=" + this.rApduList + '}';
    }
}
