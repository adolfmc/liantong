package com.bytedance.android.live.saas.middleware.applog;

import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IAppLog {
    String addCommonParams(String str, boolean z);

    void addCustomHeader(String str, String str2);

    String getAbSdkVersion();

    String getDid();

    String getInstallId();

    boolean isTob();

    void log(String str, Map<String, String> map);

    void observeIdChanged(IdChangedCallback idChangedCallback);

    void onEventV3(String str, JSONObject jSONObject);

    void putCommonParams(Map<String, String> map, boolean z);

    void tryWaitDeviceInit();
}
