package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.s2.q */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0288q extends AbstractC0174m implements InterfaceC0291t {

    /* renamed from: v3 */
    public C0284m f626v3;

    /* renamed from: w3 */
    public C0282k f627w3;

    public C0288q(C0284m c0284m, C0282k c0282k) {
        this.f626v3 = c0284m;
        this.f627w3 = c0282k;
    }

    /* renamed from: a */
    public static C0288q m23661a(Object obj) {
        if (obj instanceof C0288q) {
            return (C0288q) obj;
        }
        if (obj != null) {
            return new C0288q(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f626v3);
        c0140e.m24170a(this.f627w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0282k m23660i() {
        return this.f627w3;
    }

    /* renamed from: j */
    public C0284m m23659j() {
        return this.f626v3;
    }

    public C0288q(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        AbstractC0263s m23749a = AbstractC0263s.m23749a((Object) ((InterfaceC0136d) mo23747m.nextElement()).mo23015d());
        if (m23749a.mo23751a(0).equals(InterfaceC0291t.f669M)) {
            this.f626v3 = new C0284m(InterfaceC0291t.f669M, C0289r.m23658a(m23749a.mo23751a(1)));
        } else {
            this.f626v3 = C0284m.m23674a(m23749a);
        }
        this.f627w3 = C0282k.m23680a(mo23747m.nextElement());
    }
}
