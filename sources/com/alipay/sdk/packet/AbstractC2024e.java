package com.alipay.sdk.packet;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.app.C1997i;
import com.alipay.sdk.net.C2017a;
import com.alipay.sdk.sys.C2033b;
import com.alipay.sdk.tid.C2035b;
import com.alipay.sdk.util.C2039b;
import com.alipay.sdk.util.C2040c;
import com.alipay.sdk.util.C2051m;
import com.alipay.sdk.util.C2052n;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.packet.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC2024e {

    /* renamed from: a */
    public static final String f3781a = "msp-gzip";

    /* renamed from: b */
    public static final String f3782b = "Msp-Param";

    /* renamed from: c */
    public static final String f3783c = "Operation-Type";

    /* renamed from: d */
    public static final String f3784d = "content-type";

    /* renamed from: e */
    public static final String f3785e = "Version";

    /* renamed from: f */
    public static final String f3786f = "AppId";

    /* renamed from: g */
    public static final String f3787g = "des-mode";

    /* renamed from: h */
    public static final String f3788h = "namespace";

    /* renamed from: i */
    public static final String f3789i = "api_name";

    /* renamed from: j */
    public static final String f3790j = "api_version";

    /* renamed from: k */
    public static final String f3791k = "data";

    /* renamed from: l */
    public static final String f3792l = "params";

    /* renamed from: m */
    public static final String f3793m = "public_key";

    /* renamed from: n */
    public static final String f3794n = "device";

    /* renamed from: o */
    public static final String f3795o = "action";

    /* renamed from: p */
    public static final String f3796p = "type";

    /* renamed from: q */
    public static final String f3797q = "method";

    /* renamed from: r */
    protected boolean f3798r = true;

    /* renamed from: s */
    protected boolean f3799s = true;

    /* renamed from: a */
    protected abstract JSONObject mo20789a() throws JSONException;

    /* renamed from: b */
    protected String mo20794b() {
        return "4.9.0";
    }

    /* renamed from: a */
    protected Map<String, String> mo20791a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("msp-gzip", String.valueOf(z));
        hashMap.put("Operation-Type", "alipay.msp.cashier.dispatch.bytes");
        hashMap.put("content-type", "application/octet-stream");
        hashMap.put("Version", "2.0");
        hashMap.put("AppId", "TAOBAO");
        hashMap.put("Msp-Param", C2020a.m20821a(str));
        hashMap.put("des-mode", "CBC");
        return hashMap;
    }

    /* renamed from: c */
    protected String mo20790c() throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("device", Build.MODEL);
        hashMap.put("namespace", "com.alipay.mobilecashier");
        hashMap.put("api_name", "com.alipay.mcpay");
        hashMap.put("api_version", mo20794b());
        return m20795a(hashMap, new HashMap<>());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static JSONObject m20796a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put("method", str2);
        jSONObject.put("action", jSONObject2);
        return jSONObject;
    }

    /* renamed from: a */
    protected String mo20792a(String str, JSONObject jSONObject) {
        C2033b m20772a = C2033b.m20772a();
        C2035b m20758a = C2035b.m20758a(m20772a.m20770b());
        JSONObject m20719a = C2039b.m20719a(new JSONObject(), jSONObject);
        try {
            m20719a.put("tid", m20758a.m20759a());
            m20719a.put("user_agent", m20772a.m20769c().m20857a(m20758a));
            m20719a.put("has_alipay", C2052n.m20659b(m20772a.m20770b(), C1997i.f3583a));
            m20719a.put("has_msp_app", C2052n.m20672a(m20772a.m20770b()));
            m20719a.put("external_info", str);
            m20719a.put("app_key", "2014052600006128");
            m20719a.put("utdid", m20772a.m20767e());
            m20719a.put("new_client_key", m20758a.m20754b());
            m20719a.put("pa", m20772a.m20769c().m20860a(m20772a.m20770b()));
        } catch (Throwable th) {
            C2040c.m20715a(th);
        }
        return !(m20719a instanceof JSONObject) ? m20719a.toString() : NBSJSONObjectInstrumentation.toString(m20719a);
    }

    /* renamed from: a */
    private static boolean m20799a(C2017a.C2019b c2019b) {
        return Boolean.valueOf(m20798a(c2019b, "msp-gzip")).booleanValue();
    }

    /* renamed from: a */
    private static String m20798a(C2017a.C2019b c2019b, String str) {
        List<String> list;
        if (c2019b == null || str == null || c2019b.f3772a == null || (list = c2019b.f3772a.get(str)) == null) {
            return null;
        }
        return TextUtils.join(",", list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public String m20795a(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (hashMap != null) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
        }
        if (hashMap2 != null) {
            JSONObject jSONObject3 = new JSONObject();
            for (Map.Entry<String, String> entry2 : hashMap2.entrySet()) {
                jSONObject3.put(entry2.getKey(), entry2.getValue());
            }
            jSONObject2.put("params", jSONObject3);
        }
        jSONObject.put("data", jSONObject2);
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: a */
    private boolean m20797a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("data");
            if (jSONObject.has("params")) {
                String optString = jSONObject.getJSONObject("params").optString("public_key", null);
                if (TextUtils.isEmpty(optString)) {
                    return false;
                }
                C2033b.m20772a().m20769c().m20856a(optString);
                return true;
            }
            return false;
        } catch (JSONException e) {
            C2040c.m20715a(e);
            return false;
        }
    }

    /* renamed from: a */
    public C2021b m20802a(Context context) throws Throwable {
        return mo20793a(context, "");
    }

    /* renamed from: a */
    public C2021b mo20793a(Context context, String str) throws Throwable {
        return m20801a(context, str, C2051m.m20676a(context));
    }

    /* renamed from: a */
    public C2021b m20801a(Context context, String str, String str2) throws Throwable {
        return m20800a(context, str, str2, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public C2021b m20800a(Context context, String str, String str2, boolean z) throws Throwable {
        C2040c.m20714b("", "PacketTask::request url >" + str2);
        C2022c c2022c = new C2022c(this.f3799s);
        C2021b c2021b = new C2021b(mo20790c(), mo20792a(str, mo20789a()));
        Map<String, String> mo20791a = mo20791a(false, str);
        C2023d m20811a = c2022c.m20811a(c2021b, this.f3798r, mo20791a.get("iSr"));
        C2017a.C2019b m20825a = C2017a.m20825a(context, new C2017a.C2018a(str2, mo20791a(m20811a.m20804a(), str), m20811a.m20803b()));
        if (m20825a == null) {
            throw new RuntimeException("Response is null.");
        }
        C2021b m20810a = c2022c.m20810a(new C2023d(m20799a(m20825a), m20825a.f3774c), mo20791a.get("iSr"));
        return (m20810a != null && m20797a(m20810a.m20815a()) && z) ? m20800a(context, str, str2, false) : m20810a;
    }
}
