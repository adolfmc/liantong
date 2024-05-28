package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.C2033b;
import com.alipay.sdk.util.C2040c;
import com.alipay.sdk.util.C2048j;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.data.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2006a {

    /* renamed from: a */
    public static final int f3715a = 3500;

    /* renamed from: b */
    public static final String f3716b = "https://h5.m.taobao.com/mlapp/olist.html";

    /* renamed from: c */
    public static final int f3717c = 10;

    /* renamed from: d */
    public static final boolean f3718d = true;

    /* renamed from: e */
    public static final boolean f3719e = true;

    /* renamed from: f */
    public static final int f3720f = 1000;

    /* renamed from: g */
    public static final int f3721g = 20000;

    /* renamed from: h */
    public static final String f3722h = "alipay_cashier_dynamic_config";

    /* renamed from: i */
    public static final String f3723i = "timeout";

    /* renamed from: j */
    public static final String f3724j = "st_sdk_config";

    /* renamed from: k */
    public static final String f3725k = "tbreturl";

    /* renamed from: l */
    public static final String f3726l = "launchAppSwitch";

    /* renamed from: m */
    public static final String f3727m = "configQueryInterval";

    /* renamed from: n */
    public static final String f3728n = "scheme_pay";

    /* renamed from: o */
    public static final String f3729o = "scheme_pay_2";

    /* renamed from: p */
    public static final String f3730p = "intercept_batch";

    /* renamed from: x */
    private static C2006a f3731x;

    /* renamed from: r */
    private int f3733r = 3500;

    /* renamed from: s */
    private String f3734s = "https://h5.m.taobao.com/mlapp/olist.html";

    /* renamed from: t */
    private int f3735t = 10;

    /* renamed from: u */
    private boolean f3736u = true;

    /* renamed from: v */
    private boolean f3737v = true;

    /* renamed from: q */
    public boolean f3732q = false;

    /* renamed from: w */
    private List<C2007a> f3738w = null;

    /* renamed from: a */
    public int m20880a() {
        int i = this.f3733r;
        if (i < 1000 || i > 20000) {
            C2040c.m20714b("", "DynamicConfig::getJumpTimeout(default) >3500");
            return 3500;
        }
        C2040c.m20714b("", "DynamicConfig::getJumpTimeout >" + this.f3733r);
        return this.f3733r;
    }

    /* renamed from: b */
    public boolean m20874b() {
        return this.f3736u;
    }

    /* renamed from: c */
    public boolean m20872c() {
        return this.f3737v;
    }

    /* renamed from: d */
    public String m20871d() {
        return this.f3734s;
    }

    /* renamed from: e */
    public int m20870e() {
        return this.f3735t;
    }

    /* renamed from: f */
    public List<C2007a> m20869f() {
        return this.f3738w;
    }

    /* renamed from: a */
    public void m20875a(boolean z) {
        this.f3732q = z;
    }

    /* renamed from: g */
    public static C2006a m20868g() {
        if (f3731x == null) {
            f3731x = new C2006a();
            f3731x.m20867h();
        }
        return f3731x;
    }

    /* renamed from: h */
    private void m20867h() {
        m20876a(C2048j.m20684b(C2033b.m20772a().m20770b(), "alipay_cashier_dynamic_config", null));
    }

    /* renamed from: a */
    private void m20876a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f3733r = jSONObject.optInt("timeout", 3500);
            this.f3734s = jSONObject.optString("tbreturl", "https://h5.m.taobao.com/mlapp/olist.html").trim();
            this.f3735t = jSONObject.optInt("configQueryInterval", 10);
            this.f3738w = C2007a.m20863a(jSONObject.optJSONArray("launchAppSwitch"));
            this.f3736u = jSONObject.optBoolean("scheme_pay_2", true);
            this.f3737v = jSONObject.optBoolean("intercept_batch", true);
        } catch (Throwable th) {
            C2040c.m20715a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m20866i() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timeout", m20880a());
            jSONObject.put("tbreturl", m20871d());
            jSONObject.put("configQueryInterval", m20870e());
            jSONObject.put("launchAppSwitch", C2007a.m20864a(m20869f()));
            jSONObject.put("scheme_pay_2", m20874b());
            jSONObject.put("intercept_batch", m20872c());
            C2048j.m20686a(C2033b.m20772a().m20770b(), "alipay_cashier_dynamic_config", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (Exception e) {
            C2040c.m20715a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m20873b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("st_sdk_config");
            if (optJSONObject != null) {
                this.f3733r = optJSONObject.optInt("timeout", 3500);
                this.f3734s = optJSONObject.optString("tbreturl", "https://h5.m.taobao.com/mlapp/olist.html").trim();
                this.f3735t = optJSONObject.optInt("configQueryInterval", 10);
                this.f3738w = C2007a.m20863a(optJSONObject.optJSONArray("launchAppSwitch"));
                this.f3736u = optJSONObject.optBoolean("scheme_pay_2", true);
                this.f3737v = optJSONObject.optBoolean("intercept_batch", true);
            } else {
                C2040c.m20711d("msp", "config is null");
            }
        } catch (Throwable th) {
            C2040c.m20715a(th);
        }
    }

    /* renamed from: a */
    public void m20879a(Context context) {
        new Thread(new RunnableC2008b(this, context)).start();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.data.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C2007a {

        /* renamed from: a */
        public final String f3739a;

        /* renamed from: b */
        public final int f3740b;

        /* renamed from: c */
        public final String f3741c;

        public C2007a(String str, int i, String str2) {
            this.f3739a = str;
            this.f3740b = i;
            this.f3741c = str2;
        }

        /* renamed from: a */
        public static C2007a m20862a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new C2007a(jSONObject.optString("pn"), jSONObject.optInt("v", 0), jSONObject.optString("pk"));
        }

        /* renamed from: a */
        public static List<C2007a> m20863a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                C2007a m20862a = m20862a(jSONArray.optJSONObject(i));
                if (m20862a != null) {
                    arrayList.add(m20862a);
                }
            }
            return arrayList;
        }

        /* renamed from: a */
        public static JSONObject m20865a(C2007a c2007a) {
            if (c2007a == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", c2007a.f3739a).put("v", c2007a.f3740b).put("pk", c2007a.f3741c);
            } catch (JSONException e) {
                C2040c.m20715a(e);
                return null;
            }
        }

        /* renamed from: a */
        public static JSONArray m20864a(List<C2007a> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            for (C2007a c2007a : list) {
                jSONArray.put(m20865a(c2007a));
            }
            return jSONArray;
        }

        public String toString() {
            return String.valueOf(m20865a(this));
        }
    }
}
