package com.tydic.softphone.tydic_tracker.tydicDB;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TyViewEntity {
    public String ses_id;
    public long timestamp;
    public String page_name = "";
    public String view_class = "";
    public String view_event = "onCreate";
    public String pre_cn = "";

    public String getSes_id() {
        return this.ses_id;
    }

    public void setSes_id(String str) {
        this.ses_id = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String getPage_name() {
        return this.page_name;
    }

    public void setPage_name(String str) {
        this.page_name = str;
    }

    public String getView_class() {
        return this.view_class;
    }

    public void setView_class(String str) {
        this.view_class = str;
    }

    public String getView_event() {
        return this.view_event;
    }

    public void setView_event(String str) {
        this.view_event = str;
    }

    public String getPre_cn() {
        return this.pre_cn;
    }

    public void setPre_cn(String str) {
        this.pre_cn = str;
    }
}
