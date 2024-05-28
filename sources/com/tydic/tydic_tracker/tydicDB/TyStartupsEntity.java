package com.tydic.tydic_tracker.tydicDB;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TyStartupsEntity {
    public long end_milli;
    public String ses_id;
    public long start_milli;

    public String getSes_id() {
        return this.ses_id;
    }

    public void setSes_id(String str) {
        this.ses_id = str;
    }

    public long getStart_milli() {
        return this.start_milli;
    }

    public void setStart_milli(long j) {
        this.start_milli = j;
    }

    public long getEnd_milli() {
        return this.end_milli;
    }

    public void setEnd_milli(long j) {
        this.end_milli = j;
    }
}
