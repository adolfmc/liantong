package com.huawei.hms.push.ups.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CodeResult {

    /* renamed from: a */
    private int f11684a;

    /* renamed from: b */
    private String f11685b;

    public CodeResult() {
    }

    public String getReason() {
        return this.f11685b;
    }

    public int getReturnCode() {
        return this.f11684a;
    }

    public void setReason(String str) {
        this.f11685b = str;
    }

    public void setReturnCode(int i) {
        this.f11684a = i;
    }

    public CodeResult(int i) {
        this.f11684a = i;
    }

    public CodeResult(int i, String str) {
        this.f11684a = i;
        this.f11685b = str;
    }
}
