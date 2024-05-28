package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TYNetWorkEntity {
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18620id;
    private String isError;
    private String url;

    public long getId() {
        return this.f18620id;
    }

    public void setId(long j) {
        this.f18620id = j;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getIsError() {
        return this.isError;
    }

    public void setIsError(String str) {
        this.isError = str;
    }
}
