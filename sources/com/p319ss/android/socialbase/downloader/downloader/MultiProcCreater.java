package com.p319ss.android.socialbase.downloader.downloader;

import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.impls.IndependentDownloadServiceHandler;
import com.p319ss.android.socialbase.downloader.impls.IndependentProcessDownloadHandler;
import com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCacheAidlWrapper;

/* renamed from: com.ss.android.socialbase.downloader.downloader.MultiProcCreater */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class MultiProcCreater implements DownloadComponentManager.IndependentHolderCreator {
    @Override // com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager.IndependentHolderCreator
    public IDownloadServiceHandler createServiceHandler() {
        return new IndependentDownloadServiceHandler();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager.IndependentHolderCreator
    public IDownloadProxy createProxy() {
        return new IndependentProcessDownloadHandler();
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager.IndependentHolderCreator
    public ISqlDownloadCache createCache(DownloadComponentManager.IndependentHolderCreator.OnMainProcessRebindErrorListener onMainProcessRebindErrorListener) {
        SqlDownloadCacheAidlWrapper sqlDownloadCacheAidlWrapper = new SqlDownloadCacheAidlWrapper();
        sqlDownloadCacheAidlWrapper.setOnMainProcessRebindErrorCallback(onMainProcessRebindErrorListener);
        return sqlDownloadCacheAidlWrapper;
    }
}
