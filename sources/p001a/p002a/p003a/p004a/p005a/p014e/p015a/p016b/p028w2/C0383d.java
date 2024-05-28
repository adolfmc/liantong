package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0138d1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0153h;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0383d extends AbstractC0174m {

    /* renamed from: v3 */
    public C0153h f1044v3;

    /* renamed from: w3 */
    public C0153h f1045w3;

    public C0383d(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 2) {
            this.f1044v3 = C0138d1.m24179a(abstractC0263s.mo23751a(0));
            this.f1045w3 = C0138d1.m24179a(abstractC0263s.mo23751a(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }

    /* renamed from: a */
    public static C0383d m23422a(Object obj) {
        if (obj instanceof C0383d) {
            return (C0383d) obj;
        }
        if (obj != null) {
            return new C0383d(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1044v3);
        c0140e.m24170a(this.f1045w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0153h m23421i() {
        return this.f1045w3;
    }

    /* renamed from: j */
    public C0153h m23420j() {
        return this.f1044v3;
    }

    public C0383d(C0153h c0153h, C0153h c0153h2) {
        this.f1044v3 = c0153h;
        this.f1045w3 = c0153h2;
    }
}
