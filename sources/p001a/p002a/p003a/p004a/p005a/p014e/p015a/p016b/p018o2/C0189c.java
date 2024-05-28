package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0139d2;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;

/* renamed from: a.a.a.a.a.e.a.b.o2.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0189c extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0338u f244v3;

    public C0189c(AbstractC0338u abstractC0338u) {
        this.f244v3 = abstractC0338u;
    }

    /* renamed from: a */
    public static C0189c m24058a(Object obj) {
        if (obj instanceof C0189c) {
            return (C0189c) obj;
        }
        if (obj != null) {
            return new C0189c(AbstractC0338u.m23581a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f244v3;
    }

    /* renamed from: i */
    public C0185a[] m24057i() {
        int m23576o = this.f244v3.m23576o();
        C0185a[] c0185aArr = new C0185a[m23576o];
        for (int i = 0; i != m23576o; i++) {
            c0185aArr[i] = C0185a.m24085a(this.f244v3.m23584a(i));
        }
        return c0185aArr;
    }

    public C0189c(C0140e c0140e) {
        this.f244v3 = new C0139d2(c0140e);
    }
}
