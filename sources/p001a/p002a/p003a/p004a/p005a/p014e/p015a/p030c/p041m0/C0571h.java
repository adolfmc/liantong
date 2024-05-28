package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.math.BigInteger;

/* renamed from: a.a.a.a.a.e.a.c.m0.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0571h extends C0565e {

    /* renamed from: c */
    public BigInteger f1830c;

    public C0571h(BigInteger bigInteger, C0567f c0567f) {
        super(false, c0567f);
        this.f1830c = bigInteger;
    }

    /* renamed from: c */
    public BigInteger m22813c() {
        return this.f1830c;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0565e
    public boolean equals(Object obj) {
        return (obj instanceof C0571h) && ((C0571h) obj).m22813c().equals(this.f1830c) && super.equals(obj);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0565e
    public int hashCode() {
        return this.f1830c.hashCode() ^ super.hashCode();
    }
}
