package com.tydic.softphone.tydic_tracker.tydicDB;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TyAnrEntity {
    public String anr_id;

    /* renamed from: at */
    public String f20023at;
    public String ath;
    public String sd_raw;
    public String ses_id;

    /* renamed from: st */
    public long f20024st;

    public String getSes_id() {
        return this.ses_id;
    }

    public void setSes_id(String str) {
        this.ses_id = str;
    }

    public String getAnr_id() {
        return this.anr_id;
    }

    public void setAnr_id(String str) {
        this.anr_id = str;
    }

    public long getSt() {
        return this.f20024st;
    }

    public void setSt(long j) {
        this.f20024st = j;
    }

    public String getAt() {
        return this.f20023at;
    }

    public void setAt(String str) {
        this.f20023at = str;
    }

    public String getSd_raw() {
        return this.sd_raw;
    }

    public void setSd_raw(String str) {
        this.sd_raw = str;
    }

    public String getAth() {
        return this.ath;
    }

    public void setAth(String str) {
        this.ath = str;
    }
}
