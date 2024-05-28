package com.p319ss.android.socialbase.downloader.network;

import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.network.IDownloadHttpConnection */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadHttpConnection extends IDownloadHeadHttpConnection {
    void end();

    InputStream getInputStream() throws IOException;
}
