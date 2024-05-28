package com.bytedance.android.live.base.api;

import android.content.Context;
import android.webkit.WebResourceResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DefaultHostWebViewParam implements ILiveHostWebViewParam {
    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public Object createJsBridge2(Context context, Object obj) {
        return null;
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public String getFileProvider() {
        return null;
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public String getGeckoChannel(boolean z) {
        return null;
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public String getOfflineCacheDir() {
        return null;
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public WebResourceResponse interceptRequest(String str) {
        return null;
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public boolean isSafeDomain(String str) {
        return false;
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public void setCachePrefix(List<Pattern> list) {
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public List<String> getShareCookie(String str) {
        return Collections.emptyList();
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public Map<String, String> getHeaderMap(String str) {
        return Collections.emptyMap();
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostWebViewParam
    public List<String> getSafeJsbHostList() {
        return Collections.emptyList();
    }
}
