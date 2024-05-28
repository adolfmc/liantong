package com.bytedance.applog;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.p3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3663p3 {

    /* renamed from: a */
    public final SharedPreferences f8709a;

    @WorkerThread
    public C3663p3(Context context) {
        this.f8709a = context.getSharedPreferences("device_register_oaid_refine", 0);
    }

    @WorkerThread
    @Nullable
    /* renamed from: a */
    public C3656o3 m17164a() {
        String string = this.f8709a.getString("oaid", "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            return new C3656o3(jSONObject.optString("id", null), jSONObject.optString("req_id", null), jSONObject.has("is_track_limited") ? Boolean.valueOf(jSONObject.optBoolean("is_track_limited")) : null, jSONObject.has("take_ms") ? Long.valueOf(jSONObject.optLong("take_ms", -1L)) : null, jSONObject.has("time") ? Long.valueOf(jSONObject.optLong("time", -1L)) : null, jSONObject.has("query_times") ? Integer.valueOf(jSONObject.optInt("query_times", -1)) : null, jSONObject.has("hw_id_version_code") ? Long.valueOf(jSONObject.optLong("hw_id_version_code", -1L)) : null);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @WorkerThread
    /* renamed from: a */
    public void m17163a(@Nullable C3656o3 c3656o3) {
        if (c3656o3 == null) {
            return;
        }
        SharedPreferences.Editor edit = this.f8709a.edit();
        JSONObject m17183b = c3656o3.m17183b();
        edit.putString("oaid", !(m17183b instanceof JSONObject) ? m17183b.toString() : NBSJSONObjectInstrumentation.toString(m17183b)).apply();
    }
}
