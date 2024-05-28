package com.bytedance.sdk.openadsdk.live;

import android.os.Bundle;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ILiveAdCustomConfig {
    String convertToEnterFromMerge(int i);

    String convertToEnterMethod(int i, boolean z);

    Object invoke(int i, Bundle bundle);

    void onEventV3(String str, JSONObject jSONObject);

    int openLR(String str);
}
