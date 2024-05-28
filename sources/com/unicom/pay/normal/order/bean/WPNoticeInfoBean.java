package com.unicom.pay.normal.order.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPNoticeInfoBean {
    private boolean isExpand;
    private String noticeDesc;
    private String noticeTitle;

    public String getNoticeDesc() {
        return this.noticeDesc;
    }

    public String getNoticeTitle() {
        return this.noticeTitle;
    }

    public boolean isExpand() {
        return this.isExpand;
    }

    public void setExpand(boolean z) {
        this.isExpand = z;
    }

    public void setNoticeDesc(String str) {
        this.noticeDesc = str;
    }

    public void setNoticeTitle(String str) {
        this.noticeTitle = str;
    }
}
