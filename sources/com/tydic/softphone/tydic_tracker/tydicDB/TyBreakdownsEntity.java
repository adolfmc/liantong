package com.tydic.softphone.tydic_tracker.tydicDB;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TyBreakdownsEntity {
    public String class_name;
    public long collect_time;
    public String crash_name;
    public int crash_type;
    public String event_id;
    public String event_tag;
    public int is_thread_break = 1;
    public String lineNum;
    public String method_name;
    public String ses_id;
    public String target_name;
    public long timestamp;
    public String track_details;

    public String getSes_id() {
        return this.ses_id;
    }

    public void setSes_id(String str) {
        this.ses_id = str;
    }

    public String getClass_name() {
        return this.class_name;
    }

    public void setClass_name(String str) {
        this.class_name = str;
    }

    public String getMethod_name() {
        return this.method_name;
    }

    public void setMethod_name(String str) {
        this.method_name = str;
    }

    public String getLineNum() {
        return this.lineNum;
    }

    public void setLineNum(String str) {
        this.lineNum = str;
    }

    public int getCrash_type() {
        return this.crash_type;
    }

    public void setCrash_type(int i) {
        this.crash_type = i;
    }

    public String getTrack_details() {
        return this.track_details;
    }

    public void setTrack_details(String str) {
        this.track_details = str;
    }

    public String getTarget_name() {
        return this.target_name;
    }

    public void setTarget_name(String str) {
        this.target_name = str;
    }

    public String getEvent_tag() {
        return this.event_tag;
    }

    public void setEvent_tag(String str) {
        this.event_tag = str;
    }

    public String getCrash_name() {
        return this.crash_name;
    }

    public void setCrash_name(String str) {
        this.crash_name = str;
    }

    public int getIs_thread_break() {
        return this.is_thread_break;
    }

    public void setIs_thread_break(int i) {
        this.is_thread_break = i;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public long getCollect_time() {
        return this.collect_time;
    }

    public void setCollect_time(long j) {
        this.collect_time = j;
    }

    public String getEvent_id() {
        return this.event_id;
    }

    public void setEvent_id(String str) {
        this.event_id = str;
    }
}
