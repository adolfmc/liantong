package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CumpCollectionEntity {
    private String appId;
    private String appName;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18559id;

    public long getId() {
        return this.f18559id;
    }

    public void setId(long j) {
        this.f18559id = j;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }
}
