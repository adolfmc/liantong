package com.ijiami;

import cn.ltzf.passguard.C1730a;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ResultData {
    private int status;
    private String text;
    private boolean isVerify = false;
    private byte[] data = new byte[0];

    public byte[] getData() {
        return this.data;
    }

    public int getStatus() {
        return this.status;
    }

    public String getText() {
        return this.text;
    }

    public boolean isVerify() {
        return this.isVerify;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setVerify(boolean z) {
        this.isVerify = z;
    }

    public String toString() {
        StringBuilder m22016a = C1730a.m22016a("ResultData [status=");
        m22016a.append(this.status);
        m22016a.append(", isVerify=");
        m22016a.append(this.isVerify);
        m22016a.append(", text=");
        m22016a.append(this.text);
        m22016a.append("]");
        return m22016a.toString();
    }
}
