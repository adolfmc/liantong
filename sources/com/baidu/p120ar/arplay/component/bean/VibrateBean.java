package com.baidu.p120ar.arplay.component.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.component.bean.VibrateBean */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VibrateBean {
    public static final String KEY_INTERVAL = "interval";
    public static final String KEY_PATTERN = "pattern";
    public static final String KEY_TYPE = "type";
    private int interval;
    private String pattern;
    private int type;

    public int getType() {
        return this.type;
    }

    public int getInterval() {
        return this.interval;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setPattern(String str) {
        this.pattern = str;
    }
}
