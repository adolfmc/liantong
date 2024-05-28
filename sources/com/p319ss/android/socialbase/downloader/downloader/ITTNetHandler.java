package com.p319ss.android.socialbase.downloader.downloader;

import com.p319ss.android.socialbase.downloader.exception.DownloadTTNetException;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpService;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpService;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.downloader.ITTNetHandler */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ITTNetHandler {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.downloader.ITTNetHandler$DefaultTTNetHandler */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class DefaultTTNetHandler implements ITTNetHandler {
        @Override // com.p319ss.android.socialbase.downloader.downloader.ITTNetHandler
        public IDownloadHeadHttpService getTTNetDownloadHeadHttpService() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.ITTNetHandler
        public IDownloadHttpService getTTNetDownloadHttpService() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.ITTNetHandler
        public boolean isResponseCode304Error(Throwable th) {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.ITTNetHandler
        public boolean isTTNetEnable() {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.ITTNetHandler
        public DownloadTTNetException translateTTNetException(Throwable th, String str) {
            return null;
        }
    }

    IDownloadHeadHttpService getTTNetDownloadHeadHttpService();

    IDownloadHttpService getTTNetDownloadHttpService();

    boolean isResponseCode304Error(Throwable th);

    boolean isTTNetEnable();

    DownloadTTNetException translateTTNetException(Throwable th, String str);
}
