package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.s2.d0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0274d0 extends AbstractC0174m implements InterfaceC0291t {

    /* renamed from: A3 */
    public AbstractC0338u f582A3;

    /* renamed from: v3 */
    public C0166k f583v3;

    /* renamed from: w3 */
    public AbstractC0338u f584w3;

    /* renamed from: x3 */
    public C0278g f585x3;

    /* renamed from: y3 */
    public AbstractC0338u f586y3;

    /* renamed from: z3 */
    public AbstractC0338u f587z3;

    public C0274d0(C0166k c0166k, AbstractC0338u abstractC0338u, C0278g c0278g, AbstractC0338u abstractC0338u2, AbstractC0338u abstractC0338u3, AbstractC0338u abstractC0338u4) {
        this.f583v3 = c0166k;
        this.f584w3 = abstractC0338u;
        this.f585x3 = c0278g;
        this.f586y3 = abstractC0338u2;
        this.f587z3 = abstractC0338u3;
        this.f582A3 = abstractC0338u4;
    }

    /* renamed from: a */
    public static C0274d0 m23718a(Object obj) {
        if (obj instanceof C0274d0) {
            return (C0274d0) obj;
        }
        if (obj != null) {
            return new C0274d0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f583v3);
        c0140e.m24170a(this.f584w3);
        c0140e.m24170a(this.f585x3);
        AbstractC0338u abstractC0338u = this.f586y3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0360v1(false, 0, abstractC0338u));
        }
        AbstractC0338u abstractC0338u2 = this.f587z3;
        if (abstractC0338u2 != null) {
            c0140e.m24170a(new C0360v1(false, 1, abstractC0338u2));
        }
        c0140e.m24170a(this.f582A3);
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public AbstractC0338u m23717i() {
        return this.f587z3;
    }

    /* renamed from: j */
    public AbstractC0338u m23716j() {
        return this.f586y3;
    }

    /* renamed from: k */
    public C0278g m23715k() {
        return this.f585x3;
    }

    /* renamed from: l */
    public AbstractC0338u m23714l() {
        return this.f584w3;
    }

    /* renamed from: m */
    public AbstractC0338u m23713m() {
        return this.f582A3;
    }

    /* renamed from: n */
    public C0166k m23712n() {
        return this.f583v3;
    }

    public C0274d0(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f583v3 = (C0166k) mo23747m.nextElement();
        this.f584w3 = (AbstractC0338u) mo23747m.nextElement();
        this.f585x3 = C0278g.m23694a(mo23747m.nextElement());
        while (mo23747m.hasMoreElements()) {
            AbstractC0258r abstractC0258r = (AbstractC0258r) mo23747m.nextElement();
            if (abstractC0258r instanceof AbstractC0494y) {
                AbstractC0494y abstractC0494y = (AbstractC0494y) abstractC0258r;
                int mo22994f = abstractC0494y.mo22994f();
                if (mo22994f == 0) {
                    this.f586y3 = AbstractC0338u.m23582a(abstractC0494y, false);
                } else if (mo22994f == 1) {
                    this.f587z3 = AbstractC0338u.m23582a(abstractC0494y, false);
                } else {
                    throw new IllegalArgumentException("unknown tag value " + abstractC0494y.mo22994f());
                }
            } else {
                this.f582A3 = (AbstractC0338u) abstractC0258r;
            }
        }
    }
}
