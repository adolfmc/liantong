package com.crb.jscard.entity;

import com.crb.jscard.http.bean.Apdu;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TransmitAllEntity {
    private List<Apdu> cApduList;
    private String code;
    private String info;

    public String getCode() {
        return this.code;
    }

    public String getInfo() {
        return this.info;
    }

    public List<Apdu> getcApduList() {
        return this.cApduList;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public void setcApduList(List<Apdu> list) {
        this.cApduList = list;
    }
}
