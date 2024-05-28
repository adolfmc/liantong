package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard;

import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.LogUtil;
import java.util.regex.Pattern;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CardResult {
    private String apdu;
    private String msg;
    private String rapdu;
    private String status;

    /* renamed from: sw */
    private String f18578sw;

    public CardResult() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getSw() {
        return this.f18578sw;
    }

    public void setSw(String str) {
        this.f18578sw = str;
    }

    public String getRapdu() {
        return this.rapdu;
    }

    public void setRapdu(String str) {
        this.rapdu = str;
    }

    public String getApdu() {
        return this.apdu;
    }

    public void setApdu(String str) {
        this.apdu = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public CardResult(String str, String str2, String str3) {
        this.status = str;
        this.f18578sw = str3.substring(str3.length() - 4);
        this.rapdu = str3.substring(0, str3.length() - 4).toUpperCase();
        this.apdu = str3.toUpperCase();
        this.msg = str2;
    }

    public CardResult(String str, String str2) {
        this.status = str;
        this.msg = str2;
    }

    public boolean check(String str) {
        try {
            return isMatchSw(str, this.f18578sw);
        } catch (Exception e) {
            LogUtil.m7990d("异常" + e.getMessage());
            return false;
        }
    }

    private boolean isMatchSw(String str, String str2) {
        return Pattern.compile(str).matcher(str2).find();
    }

    public String toString() {
        return "CardResult{status=" + this.status + ", sw='" + this.f18578sw + "', rapdu='" + this.rapdu + "', apdu='" + this.apdu + "', msg='" + this.msg + "'}";
    }
}
