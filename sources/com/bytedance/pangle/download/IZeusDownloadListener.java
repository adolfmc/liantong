package com.bytedance.pangle.download;

import android.support.annotation.Keep;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IZeusDownloadListener {
    void onFailed(Throwable th, int i, String str);

    void onProgress(long j, long j2);

    void onStart();

    void onSuccess(String str, String str2, long j);
}
