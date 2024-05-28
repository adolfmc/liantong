package com.bytedance.pangle.provider;

import android.net.Uri;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.bytedance.pangle.provider.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3894b {
    /* renamed from: a */
    public static String m16741a(String str, String str2, Uri uri) {
        if (str2 == null || TextUtils.isEmpty(str2)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ContentProviderManager.PLUGIN_PROCESS_NAME, str);
            jSONObject.put("plugin_pkg_name", str2);
            jSONObject.put("uri", uri != null ? uri.toString() : "");
        } catch (Throwable unused) {
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }
}
