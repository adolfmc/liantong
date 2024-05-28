package com.p319ss.android.socialbase.downloader.network;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadHeadHttpConnection {
    void cancel();

    int getResponseCode() throws IOException;

    String getResponseHeaderField(String str);
}
