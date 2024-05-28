package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.w2.q */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0422q extends AbstractC0174m {

    /* renamed from: v3 */
    public C0416o f1269v3;

    /* renamed from: w3 */
    public C0416o f1270w3;

    public C0422q(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() != 1 && abstractC0263s.mo23745o() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
        }
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            AbstractC0494y m23008a = AbstractC0494y.m23008a(mo23747m.nextElement());
            if (m23008a.mo22994f() == 0) {
                this.f1269v3 = C0416o.m23239a(m23008a, true);
            } else if (m23008a.mo22994f() == 1) {
                this.f1270w3 = C0416o.m23239a(m23008a, true);
            } else {
                throw new IllegalArgumentException("Bad tag number: " + m23008a.mo22994f());
            }
        }
    }

    /* renamed from: a */
    public static C0422q m23207a(Object obj) {
        if (obj != null && !(obj instanceof C0422q)) {
            if (obj instanceof AbstractC0263s) {
                return new C0422q((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0422q) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0416o c0416o = this.f1269v3;
        if (c0416o != null) {
            c0140e.m24170a(new C0360v1(0, c0416o));
        }
        C0416o c0416o2 = this.f1270w3;
        if (c0416o2 != null) {
            c0140e.m24170a(new C0360v1(1, c0416o2));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0416o m23206i() {
        return this.f1269v3;
    }

    /* renamed from: j */
    public C0416o m23205j() {
        return this.f1270w3;
    }

    public C0422q(C0416o c0416o, C0416o c0416o2) {
        this.f1269v3 = c0416o;
        this.f1270w3 = c0416o2;
    }
}
