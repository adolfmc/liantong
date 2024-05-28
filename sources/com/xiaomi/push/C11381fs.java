package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.xiaomi.push.fs */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11381fs {

    /* renamed from: a */
    private int f22355a;

    /* renamed from: a */
    private String f22356a;

    /* renamed from: a */
    private List<C11371fl> f22357a;

    /* renamed from: b */
    private String f22358b;

    /* renamed from: c */
    private String f22359c;

    /* renamed from: d */
    private String f22360d;

    public C11381fs(C11382a c11382a) {
        this.f22357a = null;
        m3767a(c11382a);
        this.f22360d = null;
    }

    public C11381fs(int i, String str, String str2, String str3, String str4, List<C11371fl> list) {
        this.f22357a = null;
        this.f22355a = i;
        this.f22356a = str;
        this.f22359c = str2;
        this.f22358b = str3;
        this.f22360d = str4;
        this.f22357a = list;
    }

    public C11381fs(Bundle bundle) {
        this.f22357a = null;
        this.f22355a = bundle.getInt("ext_err_code");
        if (bundle.containsKey("ext_err_type")) {
            this.f22356a = bundle.getString("ext_err_type");
        }
        this.f22358b = bundle.getString("ext_err_cond");
        this.f22359c = bundle.getString("ext_err_reason");
        this.f22360d = bundle.getString("ext_err_msg");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f22357a = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                C11371fl m3831a = C11371fl.m3831a((Bundle) parcelable);
                if (m3831a != null) {
                    this.f22357a.add(m3831a);
                }
            }
        }
    }

    /* renamed from: a */
    private void m3767a(C11382a c11382a) {
        this.f22358b = c11382a.f22385a;
    }

    /* renamed from: a */
    public Bundle m3770a() {
        Bundle bundle = new Bundle();
        String str = this.f22356a;
        if (str != null) {
            bundle.putString("ext_err_type", str);
        }
        bundle.putInt("ext_err_code", this.f22355a);
        String str2 = this.f22359c;
        if (str2 != null) {
            bundle.putString("ext_err_reason", str2);
        }
        String str3 = this.f22358b;
        if (str3 != null) {
            bundle.putString("ext_err_cond", str3);
        }
        String str4 = this.f22360d;
        if (str4 != null) {
            bundle.putString("ext_err_msg", str4);
        }
        List<C11371fl> list = this.f22357a;
        if (list != null) {
            Bundle[] bundleArr = new Bundle[list.size()];
            int i = 0;
            for (C11371fl c11371fl : this.f22357a) {
                Bundle m3834a = c11371fl.m3834a();
                if (m3834a != null) {
                    bundleArr[i] = m3834a;
                    i++;
                }
            }
            bundle.putParcelableArray("ext_exts", bundleArr);
        }
        return bundle;
    }

    /* renamed from: a */
    public String m3769a() {
        StringBuilder sb = new StringBuilder();
        sb.append("<error code=\"");
        sb.append(this.f22355a);
        sb.append("\"");
        if (this.f22356a != null) {
            sb.append(" type=\"");
            sb.append(this.f22356a);
            sb.append("\"");
        }
        if (this.f22359c != null) {
            sb.append(" reason=\"");
            sb.append(this.f22359c);
            sb.append("\"");
        }
        sb.append(">");
        if (this.f22358b != null) {
            sb.append("<");
            sb.append(this.f22358b);
            sb.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.f22360d != null) {
            sb.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            sb.append(this.f22360d);
            sb.append("</text>");
        }
        for (C11371fl c11371fl : m3768a()) {
            sb.append(c11371fl.mo3777d());
        }
        sb.append("</error>");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f22358b;
        if (str != null) {
            sb.append(str);
        }
        sb.append("(");
        sb.append(this.f22355a);
        sb.append(")");
        if (this.f22360d != null) {
            sb.append(" ");
            sb.append(this.f22360d);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public synchronized List<C11371fl> m3768a() {
        if (this.f22357a == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(this.f22357a);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.fs$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11382a {

        /* renamed from: a */
        public static final C11382a f22361a = new C11382a("internal-server-error");

        /* renamed from: b */
        public static final C11382a f22362b = new C11382a("forbidden");

        /* renamed from: c */
        public static final C11382a f22363c = new C11382a("bad-request");

        /* renamed from: d */
        public static final C11382a f22364d = new C11382a("conflict");

        /* renamed from: e */
        public static final C11382a f22365e = new C11382a("feature-not-implemented");

        /* renamed from: f */
        public static final C11382a f22366f = new C11382a("gone");

        /* renamed from: g */
        public static final C11382a f22367g = new C11382a("item-not-found");

        /* renamed from: h */
        public static final C11382a f22368h = new C11382a("jid-malformed");

        /* renamed from: i */
        public static final C11382a f22369i = new C11382a("not-acceptable");

        /* renamed from: j */
        public static final C11382a f22370j = new C11382a("not-allowed");

        /* renamed from: k */
        public static final C11382a f22371k = new C11382a("not-authorized");

        /* renamed from: l */
        public static final C11382a f22372l = new C11382a("payment-required");

        /* renamed from: m */
        public static final C11382a f22373m = new C11382a("recipient-unavailable");

        /* renamed from: n */
        public static final C11382a f22374n = new C11382a("redirect");

        /* renamed from: o */
        public static final C11382a f22375o = new C11382a("registration-required");

        /* renamed from: p */
        public static final C11382a f22376p = new C11382a("remote-server-error");

        /* renamed from: q */
        public static final C11382a f22377q = new C11382a("remote-server-not-found");

        /* renamed from: r */
        public static final C11382a f22378r = new C11382a("remote-server-timeout");

        /* renamed from: s */
        public static final C11382a f22379s = new C11382a("resource-constraint");

        /* renamed from: t */
        public static final C11382a f22380t = new C11382a("service-unavailable");

        /* renamed from: u */
        public static final C11382a f22381u = new C11382a("subscription-required");

        /* renamed from: v */
        public static final C11382a f22382v = new C11382a("undefined-condition");

        /* renamed from: w */
        public static final C11382a f22383w = new C11382a("unexpected-request");

        /* renamed from: x */
        public static final C11382a f22384x = new C11382a("request-timeout");

        /* renamed from: a */
        private String f22385a;

        public C11382a(String str) {
            this.f22385a = str;
        }

        public String toString() {
            return this.f22385a;
        }
    }
}
