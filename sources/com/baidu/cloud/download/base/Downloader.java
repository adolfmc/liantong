package com.baidu.cloud.download.base;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface Downloader {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnDownloaderDestroyedListener {
        void onDestroyed(String str, Downloader downloader);
    }

    void cancel();

    boolean isRunning();

    void onDestroy();

    void pause();

    void start();
}
