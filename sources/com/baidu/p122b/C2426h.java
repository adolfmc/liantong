package com.baidu.p122b;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.baidu.p122b.p123a.C2358a;
import com.baidu.p122b.p124b.AbstractC2372a;
import com.baidu.p122b.p124b.C2379c;
import com.baidu.p122b.p130d.C2416b;
import com.baidu.p122b.p131e.C2419a;
import com.baidu.p122b.p132f.C2422a;
import com.baidu.p122b.p132f.C2424c;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2426h {

    /* renamed from: b */
    private static boolean f4279b;

    /* renamed from: a */
    C2393c f4280a;

    /* renamed from: c */
    private Context f4281c;

    /* renamed from: d */
    private C2419a.C2420a f4282d;

    /* renamed from: e */
    private C2379c f4283e;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.baidu.b.h$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2427a {

        /* renamed from: a */
        public static final String[] f4284a = {"V", "O", "0"};

        /* renamed from: b */
        private String f4285b;

        /* renamed from: c */
        private String f4286c;

        /* renamed from: d */
        private String f4287d;

        /* renamed from: e */
        private long f4288e;

        /* renamed from: f */
        private String f4289f;

        /* renamed from: g */
        private int f4290g = 1;

        /* renamed from: a */
        public String m20160a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("dik", this.f4285b);
                jSONObject.put("v270fk", this.f4286c);
                jSONObject.put("cck", this.f4287d);
                jSONObject.put("vsk", this.f4290g);
                jSONObject.put("ctk", this.f4288e);
                jSONObject.put("ek", this.f4289f);
                return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            } catch (JSONException e) {
                C2424c.m20177a(e);
                return null;
            }
        }

        /* renamed from: b */
        public String m20155b() {
            String str = this.f4286c;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.f4285b);
            sb.append("|");
            sb.append(str);
            if ("V".equals(str)) {
                sb.append(this.f4287d);
            }
            if (!TextUtils.isEmpty(this.f4289f)) {
                sb.append(this.f4289f);
            }
            return sb.toString().trim();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C2427a c2427a = (C2427a) obj;
            if (this.f4290g == c2427a.f4290g && this.f4285b.equals(c2427a.f4285b) && this.f4286c.equals(c2427a.f4286c) && this.f4287d.equals(c2427a.f4287d)) {
                String str = this.f4289f;
                String str2 = c2427a.f4289f;
                if (str == str2) {
                    return true;
                }
                if (str != null && str.equals(str2)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.f4285b, this.f4286c, this.f4287d, this.f4289f, Integer.valueOf(this.f4290g)});
        }
    }

    public C2426h(Context context, C2419a c2419a, C2393c c2393c) {
        if (context == null) {
            throw new NullPointerException("context should not be null!!!");
        }
        this.f4281c = context.getApplicationContext();
        this.f4282d = c2419a.m20201b().m20197a("bohrium");
        this.f4282d.m20199a();
        this.f4280a = c2393c;
        m20166a(c2419a);
    }

    /* renamed from: a */
    public static C2427a m20164a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("dik", "");
            String optString2 = jSONObject.optString("cck", "");
            long optLong = jSONObject.optLong("ctk", 0L);
            int optInt = jSONObject.optInt("vsk", 1);
            String optString3 = jSONObject.optString("ek", "");
            String optString4 = jSONObject.optString("v270fk", "V");
            if (!TextUtils.isEmpty(optString)) {
                C2427a c2427a = new C2427a();
                c2427a.f4285b = optString;
                c2427a.f4287d = optString2;
                c2427a.f4288e = optLong;
                c2427a.f4290g = optInt;
                c2427a.f4289f = optString3;
                c2427a.f4286c = optString4;
                return c2427a;
            }
        } catch (Exception e) {
            C2424c.m20177a(e);
        }
        return null;
    }

    /* renamed from: a */
    public static C2427a m20163a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                String m20161c = m20161c(str);
                long currentTimeMillis = System.currentTimeMillis();
                C2427a c2427a = new C2427a();
                c2427a.f4285b = str;
                c2427a.f4287d = m20161c;
                c2427a.f4288e = currentTimeMillis;
                c2427a.f4290g = 1;
                c2427a.f4289f = str3;
                c2427a.f4286c = str2;
                return c2427a;
            } catch (Exception e) {
                C2424c.m20177a(e);
            }
        }
        return null;
    }

    /* renamed from: a */
    private String m20167a(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    /* renamed from: a */
    private void m20166a(C2419a c2419a) {
        C2379c c2379c = new C2379c(new C2357a());
        AbstractC2372a.C2373a c2373a = new AbstractC2372a.C2373a();
        c2373a.f4132a = this.f4281c;
        c2373a.f4133b = c2419a;
        AbstractC2372a.C2375c c2375c = new AbstractC2372a.C2375c();
        for (AbstractC2372a abstractC2372a : c2379c.m20343a()) {
            abstractC2372a.m20353a(c2373a);
            abstractC2372a.mo20326a(c2375c);
        }
        this.f4283e = c2379c;
    }

    /* renamed from: c */
    private static String m20161c(String str) {
        try {
            return new C2422a("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567=", false, false).m20180a(new C2358a().m20412a(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public C2427a m20168a() {
        AbstractC2372a.C2376d c2376d = new AbstractC2372a.C2376d();
        c2376d.f4138a = true;
        List<AbstractC2372a> m20343a = this.f4283e.m20343a();
        Collections.sort(m20343a, AbstractC2372a.f4127c);
        List<C2371b> m20292b = this.f4280a.m20292b(this.f4281c);
        if (m20292b != null) {
            for (C2371b c2371b : m20292b) {
                if (!c2371b.f4126d && c2371b.f4125c) {
                    for (AbstractC2372a abstractC2372a : m20343a) {
                        AbstractC2372a.C2377e mo20321a = abstractC2372a.mo20321a(c2371b.f4123a.packageName, c2376d);
                        if (mo20321a != null && mo20321a.m20348a() && mo20321a.f4139a != null) {
                            return mo20321a.f4139a;
                        }
                    }
                    continue;
                }
            }
            return null;
        }
        return null;
    }

    /* renamed from: a */
    public C2427a m20165a(C2421f c2421f) {
        if (c2421f != null) {
            C2427a c2427a = new C2427a();
            c2427a.f4288e = System.currentTimeMillis();
            c2427a.f4290g = 1;
            try {
                boolean z = false;
                c2427a.f4286c = c2421f.f4270b.substring(0, 1);
                c2427a.f4285b = c2421f.f4269a;
                c2427a.f4287d = m20161c(c2421f.f4269a);
                String[] strArr = C2427a.f4284a;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = true;
                        break;
                    } else if (strArr[i].equals(c2427a.f4286c)) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (z && c2421f.f4270b != null && c2421f.f4270b.length() >= 2) {
                    c2427a.f4289f = c2421f.f4270b.substring(1);
                }
                return c2427a;
            } catch (Exception unused) {
                return null;
            }
        }
        throw new IllegalArgumentException("arg non-nullable is expected");
    }

    /* renamed from: b */
    public C2427a m20162b(String str) {
        String m20167a = m20167a(this.f4281c);
        String m20209a = C2416b.m20209a(("com.baidu" + m20167a).getBytes(), true);
        C2427a c2427a = new C2427a();
        c2427a.f4288e = System.currentTimeMillis();
        c2427a.f4290g = 1;
        c2427a.f4285b = m20209a;
        c2427a.f4286c = "E";
        c2427a.f4287d = m20161c(m20209a);
        c2427a.f4289f = "RO";
        return c2427a;
    }
}
