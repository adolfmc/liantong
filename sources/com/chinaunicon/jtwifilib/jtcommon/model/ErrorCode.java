package com.chinaunicon.jtwifilib.jtcommon.model;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ErrorCode {
    private int code;
    private String errorCode;
    private String errorMsg;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public ErrorCode(int i, String str, String str2, String str3) {
        this.code = i;
        this.msg = str;
        this.errorCode = str2;
        this.errorMsg = str3;
    }
}
