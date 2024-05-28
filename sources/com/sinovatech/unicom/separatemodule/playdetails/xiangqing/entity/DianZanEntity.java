package com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DianZanEntity {
    private DataDTO data;
    private String message;
    private String statusCode;

    public DataDTO getData() {
        return this.data;
    }

    public void setData(DataDTO dataDTO) {
        this.data = dataDTO;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class DataDTO {
        private String bury_count;
        private String digg_count;

        public String getBury_count() {
            return this.bury_count;
        }

        public void setBury_count(String str) {
            this.bury_count = str;
        }

        public String getDigg_count() {
            return this.digg_count;
        }

        public void setDigg_count(String str) {
            this.digg_count = str;
        }
    }
}
