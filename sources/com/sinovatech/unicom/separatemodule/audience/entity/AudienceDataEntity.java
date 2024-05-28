package com.sinovatech.unicom.separatemodule.audience.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AudienceDataEntity {
    private List<ListDataEntity> list;
    private String message;
    private String nextPageNum;
    private String statusCode;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getNextPageNum() {
        return this.nextPageNum;
    }

    public void setNextPageNum(String str) {
        this.nextPageNum = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public List<ListDataEntity> getList() {
        return this.list;
    }

    public void setList(List<ListDataEntity> list) {
        this.list = list;
    }
}
