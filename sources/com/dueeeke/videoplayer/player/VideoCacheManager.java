package com.dueeeke.videoplayer.player;

import android.content.Context;
import com.danikula.videocache.HttpProxyCacheServer;
import com.dueeeke.videoplayer.util.StorageUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class VideoCacheManager {
    private static HttpProxyCacheServer sharedProxy;

    private VideoCacheManager() {
    }

    public static HttpProxyCacheServer getProxy(Context context) {
        HttpProxyCacheServer httpProxyCacheServer = sharedProxy;
        if (httpProxyCacheServer == null) {
            HttpProxyCacheServer newProxy = newProxy(context);
            sharedProxy = newProxy;
            return newProxy;
        }
        return httpProxyCacheServer;
    }

    private static HttpProxyCacheServer newProxy(Context context) {
        return new HttpProxyCacheServer.Builder(context).maxCacheSize(104857600L).build();
    }

    public static boolean clearAllCache(Context context) {
        return StorageUtil.deleteFiles(StorageUtil.getIndividualCacheDirectory(context));
    }
}
