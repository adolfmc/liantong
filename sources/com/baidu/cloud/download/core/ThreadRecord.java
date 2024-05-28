package com.baidu.cloud.download.core;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ThreadRecord {
    private long end;
    private long finished;

    /* renamed from: id */
    private int f4348id;
    private long start;
    private String tag;
    private String uri;

    public ThreadRecord() {
    }

    public ThreadRecord(int i, String str, String str2, long j) {
        this(i, str, str2, 0L, 0L, j);
    }

    public ThreadRecord(int i, String str, String str2, long j, long j2, long j3) {
        this.f4348id = i;
        this.tag = str;
        this.uri = str2;
        this.start = j;
        this.end = j2;
        this.finished = j3;
    }

    public int getId() {
        return this.f4348id;
    }

    public void setId(int i) {
        this.f4348id = i;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public long getStart() {
        return this.start;
    }

    public void setStart(long j) {
        this.start = j;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long j) {
        this.end = j;
    }

    public long getFinished() {
        return this.finished;
    }

    public void setFinished(long j) {
        this.finished = j;
    }
}
