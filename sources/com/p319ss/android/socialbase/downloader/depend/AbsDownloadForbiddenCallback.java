package com.p319ss.android.socialbase.downloader.depend;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.AbsDownloadForbiddenCallback */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbsDownloadForbiddenCallback implements IDownloadForbiddenCallback {
    private boolean hasCallback = false;

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback
    public void onCallback(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.hasCallback = true;
    }

    @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback
    public boolean hasCallback() {
        return this.hasCallback;
    }
}
