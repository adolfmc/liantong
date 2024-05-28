package com.alipay.sdk.protocol;

import android.text.TextUtils;
import com.alipay.sdk.sys.C2033b;
import com.alipay.sdk.tid.C2035b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.protocol.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2031b {

    /* renamed from: a */
    private EnumC2030a f3809a;

    /* renamed from: b */
    private String f3810b;

    /* renamed from: c */
    private String[] f3811c;

    public C2031b(String str) {
        this.f3810b = str;
    }

    public C2031b(String str, EnumC2030a enumC2030a) {
        this.f3810b = str;
        this.f3809a = enumC2030a;
    }

    /* renamed from: a */
    public static void m20786a(C2031b c2031b) {
        String[] m20781c = c2031b.m20781c();
        if (m20781c.length == 3 && TextUtils.equals("tid", m20781c[0])) {
            C2035b m20758a = C2035b.m20758a(C2033b.m20772a().m20770b());
            if (TextUtils.isEmpty(m20781c[1]) || TextUtils.isEmpty(m20781c[2])) {
                return;
            }
            m20758a.m20757a(m20781c[1], m20781c[2]);
        }
    }

    /* renamed from: a */
    public static List<C2031b> m20784a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        String[] m20782b = m20782b(jSONObject.optString("name", ""));
        for (int i = 0; i < m20782b.length; i++) {
            EnumC2030a m20788a = EnumC2030a.m20788a(m20782b[i]);
            if (m20788a != EnumC2030a.None) {
                C2031b c2031b = new C2031b(m20782b[i], m20788a);
                c2031b.f3811c = m20785a(m20782b[i]);
                arrayList.add(c2031b);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static String[] m20785a(String str) {
        ArrayList arrayList = new ArrayList();
        int indexOf = str.indexOf(40);
        int lastIndexOf = str.lastIndexOf(41);
        if (indexOf == -1 || lastIndexOf == -1 || lastIndexOf <= indexOf) {
            return null;
        }
        for (String str2 : str.substring(indexOf + 1, lastIndexOf).split("' *, *'", -1)) {
            arrayList.add(str2.trim().replaceAll("'", "").replaceAll("\"", ""));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* renamed from: b */
    private static String[] m20782b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(";");
    }

    /* renamed from: a */
    public String m20787a() {
        return this.f3810b;
    }

    /* renamed from: b */
    public EnumC2030a m20783b() {
        return this.f3809a;
    }

    /* renamed from: c */
    public String[] m20781c() {
        return this.f3811c;
    }
}
