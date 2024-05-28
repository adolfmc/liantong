package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.d1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0385d1 extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0263s f1047v3;

    public C0385d1(AbstractC0263s abstractC0263s) {
        this.f1047v3 = abstractC0263s;
    }

    /* renamed from: a */
    public static C0385d1 m23416a(Object obj) {
        if (obj instanceof C0385d1) {
            return (C0385d1) obj;
        }
        if (obj != null) {
            return new C0385d1(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1047v3;
    }

    /* renamed from: i */
    public C0388e1[] m23415i() {
        C0388e1[] c0388e1Arr = new C0388e1[this.f1047v3.mo23745o()];
        Enumeration mo23747m = this.f1047v3.mo23747m();
        int i = 0;
        while (mo23747m.hasMoreElements()) {
            c0388e1Arr[i] = C0388e1.m23405a(mo23747m.nextElement());
            i++;
        }
        return c0388e1Arr;
    }

    public C0385d1(C0388e1 c0388e1) {
        this.f1047v3 = new C0184o1(c0388e1);
    }

    public C0385d1(C0382c1[] c0382c1Arr) {
        this(new C0388e1(c0382c1Arr));
    }
}
