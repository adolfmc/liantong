package com.baidu.cloud.videocache;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.cloud.videocache.HttpProxyCacheServer;
import com.baidu.cloud.videocache.file.C2570b;
import com.baidu.cloud.videocache.preload.PreloadCallback;
import com.baidu.cloud.videocache.utils.Logger;
import java.io.File;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ProxyCacheManager implements IVideoCache {
    private static ProxyCacheManager sInstance;
    private Context mAppContext;
    private CacheProxyConfig mCacheProxyConfig = new CacheProxyConfig();
    private HttpProxyCacheServer mProxy;
    private com.baidu.cloud.videocache.preload.ass mVideoPreloadManager;

    private ProxyCacheManager(Context context) {
        this.mAppContext = context.getApplicationContext();
        this.mVideoPreloadManager = com.baidu.cloud.videocache.preload.ass.m19766a(this.mAppContext);
    }

    private File cacheDirMake(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists() || file.mkdirs()) {
                return file;
            }
        }
        return Util.getVideoCacheDir(context);
    }

    public static ProxyCacheManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (ProxyCacheManager.class) {
                if (sInstance == null) {
                    sInstance = new ProxyCacheManager(context);
                }
            }
        }
        return sInstance;
    }

    private HttpProxyCacheServer newProxy(Context context) {
        if (this.mCacheProxyConfig == null) {
            this.mCacheProxyConfig = new CacheProxyConfig();
        }
        com.baidu.cloud.videocache.preload.ass assVar = this.mVideoPreloadManager;
        if (assVar != null) {
            assVar.m19767a(this.mCacheProxyConfig.getPreloadSize());
            this.mVideoPreloadManager.m19759b(this.mCacheProxyConfig.getConnectionTimeout());
        }
        Logger.enableLogging(this.mCacheProxyConfig.isEnableLog());
        return new HttpProxyCacheServer.Builder(context.getApplicationContext()).cacheDirectory(cacheDirMake(context, this.mCacheProxyConfig.getCacheDir())).diskUsage(new C2570b(this.mCacheProxyConfig.getCacheSize())).build();
    }

    public long cachedSize(String str) {
        String tempCacheFilePath = getProxy().getTempCacheFilePath(str);
        if (TextUtils.isEmpty(tempCacheFilePath)) {
            return -1L;
        }
        File file = new File(tempCacheFilePath);
        if (file.exists()) {
            return file.length();
        }
        return -1L;
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void cancelPreload(String str) {
        com.baidu.cloud.videocache.preload.ass assVar = this.mVideoPreloadManager;
        if (assVar != null) {
            assVar.cancelPreload(str);
        }
    }

    @Override // com.baidu.cloud.videocache.IVideoCache
    public void cleanVideoCacheDir() {
        HttpProxyCacheServer httpProxyCacheServer = this.mProxy;
        if (httpProxyCacheServer != null) {
            httpProxyCacheServer.clearCache();
        }
    }

    @Override // com.baidu.cloud.videocache.IVideoCache
    public long getCacheSize() {
        HttpProxyCacheServer httpProxyCacheServer = this.mProxy;
        if (httpProxyCacheServer != null) {
            return httpProxyCacheServer.getCacheSize();
        }
        return 0L;
    }

    public com.baidu.cloud.videocache.file.oia getFileCache(String str) {
        return getProxy().getFileCache(str);
    }

    @Override // com.baidu.cloud.videocache.IVideoCache
    public HttpProxyCacheServer getProxy() {
        if (getInstance(this.mAppContext).mProxy == null) {
            ProxyCacheManager proxyCacheManager = getInstance(this.mAppContext);
            HttpProxyCacheServer newProxy = getInstance(this.mAppContext).newProxy(this.mAppContext);
            proxyCacheManager.mProxy = newProxy;
            return newProxy;
        }
        return this.mProxy;
    }

    @Override // com.baidu.cloud.videocache.IVideoCache
    public String getProxyUrl(String str) {
        return (TextUtils.isEmpty(str) || !str.startsWith("http") || str.contains("127.0.0.1")) ? str : getProxy().getProxyUrl(str);
    }

    public String getTempCachePath(String str) {
        if (isCached(str)) {
            return "";
        }
        String tempCacheFilePath = getProxy().getTempCacheFilePath(str);
        if (TextUtils.isEmpty(tempCacheFilePath)) {
            return "";
        }
        File file = new File(tempCacheFilePath);
        return !file.exists() ? "" : file.getAbsolutePath();
    }

    @Override // com.baidu.cloud.videocache.IVideoCache
    public boolean isCached(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return getProxy().isCached(str) || Util.isLocalFile(getProxy().getProxyUrl(str));
    }

    public boolean isPreloaded(String str) {
        CacheProxyConfig cacheProxyConfig = this.mCacheProxyConfig;
        return isPreloaded(str, cacheProxyConfig != null ? cacheProxyConfig.getPreloadSize() : 819200L);
    }

    @Override // com.baidu.cloud.videocache.IVideoCache
    public boolean isPreloaded(String str, long j) {
        return isCached(str) || cachedSize(str) >= j;
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public boolean isPreloading() {
        com.baidu.cloud.videocache.preload.ass assVar = this.mVideoPreloadManager;
        if (assVar != null) {
            return assVar.isPreloading();
        }
        return false;
    }

    public boolean isReadingInProgress(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return getProxy().isReadingInProgress(str);
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void pausePreload() {
        com.baidu.cloud.videocache.preload.ass assVar = this.mVideoPreloadManager;
        if (assVar != null) {
            assVar.pausePreload();
        }
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void preload(String str, long j, PreloadCallback preloadCallback) {
        com.baidu.cloud.videocache.preload.ass assVar = this.mVideoPreloadManager;
        if (assVar != null) {
            assVar.preload(str, j, preloadCallback);
        }
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void preload(List list, PreloadCallback preloadCallback) {
        com.baidu.cloud.videocache.preload.ass assVar = this.mVideoPreloadManager;
        if (assVar != null) {
            assVar.preload(list, preloadCallback);
        }
    }

    @Override // com.baidu.cloud.videocache.IVideoCache
    public void registerCacheListener(CacheListener cacheListener, String str) {
        getProxy().registerCacheListener(cacheListener, str);
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void release() {
        com.baidu.cloud.videocache.preload.ass assVar = this.mVideoPreloadManager;
        if (assVar != null) {
            assVar.release();
            this.mVideoPreloadManager = null;
        }
        HttpProxyCacheServer httpProxyCacheServer = this.mProxy;
        if (httpProxyCacheServer != null) {
            httpProxyCacheServer.shutdown();
            this.mProxy = null;
        }
        sInstance = null;
        this.mAppContext = null;
    }

    @Override // com.baidu.cloud.videocache.preload.IVideoPreload
    public void resumePreload() {
        com.baidu.cloud.videocache.preload.ass assVar = this.mVideoPreloadManager;
        if (assVar != null) {
            assVar.resumePreload();
        }
    }

    @Override // com.baidu.cloud.videocache.IVideoCache
    public void setProxyConfig(CacheProxyConfig cacheProxyConfig) {
        this.mCacheProxyConfig = cacheProxyConfig;
    }

    @Override // com.baidu.cloud.videocache.IVideoCache
    public void unregisterCacheListener(CacheListener cacheListener) {
        getProxy().unregisterCacheListener(cacheListener);
    }
}
