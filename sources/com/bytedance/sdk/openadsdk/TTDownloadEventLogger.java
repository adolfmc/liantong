package com.bytedance.sdk.openadsdk;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TTDownloadEventLogger {
    void onDownloadConfigReady();

    void onEvent(JSONObject jSONObject);

    void onV3Event(JSONObject jSONObject);

    boolean shouldFilterOpenSdkLog();
}
