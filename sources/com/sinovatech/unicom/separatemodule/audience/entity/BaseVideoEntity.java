package com.sinovatech.unicom.separatemodule.audience.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BaseVideoEntity<T> {
    private String count;
    private T data;
    private String message;
    private String nextPageNum;
    private String redDotState;
    private String statusCode;

    public String getRedDotState() {
        return this.redDotState;
    }

    public void setRedDotState(String str) {
        this.redDotState = str;
    }

    public String getNextPageNum() {
        return this.nextPageNum;
    }

    public void setNextPageNum(String str) {
        this.nextPageNum = str;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }
}
