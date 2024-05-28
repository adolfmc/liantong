package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p022r2;

import java.math.BigInteger;
import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.r2.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0261a extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f538v3;

    /* renamed from: w3 */
    public C0166k f539w3;

    public C0261a(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f538v3 = new C0166k(bigInteger);
        this.f539w3 = new C0166k(bigInteger2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f538v3);
        c0140e.m24170a(this.f539w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23753i() {
        return this.f539w3.m24146m();
    }

    /* renamed from: j */
    public BigInteger m23752j() {
        return this.f538v3.m24146m();
    }

    public C0261a(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f538v3 = (C0166k) mo23747m.nextElement();
        this.f539w3 = (C0166k) mo23747m.nextElement();
    }
}
