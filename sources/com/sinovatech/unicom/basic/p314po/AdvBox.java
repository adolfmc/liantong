package com.sinovatech.unicom.basic.p314po;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.po.AdvBox */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AdvBox {
    private String cityCode;
    private String content;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18371id;
    private String mobile;
    private String proCode;
    private String ywCode;

    public long getId() {
        return this.f18371id;
    }

    public void setId(long j) {
        this.f18371id = j;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getProCode() {
        return this.proCode;
    }

    public void setProCode(String str) {
        this.proCode = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public String getYwCode() {
        return this.ywCode;
    }

    public void setYwCode(String str) {
        this.ywCode = str;
    }
}
