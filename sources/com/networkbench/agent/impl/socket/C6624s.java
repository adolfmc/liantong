package com.networkbench.agent.impl.socket;

import com.networkbench.agent.impl.util.C6631a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.s */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6624s {

    /* renamed from: a */
    private String f17055a;

    /* renamed from: b */
    private String f17056b;

    /* renamed from: c */
    private String f17057c = "/";

    /* renamed from: d */
    private EnumC6625a f17058d = null;

    /* renamed from: e */
    private int f17059e = -1;

    /* renamed from: f */
    private boolean f17060f = false;

    /* renamed from: a */
    public String m9197a() {
        return this.f17055a;
    }

    /* renamed from: b */
    public String m9191b() {
        return this.f17056b;
    }

    /* renamed from: c */
    public int m9189c() {
        return this.f17059e;
    }

    /* renamed from: a */
    public void m9196a(int i) {
        C6631a.m9134a(i > 0);
        if (i > 0) {
            this.f17059e = i;
        }
    }

    /* renamed from: a */
    public void m9192a(boolean z) {
        this.f17060f = z;
    }

    /* renamed from: a */
    public void m9194a(String str) {
        this.f17055a = str;
    }

    /* renamed from: b */
    public void m9190b(String str) {
        this.f17056b = str;
    }

    /* renamed from: c */
    public void m9188c(String str) {
        if (str != null) {
            this.f17057c = str;
        }
    }

    /* renamed from: d */
    public String m9187d() {
        return this.f17057c;
    }

    /* renamed from: a */
    public void m9195a(EnumC6625a enumC6625a) {
        this.f17058d = enumC6625a;
    }

    /* renamed from: e */
    public EnumC6625a m9185e() {
        return this.f17058d;
    }

    /* renamed from: g */
    private String m9183g() {
        String str = this.f17056b;
        return str != null ? str : "unknown-host";
    }

    /* renamed from: d */
    public boolean m9186d(String str) {
        return str != null && (str.regionMatches(true, 0, "http:", 0, 5) || str.regionMatches(true, 0, "https:", 0, 6));
    }

    /* renamed from: f */
    public String m9184f() {
        EnumC6625a enumC6625a;
        String m9183g = m9183g();
        if (this.f17060f) {
            return m9193a(m9183g, this.f17059e);
        }
        String str = this.f17057c;
        String str2 = "";
        if (m9186d(str)) {
            return str;
        }
        if (this.f17058d != null) {
            str2 = "" + this.f17058d.f17064c + ":";
        }
        if (str.startsWith("//")) {
            return str2 + str;
        }
        String str3 = str2 + "//";
        if (str.startsWith(m9183g)) {
            return str3 + str;
        }
        String str4 = "";
        if (this.f17059e > 0 && ((enumC6625a = this.f17058d) == null || enumC6625a.f17065d != this.f17059e)) {
            String str5 = ":" + this.f17059e;
            if (!m9183g.endsWith(str5)) {
                str4 = str5;
            }
        }
        return str3 + m9183g + str4 + str;
    }

    /* renamed from: a */
    private String m9193a(String str, int i) {
        if (i > 0) {
            String str2 = ":" + i;
            if (!str.endsWith(str2)) {
                return str + str2;
            }
        }
        return str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.socket.s$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6625a {
        HTTP("http", 80),
        HTTPS("https", 443);
        

        /* renamed from: c */
        private String f17064c;

        /* renamed from: d */
        private int f17065d;

        EnumC6625a(String str, int i) {
            this.f17064c = str;
            this.f17065d = i;
        }

        /* renamed from: a */
        public String m9182a() {
            return this.f17064c;
        }

        /* renamed from: b */
        public int m9180b() {
            return this.f17065d;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("hostAddress: " + this.f17055a);
        sb.append("hostname: " + this.f17056b);
        sb.append("httpPath: " + this.f17057c);
        sb.append("scheme: " + this.f17058d);
        sb.append("hostPort: " + this.f17059e);
        return sb.toString();
    }
}
