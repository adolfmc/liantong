package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.x2.n */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0491n extends AbstractC0174m implements InterfaceC0493p {

    /* renamed from: v3 */
    public C0178n f1540v3;

    /* renamed from: w3 */
    public AbstractC0258r f1541w3;

    public C0491n(BigInteger bigInteger) {
        this.f1540v3 = InterfaceC0493p.f1582m2;
        this.f1541w3 = new C0166k(bigInteger);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1540v3);
        c0140e.m24170a(this.f1541w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m23014i() {
        return this.f1540v3;
    }

    /* renamed from: j */
    public AbstractC0258r m23013j() {
        return this.f1541w3;
    }

    public C0491n(int i, int i2, int i3, int i4) {
        this.f1540v3 = InterfaceC0493p.f1584n2;
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(i));
        if (i3 == 0) {
            c0140e.m24170a(InterfaceC0493p.f1588p2);
            c0140e.m24170a(new C0166k(i2));
        } else {
            c0140e.m24170a(InterfaceC0493p.f1590q2);
            C0140e c0140e2 = new C0140e();
            c0140e2.m24170a(new C0166k(i2));
            c0140e2.m24170a(new C0166k(i3));
            c0140e2.m24170a(new C0166k(i4));
            c0140e.m24170a(new C0184o1(c0140e2));
        }
        this.f1541w3 = new C0184o1(c0140e);
    }

    public C0491n(AbstractC0263s abstractC0263s) {
        this.f1540v3 = (C0178n) abstractC0263s.mo23751a(0);
        this.f1541w3 = (AbstractC0258r) abstractC0263s.mo23751a(1);
    }
}
