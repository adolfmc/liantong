package com.p319ss.android.socialbase.downloader.impls;

import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.IDownloadProxy;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.impls.DownloadProxy */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DownloadProxy {
    private static volatile IDownloadProxy downloadIndependentProxy;
    private static volatile IDownloadProxy downloadProxy;

    public static IDownloadProxy get(boolean z) {
        if (z && DownloadComponentManager.supportMultiProc()) {
            if (downloadIndependentProxy == null) {
                synchronized (DownloadProxy.class) {
                    if (downloadIndependentProxy == null) {
                        downloadIndependentProxy = DownloadComponentManager.getIndependentHolderCreator().createProxy();
                    }
                }
            }
            return downloadIndependentProxy;
        }
        if (downloadProxy == null) {
            synchronized (DownloadProxy.class) {
                if (downloadProxy == null) {
                    downloadProxy = new ProcessDownloadHandler();
                }
            }
        }
        return downloadProxy;
    }
}
