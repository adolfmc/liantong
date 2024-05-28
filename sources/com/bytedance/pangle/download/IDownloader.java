package com.bytedance.pangle.download;

import android.content.Context;
import android.support.annotation.Keep;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IDownloader {
    void downloader(Context context, String str, String str2, boolean z, String str3, int i, String str4, List<String> list, IZeusDownloadListener iZeusDownloadListener, IZeusDownloadInterceptor iZeusDownloadInterceptor);
}
