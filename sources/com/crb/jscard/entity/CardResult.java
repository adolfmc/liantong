package com.crb.jscard.entity;

import java.util.regex.Pattern;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CardResult {
    private String message;
    private String rapdu;
    private int status;

    /* renamed from: sw */
    private String f9731sw;

    public CardResult(int i, String str, String str2) {
        this.f9731sw = str2.substring(str2.length() - 4);
        this.rapdu = str2.substring(0, str2.length() - 4);
        this.status = i;
        this.message = str;
    }

    private boolean isMatchSw(String str, String str2) {
        return Pattern.compile(str).matcher(str2).find();
    }

    public void check(String str) {
        C14231v.m73b("expSw:" + str);
        C14231v.m73b("sw:" + this.f9731sw);
        if (!isMatchSw(str, this.f9731sw)) {
            throw new CardException(Integer.parseInt(this.f9731sw, 16));
        }
    }

    public String getMessage() {
        return this.message;
    }

    public String getRapdu() {
        return this.rapdu;
    }

    public int getStatus() {
        return this.status;
    }

    public String getSw() {
        return this.f9731sw;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setRapdu(String str) {
        this.rapdu = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setSw(String str) {
        this.f9731sw = str;
    }

    public String toString() {
        return "CardResult{sw='" + this.f9731sw + "', rapdu='" + this.rapdu + "', status=" + this.status + ", message='" + this.message + "'}";
    }

    public CardResult(int i, String str) {
        this.status = i;
        this.message = str;
    }
}
