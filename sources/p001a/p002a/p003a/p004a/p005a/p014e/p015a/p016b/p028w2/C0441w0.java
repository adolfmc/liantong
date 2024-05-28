package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.w0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0441w0 extends AbstractC0174m {

    /* renamed from: v3 */
    public Vector f1431v3 = new Vector();

    public C0441w0(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            this.f1431v3.addElement(C0386e.m23414a(AbstractC0263s.m23749a(mo23747m.nextElement())));
        }
    }

    /* renamed from: a */
    public static C0441w0 m23124a(Object obj) {
        if (obj instanceof C0441w0) {
            return (C0441w0) obj;
        }
        if (obj != null) {
            return new C0441w0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        Enumeration elements = this.f1431v3.elements();
        while (elements.hasMoreElements()) {
            c0140e.m24170a((C0386e) elements.nextElement());
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public Vector m23123i() {
        return this.f1431v3;
    }

    public C0441w0(Vector vector) {
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.f1431v3.addElement(elements.nextElement());
        }
    }
}
