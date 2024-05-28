package com.baidu.cloud.videocache;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CacheProxyConfig {
    public static final int DEFAULT_CONNECTION_TIMEOUT = 10000;
    public static final long DEFAULT_PRELOAD_SIZE_BYTE = 819200;
    public static final long MAX_CACHE_SIZE = 536870912;
    private String cacheDir;
    private long maxCacheSize = 536870912;
    private long preloadSize = 819200;
    private int connectionTimeout = 10000;
    private boolean enableLog = false;

    public String getCacheDir() {
        return this.cacheDir;
    }

    public long getCacheSize() {
        return this.maxCacheSize;
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public long getPreloadSize() {
        return this.preloadSize;
    }

    public boolean isEnableLog() {
        return this.enableLog;
    }

    public void setCacheDir(String str) {
        this.cacheDir = str;
    }

    public void setCacheSize(long j) {
        this.maxCacheSize = j;
    }

    public void setConnectionTimeout(int i) {
        this.connectionTimeout = i;
    }

    public void setEnableLog(boolean z) {
        this.enableLog = z;
    }

    public void setPreloadSize(long j) {
        this.preloadSize = j;
    }
}
