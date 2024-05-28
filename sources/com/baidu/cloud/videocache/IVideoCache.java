package com.baidu.cloud.videocache;

import com.baidu.cloud.videocache.preload.IVideoPreload;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IVideoCache extends IVideoPreload {
    void cleanVideoCacheDir();

    long getCacheSize();

    HttpProxyCacheServer getProxy();

    String getProxyUrl(String str);

    boolean isCached(String str);

    boolean isPreloaded(String str, long j);

    void registerCacheListener(CacheListener cacheListener, String str);

    void setProxyConfig(CacheProxyConfig cacheProxyConfig);

    void unregisterCacheListener(CacheListener cacheListener);
}
