package com.baidu.cloud.download;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DownloadConfig {
    public static final int DEFAULT_MAX_THREAD_NUMBER = 10;
    public static final int DEFAULT_THREAD_NUMBER = 2;
    private int maxThreadNum = 10;
    private int threadNum = 2;

    public int getMaxThreadNum() {
        return this.maxThreadNum;
    }

    public void setMaxThreadNum(int i) {
        this.maxThreadNum = i;
    }

    public int getThreadNum() {
        return this.threadNum;
    }

    public void setThreadNum(int i) {
        this.threadNum = i;
    }
}
