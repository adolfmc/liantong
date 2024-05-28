package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class JSStorageEntity {
    private String appId;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18582id;
    private String key;
    private String value;

    public long getId() {
        return this.f18582id;
    }

    public void setId(long j) {
        this.f18582id = j;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }
}
