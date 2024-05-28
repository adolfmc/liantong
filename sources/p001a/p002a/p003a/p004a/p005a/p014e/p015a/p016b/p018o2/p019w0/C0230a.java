package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2.p019w0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2.C0192d0;

/* renamed from: a.a.a.a.a.e.a.b.o2.w0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0230a extends AbstractC0174m {

    /* renamed from: v3 */
    public C0192d0 f392v3;

    /* renamed from: w3 */
    public AbstractC0182o f393w3;

    public C0230a(C0192d0 c0192d0, AbstractC0182o abstractC0182o) {
        this.f392v3 = c0192d0;
        this.f393w3 = abstractC0182o;
    }

    /* renamed from: a */
    public static C0230a m23865a(AbstractC0494y abstractC0494y, boolean z) {
        return m23864a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f392v3);
        AbstractC0182o abstractC0182o = this.f393w3;
        if (abstractC0182o != null) {
            c0140e.m24170a(new C0360v1(true, 0, abstractC0182o));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0182o m23863i() {
        return this.f393w3;
    }

    /* renamed from: j */
    public C0192d0 m23862j() {
        return this.f392v3;
    }

    /* renamed from: a */
    public static C0230a m23864a(Object obj) {
        if (obj != null && !(obj instanceof C0230a)) {
            if (obj instanceof AbstractC0263s) {
                return new C0230a((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid MQVuserKeyingMaterial: " + obj.getClass().getName());
        }
        return (C0230a) obj;
    }

    public C0230a(AbstractC0263s abstractC0263s) {
        this.f392v3 = C0192d0.m24042a(abstractC0263s.mo23751a(0));
        if (abstractC0263s.mo23745o() > 1) {
            this.f393w3 = AbstractC0182o.m24090a((AbstractC0494y) abstractC0263s.mo23751a(1), true);
        }
    }
}
