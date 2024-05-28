package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.s2.e0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0276e0 extends AbstractC0174m {

    /* renamed from: A3 */
    public AbstractC0182o f591A3;

    /* renamed from: B3 */
    public AbstractC0338u f592B3;

    /* renamed from: v3 */
    public C0166k f593v3;

    /* renamed from: w3 */
    public C0283l f594w3;

    /* renamed from: x3 */
    public C0377b f595x3;

    /* renamed from: y3 */
    public AbstractC0338u f596y3;

    /* renamed from: z3 */
    public C0377b f597z3;

    public C0276e0(C0166k c0166k, C0283l c0283l, C0377b c0377b, AbstractC0338u abstractC0338u, C0377b c0377b2, AbstractC0182o abstractC0182o, AbstractC0338u abstractC0338u2) {
        this.f593v3 = c0166k;
        this.f594w3 = c0283l;
        this.f595x3 = c0377b;
        this.f596y3 = abstractC0338u;
        this.f597z3 = c0377b2;
        this.f591A3 = abstractC0182o;
        this.f592B3 = abstractC0338u2;
    }

    /* renamed from: a */
    public static C0276e0 m23707a(Object obj) {
        if (obj instanceof C0276e0) {
            return (C0276e0) obj;
        }
        if (obj instanceof AbstractC0263s) {
            return new C0276e0((AbstractC0263s) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f593v3);
        c0140e.m24170a(this.f594w3);
        c0140e.m24170a(this.f595x3);
        AbstractC0338u abstractC0338u = this.f596y3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0360v1(false, 0, abstractC0338u));
        }
        c0140e.m24170a(this.f597z3);
        c0140e.m24170a(this.f591A3);
        AbstractC0338u abstractC0338u2 = this.f592B3;
        if (abstractC0338u2 != null) {
            c0140e.m24170a(new C0360v1(false, 1, abstractC0338u2));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0338u m23706i() {
        return this.f596y3;
    }

    /* renamed from: j */
    public C0377b m23705j() {
        return this.f595x3;
    }

    /* renamed from: k */
    public C0377b m23704k() {
        return this.f597z3;
    }

    /* renamed from: l */
    public AbstractC0182o m23703l() {
        return this.f591A3;
    }

    /* renamed from: m */
    public C0283l m23702m() {
        return this.f594w3;
    }

    /* renamed from: n */
    public AbstractC0338u m23701n() {
        return this.f592B3;
    }

    /* renamed from: o */
    public C0166k m23700o() {
        return this.f593v3;
    }

    public C0276e0(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f593v3 = (C0166k) mo23747m.nextElement();
        this.f594w3 = C0283l.m23677a(mo23747m.nextElement());
        this.f595x3 = C0377b.m23460a(mo23747m.nextElement());
        Object nextElement = mo23747m.nextElement();
        if (nextElement instanceof AbstractC0494y) {
            this.f596y3 = AbstractC0338u.m23582a((AbstractC0494y) nextElement, false);
            this.f597z3 = C0377b.m23460a(mo23747m.nextElement());
        } else {
            this.f596y3 = null;
            this.f597z3 = C0377b.m23460a(nextElement);
        }
        this.f591A3 = AbstractC0182o.m24089a(mo23747m.nextElement());
        if (mo23747m.hasMoreElements()) {
            this.f592B3 = AbstractC0338u.m23582a((AbstractC0494y) mo23747m.nextElement(), false);
        } else {
            this.f592B3 = null;
        }
    }
}
