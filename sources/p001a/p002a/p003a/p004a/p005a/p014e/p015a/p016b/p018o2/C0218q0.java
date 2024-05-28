package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

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

/* renamed from: a.a.a.a.a.e.a.b.o2.q0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0218q0 extends AbstractC0174m {

    /* renamed from: A3 */
    public AbstractC0182o f351A3;

    /* renamed from: B3 */
    public AbstractC0338u f352B3;

    /* renamed from: v3 */
    public C0166k f353v3;

    /* renamed from: w3 */
    public C0216p0 f354w3;

    /* renamed from: x3 */
    public C0377b f355x3;

    /* renamed from: y3 */
    public AbstractC0338u f356y3;

    /* renamed from: z3 */
    public C0377b f357z3;

    public C0218q0(C0216p0 c0216p0, C0377b c0377b, AbstractC0338u abstractC0338u, C0377b c0377b2, AbstractC0182o abstractC0182o, AbstractC0338u abstractC0338u2) {
        if (c0216p0.m23929j()) {
            this.f353v3 = new C0166k(3L);
        } else {
            this.f353v3 = new C0166k(1L);
        }
        this.f354w3 = c0216p0;
        this.f355x3 = c0377b;
        this.f356y3 = abstractC0338u;
        this.f357z3 = c0377b2;
        this.f351A3 = abstractC0182o;
        this.f352B3 = abstractC0338u2;
    }

    /* renamed from: a */
    public static C0218q0 m23924a(Object obj) {
        if (obj != null && !(obj instanceof C0218q0)) {
            if (obj instanceof AbstractC0263s) {
                return new C0218q0((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
        }
        return (C0218q0) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f353v3);
        c0140e.m24170a(this.f354w3);
        c0140e.m24170a(this.f355x3);
        AbstractC0338u abstractC0338u = this.f356y3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0360v1(false, 0, abstractC0338u));
        }
        c0140e.m24170a(this.f357z3);
        c0140e.m24170a(this.f351A3);
        AbstractC0338u abstractC0338u2 = this.f352B3;
        if (abstractC0338u2 != null) {
            c0140e.m24170a(new C0360v1(false, 1, abstractC0338u2));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0338u m23923i() {
        return this.f356y3;
    }

    /* renamed from: j */
    public C0377b m23922j() {
        return this.f355x3;
    }

    /* renamed from: k */
    public C0377b m23921k() {
        return this.f357z3;
    }

    /* renamed from: l */
    public AbstractC0182o m23920l() {
        return this.f351A3;
    }

    /* renamed from: m */
    public C0216p0 m23919m() {
        return this.f354w3;
    }

    /* renamed from: n */
    public AbstractC0338u m23918n() {
        return this.f352B3;
    }

    /* renamed from: o */
    public C0166k m23917o() {
        return this.f353v3;
    }

    public C0218q0(C0216p0 c0216p0, C0377b c0377b, C0189c c0189c, C0377b c0377b2, AbstractC0182o abstractC0182o, C0189c c0189c2) {
        if (c0216p0.m23929j()) {
            this.f353v3 = new C0166k(3L);
        } else {
            this.f353v3 = new C0166k(1L);
        }
        this.f354w3 = c0216p0;
        this.f355x3 = c0377b;
        this.f356y3 = AbstractC0338u.m23581a((Object) c0189c);
        this.f357z3 = c0377b2;
        this.f351A3 = abstractC0182o;
        this.f352B3 = AbstractC0338u.m23581a((Object) c0189c2);
    }

    public C0218q0(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f353v3 = (C0166k) mo23747m.nextElement();
        this.f354w3 = C0216p0.m23931a(mo23747m.nextElement());
        this.f355x3 = C0377b.m23460a(mo23747m.nextElement());
        Object nextElement = mo23747m.nextElement();
        if (nextElement instanceof AbstractC0494y) {
            this.f356y3 = AbstractC0338u.m23582a((AbstractC0494y) nextElement, false);
            this.f357z3 = C0377b.m23460a(mo23747m.nextElement());
        } else {
            this.f356y3 = null;
            this.f357z3 = C0377b.m23460a(nextElement);
        }
        this.f351A3 = AbstractC0182o.m24089a(mo23747m.nextElement());
        if (mo23747m.hasMoreElements()) {
            this.f352B3 = AbstractC0338u.m23582a((AbstractC0494y) mo23747m.nextElement(), false);
        } else {
            this.f352B3 = null;
        }
    }
}
