package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.x2.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0459e extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f1494v3;

    /* renamed from: w3 */
    public AbstractC0182o f1495w3;

    public C0459e(C0178n c0178n, AbstractC0182o abstractC0182o) {
        this.f1494v3 = c0178n;
        this.f1495w3 = abstractC0182o;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1494v3);
        c0140e.m24170a(this.f1495w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m23044i() {
        return this.f1494v3;
    }

    /* renamed from: j */
    public AbstractC0182o m23043j() {
        return this.f1495w3;
    }

    public C0459e(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f1494v3 = (C0178n) mo23747m.nextElement();
        this.f1495w3 = (AbstractC0182o) mo23747m.nextElement();
    }
}
