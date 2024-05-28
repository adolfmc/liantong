package com.tydic.tydic_tracker.tydicDB;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TyEventEntity {
    public String ses_id;
    public long timestamp;
    public String event_type = "onClick";
    public String action_title = "";
    public String event_tag = "";

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

    public String getEvent_type() {
        return this.event_type;
    }

    public void setEvent_type(String str) {
        this.event_type = str;
    }

    public String getAction_title() {
        return this.action_title;
    }

    public void setAction_title(String str) {
        this.action_title = str;
    }

    public String getEvent_tag() {
        return this.event_tag;
    }

    public void setEvent_tag(String str) {
        this.event_tag = str;
    }
}
