package com.baidu.p122b;

import android.text.TextUtils;
import com.baidu.p122b.p125c.p126a.C2396c;
import com.baidu.p122b.p125c.p126a.C2400g;
import com.baidu.p122b.p130d.C2415a;
import com.baidu.p122b.p132f.C2424c;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2421f {

    /* renamed from: a */
    public String f4269a;

    /* renamed from: b */
    public String f4270b;

    /* renamed from: c */
    public int f4271c = 2;

    /* renamed from: d */
    private int f4272d = 0;

    /* renamed from: a */
    public static C2421f m20188a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        C2421f c2421f = new C2421f();
        c2421f.f4269a = str;
        c2421f.f4272d = TextUtils.isEmpty(str2) ? 0 : str2.length();
        if (c2421f.f4272d < 14) {
            if (TextUtils.isEmpty(str2)) {
                str2 = "0";
            }
            c2421f.f4270b = str2;
        }
        return c2421f;
    }

    /* renamed from: a */
    public static boolean m20190a(int i) {
        return i >= 14;
    }

    /* renamed from: a */
    public static boolean m20189a(String str) {
        return TextUtils.isEmpty(str);
    }

    /* renamed from: b */
    public static C2421f m20186b(String str) {
        return m20184c(m20182e(str));
    }

    /* renamed from: c */
    private static C2421f m20184c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            String str2 = "0";
            while (keys.hasNext()) {
                String next = keys.next();
                if (!m20183d("ZGV2aWNlaWQ=").equals(next) && !m20183d("dmVy").equals(next)) {
                    str2 = jSONObject.optString(next, "0");
                }
            }
            String string = jSONObject.getString(m20183d("ZGV2aWNlaWQ="));
            int i = jSONObject.getInt(m20183d("dmVy"));
            int length = TextUtils.isEmpty(str2) ? 0 : str2.length();
            if (!TextUtils.isEmpty(string)) {
                C2421f c2421f = new C2421f();
                c2421f.f4269a = string;
                c2421f.f4271c = i;
                c2421f.f4272d = length;
                if (c2421f.f4272d < 14) {
                    if (TextUtils.isEmpty(str2)) {
                        str2 = "0";
                    }
                    c2421f.f4270b = str2;
                }
                c2421f.m20185c();
                return c2421f;
            }
        } catch (JSONException e) {
            C2424c.m20177a(e);
        }
        return null;
    }

    /* renamed from: d */
    private static String m20183d(String str) {
        return new String(C2415a.m20212a(str.getBytes()));
    }

    /* renamed from: e */
    private static String m20182e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] m20265a = C2400g.m20265a();
            return new String(C2396c.m20279a(m20265a, m20265a, C2415a.m20212a(str.getBytes())));
        } catch (Exception e) {
            C2424c.m20177a(e);
            return "";
        }
    }

    /* renamed from: a */
    boolean m20191a() {
        return m20189a(this.f4270b);
    }

    /* renamed from: b */
    boolean m20187b() {
        return m20190a(this.f4272d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean m20185c() {
        String str;
        if (m20187b()) {
            str = "O";
        } else if (!m20191a()) {
            return false;
        } else {
            str = "0";
        }
        this.f4270b = str;
        return true;
    }
}
