package com.bytedance.applog;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ISessionObserver {
    void onSessionBatchEvent(long j, String str, JSONObject jSONObject);

    void onSessionStart(long j, String str);

    void onSessionTerminate(long j, String str, JSONObject jSONObject);
}
