package com.sinovatech.unicom.basic.p314po;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.po.CacheBox */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CacheBox {
    private String content;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18375id;
    private String key;
    private String userMobile;

    public long getId() {
        return this.f18375id;
    }

    public void setId(long j) {
        this.f18375id = j;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getUserMobile() {
        return this.userMobile;
    }

    public void setUserMobile(String str) {
        this.userMobile = str;
    }
}
