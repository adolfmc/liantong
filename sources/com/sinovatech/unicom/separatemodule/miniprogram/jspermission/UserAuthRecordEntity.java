package com.sinovatech.unicom.separatemodule.miniprogram.jspermission;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserAuthRecordEntity {
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18567id;
    private boolean isGrant;
    private String key;
    private String reason;
    private String scene;
    private String scope;
    private String time;
    private String userAccount;

    public UserAuthRecordEntity(String str, String str2, String str3, String str4, boolean z, String str5, String str6) {
        this.isGrant = false;
        this.userAccount = str;
        this.key = str2;
        this.scope = str3;
        this.time = str4;
        this.isGrant = z;
        this.scene = str5;
        this.reason = str6;
    }

    public UserAuthRecordEntity() {
        this.isGrant = false;
    }

    public long getId() {
        return this.f18567id;
    }

    public void setId(long j) {
        this.f18567id = j;
    }

    public String getUserAccount() {
        return this.userAccount;
    }

    public void setUserAccount(String str) {
        this.userAccount = str;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String str) {
        this.scope = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public boolean isGrant() {
        return this.isGrant;
    }

    public void setGrant(boolean z) {
        this.isGrant = z;
    }

    public String getScene() {
        return this.scene;
    }

    public void setScene(String str) {
        this.scene = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }
}
