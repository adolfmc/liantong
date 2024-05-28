package com.p319ss.android.socialbase.downloader.network;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.network.AbsDownloadHttpConnection */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbsDownloadHttpConnection implements IDownloadHttpConnection {
    public String getHostIp() {
        return "";
    }

    public String getRequestLog() {
        return "";
    }

    public void monitorNetworkInfo(JSONObject jSONObject, boolean z) {
    }

    public void onThrowable(Throwable th) {
    }

    public void setThrottleNetSpeedWhenRunning(long j) {
    }
}
