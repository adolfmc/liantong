package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.o2.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0185a extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f236v3;

    /* renamed from: w3 */
    public AbstractC0338u f237w3;

    public C0185a(AbstractC0263s abstractC0263s) {
        this.f236v3 = (C0178n) abstractC0263s.mo23751a(0);
        this.f237w3 = (AbstractC0338u) abstractC0263s.mo23751a(1);
    }

    /* renamed from: a */
    public static C0185a m24085a(Object obj) {
        if (obj instanceof C0185a) {
            return (C0185a) obj;
        }
        if (obj != null) {
            return new C0185a(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f236v3);
        c0140e.m24170a(this.f237w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m24084i() {
        return this.f236v3;
    }

    /* renamed from: j */
    public AbstractC0338u m24083j() {
        return this.f237w3;
    }

    /* renamed from: k */
    public InterfaceC0136d[] m24082k() {
        return this.f237w3.m23574q();
    }

    public C0185a(C0164j1 c0164j1, AbstractC0338u abstractC0338u) {
        this.f236v3 = new C0178n(c0164j1.m24113n());
        this.f237w3 = abstractC0338u;
    }

    public C0185a(C0178n c0178n, AbstractC0338u abstractC0338u) {
        this.f236v3 = c0178n;
        this.f237w3 = abstractC0338u;
    }
}
