package com.bytedance.sdk.openadsdk;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TTAppDownloadListener {
    void onDownloadActive(long j, long j2, String str, String str2);

    void onDownloadFailed(long j, long j2, String str, String str2);

    void onDownloadFinished(long j, String str, String str2);

    void onDownloadPaused(long j, long j2, String str, String str2);

    void onIdle();

    void onInstalled(String str, String str2);
}
