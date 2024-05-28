package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c;

/* renamed from: a.a.a.a.a.e.a.b.x2.i */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0486i extends AbstractC0174m implements InterfaceC0493p {

    /* renamed from: v3 */
    public AbstractC0648c f1526v3;

    /* renamed from: w3 */
    public byte[] f1527w3;

    /* renamed from: x3 */
    public C0178n f1528x3;

    public C0486i(AbstractC0648c abstractC0648c) {
        this.f1528x3 = null;
        this.f1526v3 = abstractC0648c;
        this.f1527w3 = null;
        m23026k();
    }

    /* renamed from: k */
    private void m23026k() {
        AbstractC0648c abstractC0648c = this.f1526v3;
        if (abstractC0648c instanceof AbstractC0648c.C0650b) {
            this.f1528x3 = InterfaceC0493p.f1582m2;
        } else if (abstractC0648c instanceof AbstractC0648c.C0649a) {
            this.f1528x3 = InterfaceC0493p.f1584n2;
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        if (this.f1528x3.equals(InterfaceC0493p.f1582m2)) {
            c0140e.m24170a(new C0490m(this.f1526v3.m22622a()).mo23015d());
            c0140e.m24170a(new C0490m(this.f1526v3.m22619b()).mo23015d());
        } else if (this.f1528x3.equals(InterfaceC0493p.f1584n2)) {
            c0140e.m24170a(new C0490m(this.f1526v3.m22622a()).mo23015d());
            c0140e.m24170a(new C0490m(this.f1526v3.m22619b()).mo23015d());
        }
        byte[] bArr = this.f1527w3;
        if (bArr != null) {
            c0140e.m24170a(new C0359v0(bArr));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0648c m23028i() {
        return this.f1526v3;
    }

    /* renamed from: j */
    public byte[] m23027j() {
        return this.f1527w3;
    }

    public C0486i(AbstractC0648c abstractC0648c, byte[] bArr) {
        this.f1528x3 = null;
        this.f1526v3 = abstractC0648c;
        this.f1527w3 = bArr;
        m23026k();
    }

    public C0486i(C0491n c0491n, AbstractC0263s abstractC0263s) {
        int intValue;
        int i;
        int i2;
        this.f1528x3 = null;
        C0178n m23014i = c0491n.m23014i();
        this.f1528x3 = m23014i;
        if (m23014i.equals(InterfaceC0493p.f1582m2)) {
            BigInteger m24145n = ((C0166k) c0491n.m23013j()).m24145n();
            this.f1526v3 = new AbstractC0648c.C0650b(m24145n, new C0490m(m24145n, (AbstractC0182o) abstractC0263s.mo23751a(0)).m23016i().mo22584g(), new C0490m(m24145n, (AbstractC0182o) abstractC0263s.mo23751a(1)).m23016i().mo22584g());
        } else if (this.f1528x3.equals(InterfaceC0493p.f1584n2)) {
            AbstractC0263s m23749a = AbstractC0263s.m23749a((Object) c0491n.m23013j());
            int intValue2 = ((C0166k) m23749a.mo23751a(0)).m24145n().intValue();
            C0178n c0178n = (C0178n) m23749a.mo23751a(1);
            if (c0178n.equals(InterfaceC0493p.f1588p2)) {
                i = C0151g1.m24147a(m23749a.mo23751a(2)).m24145n().intValue();
                i2 = 0;
                intValue = 0;
            } else if (c0178n.equals(InterfaceC0493p.f1590q2)) {
                AbstractC0263s m23749a2 = AbstractC0263s.m23749a(m23749a.mo23751a(2));
                int intValue3 = C0151g1.m24147a(m23749a2.mo23751a(0)).m24145n().intValue();
                int intValue4 = C0151g1.m24147a(m23749a2.mo23751a(1)).m24145n().intValue();
                intValue = C0151g1.m24147a(m23749a2.mo23751a(2)).m24145n().intValue();
                i = intValue3;
                i2 = intValue4;
            } else {
                throw new IllegalArgumentException("This type of EC basis is not implemented");
            }
            int i3 = i;
            int i4 = i2;
            int i5 = intValue;
            this.f1526v3 = new AbstractC0648c.C0649a(intValue2, i3, i4, i5, new C0490m(intValue2, i3, i4, i5, (AbstractC0182o) abstractC0263s.mo23751a(0)).m23016i().mo22584g(), new C0490m(intValue2, i3, i4, i5, (AbstractC0182o) abstractC0263s.mo23751a(1)).m23016i().mo22584g());
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
        if (abstractC0263s.mo23745o() == 3) {
            this.f1527w3 = ((C0359v0) abstractC0263s.mo23751a(2)).m23554m();
        }
    }
}
