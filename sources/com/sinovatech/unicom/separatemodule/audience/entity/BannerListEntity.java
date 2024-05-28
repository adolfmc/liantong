package com.sinovatech.unicom.separatemodule.audience.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BannerListEntity {
    private List<BannerEntitys> data;
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

    public List<BannerEntitys> getData() {
        return this.data;
    }

    public void setData(List<BannerEntitys> list) {
        this.data = list;
    }
}
