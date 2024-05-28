package com.sinovatech.unicom.common.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GDTEntity {
    private String code;
    private DataBean data;
    private GTDAndroidEntity entity;
    private String message;
    private String url;

    public GTDAndroidEntity getEntity() {
        return this.entity;
    }

    public void setEntity(GTDAndroidEntity gTDAndroidEntity) {
        this.entity = gTDAndroidEntity;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class DataBean {
        private String androidIdGdt;
        private String muid;

        public String getMuid() {
            return this.muid;
        }

        public void setMuid(String str) {
            this.muid = str;
        }

        public String getAndroidIdGdt() {
            return this.androidIdGdt;
        }

        public void setAndroidIdGdt(String str) {
            this.androidIdGdt = str;
        }
    }
}
