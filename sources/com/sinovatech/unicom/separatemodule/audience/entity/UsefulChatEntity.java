package com.sinovatech.unicom.separatemodule.audience.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UsefulChatEntity {
    private List<String> data;
    private String message;
    private String statusCode;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public List<String> getData() {
        return this.data;
    }

    public void setData(List<String> list) {
        this.data = list;
    }
}
