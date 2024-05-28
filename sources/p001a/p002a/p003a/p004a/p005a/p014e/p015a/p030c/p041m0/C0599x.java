package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;

/* renamed from: a.a.a.a.a.e.a.c.m0.x */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0599x implements InterfaceC0542i {

    /* renamed from: a */
    public BigInteger f1891a;

    /* renamed from: b */
    public BigInteger f1892b;

    /* renamed from: c */
    public int f1893c;

    public C0599x(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    /* renamed from: a */
    public BigInteger m22753a() {
        return this.f1891a;
    }

    /* renamed from: b */
    public int m22752b() {
        return this.f1893c;
    }

    /* renamed from: c */
    public BigInteger m22751c() {
        return this.f1892b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0599x) {
            C0599x c0599x = (C0599x) obj;
            return c0599x.m22751c().equals(this.f1892b) && c0599x.m22753a().equals(this.f1891a) && c0599x.m22752b() == this.f1893c;
        }
        return false;
    }

    public int hashCode() {
        return (m22751c().hashCode() ^ m22753a().hashCode()) + this.f1893c;
    }

    public C0599x(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f1891a = bigInteger2;
        this.f1892b = bigInteger;
        this.f1893c = i;
    }
}
