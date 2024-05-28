package com.xiaomi.push;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.fm */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11372fm extends AbstractC11375fo {

    /* renamed from: a */
    private C11373a f22299a;

    /* renamed from: a */
    private final Map<String, String> f22300a;

    /* renamed from: b */
    public String mo3750b() {
        return null;
    }

    public C11372fm() {
        this.f22299a = C11373a.f22301a;
        this.f22300a = new HashMap();
    }

    public C11372fm(Bundle bundle) {
        super(bundle);
        this.f22299a = C11373a.f22301a;
        this.f22300a = new HashMap();
        if (bundle.containsKey("ext_iq_type")) {
            this.f22299a = C11373a.m3820a(bundle.getString("ext_iq_type"));
        }
    }

    /* renamed from: a */
    public synchronized void m3821a(Map<String, String> map) {
        this.f22300a.putAll(map);
    }

    /* renamed from: a */
    public C11373a m3823a() {
        return this.f22299a;
    }

    /* renamed from: a */
    public void m3822a(C11373a c11373a) {
        if (c11373a == null) {
            this.f22299a = C11373a.f22301a;
        } else {
            this.f22299a = c11373a;
        }
    }

    @Override // com.xiaomi.push.AbstractC11375fo
    /* renamed from: a */
    public Bundle mo3776a() {
        Bundle mo3776a = super.mo3776a();
        C11373a c11373a = this.f22299a;
        if (c11373a != null) {
            mo3776a.putString("ext_iq_type", c11373a.toString());
        }
        return mo3776a;
    }

    @Override // com.xiaomi.push.AbstractC11375fo
    /* renamed from: a */
    public String mo3775a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<iq ");
        if (m3790j() != null) {
            sb.append("id=\"" + m3790j() + "\" ");
        }
        if (m3787l() != null) {
            sb.append("to=\"");
            sb.append(C11389fx.m3748a(m3787l()));
            sb.append("\" ");
        }
        if (m3785m() != null) {
            sb.append("from=\"");
            sb.append(C11389fx.m3748a(m3785m()));
            sb.append("\" ");
        }
        if (m3789k() != null) {
            sb.append("chid=\"");
            sb.append(C11389fx.m3748a(m3789k()));
            sb.append("\" ");
        }
        for (Map.Entry<String, String> entry : this.f22300a.entrySet()) {
            sb.append(C11389fx.m3748a(entry.getKey()));
            sb.append("=\"");
            sb.append(C11389fx.m3748a(entry.getValue()));
            sb.append("\" ");
        }
        if (this.f22299a == null) {
            sb.append("type=\"get\">");
        } else {
            sb.append("type=\"");
            sb.append(m3823a());
            sb.append("\">");
        }
        String mo3750b = mo3750b();
        if (mo3750b != null) {
            sb.append(mo3750b);
        }
        sb.append(m3781o());
        C11381fs a = mo3776a();
        if (a != null) {
            sb.append(a.m3769a());
        }
        sb.append("</iq>");
        return sb.toString();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.fm$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11373a {

        /* renamed from: a */
        public static final C11373a f22301a = new C11373a("get");

        /* renamed from: b */
        public static final C11373a f22302b = new C11373a("set");

        /* renamed from: c */
        public static final C11373a f22303c = new C11373a("result");

        /* renamed from: d */
        public static final C11373a f22304d = new C11373a("error");

        /* renamed from: e */
        public static final C11373a f22305e = new C11373a("command");

        /* renamed from: a */
        private String f22306a;

        /* renamed from: a */
        public static C11373a m3820a(String str) {
            if (str == null) {
                return null;
            }
            String lowerCase = str.toLowerCase();
            if (f22301a.toString().equals(lowerCase)) {
                return f22301a;
            }
            if (f22302b.toString().equals(lowerCase)) {
                return f22302b;
            }
            if (f22304d.toString().equals(lowerCase)) {
                return f22304d;
            }
            if (f22303c.toString().equals(lowerCase)) {
                return f22303c;
            }
            if (f22305e.toString().equals(lowerCase)) {
                return f22305e;
            }
            return null;
        }

        private C11373a(String str) {
            this.f22306a = str;
        }

        public String toString() {
            return this.f22306a;
        }
    }
}
