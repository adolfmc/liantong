package com.alipay.apmobilesecuritysdk.p108e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p106c.C1961a;
import com.alipay.apmobilesecuritysdk.p109f.C1978a;
import com.alipay.security.mobile.module.p110a.C2081a;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.apmobilesecuritysdk.e.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1973e {
    /* renamed from: a */
    public static C1974f m21018a(Context context) {
        if (context == null) {
            return null;
        }
        String m20968a = C1978a.m20968a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (C2081a.m20577a(m20968a)) {
            m20968a = C1978a.m20966a("device_feature_file_name", "device_feature_file_key");
        }
        if (C2081a.m20577a(m20968a)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(m20968a);
            C1974f c1974f = new C1974f();
            c1974f.m21016a(jSONObject.getString("imei"));
            c1974f.m21014b(jSONObject.getString("imsi"));
            c1974f.m21012c(jSONObject.getString("mac"));
            c1974f.m21010d(jSONObject.getString("bluetoothmac"));
            c1974f.m21008e(jSONObject.getString("gsi"));
            return c1974f;
        } catch (Exception e) {
            C1961a.m21046a(e);
            return null;
        }
    }
}
