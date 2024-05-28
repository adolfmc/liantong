package com.p319ss.android.socialbase.downloader.depend;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadForbiddenCallback {
    boolean hasCallback();

    void onCallback(List<String> list);
}
