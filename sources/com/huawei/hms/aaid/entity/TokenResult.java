package com.huawei.hms.aaid.entity;

import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.api.client.Result;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TokenResult extends Result {
    @Packed
    private String belongId;
    @Packed
    private String token = "";
    @Packed
    private int retCode = 0;

    public String getBelongId() {
        return this.belongId;
    }

    public int getRetCode() {
        return this.retCode;
    }

    public String getToken() {
        return this.token;
    }

    public void setBelongId(String str) {
        this.belongId = str;
    }

    public void setRetCode(int i) {
        this.retCode = i;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
