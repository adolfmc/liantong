package com.p319ss.android.socialbase.downloader.depend;

import com.p319ss.android.socialbase.downloader.exception.BaseException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.ITempFileSaveCompleteCallback */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ITempFileSaveCompleteCallback {
    void onFailed(BaseException baseException);

    void onSuccess();
}
