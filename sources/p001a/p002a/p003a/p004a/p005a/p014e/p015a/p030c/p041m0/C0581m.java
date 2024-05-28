package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;

/* renamed from: a.a.a.a.a.e.a.c.m0.m */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0581m implements InterfaceC0542i {

    /* renamed from: a */
    public BigInteger f1853a;

    /* renamed from: b */
    public BigInteger f1854b;

    /* renamed from: c */
    public BigInteger f1855c;

    /* renamed from: d */
    public C0587p f1856d;

    public C0581m(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f1853a = bigInteger3;
        this.f1855c = bigInteger;
        this.f1854b = bigInteger2;
    }

    /* renamed from: a */
    public BigInteger m22792a() {
        return this.f1853a;
    }

    /* renamed from: b */
    public BigInteger m22791b() {
        return this.f1855c;
    }

    /* renamed from: c */
    public BigInteger m22790c() {
        return this.f1854b;
    }

    /* renamed from: d */
    public C0587p m22789d() {
        return this.f1856d;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0581m) {
            C0581m c0581m = (C0581m) obj;
            return c0581m.m22791b().equals(this.f1855c) && c0581m.m22790c().equals(this.f1854b) && c0581m.m22792a().equals(this.f1853a);
        }
        return false;
    }

    public int hashCode() {
        return (m22791b().hashCode() ^ m22790c().hashCode()) ^ m22792a().hashCode();
    }

    public C0581m(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, C0587p c0587p) {
        this.f1853a = bigInteger3;
        this.f1855c = bigInteger;
        this.f1854b = bigInteger2;
        this.f1856d = c0587p;
    }
}
