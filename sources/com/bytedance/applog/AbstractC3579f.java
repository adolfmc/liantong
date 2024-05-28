package com.bytedance.applog;

import com.bytedance.applog.C3730x2;

/* renamed from: com.bytedance.applog.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC3579f {

    /* renamed from: a */
    public final C3591h f8439a;

    /* renamed from: b */
    public int f8440b;

    /* renamed from: c */
    public boolean f8441c;

    /* renamed from: d */
    public long f8442d;

    /* renamed from: e */
    public boolean f8443e;

    public AbstractC3579f(C3591h c3591h) {
        this.f8439a = c3591h;
    }

    /* renamed from: a */
    public final long m17304a() {
        String str;
        long m17303b = m17303b();
        if (m17303b <= System.currentTimeMillis()) {
            try {
                try {
                    boolean mo17162c = mo17162c();
                    this.f8442d = System.currentTimeMillis();
                    this.f8440b = mo17162c ? 0 : this.f8440b + 1;
                    str = mo17161d() + " worked:" + mo17162c;
                } catch (Exception e) {
                    C3704u2.m17108a("U SHALL NOT PASS!", e);
                    this.f8442d = System.currentTimeMillis();
                    this.f8440b++;
                    str = mo17161d() + " worked:false";
                }
                C3704u2.m17108a(str, (Throwable) null);
                return m17303b();
            } catch (Throwable th) {
                this.f8442d = System.currentTimeMillis();
                this.f8440b++;
                C3704u2.m17108a(mo17161d() + " worked:false", (Throwable) null);
                throw th;
            }
        }
        return m17303b;
    }

    /* renamed from: b */
    public final long m17303b() {
        if (mo17159g() && this.f8439a.m17289b() == C3730x2.EnumC3731a.NONE.f8930a) {
            C3704u2.m17108a("checkWorkTime, 0", (Throwable) null);
            return System.currentTimeMillis() + 5000;
        }
        long j = 0;
        if (this.f8441c) {
            this.f8442d = 0L;
            this.f8441c = false;
        } else {
            int i = this.f8440b;
            if (i > 0) {
                long[] mo17160e = mo17160e();
                j = mo17160e[(i - 1) % mo17160e.length];
            } else {
                j = mo17158h();
            }
        }
        return this.f8442d + j;
    }

    /* renamed from: c */
    public abstract boolean mo17162c();

    /* renamed from: d */
    public abstract String mo17161d();

    /* renamed from: e */
    public abstract long[] mo17160e();

    /* renamed from: f */
    public boolean m17302f() {
        return this.f8443e;
    }

    /* renamed from: g */
    public abstract boolean mo17159g();

    /* renamed from: h */
    public abstract long mo17158h();

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: i */
    public <T extends AbstractC3579f> T m17301i() {
        StringBuilder m17349a = C3535a.m17349a("setImmediately, ");
        m17349a.append(mo17161d());
        C3704u2.m17108a(m17349a.toString(), (Throwable) null);
        this.f8441c = true;
        return this;
    }

    public void setStop(boolean z) {
        this.f8443e = z;
    }
}
