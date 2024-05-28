package com.sinovatech.unicom.separatemodule.audience.entity;

import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SmallVideoInfoEntity {
    private SmallVideoEntity.Data data;
    private String message;
    private String statusCode;

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public SmallVideoEntity.Data getData() {
        return this.data;
    }

    public void setData(SmallVideoEntity.Data data) {
        this.data = data;
    }
}
