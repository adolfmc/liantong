package com.bytedance.applog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IEventObserver {
    void onEvent(@NonNull String str, @NonNull String str2, String str3, long j, long j2, String str4);

    void onEventV3(@NonNull String str, @Nullable JSONObject jSONObject);
}
