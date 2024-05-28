package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.math.BigInteger;

/* renamed from: a.a.a.a.a.e.a.c.m0.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0569g extends C0565e {

    /* renamed from: c */
    public BigInteger f1828c;

    public C0569g(BigInteger bigInteger, C0567f c0567f) {
        super(true, c0567f);
        this.f1828c = bigInteger;
    }

    /* renamed from: c */
    public BigInteger m22815c() {
        return this.f1828c;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0565e
    public boolean equals(Object obj) {
        return (obj instanceof C0569g) && ((C0569g) obj).m22815c().equals(this.f1828c) && super.equals(obj);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0565e
    public int hashCode() {
        return this.f1828c.hashCode() ^ super.hashCode();
    }
}
