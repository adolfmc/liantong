package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

@Entity
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AnquanWhiteEntity {
    private String code;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18459id;
    private String mobile;
    private String name;
    private boolean selected;

    public long getId() {
        return this.f18459id;
    }

    public void setId(long j) {
        this.f18459id = j;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String toString() {
        return "AnquanWhiteEntity{id=" + this.f18459id + ", code='" + this.code + "', name='" + this.name + "', selected=" + this.selected + ", mobile='" + this.mobile + "'}";
    }
}
