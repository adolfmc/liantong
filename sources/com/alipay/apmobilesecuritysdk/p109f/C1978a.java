package com.alipay.apmobilesecuritysdk.p109f;

import android.content.Context;
import android.os.Environment;
import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.security.mobile.module.p110a.p111a.C2084c;
import com.alipay.security.mobile.module.p113c.C2091b;
import com.alipay.security.mobile.module.p113c.C2092c;
import com.alipay.security.mobile.module.p113c.C2094e;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.apmobilesecuritysdk.f.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1978a {
    /* renamed from: a */
    public static String m20968a(Context context, String str, String str2) {
        String m20487a;
        if (context == null || C2081a.m20577a(str)) {
            return null;
        }
        if (!C2081a.m20577a(str2)) {
            try {
                m20487a = C2094e.m20487a(context, str, str2, "");
                if (C2081a.m20577a(m20487a)) {
                    return null;
                }
            } catch (Throwable unused) {
                return null;
            }
        }
        return C2084c.m20559b(C2084c.m20564a(), m20487a);
    }

    /* renamed from: a */
    public static String m20966a(String str, String str2) {
        synchronized (C1978a.class) {
            if (C2081a.m20577a(str) || C2081a.m20577a(str2)) {
                return null;
            }
            String m20491a = C2091b.m20491a(str);
            if (C2081a.m20577a(m20491a)) {
                return null;
            }
            String string = new JSONObject(m20491a).getString(str2);
            if (C2081a.m20577a(string)) {
                return null;
            }
            return C2084c.m20559b(C2084c.m20564a(), string);
        }
    }

    /* renamed from: a */
    public static void m20967a(Context context, String str, String str2, String str3) {
        if (!C2081a.m20577a(str) && !C2081a.m20577a(str2) && context != null) {
            try {
                String m20562a = C2084c.m20562a(C2084c.m20564a(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put(str2, m20562a);
                C2094e.m20486a(context, str, hashMap);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m20965a(String str, String str2, String str3) {
        synchronized (C1978a.class) {
            if (C2081a.m20577a(str) || C2081a.m20577a(str2)) {
                return;
            }
            try {
                String m20491a = C2091b.m20491a(str);
                JSONObject jSONObject = new JSONObject();
                if (C2081a.m20573b(m20491a)) {
                    try {
                        jSONObject = new JSONObject(m20491a);
                    } catch (Exception unused) {
                        jSONObject = new JSONObject();
                    }
                }
                jSONObject.put(str2, C2084c.m20562a(C2084c.m20564a(), str3));
                if (jSONObject instanceof JSONObject) {
                    NBSJSONObjectInstrumentation.toString(jSONObject);
                } else {
                    jSONObject.toString();
                }
                try {
                    System.clearProperty(str);
                } catch (Throwable unused2) {
                }
                if (C2092c.m20490a()) {
                    String str4 = ".SystemConfig" + File.separator + str;
                    if (C2092c.m20490a()) {
                        File file = new File(Environment.getExternalStorageDirectory(), str4);
                        if (file.exists() && file.isFile()) {
                            file.delete();
                        }
                    }
                }
            } catch (Throwable unused3) {
            }
        }
    }
}
