package com.bytedance.android.live.base.api;

import android.content.Context;
import android.webkit.WebResourceResponse;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ILiveHostWebViewParam {
    Object createJsBridge2(Context context, Object obj);

    String getFileProvider();

    String getGeckoChannel(boolean z);

    Map<String, String> getHeaderMap(String str);

    @Deprecated
    String getOfflineCacheDir();

    List<String> getSafeJsbHostList();

    List<String> getShareCookie(String str);

    WebResourceResponse interceptRequest(String str);

    boolean isSafeDomain(String str);

    void setCachePrefix(List<Pattern> list);
}
