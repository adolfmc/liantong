package com.bytedance.sdk.openadsdk.api.plugin.p185ox;

import android.os.Build;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.bytedance.JProtect;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.security.SecureRandom;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.bytedance.sdk.openadsdk.api.plugin.ox.ox */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4020ox {
    /* renamed from: b */
    private static SecureRandom m16460b() {
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                return SecureRandom.getInstanceStrong();
            } catch (Throwable unused) {
                return new SecureRandom();
            }
        }
        return new SecureRandom();
    }

    /* renamed from: mb */
    public static String m16459mb() {
        String m16458mb = m16458mb(16);
        if (m16458mb == null || m16458mb.length() != 32) {
            return null;
        }
        return m16458mb;
    }

    /* renamed from: mb */
    public static String m16458mb(int i) {
        try {
            byte[] bArr = new byte[i];
            m16460b().nextBytes(bArr);
            return C4018b.m16463mb(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x005a, code lost:
        if (((((98 * 98) * 98) + ((45 * 45) * 45)) + ((23 * 23) * 23)) >= (((98 * 45) * 23) * 3)) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0070 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007b A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0078 -> B:16:0x005c). Please submit an issue!!! */
    @android.support.annotation.Keep
    @com.bytedance.JProtect
    /* renamed from: mb */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m16457mb(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.openadsdk.api.plugin.p185ox.C4020ox.m16457mb(java.lang.String):java.lang.String");
    }

    /* renamed from: mb */
    public static String m16456mb(String str, int i) {
        if (str == null || str.length() != i) {
            return null;
        }
        int i2 = i / 2;
        return str.substring(i2, i) + str.substring(0, i2);
    }

    @Keep
    @JProtect
    /* renamed from: mb */
    public static JSONObject m16455mb(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            return jSONObject2;
        }
        try {
            try {
                String m16457mb = m16457mb(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                if (TextUtils.isEmpty(m16457mb)) {
                    jSONObject2.put("message", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                    jSONObject2.put("cypher", 0);
                } else {
                    jSONObject2.put("message", m16457mb);
                    jSONObject2.put("cypher", 3);
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            jSONObject2.put("message", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            jSONObject2.put("cypher", 0);
        }
        return jSONObject2;
    }

    /* renamed from: ox */
    public static String m16454ox() {
        String m16458mb = m16458mb(8);
        if (m16458mb == null || m16458mb.length() != 16) {
            return null;
        }
        return m16458mb;
    }
}
