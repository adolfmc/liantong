package com.sinovatech.unicom.separatemodule.audience.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LiveInfoEntity {
    private List<LiveInfoItem> data;
    private boolean isPv;
    private String message;
    private String nextPageNum;
    private String statusCode;

    public List<LiveInfoItem> getData() {
        return this.data;
    }

    public void setData(List<LiveInfoItem> list) {
        this.data = list;
    }

    public boolean isPv() {
        return this.isPv;
    }

    public void setPv(boolean z) {
        this.isPv = z;
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

    public String getNextPageNum() {
        return this.nextPageNum;
    }

    public void setNextPageNum(String str) {
        this.nextPageNum = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LiveInfoItem {
        private String desc;
        private String headImg;
        private boolean isPv;
        private String jobNumber;
        private String nickName;
        private String prevueImg;
        private String prevueTime;

        public boolean isPv() {
            return this.isPv;
        }

        public void setPv(boolean z) {
            this.isPv = z;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public String getHeadImg() {
            return this.headImg;
        }

        public void setHeadImg(String str) {
            this.headImg = str;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String str) {
            this.desc = str;
        }

        public String getPrevueTime() {
            return this.prevueTime;
        }

        public void setPrevueTime(String str) {
            this.prevueTime = str;
        }

        public String getPrevueImg() {
            return this.prevueImg;
        }

        public void setPrevueImg(String str) {
            this.prevueImg = str;
        }

        public String getJobNumber() {
            return this.jobNumber;
        }

        public void setJobNumber(String str) {
            this.jobNumber = str;
        }
    }
}
