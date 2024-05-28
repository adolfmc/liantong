package com.bytedance.applog;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IDataObserver {
    void onAbVidsChange(String str, String str2);

    void onIdLoaded(String str, String str2, String str3);

    void onRemoteAbConfigGet(boolean z, JSONObject jSONObject);

    void onRemoteConfigGet(boolean z, JSONObject jSONObject);

    void onRemoteIdGet(boolean z, String str, String str2, String str3, String str4, String str5, String str6);
}
