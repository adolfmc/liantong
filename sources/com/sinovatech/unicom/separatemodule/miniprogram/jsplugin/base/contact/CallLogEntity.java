package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CallLogEntity {
    private int callDuration;
    private String callName;
    private String callNumber;
    private String callTime;
    private String callType;

    public String getCallNumber() {
        return this.callNumber;
    }

    public void setCallNumber(String str) {
        this.callNumber = str;
    }

    public String getCallName() {
        return this.callName;
    }

    public void setCallName(String str) {
        this.callName = str;
    }

    public String getCallType() {
        return this.callType;
    }

    public void setCallType(String str) {
        this.callType = str;
    }

    public String getCallTime() {
        return this.callTime;
    }

    public void setCallTime(String str) {
        this.callTime = str;
    }

    public int getCallDuration() {
        return this.callDuration;
    }

    public void setCallDuration(int i) {
        this.callDuration = i;
    }
}
