package com.sinovatech.unicom.basic.p314po;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.po.PointEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PointEntity {
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18392id;
    private String menuId;
    private String mobile;
    private String tag;
    private String type;

    public long getId() {
        return this.f18392id;
    }

    public void setId(long j) {
        this.f18392id = j;
    }

    public String getMenuId() {
        return this.menuId;
    }

    public void setMenuId(String str) {
        this.menuId = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }
}
