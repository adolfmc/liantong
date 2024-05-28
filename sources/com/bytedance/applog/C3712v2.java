package com.bytedance.applog;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.bytedance.applog.v2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3712v2 {

    /* renamed from: a */
    public static String f8864a;

    /* renamed from: a */
    public static String m17077a(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m17074a(boolean z) {
        return z ? "yes" : "no";
    }

    /* renamed from: a */
    public static JSONObject m17075a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, jSONObject2.opt(next));
            }
        } catch (JSONException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static void m17079a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: a */
    public static boolean m17078a(String str) {
        int length = str != null ? str.length() : 0;
        if (length < 13 || length > 128) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'f') && ((charAt < 'A' || charAt > 'F') && charAt != '-'))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m17073b(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || (str != null && str.equals(str2));
    }

    /* renamed from: b */
    public static boolean m17072b(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return jSONObject == jSONObject2 || (jSONObject != null && jSONObject.equals(jSONObject2));
        }
        return (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).equals(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
    }

    /* renamed from: a */
    public static JSONObject m17076a(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject jSONObject2 = new JSONObject();
            m17075a(jSONObject2, jSONObject);
            try {
                String m17311a = C3572d3.m17311a(jSONObject2.optJSONObject("oaid"));
                if (TextUtils.isEmpty(m17311a)) {
                    return jSONObject2;
                }
                jSONObject2.put("oaid", m17311a);
                return jSONObject2;
            } catch (Exception e) {
                C3704u2.m17108a("U SHALL NOT PASS!", e);
                return jSONObject2;
            }
        }
        return null;
    }
}
