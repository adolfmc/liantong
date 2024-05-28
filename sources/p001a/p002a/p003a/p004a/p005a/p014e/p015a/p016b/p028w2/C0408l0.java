package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.w2.l0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0408l0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0387e0[] f1162v3;

    /* renamed from: w3 */
    public C0387e0[] f1163w3;

    public C0408l0(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            AbstractC0494y m23008a = AbstractC0494y.m23008a(mo23747m.nextElement());
            int mo22994f = m23008a.mo22994f();
            if (mo22994f == 0) {
                this.f1162v3 = m23286a(AbstractC0263s.m23750a(m23008a, false));
            } else if (mo22994f == 1) {
                this.f1163w3 = m23286a(AbstractC0263s.m23750a(m23008a, false));
            }
        }
    }

    /* renamed from: a */
    public static C0408l0 m23285a(Object obj) {
        if (obj instanceof C0408l0) {
            return (C0408l0) obj;
        }
        if (obj != null) {
            return new C0408l0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0387e0[] c0387e0Arr = this.f1162v3;
        if (c0387e0Arr != null) {
            c0140e.m24170a(new C0360v1(false, 0, new C0184o1(c0387e0Arr)));
        }
        C0387e0[] c0387e0Arr2 = this.f1163w3;
        if (c0387e0Arr2 != null) {
            c0140e.m24170a(new C0360v1(false, 1, new C0184o1(c0387e0Arr2)));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0387e0[] m23284i() {
        return this.f1163w3;
    }

    /* renamed from: j */
    public C0387e0[] m23283j() {
        return this.f1162v3;
    }

    /* renamed from: a */
    private C0387e0[] m23286a(AbstractC0263s abstractC0263s) {
        int mo23745o = abstractC0263s.mo23745o();
        C0387e0[] c0387e0Arr = new C0387e0[mo23745o];
        for (int i = 0; i != mo23745o; i++) {
            c0387e0Arr[i] = C0387e0.m23409a(abstractC0263s.mo23751a(i));
        }
        return c0387e0Arr;
    }

    public C0408l0(C0387e0[] c0387e0Arr, C0387e0[] c0387e0Arr2) {
        if (c0387e0Arr != null) {
            this.f1162v3 = c0387e0Arr;
        }
        if (c0387e0Arr2 != null) {
            this.f1163w3 = c0387e0Arr2;
        }
    }
}
