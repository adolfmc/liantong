package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.o2.z */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0233z extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f401v3;

    /* renamed from: w3 */
    public C0204j0 f402w3;

    /* renamed from: x3 */
    public C0377b f403x3;

    /* renamed from: y3 */
    public AbstractC0182o f404y3;

    public C0233z(C0204j0 c0204j0, C0377b c0377b, AbstractC0182o abstractC0182o) {
        if (c0204j0.mo23015d() instanceof AbstractC0494y) {
            this.f401v3 = new C0166k(2L);
        } else {
            this.f401v3 = new C0166k(0L);
        }
        this.f402w3 = c0204j0;
        this.f403x3 = c0377b;
        this.f404y3 = abstractC0182o;
    }

    /* renamed from: a */
    public static C0233z m23850a(Object obj) {
        if (obj != null && !(obj instanceof C0233z)) {
            if (obj instanceof AbstractC0263s) {
                return new C0233z((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Illegal object in KeyTransRecipientInfo: " + obj.getClass().getName());
        }
        return (C0233z) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f401v3);
        c0140e.m24170a(this.f402w3);
        c0140e.m24170a(this.f403x3);
        c0140e.m24170a(this.f404y3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0182o m23849i() {
        return this.f404y3;
    }

    /* renamed from: j */
    public C0377b m23848j() {
        return this.f403x3;
    }

    /* renamed from: k */
    public C0204j0 m23847k() {
        return this.f402w3;
    }

    /* renamed from: l */
    public C0166k m23846l() {
        return this.f401v3;
    }

    public C0233z(AbstractC0263s abstractC0263s) {
        this.f401v3 = (C0166k) abstractC0263s.mo23751a(0);
        this.f402w3 = C0204j0.m23985a(abstractC0263s.mo23751a(1));
        this.f403x3 = C0377b.m23460a(abstractC0263s.mo23751a(2));
        this.f404y3 = (AbstractC0182o) abstractC0263s.mo23751a(3);
    }
}
