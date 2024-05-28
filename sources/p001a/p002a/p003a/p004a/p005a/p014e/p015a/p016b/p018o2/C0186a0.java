package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0128b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0146f1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0372w0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0496y1;

/* renamed from: a.a.a.a.a.e.a.b.o2.a0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0186a0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0128b f238v3;

    /* renamed from: w3 */
    public C0496y1 f239w3;

    /* renamed from: x3 */
    public C0146f1 f240x3;

    /* renamed from: y3 */
    public C0189c f241y3;

    public C0186a0(C0128b c0128b, C0496y1 c0496y1, C0146f1 c0146f1, C0189c c0189c) {
        this.f238v3 = c0128b;
        this.f239w3 = c0496y1;
        this.f240x3 = c0146f1;
        this.f241y3 = c0189c;
    }

    /* renamed from: a */
    public static C0186a0 m24081a(Object obj) {
        if (obj instanceof C0186a0) {
            return (C0186a0) obj;
        }
        if (obj != null) {
            return new C0186a0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f238v3);
        C0496y1 c0496y1 = this.f239w3;
        if (c0496y1 != null) {
            c0140e.m24170a(c0496y1);
        }
        C0146f1 c0146f1 = this.f240x3;
        if (c0146f1 != null) {
            c0140e.m24170a(c0146f1);
        }
        C0189c c0189c = this.f241y3;
        if (c0189c != null) {
            c0140e.m24170a(c0189c);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0496y1 m24080i() {
        return this.f239w3;
    }

    /* renamed from: j */
    public C0146f1 m24079j() {
        return this.f240x3;
    }

    /* renamed from: k */
    public C0189c m24078k() {
        return this.f241y3;
    }

    /* renamed from: l */
    public boolean m24077l() {
        return this.f238v3.m23484m();
    }

    public C0186a0(AbstractC0263s abstractC0263s) {
        int i;
        this.f238v3 = C0372w0.m23487a(abstractC0263s.mo23751a(0));
        if (1 >= abstractC0263s.mo23745o() || !(abstractC0263s.mo23751a(1) instanceof C0496y1)) {
            i = 1;
        } else {
            i = 2;
            this.f239w3 = C0496y1.m22996a(abstractC0263s.mo23751a(1));
        }
        if (i < abstractC0263s.mo23745o() && (abstractC0263s.mo23751a(i) instanceof C0146f1)) {
            this.f240x3 = C0146f1.m24157a(abstractC0263s.mo23751a(i));
            i++;
        }
        if (i < abstractC0263s.mo23745o()) {
            this.f241y3 = C0189c.m24058a(abstractC0263s.mo23751a(i));
        }
    }
}
