package com.bytedance.sdk.openadsdk;

import android.webkit.WebResourceResponse;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IKGUtils {
    WebResourceResponse findRes(String str, String str2, FindResProxy findResProxy);

    Map<String, Long> getChannelVersion();

    void preload(List<String> list);

    void releaseLoader();
}
