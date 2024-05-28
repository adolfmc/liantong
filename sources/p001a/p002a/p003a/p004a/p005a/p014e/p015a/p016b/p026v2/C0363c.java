package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0260r1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.v2.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0363c extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0338u f934v3;

    public C0363c(AbstractC0338u abstractC0338u) {
        this.f934v3 = abstractC0338u;
    }

    /* renamed from: a */
    public static C0363c m23546a(Object obj) {
        if (obj instanceof C0363c) {
            return (C0363c) obj;
        }
        if (obj != null) {
            return new C0363c(AbstractC0338u.m23581a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f934v3;
    }

    /* renamed from: i */
    public C0361a m23545i() {
        if (this.f934v3.m23576o() == 0) {
            return null;
        }
        return C0361a.m23551a(this.f934v3.m23584a(0));
    }

    /* renamed from: j */
    public C0361a[] m23544j() {
        int m23576o = this.f934v3.m23576o();
        C0361a[] c0361aArr = new C0361a[m23576o];
        for (int i = 0; i != m23576o; i++) {
            c0361aArr[i] = C0361a.m23551a(this.f934v3.m23584a(i));
        }
        return c0361aArr;
    }

    /* renamed from: k */
    public boolean m23543k() {
        return this.f934v3.m23576o() > 1;
    }

    /* renamed from: l */
    public int m23542l() {
        return this.f934v3.m23576o();
    }

    public C0363c(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(c0178n);
        c0140e.m24170a(interfaceC0136d);
        this.f934v3 = new C0260r1(new C0184o1(c0140e));
    }

    public C0363c(C0361a c0361a) {
        this.f934v3 = new C0260r1(c0361a);
    }

    public C0363c(C0361a[] c0361aArr) {
        this.f934v3 = new C0260r1(c0361aArr);
    }
}
