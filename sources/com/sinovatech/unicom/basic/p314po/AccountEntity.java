package com.sinovatech.unicom.basic.p314po;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.AccountEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AccountEntity {
    private String name;
    private String url;
    private String value;

    public AccountEntity(String str, String str2, String str3) {
        this.name = str;
        this.url = str3;
        this.value = str2;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
