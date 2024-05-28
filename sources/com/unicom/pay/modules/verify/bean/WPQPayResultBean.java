package com.unicom.pay.modules.verify.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPQPayResultBean {
    public static final String QPAY_001 = "001";
    public static final String QPAY_002 = "002";
    public static final String QPAY_003 = "003";
    private String phoneNo;
    private String resultCode;
    private String resultMsg;

    public WPQPayResultBean() {
    }

    public WPQPayResultBean(String str, String str2, String str3) {
        this.phoneNo = str;
        this.resultCode = str2;
        this.resultMsg = str3;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getResultMsg() {
        return this.resultMsg;
    }

    public void setPhoneNo(String str) {
        this.phoneNo = str;
    }

    public void setResultCode(String str) {
        this.resultCode = str;
    }

    public void setResultMsg(String str) {
        this.resultMsg = str;
    }
}
