package com.sinovatech.unicom.basic.p314po;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.QuickEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class QuickEntity {
    private String code;
    private List<DataBean> data;
    private String msg;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.po.QuickEntity$DataBean */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class DataBean {
        private String iconType;

        /* renamed from: id */
        private String f18394id;
        private boolean isUse;
        private String linkUrl;
        private String title;

        public boolean isUse() {
            return this.isUse;
        }

        public void setUse(boolean z) {
            this.isUse = z;
        }

        public String getId() {
            return this.f18394id;
        }

        public void setId(String str) {
            this.f18394id = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getIconType() {
            return this.iconType;
        }

        public void setIconType(String str) {
            this.iconType = str;
        }

        public String getLinkUrl() {
            return this.linkUrl;
        }

        public void setLinkUrl(String str) {
            this.linkUrl = str;
        }
    }
}
