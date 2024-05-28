package com.bytedance.pangle.apm;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbsApm {
    public abstract String getDid();

    public abstract void init();

    public abstract void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    public abstract void reportError(String str, @NonNull Throwable th);
}
