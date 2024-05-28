package com.baidu.p120ar.arplay.component.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.component.bean.VideoBean */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoBean {
    public static final String KEY_FROM_TIME = "from_time";
    public static final String KEY_ID = "id";
    public static final String KEY_LOOP = "loop";
    public static final String KEY_TARGET = "target";
    public static final String KEY_TEXTUREID = "texture_id";
    public static final String KEY_URL = "url";
    private long fromTime;

    /* renamed from: id */
    private String f4078id;
    private boolean isLoopForever;
    private int loop;
    private String targetName;
    private int textureid;
    private String url;

    public void setId(String str) {
        this.f4078id = str;
    }

    public void setTextureid(int i) {
        this.textureid = i;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getId() {
        return this.f4078id;
    }

    public int getTextureid() {
        return this.textureid;
    }

    public String getUrl() {
        return this.url;
    }

    public void setLoop(int i) {
        this.loop = i;
    }

    public int getLoop() {
        return this.loop;
    }

    public boolean isLoopForever() {
        return this.isLoopForever;
    }

    public void setLoopForever(boolean z) {
        this.isLoopForever = z;
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
}
