package com.crb.jscard.http.bean;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ApplicationOperationResp {
    private List<Apdu> apdus;
    private String code;
    private String info;
    private String task_id;

    public List<Apdu> getApdus() {
        return this.apdus;
    }

    public String getCode() {
        return this.code;
    }

    public String getInfo() {
        return this.info;
    }

    public String getTask_id() {
        return this.task_id;
    }

    public void setApdus(List<Apdu> list) {
        this.apdus = list;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public void setTask_id(String str) {
        this.task_id = str;
    }
}
