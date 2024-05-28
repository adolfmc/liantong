package com.bytedance.applog;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.y */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3735y {

    /* renamed from: k */
    public static final String[] f8932k = {"channel", "package", "app_version"};

    /* renamed from: a */
    public boolean f8933a;

    /* renamed from: b */
    public final Context f8934b;

    /* renamed from: c */
    public final C3726x f8935c;

    /* renamed from: f */
    public final SharedPreferences f8938f;

    /* renamed from: g */
    public InterfaceC3662p2 f8939g;

    /* renamed from: i */
    public boolean f8941i;

    /* renamed from: e */
    public final ArrayList<AbstractC3692t> f8937e = new ArrayList<>(32);

    /* renamed from: h */
    public int f8940h = 0;

    /* renamed from: j */
    public boolean f8942j = false;
    @NonNull

    /* renamed from: d */
    public JSONObject f8936d = new JSONObject();

    public C3735y(Context context, C3726x c3726x) {
        this.f8934b = context;
        this.f8935c = c3726x;
        this.f8938f = c3726x.f8900e;
        C3572d3.f8427b.m16988b(this.f8934b).m17226a();
        this.f8939g = C3536a0.m17345a(this.f8934b, this.f8935c);
    }

    /* renamed from: a */
    public static void m17019a(JSONObject jSONObject, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        jSONObject.put(str, str2);
    }

    /* renamed from: f */
    public static boolean m17007f(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || "unknown".equalsIgnoreCase(str) || "Null".equalsIgnoreCase(str)) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                z = true;
                break;
            } else if (str.charAt(i) != '0') {
                break;
            } else {
                i++;
            }
        }
        return !z;
    }

    /* renamed from: a */
    public final String m17021a(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public final synchronized void m17025a(String str) {
        String[] split;
        String optString = this.f8936d.optString("ab_sdk_version");
        if (!TextUtils.isEmpty(optString)) {
            for (String str2 : optString.split(",")) {
                if (!TextUtils.isEmpty(str2) && str2.equals(str)) {
                    if (C3704u2.f8845b) {
                        C3704u2.m17108a("addExposedVid ready added " + optString, (Throwable) null);
                    }
                    return;
                }
            }
            str = optString + "," + str;
        }
        m17011d(str);
        m17023a(str, this.f8935c.m17048c());
    }

    /* renamed from: a */
    public final boolean m17026a(AbstractC3692t abstractC3692t) {
        boolean z = !this.f8935c.m17045f() && abstractC3692t.f8815d;
        C3704u2.m17108a("needSyncFromSub " + abstractC3692t + " " + z, (Throwable) null);
        return z;
    }

    /* renamed from: b */
    public final Set<String> m17016b(String str) {
        String[] split;
        HashSet hashSet = new HashSet();
        if (!TextUtils.isEmpty(str) && (split = str.split(",")) != null && split.length > 0) {
            for (String str2 : split) {
                if (!TextUtils.isEmpty(str2)) {
                    hashSet.add(str2);
                }
            }
        }
        return hashSet;
    }

    @Nullable
    /* renamed from: b */
    public JSONObject m17017b() {
        if (this.f8933a) {
            return this.f8936d;
        }
        return null;
    }

    /* renamed from: c */
    public int m17014c() {
        String optString = this.f8936d.optString("device_id", "");
        String optString2 = this.f8936d.optString("install_id", "");
        String optString3 = this.f8936d.optString("bd_did", "");
        if ((m17007f(optString) || m17007f(optString3)) && m17007f(optString2)) {
            return this.f8938f.getInt("version_code", 0) == this.f8936d.optInt("version_code", -1) ? 1 : 2;
        }
        return 0;
    }

    /* renamed from: c */
    public void m17013c(String str) {
        JSONObject m17027a;
        if (TextUtils.isEmpty(str) || (m17027a = m17027a()) == null || !m17027a.has(str)) {
            return;
        }
        m17027a.remove(str);
        JSONObject jSONObject = new JSONObject();
        C3712v2.m17075a(jSONObject, m17027a);
        m17020a(jSONObject);
    }

    /* renamed from: d */
    public String m17012d() {
        String str = "";
        if (this.f8933a) {
            str = this.f8936d.optString("user_unique_id", "");
        } else {
            C3726x c3726x = this.f8935c;
            if (c3726x != null) {
                str = c3726x.f8898c.getString("user_unique_id", null);
            }
        }
        return TextUtils.isEmpty(str) ? this.f8936d.optString("device_id", "") : str;
    }

    /* renamed from: e */
    public int m17010e() {
        int optInt = this.f8933a ? this.f8936d.optInt("version_code", -1) : -1;
        for (int i = 0; i < 3 && optInt == -1; i++) {
            m17006g();
            optInt = this.f8933a ? this.f8936d.optInt("version_code", -1) : -1;
        }
        return optInt;
    }

    /* renamed from: f */
    public String m17008f() {
        String optString = this.f8933a ? this.f8936d.optString("app_version", null) : null;
        for (int i = 0; i < 3 && optString == null; i++) {
            m17006g();
            optString = this.f8933a ? this.f8936d.optString("app_version", null) : null;
        }
        return optString;
    }

    /* renamed from: g */
    public boolean m17006g() {
        String[] strArr;
        synchronized (this.f8937e) {
            if (this.f8937e.size() == 0) {
                this.f8937e.add(new C3700u(this.f8934b));
                this.f8937e.add(new C3716w(this.f8934b, this.f8935c));
                this.f8937e.add(new C3551b0(this.f8934b));
                this.f8937e.add(new C3558c0(this.f8934b));
                this.f8937e.add(new C3601i0(this.f8934b, this.f8935c, this));
                this.f8937e.add(new C3564d0(this.f8934b));
                this.f8937e.add(new C3587g0(this.f8934b, this.f8935c));
                this.f8937e.add(new C3594h0());
                this.f8937e.add(new C3606j0(this.f8935c, this));
                this.f8937e.add(new C3613k0(this.f8934b));
                this.f8937e.add(new C3619l0(this.f8934b));
                this.f8937e.add(new C3743z(this.f8934b, this));
                this.f8937e.add(new C3575e0(this.f8934b));
                this.f8937e.add(new C3580f0(this.f8934b, this.f8935c));
                this.f8937e.add(new C3709v(this.f8935c));
                this.f8937e.add(new C3677r(this.f8934b));
            }
        }
        JSONObject jSONObject = this.f8936d;
        JSONObject jSONObject2 = new JSONObject();
        C3712v2.m17075a(jSONObject2, jSONObject);
        Iterator<AbstractC3692t> it = this.f8937e.iterator();
        int i = 0;
        int i2 = 0;
        boolean z = true;
        while (it.hasNext()) {
            AbstractC3692t next = it.next();
            if (!next.f8812a || next.f8814c || m17026a(next)) {
                try {
                    next.f8812a = next.mo16995a(jSONObject2);
                } catch (SecurityException e) {
                    if (!next.f8813b) {
                        i++;
                        StringBuilder m17349a = C3535a.m17349a("loadHeader, ");
                        m17349a.append(this.f8940h);
                        C3704u2.m17108a(m17349a.toString(), e);
                        if (!next.f8812a && this.f8940h > 10) {
                            next.f8812a = true;
                        }
                    }
                } catch (JSONException e2) {
                    C3704u2.m17108a("U SHALL NOT PASS!", e2);
                }
                if (!next.f8812a && !next.f8813b) {
                    i2++;
                }
            }
            z &= next.f8812a || next.f8813b;
        }
        if (z) {
            for (String str : f8932k) {
                boolean isEmpty = TextUtils.isEmpty(jSONObject2.optString(str));
                z &= !isEmpty;
                if (isEmpty) {
                    StringBuilder m17349a2 = C3535a.m17349a("loadHeader, ");
                    m17349a2.append(this.f8933a);
                    m17349a2.append(", ");
                    m17349a2.append(str);
                    C3704u2.m17108a(m17349a2.toString(), (Throwable) null);
                }
            }
        }
        JSONObject jSONObject3 = this.f8936d;
        this.f8936d = jSONObject2;
        Iterator<String> keys = jSONObject3.keys();
        while (keys.hasNext()) {
            String next2 = keys.next();
            m17024a(next2, jSONObject3.opt(next2));
        }
        this.f8933a = z;
        if (C3704u2.f8845b) {
            StringBuilder m17349a3 = C3535a.m17349a("loadHeader, ");
            m17349a3.append(this.f8933a);
            m17349a3.append(", ");
            m17349a3.append(this.f8940h);
            m17349a3.append(", ");
            JSONObject jSONObject4 = this.f8936d;
            m17349a3.append(!(jSONObject4 instanceof JSONObject) ? jSONObject4.toString() : NBSJSONObjectInstrumentation.toString(jSONObject4));
            C3704u2.m17108a(m17349a3.toString(), (Throwable) null);
        } else {
            StringBuilder m17349a4 = C3535a.m17349a("loadHeader, ");
            m17349a4.append(this.f8933a);
            m17349a4.append(", ");
            m17349a4.append(this.f8940h);
            C3704u2.m17108a(m17349a4.toString(), (Throwable) null);
        }
        if (i > 0 && i == i2) {
            this.f8940h++;
            if (m17014c() != 0) {
                this.f8940h += 10;
            }
        }
        if (this.f8933a) {
            C3577e2.m17309a().onIdLoaded(AppLog.getDid(), this.f8936d.optString("install_id", ""), this.f8936d.optString("ssid", ""));
        }
        return this.f8933a;
    }

    /* renamed from: h */
    public boolean m17005h() {
        return !this.f8942j;
    }

    /* renamed from: b */
    public final synchronized void m17015b(JSONObject jSONObject) {
        if (jSONObject == null) {
            C3704u2.m17108a("null abconfig", (Throwable) null);
        }
        String optString = this.f8936d.optString("ab_sdk_version");
        if (!TextUtils.isEmpty(optString)) {
            Set<String> m17016b = m17016b(optString);
            HashSet hashSet = new HashSet();
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next instanceof String) {
                        String str = next;
                        if (!TextUtils.isEmpty(str)) {
                            try {
                                hashSet.add(jSONObject.getJSONObject(str).optString("vid"));
                            } catch (JSONException e) {
                                C3704u2.m17108a("U SHALL NOT PASS!", e);
                            }
                        }
                    }
                }
            }
            String m17048c = this.f8935c.m17048c();
            hashSet.addAll(m17016b(m17048c));
            m17016b.retainAll(hashSet);
            String m17021a = m17021a(m17016b);
            m17011d(m17021a);
            if (!TextUtils.equals(optString, m17021a)) {
                m17023a(m17021a, m17048c);
            }
        }
    }

    /* renamed from: a */
    public final JSONObject m17027a() {
        if (this.f8933a) {
            return this.f8936d.optJSONObject("custom");
        }
        C3726x c3726x = this.f8935c;
        if (c3726x != null) {
            try {
                return new JSONObject(c3726x.f8898c.getString("header_custom_info", null));
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: e */
    public synchronized void m17009e(String str) {
        Set<String> m17016b = m17016b(this.f8935c.m17048c());
        Set<String> m17016b2 = m17016b(this.f8936d.optString("ab_sdk_version"));
        m17016b2.removeAll(m17016b);
        m17016b2.addAll(m17016b(str));
        this.f8935c.m17054a(str);
        m17011d(m17021a(m17016b2));
    }

    /* renamed from: a */
    public final void m17023a(String str, String str2) {
        if (this.f8935c.f8900e.getBoolean("bav_ab_config", false) && this.f8935c.f8897b.isAbEnable()) {
            Set<String> m17016b = m17016b(str);
            m17016b.removeAll(m17016b(str2));
            C3577e2.m17309a().onAbVidsChange(m17021a(m17016b), str2);
        }
    }

    /* renamed from: d */
    public final void m17011d(String str) {
        if (m17024a("ab_sdk_version", (Object) str)) {
            C3535a.m17350a(this.f8935c.f8898c, "ab_sdk_version", str);
        }
    }

    /* renamed from: a */
    public boolean m17018a(JSONObject jSONObject, String str, String str2, String str3, String str4, String str5) {
        StringBuilder sb;
        boolean z;
        boolean z2;
        if (C3704u2.f8845b) {
            sb = new StringBuilder();
            sb.append("saveRegisterInfo, ");
            sb.append(str);
            sb.append(", ");
            sb.append(str2);
            sb.append(", ");
            sb.append(str3);
            sb.append(", ");
            sb.append(str4);
            sb.append(", ");
            sb.append(str5);
            sb.append(", ");
            sb.append(jSONObject);
        } else {
            sb = new StringBuilder();
            sb.append("saveRegisterInfo, ");
            sb.append(str);
            sb.append(", ");
            sb.append(str2);
            sb.append(", ");
            sb.append(str3);
            sb.append(", ");
            sb.append(str4);
            sb.append(", ");
            sb.append(str5);
        }
        C3704u2.m17108a(sb.toString(), (Throwable) null);
        this.f8941i = jSONObject.optInt("new_user", 0) > 0;
        boolean m17007f = m17007f(str);
        boolean m17007f2 = m17007f(str2);
        boolean m17007f3 = m17007f(str4);
        boolean m17007f4 = m17007f(str5);
        try {
            boolean m17007f5 = m17007f(str3);
            int i = this.f8938f.getInt("version_code", 0);
            int optInt = this.f8936d.optInt("version_code", 0);
            SharedPreferences.Editor edit = this.f8938f.edit();
            if (i != optInt) {
                edit.putInt("version_code", optInt);
            }
            if ((m17007f || (m17007f3 && m17007f4)) && m17007f2) {
                long currentTimeMillis = System.currentTimeMillis();
                edit.putLong("register_time", currentTimeMillis);
                m17024a("register_time", Long.valueOf(currentTimeMillis));
            } else if (!m17007f && (!m17007f3 || !m17007f4)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("response", jSONObject);
                AppLog.onEventV3("tt_fetch_did_error", jSONObject2);
            }
            String m17276b = ((C3603i2) this.f8939g).m17276b();
            String string = this.f8938f.getString("bd_did", null);
            if (C3704u2.f8845b) {
                C3704u2.m17108a("od=" + m17276b + " nd=" + str + " ck=" + m17007f, (Throwable) null);
            }
            if (m17007f) {
                if (str.equals(this.f8936d.optString("device_id"))) {
                    z = false;
                } else {
                    JSONObject jSONObject3 = this.f8936d;
                    JSONObject jSONObject4 = new JSONObject();
                    C3712v2.m17075a(jSONObject4, jSONObject3);
                    jSONObject4.put("device_id", str);
                    this.f8936d = jSONObject4;
                    ((C3603i2) this.f8939g).m17279a(str);
                    z = true;
                }
                if (!str.equals(m17276b)) {
                    z = true;
                }
            } else {
                z = false;
            }
            if (m17007f3 && m17024a("bd_did", (Object) str4)) {
                edit.putString("bd_did", str4);
                z = true;
            }
            String optString = this.f8936d.optString("install_id", "");
            if (m17007f2 && m17024a("install_id", (Object) str2)) {
                edit.putString("install_id", str2);
                z = true;
            }
            String optString2 = this.f8936d.optString("ssid", "");
            if (m17007f5 && m17024a("ssid", (Object) str3)) {
                edit.putString("ssid", str3);
                z2 = true;
            } else {
                z2 = z;
            }
            C3577e2.m17309a().onRemoteIdGet(z2, string, str4, optString, str2, optString2, str3);
            edit.apply();
        } catch (JSONException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
        }
        return (m17007f || (m17007f3 && m17007f4)) && m17007f2;
    }

    /* renamed from: a */
    public void m17022a(HashMap<String, Object> hashMap) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        if (hashMap != null && !hashMap.isEmpty()) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e) {
                e = e;
            }
            try {
                JSONObject m17027a = m17027a();
                if (m17027a != null) {
                    C3712v2.m17075a(jSONObject, m17027a);
                }
                for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                    if (!TextUtils.isEmpty(entry.getKey())) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                }
                jSONObject2 = jSONObject;
            } catch (JSONException e2) {
                e = e2;
                jSONObject2 = jSONObject;
                C3704u2.m17108a("U SHALL NOT PASS!", e);
                m17020a(jSONObject2);
            }
        }
        m17020a(jSONObject2);
    }

    /* renamed from: a */
    public final void m17020a(JSONObject jSONObject) {
        if (m17024a("custom", jSONObject)) {
            this.f8935c.f8898c.edit().putString("header_custom_info", jSONObject != null ? !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject) : "").apply();
        }
    }

    /* renamed from: a */
    public final boolean m17024a(String str, Object obj) {
        boolean z;
        Object opt = this.f8936d.opt(str);
        if ((obj == null || obj.equals(opt)) && (obj != null || opt == null)) {
            z = false;
        } else {
            synchronized (this) {
                try {
                    JSONObject jSONObject = this.f8936d;
                    JSONObject jSONObject2 = new JSONObject();
                    C3712v2.m17075a(jSONObject2, jSONObject);
                    jSONObject2.put(str, obj);
                    this.f8936d = jSONObject2;
                } catch (JSONException e) {
                    C3704u2.m17108a("U SHALL NOT PASS!", e);
                }
            }
            z = true;
        }
        C3704u2.m17108a("updateHeader, " + str + ", " + opt + ", " + obj, (Throwable) null);
        return z;
    }
}
