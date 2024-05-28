package com.p319ss.android.socialbase.downloader.network;

import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.network.IDownloadHttpService */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadHttpService {
    IDownloadHttpConnection downloadWithConnection(int i, String str, List<HttpHeader> list) throws IOException;
}
