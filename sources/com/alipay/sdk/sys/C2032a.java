package com.alipay.sdk.sys;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alipay.sdk.util.C2040c;
import com.alipay.sdk.util.C2052n;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.sys.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2032a {

    /* renamed from: a */
    public static final String f3812a = "\"&";

    /* renamed from: b */
    public static final String f3813b = "&";

    /* renamed from: c */
    public static final String f3814c = "bizcontext=\"";

    /* renamed from: d */
    public static final String f3815d = "bizcontext=";

    /* renamed from: e */
    public static final String f3816e = "\"";

    /* renamed from: f */
    public static final String f3817f = "appkey";

    /* renamed from: g */
    public static final String f3818g = "ty";

    /* renamed from: h */
    public static final String f3819h = "sv";

    /* renamed from: i */
    public static final String f3820i = "an";

    /* renamed from: j */
    public static final String f3821j = "setting";

    /* renamed from: k */
    public static final String f3822k = "av";

    /* renamed from: l */
    public static final String f3823l = "sdk_start_time";

    /* renamed from: m */
    public static final String f3824m = "UTF-8";

    /* renamed from: n */
    private String f3825n;

    /* renamed from: o */
    private String f3826o;

    /* renamed from: p */
    private Context f3827p;

    public C2032a(Context context) {
        this.f3825n = "";
        this.f3826o = "";
        this.f3827p = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f3825n = packageInfo.versionName;
            this.f3826o = packageInfo.packageName;
            this.f3827p = context.getApplicationContext();
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public String m20780a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if (m20776b(str)) {
            return m20774c(str);
        }
        return m20773d(str);
    }

    /* renamed from: b */
    private boolean m20776b(String str) {
        return !str.contains("\"&");
    }

    /* renamed from: c */
    private String m20774c(String str) {
        try {
            String m20778a = m20778a(str, "&", "bizcontext=");
            if (TextUtils.isEmpty(m20778a)) {
                str = str + "&" + m20775b("bizcontext=", "");
            } else {
                int indexOf = str.indexOf(m20778a);
                str = str.substring(0, indexOf) + m20777a(m20778a, "bizcontext=", "", true) + str.substring(indexOf + m20778a.length());
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    /* renamed from: d */
    private String m20773d(String str) {
        try {
            String m20778a = m20778a(str, "\"&", "bizcontext=\"");
            if (TextUtils.isEmpty(m20778a)) {
                return str + "&" + m20775b("bizcontext=\"", "\"");
            }
            if (!m20778a.endsWith("\"")) {
                m20778a = m20778a + "\"";
            }
            int indexOf = str.indexOf(m20778a);
            return str.substring(0, indexOf) + m20777a(m20778a, "bizcontext=\"", "\"", false) + str.substring(indexOf + m20778a.length());
        } catch (Throwable unused) {
            return str;
        }
    }

    /* renamed from: a */
    private String m20778a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(str2);
        for (int i = 0; i < split.length; i++) {
            if (!TextUtils.isEmpty(split[i]) && split[i].startsWith(str3)) {
                return split[i];
            }
        }
        return null;
    }

    /* renamed from: b */
    private String m20775b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        String m20779a = m20779a("", "");
        return str + m20779a + str2;
    }

    /* renamed from: a */
    public String m20779a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", "2014052600006128");
            jSONObject.put("ty", "and_lite");
            jSONObject.put("sv", "h.a.3.6.8");
            if (!this.f3826o.contains("setting") || !C2052n.m20661b(this.f3827p)) {
                jSONObject.put("an", this.f3826o);
            }
            jSONObject.put("av", this.f3825n);
            jSONObject.put("sdk_start_time", System.currentTimeMillis());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Throwable th) {
            C2040c.m20715a(th);
            return "";
        }
    }

    /* renamed from: a */
    private String m20777a(String str, String str2, String str3, boolean z) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject;
        String substring = str.substring(str2.length());
        boolean z2 = false;
        String substring2 = substring.substring(0, substring.length() - str3.length());
        if (substring2.length() >= 2 && substring2.startsWith("\"") && substring2.endsWith("\"")) {
            jSONObject = new JSONObject(substring2.substring(1, substring2.length() - 1));
            z2 = true;
        } else {
            jSONObject = new JSONObject(substring2);
        }
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", "2014052600006128");
        }
        if (!jSONObject.has("ty")) {
            jSONObject.put("ty", "and_lite");
        }
        if (!jSONObject.has("sv")) {
            jSONObject.put("sv", "h.a.3.6.8");
        }
        if (!jSONObject.has("an") && (!this.f3826o.contains("setting") || !C2052n.m20661b(this.f3827p))) {
            jSONObject.put("an", this.f3826o);
        }
        if (!jSONObject.has("av")) {
            jSONObject.put("av", this.f3825n);
        }
        if (!jSONObject.has("sdk_start_time")) {
            jSONObject.put("sdk_start_time", System.currentTimeMillis());
        }
        String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        if (z2) {
            jSONObject2 = "\"" + jSONObject2 + "\"";
        }
        return str2 + jSONObject2 + str3;
    }
}
