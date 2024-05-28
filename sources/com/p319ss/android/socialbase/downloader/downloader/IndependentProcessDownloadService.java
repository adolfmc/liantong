package com.p319ss.android.socialbase.downloader.downloader;

import java.lang.ref.WeakReference;

/* renamed from: com.ss.android.socialbase.downloader.downloader.IndependentProcessDownloadService */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class IndependentProcessDownloadService extends DownloadService {
    @Override // com.p319ss.android.socialbase.downloader.downloader.DownloadService, android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadComponentManager.setAppContext(this);
        if (DownloadComponentManager.getIndependentHolderCreator() == null) {
            DownloadComponentManager.setIndependentServiceCreator(new MultiProcCreater());
        }
        this.downloadServiceHandler = DownloadComponentManager.getIndependentDownloadServiceHandler();
        this.downloadServiceHandler.setDownloadService(new WeakReference(this));
    }
}
