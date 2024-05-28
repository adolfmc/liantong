package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0378b0;

/* renamed from: a.a.a.a.a.e.a.b.p2.n */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0250n extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f461v3;

    /* renamed from: w3 */
    public C0378b0 f462w3;

    public C0250n(AbstractC0263s abstractC0263s) {
        this.f461v3 = C0151g1.m24147a(abstractC0263s.mo23751a(0));
        if (abstractC0263s.mo23745o() == 2) {
            this.f462w3 = C0378b0.m23455a(abstractC0263s.mo23751a(1));
        }
    }

    /* renamed from: a */
    public static C0250n m23779a(Object obj) {
        if (obj instanceof C0250n) {
            return (C0250n) obj;
        }
        if (obj != null) {
            return new C0250n(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f461v3);
        C0378b0 c0378b0 = this.f462w3;
        if (c0378b0 != null) {
            c0140e.m24170a(c0378b0);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0378b0 m23778i() {
        return this.f462w3;
    }
}
