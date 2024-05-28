package com.crb.jscard.http.bean;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ApplicationOperationReq {
    private List<Apdu> apdus;
    private String app_aid;
    private String city_id;
    private String iccid;
    private String msisdn;
    private String task_id;
    private int type;

    public List<Apdu> getApdus() {
        return this.apdus;
    }

    public String getApp_aid() {
        return this.app_aid;
    }

    public String getCity_id() {
        return this.city_id;
    }

    public String getIccid() {
        return this.iccid;
    }

    public String getMsisdn() {
        return this.msisdn;
    }

    public String getTask_id() {
        return this.task_id;
    }

    public int getType() {
        return this.type;
    }

    public void setApdus(List<Apdu> list) {
        this.apdus = list;
    }

    public void setApp_aid(String str) {
        this.app_aid = str;
    }

    public void setCity_id(String str) {
        this.city_id = str;
    }

    public void setIccid(String str) {
        this.iccid = str;
    }

    public void setMsisdn(String str) {
        this.msisdn = str;
    }

    public void setTask_id(String str) {
        this.task_id = str;
    }

    public void setType(int i) {
        this.type = i;
    }
}
