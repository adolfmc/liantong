package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.ping;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PingEntity {
    private String code;
    private String consumeTime;
    private String msg;
    private String sequenceNumber;

    public PingEntity(String str, String str2, String str3, String str4) {
        this.code = str;
        this.sequenceNumber = str2;
        this.consumeTime = str3;
        this.msg = str4;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getSequenceNumber() {
        return this.sequenceNumber;
    }

    public void setSequenceNumber(String str) {
        this.sequenceNumber = str;
    }

    public String getConsumeTime() {
        return this.consumeTime;
    }

    public void setConsumeTime(String str) {
        this.consumeTime = str;
    }
}
