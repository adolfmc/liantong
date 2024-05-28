package com.bytedance.sdk.openadsdk;

import android.content.Context;
import com.bytedance.sdk.openadsdk.TTAdSdk;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TTInitializer {
    TTAdManager getAdManager();

    void init(Context context, AdConfig adConfig, TTAdSdk.InitCallback initCallback);

    boolean isInitSuccess();
}
