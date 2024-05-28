package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.u */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3700u extends AbstractC3692t {
    public C3700u(Context context) {
        super(true, false);
    }

    @Override // com.bytedance.applog.AbstractC3692t
    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        jSONObject.put("os", "Android");
        jSONObject.put("os_version", Build.VERSION.RELEASE);
        jSONObject.put("os_api", Build.VERSION.SDK_INT);
        jSONObject.put("device_model", Build.MODEL);
        jSONObject.put("device_brand", Build.BRAND);
        jSONObject.put("device_manufacturer", Build.MANUFACTURER);
        jSONObject.put("cpu_abi", Build.CPU_ABI);
        return true;
    }
}
