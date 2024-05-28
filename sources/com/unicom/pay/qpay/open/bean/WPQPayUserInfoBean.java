package com.unicom.pay.qpay.open.bean;

import android.text.TextUtils;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPQPayUserInfoBean {
    public static final String CREATE_TABLE = "CREATE TABLE QPayUserInfoBean(userNo TEXT PRIMARY KEY, payToken TEXT,currentFido TEXT,isSupportTwo TEXT)";
    public static final String QPAY_COLUMN_CURRENT_KEY = "currentFido";
    public static final String QPAY_COLUMN_ID = "userNo";
    public static final String QPAY_COLUMN_KEY = "payToken";
    public static final String QPAY_COLUMN_SUPPORT_DOUBLE_KEY = "isSupportTwo";
    public static final String QPAY_TABLE_NAME = "QPayUserInfoBean";
    public static final String SUPPORT_FACE = "02";
    public static final String SUPPORT_FP = "00";
    public static final String SUPPORT_TWO = "1";
    private String currentFido;
    private String isSupportTwo;
    private String payToken;
    private String userNo;

    public WPQPayUserInfoBean() {
        this.userNo = "";
        this.payToken = "";
        this.currentFido = "";
        this.isSupportTwo = "";
    }

    public WPQPayUserInfoBean(String str, String str2, String str3, String str4) {
        this.userNo = str;
        this.payToken = str2;
        this.currentFido = str3;
        this.isSupportTwo = str4;
    }

    public void clearFido() {
        setCurrentFido("");
        setIsSupportTwo("");
    }

    public String getCurrentFido() {
        return this.currentFido;
    }

    public String getIsSupportTwo() {
        return this.isSupportTwo;
    }

    public String getPayToken() {
        if (this.payToken == null) {
            this.payToken = "";
        }
        return this.payToken;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void openFido(String str) {
        if (TextUtils.isEmpty(this.currentFido)) {
            setCurrentFido(str);
            return;
        }
        setCurrentFido("00");
        setIsSupportTwo("1");
    }

    public void resetFido(String str) {
        String str2;
        if ("1".equals(this.isSupportTwo)) {
            str2 = str.equals(this.currentFido) ? "00".equals(str) ? "02" : "00" : "";
            setIsSupportTwo("");
        }
        setCurrentFido(str2);
        setIsSupportTwo("");
    }

    public void setCurrentFido(String str) {
        this.currentFido = str;
    }

    public void setIsSupportTwo(String str) {
        this.isSupportTwo = str;
    }

    public void setPayToken(String str) {
        this.payToken = str;
    }

    public void setUserNo(String str) {
        if (str == null) {
            str = "";
        }
        this.userNo = str;
    }
}
