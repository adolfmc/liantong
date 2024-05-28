package com.ijiami;

import cn.ltzf.passguard.C1730a;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class StatusInfo {
    private int statusCode = 0;
    private String expiredDate = "";

    public String getExpiredDate() {
        return this.expiredDate;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusDescribe() {
        switch (this.statusCode) {
            case -5:
                return "sign info verify failed";
            case -4:
                return "package name verify failed";
            case -3:
                return "date is expired";
            case -2:
                return "user key data is changed or error format";
            case -1:
                return "user key data is too short or error format";
            case 0:
                return "error";
            case 1:
                return "success";
            default:
                return "";
        }
    }

    public void setExpiredDate(String str) {
        this.expiredDate = str;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public String toString() {
        StringBuilder m22016a = C1730a.m22016a("[statusCode=");
        m22016a.append(this.statusCode);
        m22016a.append(", expiredDate=");
        m22016a.append(this.expiredDate);
        m22016a.append("]");
        return m22016a.toString();
    }
}
