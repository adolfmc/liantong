package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.o2.t0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0224t0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0222s0[] f371v3;

    public C0224t0(C0222s0[] c0222s0Arr) {
        this.f371v3 = c0222s0Arr;
    }

    /* renamed from: a */
    public static C0224t0 m23893a(AbstractC0494y abstractC0494y, boolean z) {
        return m23892a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        int i = 0;
        while (true) {
            C0222s0[] c0222s0Arr = this.f371v3;
            if (i != c0222s0Arr.length) {
                c0140e.m24170a(c0222s0Arr[i]);
                i++;
            } else {
                return new C0184o1(c0140e);
            }
        }
    }

    /* renamed from: i */
    public C0222s0[] m23891i() {
        return this.f371v3;
    }

    /* renamed from: a */
    public static C0224t0 m23892a(Object obj) {
        if (obj instanceof C0224t0) {
            return (C0224t0) obj;
        }
        if (obj != null) {
            return new C0224t0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0224t0(C0222s0 c0222s0) {
        this.f371v3 = r0;
        C0222s0[] c0222s0Arr = {c0222s0};
    }

    public C0224t0(AbstractC0263s abstractC0263s) {
        this.f371v3 = new C0222s0[abstractC0263s.mo23745o()];
        Enumeration mo23747m = abstractC0263s.mo23747m();
        int i = 0;
        while (mo23747m.hasMoreElements()) {
            this.f371v3[i] = C0222s0.m23899a(mo23747m.nextElement());
            i++;
        }
    }
}
