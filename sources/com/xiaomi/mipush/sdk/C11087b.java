package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11455i;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.xiaomi.mipush.sdk.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11087b {

    /* renamed from: a */
    private static volatile C11087b f21351a;

    /* renamed from: a */
    private Context f21352a;

    /* renamed from: a */
    private C11088a f21353a;

    /* renamed from: a */
    String f21354a;

    /* renamed from: a */
    private Map<String, C11088a> f21355a;

    /* renamed from: a */
    public static C11087b m5151a(Context context) {
        if (f21351a == null) {
            synchronized (C11087b.class) {
                if (f21351a == null) {
                    f21351a = new C11087b(context);
                }
            }
        }
        return f21351a;
    }

    private C11087b(Context context) {
        this.f21352a = context;
        m5137c();
    }

    /* renamed from: c */
    private void m5137c() {
        this.f21353a = new C11088a(this.f21352a);
        this.f21355a = new HashMap();
        SharedPreferences m5152a = m5152a(this.f21352a);
        this.f21353a.f21358a = m5152a.getString("appId", null);
        this.f21353a.f21360b = m5152a.getString("appToken", null);
        this.f21353a.f21362c = m5152a.getString("regId", null);
        this.f21353a.f21363d = m5152a.getString("regSec", null);
        this.f21353a.f21365f = m5152a.getString("devId", null);
        if (!TextUtils.isEmpty(this.f21353a.f21365f) && C11455i.m3044a(this.f21353a.f21365f)) {
            this.f21353a.f21365f = C11455i.m3030h(this.f21352a);
            m5152a.edit().putString("devId", this.f21353a.f21365f).commit();
        }
        this.f21353a.f21364e = m5152a.getString("vName", null);
        this.f21353a.f21359a = m5152a.getBoolean("valid", true);
        this.f21353a.f21361b = m5152a.getBoolean("paused", false);
        this.f21353a.f21356a = m5152a.getInt("envType", 1);
        this.f21353a.f21366g = m5152a.getString("regResource", null);
        this.f21353a.f21367h = m5152a.getString("appRegion", null);
    }

    /* renamed from: a */
    public boolean m5154a() {
        Context context = this.f21352a;
        return !TextUtils.equals(C11395g.m3717a(context, context.getPackageName()), this.f21353a.f21364e);
    }

    /* renamed from: a */
    public void m5149a(String str) {
        SharedPreferences.Editor edit = m5152a(this.f21352a).edit();
        edit.putString("vName", str);
        edit.commit();
        this.f21353a.f21364e = str;
    }

    /* renamed from: b */
    public boolean m5141b() {
        if (this.f21353a.m5127a()) {
            return true;
        }
        AbstractC11049b.m5282a("Don't send message before initialization succeeded!");
        return false;
    }

    /* renamed from: a */
    public String m5156a() {
        return this.f21353a.f21358a;
    }

    /* renamed from: b */
    public String m5143b() {
        return this.f21353a.f21360b;
    }

    /* renamed from: c */
    public String m5138c() {
        return this.f21353a.f21362c;
    }

    /* renamed from: d */
    public String m5135d() {
        return this.f21353a.f21363d;
    }

    /* renamed from: e */
    public String m5133e() {
        return this.f21353a.f21366g;
    }

    /* renamed from: a */
    public boolean m5147a(String str, String str2) {
        return this.f21353a.m5122a(str, str2);
    }

    /* renamed from: f */
    public String m5131f() {
        return this.f21353a.f21367h;
    }

    /* renamed from: a */
    public void m5146a(String str, String str2, String str3) {
        this.f21353a.m5121a(str, str2, str3);
    }

    /* renamed from: b */
    public void m5139b(String str, String str2, String str3) {
        this.f21353a.m5118b(str, str2, str3);
    }

    /* renamed from: a */
    public void m5155a() {
        this.f21353a.m5128a();
    }

    /* renamed from: c */
    public boolean m5136c() {
        return this.f21353a.m5127a();
    }

    /* renamed from: d */
    public boolean m5134d() {
        return (TextUtils.isEmpty(this.f21353a.f21358a) || TextUtils.isEmpty(this.f21353a.f21360b) || TextUtils.isEmpty(this.f21353a.f21362c) || TextUtils.isEmpty(this.f21353a.f21363d)) ? false : true;
    }

    /* renamed from: a */
    public C11088a m5150a(String str) {
        if (this.f21355a.containsKey(str)) {
            return this.f21355a.get(str);
        }
        String str2 = "hybrid_app_info_" + str;
        SharedPreferences m5152a = m5152a(this.f21352a);
        if (m5152a.contains(str2)) {
            C11088a m5125a = C11088a.m5125a(this.f21352a, m5152a.getString(str2, ""));
            this.f21355a.put(str2, m5125a);
            return m5125a;
        }
        return null;
    }

    /* renamed from: a */
    public void m5148a(String str, C11088a c11088a) {
        this.f21355a.put(str, c11088a);
        String m5124a = C11088a.m5124a(c11088a);
        m5152a(this.f21352a).edit().putString("hybrid_app_info_" + str, m5124a).commit();
    }

    /* renamed from: b */
    public void m5140b(String str) {
        this.f21355a.remove(str);
        m5152a(this.f21352a).edit().remove("hybrid_app_info_" + str).commit();
    }

    /* renamed from: a */
    public boolean m5145a(String str, String str2, String str3) {
        C11088a m5150a = m5150a(str3);
        return m5150a != null && TextUtils.equals(str, m5150a.f21358a) && TextUtils.equals(str2, m5150a.f21360b);
    }

    @NBSInstrumented
    /* renamed from: com.xiaomi.mipush.sdk.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11088a {

        /* renamed from: a */
        private Context f21357a;

        /* renamed from: a */
        public String f21358a;

        /* renamed from: b */
        public String f21360b;

        /* renamed from: c */
        public String f21362c;

        /* renamed from: d */
        public String f21363d;

        /* renamed from: e */
        public String f21364e;

        /* renamed from: f */
        public String f21365f;

        /* renamed from: g */
        public String f21366g;

        /* renamed from: h */
        public String f21367h;

        /* renamed from: a */
        public boolean f21359a = true;

        /* renamed from: b */
        public boolean f21361b = false;

        /* renamed from: a */
        public int f21356a = 1;

        public C11088a(Context context) {
            this.f21357a = context;
        }

        /* renamed from: a */
        public void m5121a(String str, String str2, String str3) {
            this.f21358a = str;
            this.f21360b = str2;
            this.f21366g = str3;
            SharedPreferences.Editor edit = C11087b.m5152a(this.f21357a).edit();
            edit.putString("appId", this.f21358a);
            edit.putString("appToken", str2);
            edit.putString("regResource", str3);
            edit.commit();
        }

        /* renamed from: b */
        public void m5118b(String str, String str2, String str3) {
            this.f21362c = str;
            this.f21363d = str2;
            this.f21365f = C11455i.m3030h(this.f21357a);
            this.f21364e = m5129a();
            this.f21359a = true;
            this.f21367h = str3;
            SharedPreferences.Editor edit = C11087b.m5152a(this.f21357a).edit();
            edit.putString("regId", str);
            edit.putString("regSec", str2);
            edit.putString("devId", this.f21365f);
            edit.putString("vName", m5129a());
            edit.putBoolean("valid", true);
            edit.putString("appRegion", str3);
            edit.commit();
        }

        /* renamed from: c */
        public void m5117c(String str, String str2, String str3) {
            this.f21358a = str;
            this.f21360b = str2;
            this.f21366g = str3;
        }

        /* renamed from: a */
        public void m5123a(String str, String str2) {
            this.f21362c = str;
            this.f21363d = str2;
            this.f21365f = C11455i.m3030h(this.f21357a);
            this.f21364e = m5129a();
            this.f21359a = true;
        }

        /* renamed from: a */
        public boolean m5122a(String str, String str2) {
            boolean z;
            boolean equals = TextUtils.equals(this.f21358a, str);
            boolean equals2 = TextUtils.equals(this.f21360b, str2);
            boolean z2 = !TextUtils.isEmpty(this.f21362c);
            boolean z3 = !TextUtils.isEmpty(this.f21363d);
            if (TextUtils.isEmpty(C11455i.m3041b(this.f21357a))) {
                z = true;
            } else {
                z = TextUtils.equals(this.f21365f, C11455i.m3030h(this.f21357a)) || TextUtils.equals(this.f21365f, C11455i.m3031g(this.f21357a));
            }
            boolean z4 = equals && equals2 && z2 && z3 && z;
            if (!z4) {
                AbstractC11049b.m5266e(String.format("register invalid, aid=%s;atn=%s;rid=%s;rse=%s;did=%s", Boolean.valueOf(equals), Boolean.valueOf(equals2), Boolean.valueOf(z2), Boolean.valueOf(z3), Boolean.valueOf(z)));
            }
            return z4;
        }

        /* renamed from: a */
        public boolean m5127a() {
            return m5122a(this.f21358a, this.f21360b);
        }

        /* renamed from: a */
        private String m5129a() {
            Context context = this.f21357a;
            return C11395g.m3717a(context, context.getPackageName());
        }

        /* renamed from: a */
        public void m5128a() {
            C11087b.m5152a(this.f21357a).edit().clear().commit();
            this.f21358a = null;
            this.f21360b = null;
            this.f21362c = null;
            this.f21363d = null;
            this.f21365f = null;
            this.f21364e = null;
            this.f21359a = false;
            this.f21361b = false;
            this.f21367h = null;
            this.f21356a = 1;
        }

        /* renamed from: a */
        public void m5120a(boolean z) {
            this.f21361b = z;
        }

        /* renamed from: a */
        public void m5126a(int i) {
            this.f21356a = i;
        }

        /* renamed from: b */
        public void m5119b() {
            this.f21359a = false;
            C11087b.m5152a(this.f21357a).edit().putBoolean("valid", this.f21359a).commit();
        }

        /* renamed from: a */
        public static C11088a m5125a(Context context, String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                C11088a c11088a = new C11088a(context);
                c11088a.f21358a = jSONObject.getString("appId");
                c11088a.f21360b = jSONObject.getString("appToken");
                c11088a.f21362c = jSONObject.getString("regId");
                c11088a.f21363d = jSONObject.getString("regSec");
                c11088a.f21365f = jSONObject.getString("devId");
                c11088a.f21364e = jSONObject.getString("vName");
                c11088a.f21359a = jSONObject.getBoolean("valid");
                c11088a.f21361b = jSONObject.getBoolean("paused");
                c11088a.f21356a = jSONObject.getInt("envType");
                c11088a.f21366g = jSONObject.getString("regResource");
                return c11088a;
            } catch (Throwable th) {
                AbstractC11049b.m5276a(th);
                return null;
            }
        }

        /* renamed from: a */
        public static String m5124a(C11088a c11088a) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appId", c11088a.f21358a);
                jSONObject.put("appToken", c11088a.f21360b);
                jSONObject.put("regId", c11088a.f21362c);
                jSONObject.put("regSec", c11088a.f21363d);
                jSONObject.put("devId", c11088a.f21365f);
                jSONObject.put("vName", c11088a.f21364e);
                jSONObject.put("valid", c11088a.f21359a);
                jSONObject.put("paused", c11088a.f21361b);
                jSONObject.put("envType", c11088a.f21356a);
                jSONObject.put("regResource", c11088a.f21366g);
                return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            } catch (Throwable th) {
                AbstractC11049b.m5276a(th);
                return null;
            }
        }
    }

    /* renamed from: a */
    public static SharedPreferences m5152a(Context context) {
        return context.getSharedPreferences("mipush", 0);
    }

    /* renamed from: b */
    public void m5142b() {
        this.f21353a.m5119b();
    }

    /* renamed from: e */
    public boolean m5132e() {
        return this.f21353a.f21361b;
    }

    /* renamed from: a */
    public int m5157a() {
        return this.f21353a.f21356a;
    }

    /* renamed from: a */
    public void m5144a(boolean z) {
        this.f21353a.m5120a(z);
        m5152a(this.f21352a).edit().putBoolean("paused", z).commit();
    }

    /* renamed from: a */
    public void m5153a(int i) {
        this.f21353a.m5126a(i);
        m5152a(this.f21352a).edit().putInt("envType", i).commit();
    }

    /* renamed from: f */
    public boolean m5130f() {
        return !this.f21353a.f21359a;
    }
}
