package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.s2.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0279h extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f605v3;

    /* renamed from: w3 */
    public C0166k f606w3;

    /* renamed from: x3 */
    public C0166k f607x3;

    public C0279h(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f605v3 = new C0166k(bigInteger);
        this.f606w3 = new C0166k(bigInteger2);
        if (i != 0) {
            this.f607x3 = new C0166k(i);
        } else {
            this.f607x3 = null;
        }
    }

    /* renamed from: a */
    public static C0279h m23691a(Object obj) {
        if (obj instanceof C0279h) {
            return (C0279h) obj;
        }
        if (obj != null) {
            return new C0279h(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f605v3);
        c0140e.m24170a(this.f606w3);
        if (m23689j() != null) {
            c0140e.m24170a(this.f607x3);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23690i() {
        return this.f606w3.m24146m();
    }

    /* renamed from: j */
    public BigInteger m23689j() {
        C0166k c0166k = this.f607x3;
        if (c0166k == null) {
            return null;
        }
        return c0166k.m24146m();
    }

    /* renamed from: k */
    public BigInteger m23688k() {
        return this.f605v3.m24146m();
    }

    public C0279h(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f605v3 = C0151g1.m24147a(mo23747m.nextElement());
        this.f606w3 = C0151g1.m24147a(mo23747m.nextElement());
        if (mo23747m.hasMoreElements()) {
            this.f607x3 = (C0166k) mo23747m.nextElement();
        } else {
            this.f607x3 = null;
        }
    }
}
