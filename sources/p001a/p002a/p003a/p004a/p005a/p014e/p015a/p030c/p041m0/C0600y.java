package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.math.BigInteger;

/* renamed from: a.a.a.a.a.e.a.c.m0.y */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0600y extends C0598w {

    /* renamed from: c */
    public BigInteger f1894c;

    public C0600y(BigInteger bigInteger, C0599x c0599x) {
        super(true, c0599x);
        this.f1894c = bigInteger;
    }

    /* renamed from: c */
    public BigInteger m22750c() {
        return this.f1894c;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0598w
    public boolean equals(Object obj) {
        if ((obj instanceof C0600y) && ((C0600y) obj).m22750c().equals(this.f1894c)) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0598w
    public int hashCode() {
        return m22750c().hashCode();
    }
}
