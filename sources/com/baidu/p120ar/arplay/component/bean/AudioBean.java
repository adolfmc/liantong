package com.baidu.p120ar.arplay.component.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.component.bean.AudioBean */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioBean {
    public static final String KEY_DELAY = "delay";
    public static final String KEY_FROM_TIME = "from_time";
    public static final String KEY_ID = "id";
    public static final String KEY_LOOP = "loop";
    public static final String KEY_TARGET = "target";
    public static final String KEY_URL = "url";
    private float delay;
    private long fromTime;

    /* renamed from: id */
    private String f4077id;
    private boolean isLoopForever;
    private int loop;
    private String targetName;
    private String url;

    public void setDelay(float f) {
        this.delay = f;
    }

    public void setId(String str) {
        this.f4077id = str;
    }

    public void setLoop(int i) {
        this.loop = i;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public float getDelay() {
        return this.delay;
    }

    public String getId() {
        return this.f4077id;
    }

    public int getLoop() {
        return this.loop;
    }

    public String getUrl() {
        return this.url;
    }

    public long getFromTime() {
        return this.fromTime;
    }

    public void setFromTime(long j) {
        this.fromTime = j;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public void setTargetName(String str) {
        this.targetName = str;
    }

    public boolean isLoopForever() {
        return this.isLoopForever;
    }

    public void setLoopForever(boolean z) {
        this.isLoopForever = z;
    }
}
