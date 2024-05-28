package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.o2.c0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0190c0 extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0338u f245v3;

    /* renamed from: w3 */
    public AbstractC0338u f246w3;

    public C0190c0(AbstractC0338u abstractC0338u, AbstractC0338u abstractC0338u2) {
        this.f245v3 = abstractC0338u;
        this.f246w3 = abstractC0338u2;
    }

    /* renamed from: a */
    public static C0190c0 m24056a(AbstractC0494y abstractC0494y, boolean z) {
        return m24055a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        AbstractC0338u abstractC0338u = this.f245v3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0360v1(false, 0, abstractC0338u));
        }
        AbstractC0338u abstractC0338u2 = this.f246w3;
        if (abstractC0338u2 != null) {
            c0140e.m24170a(new C0360v1(false, 1, abstractC0338u2));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0338u m24054i() {
        return this.f246w3;
    }

    /* renamed from: j */
    public AbstractC0338u m24053j() {
        return this.f245v3;
    }

    /* renamed from: a */
    public static C0190c0 m24055a(Object obj) {
        if (obj instanceof C0190c0) {
            return (C0190c0) obj;
        }
        if (obj != null) {
            return new C0190c0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0190c0(AbstractC0263s abstractC0263s) {
        int mo23745o = abstractC0263s.mo23745o();
        if (mo23745o != 0) {
            if (mo23745o != 1) {
                if (mo23745o == 2) {
                    this.f245v3 = AbstractC0338u.m23582a((AbstractC0494y) abstractC0263s.mo23751a(0), false);
                    this.f246w3 = AbstractC0338u.m23582a((AbstractC0494y) abstractC0263s.mo23751a(1), false);
                    return;
                }
                throw new IllegalArgumentException("OriginatorInfo too big");
            }
            AbstractC0494y abstractC0494y = (AbstractC0494y) abstractC0263s.mo23751a(0);
            int mo22994f = abstractC0494y.mo22994f();
            if (mo22994f == 0) {
                this.f245v3 = AbstractC0338u.m23582a(abstractC0494y, false);
            } else if (mo22994f == 1) {
                this.f246w3 = AbstractC0338u.m23582a(abstractC0494y, false);
            } else {
                throw new IllegalArgumentException("Bad tag in OriginatorInfo: " + abstractC0494y.mo22994f());
            }
        }
    }
}
