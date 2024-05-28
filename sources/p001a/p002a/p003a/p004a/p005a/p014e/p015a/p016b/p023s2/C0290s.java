package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.s2.s */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0290s extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f631v3;

    /* renamed from: w3 */
    public AbstractC0182o f632w3;

    public C0290s(byte[] bArr, int i) {
        this.f632w3 = new C0168k1(bArr);
        this.f631v3 = new C0166k(i);
    }

    /* renamed from: a */
    public static C0290s m23654a(Object obj) {
        if (obj instanceof C0290s) {
            return (C0290s) obj;
        }
        if (obj != null) {
            return new C0290s(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f632w3);
        c0140e.m24170a(this.f631v3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public byte[] m23653i() {
        return this.f632w3.mo24088m();
    }

    /* renamed from: j */
    public BigInteger m23652j() {
        return this.f631v3.m24145n();
    }

    public C0290s(AbstractC0263s abstractC0263s) {
        this.f632w3 = (AbstractC0182o) abstractC0263s.mo23751a(0);
        this.f631v3 = C0151g1.m24147a(abstractC0263s.mo23751a(1));
    }
}
