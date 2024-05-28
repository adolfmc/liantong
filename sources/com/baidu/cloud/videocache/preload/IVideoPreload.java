package com.baidu.cloud.videocache.preload;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IVideoPreload {
    void cancelPreload(String str);

    boolean isPreloading();

    void pausePreload();

    void preload(String str, long j, PreloadCallback preloadCallback);

    void preload(List list, PreloadCallback preloadCallback);

    void release();

    void resumePreload();
}
