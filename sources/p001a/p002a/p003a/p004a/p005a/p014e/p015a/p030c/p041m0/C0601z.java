package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.math.BigInteger;

/* renamed from: a.a.a.a.a.e.a.c.m0.z */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0601z extends C0598w {

    /* renamed from: c */
    public BigInteger f1895c;

    public C0601z(BigInteger bigInteger, C0599x c0599x) {
        super(false, c0599x);
        this.f1895c = bigInteger;
    }

    /* renamed from: c */
    public BigInteger m22749c() {
        return this.f1895c;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0598w
    public boolean equals(Object obj) {
        return (obj instanceof C0601z) && ((C0601z) obj).m22749c().equals(this.f1895c) && super.equals(obj);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0598w
    public int hashCode() {
        return this.f1895c.hashCode() ^ super.hashCode();
    }
}
