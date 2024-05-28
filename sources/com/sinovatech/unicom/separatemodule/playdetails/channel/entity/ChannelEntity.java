package com.sinovatech.unicom.separatemodule.playdetails.channel.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ChannelEntity {
    private List<DataDTO> data;
    private String message;
    private String statusCode;

    public List<DataDTO> getData() {
        return this.data;
    }

    public void setData(List<DataDTO> list) {
        this.data = list;
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
        private boolean isUnMove;
        private int pageIndex;
        private String pageName;
        private String subCode;
        private String whetherShow;

        public boolean isUnMove() {
            return this.isUnMove;
        }

        public void setUnMove(boolean z) {
            this.isUnMove = z;
        }

        public int getPageIndex() {
            return this.pageIndex;
        }

        public void setPageIndex(int i) {
            this.pageIndex = i;
        }

        public String getPageName() {
            return this.pageName;
        }

        public void setPageName(String str) {
            this.pageName = str;
        }

        public String getSubCode() {
            return this.subCode;
        }

        public void setSubCode(String str) {
            this.subCode = str;
        }

        public String getWhetherShow() {
            return this.whetherShow;
        }

        public void setWhetherShow(String str) {
            this.whetherShow = str;
        }
    }
}
