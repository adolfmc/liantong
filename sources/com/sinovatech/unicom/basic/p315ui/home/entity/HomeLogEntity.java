package com.sinovatech.unicom.basic.p315ui.home.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeLogEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HomeLogEntity {
    public String title;
    public String transId;

    public HomeLogEntity(String str, String str2) {
        this.transId = "";
        this.title = "";
        this.transId = str;
        this.title = str2;
    }

    public String getTransId() {
        return this.transId;
    }

    public void setTransId(String str) {
        this.transId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
