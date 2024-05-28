package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TYImageDataEntity {
    private String businessCode;
    private String businessname;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18618id;
    private String isError;
    private String timeStamp;
    private String url;

    public long getId() {
        return this.f18618id;
    }

    public void setId(long j) {
        this.f18618id = j;
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

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String str) {
        this.timeStamp = str;
    }

    public String getBusinessname() {
        return this.businessname;
    }

    public void setBusinessname(String str) {
        this.businessname = str;
    }

    public String getBusinessCode() {
        return this.businessCode;
    }

    public void setBusinessCode(String str) {
        this.businessCode = str;
    }
}
