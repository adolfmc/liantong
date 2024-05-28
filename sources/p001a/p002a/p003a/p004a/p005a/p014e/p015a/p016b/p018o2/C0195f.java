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
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.o2.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0195f extends AbstractC0174m {

    /* renamed from: A3 */
    public C0207l f262A3;

    /* renamed from: B3 */
    public AbstractC0338u f263B3;

    /* renamed from: C3 */
    public AbstractC0182o f264C3;

    /* renamed from: D3 */
    public AbstractC0338u f265D3;

    /* renamed from: v3 */
    public C0166k f266v3;

    /* renamed from: w3 */
    public C0190c0 f267w3;

    /* renamed from: x3 */
    public AbstractC0338u f268x3;

    /* renamed from: y3 */
    public C0377b f269y3;

    /* renamed from: z3 */
    public C0377b f270z3;

    public C0195f(C0190c0 c0190c0, AbstractC0338u abstractC0338u, C0377b c0377b, C0377b c0377b2, C0207l c0207l, AbstractC0338u abstractC0338u2, AbstractC0182o abstractC0182o, AbstractC0338u abstractC0338u3) {
        if ((c0377b2 == null && abstractC0338u2 == null) || (c0377b2 != null && abstractC0338u2 != null)) {
            this.f266v3 = new C0166k(m24029a(c0190c0));
            this.f267w3 = c0190c0;
            this.f269y3 = c0377b;
            this.f270z3 = c0377b2;
            this.f268x3 = abstractC0338u;
            this.f262A3 = c0207l;
            this.f263B3 = abstractC0338u2;
            this.f264C3 = abstractC0182o;
            this.f265D3 = abstractC0338u3;
            return;
        }
        throw new IllegalArgumentException("digestAlgorithm and authAttrs must be set together");
    }

    /* renamed from: a */
    public static C0195f m24028a(AbstractC0494y abstractC0494y, boolean z) {
        return m24027a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f266v3);
        C0190c0 c0190c0 = this.f267w3;
        if (c0190c0 != null) {
            c0140e.m24170a(new C0360v1(false, 0, c0190c0));
        }
        c0140e.m24170a(this.f268x3);
        c0140e.m24170a(this.f269y3);
        C0377b c0377b = this.f270z3;
        if (c0377b != null) {
            c0140e.m24170a(new C0360v1(false, 1, c0377b));
        }
        c0140e.m24170a(this.f262A3);
        AbstractC0338u abstractC0338u = this.f263B3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0360v1(false, 2, abstractC0338u));
        }
        c0140e.m24170a(this.f264C3);
        AbstractC0338u abstractC0338u2 = this.f265D3;
        if (abstractC0338u2 != null) {
            c0140e.m24170a(new C0360v1(false, 3, abstractC0338u2));
        }
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public AbstractC0338u m24026i() {
        return this.f263B3;
    }

    /* renamed from: j */
    public C0377b m24025j() {
        return this.f270z3;
    }

    /* renamed from: k */
    public C0207l m24024k() {
        return this.f262A3;
    }

    /* renamed from: l */
    public AbstractC0182o m24023l() {
        return this.f264C3;
    }

    /* renamed from: m */
    public C0377b m24022m() {
        return this.f269y3;
    }

    /* renamed from: n */
    public C0190c0 m24021n() {
        return this.f267w3;
    }

    /* renamed from: o */
    public AbstractC0338u m24020o() {
        return this.f268x3;
    }

    /* renamed from: p */
    public AbstractC0338u m24019p() {
        return this.f265D3;
    }

    /* renamed from: q */
    public C0166k m24018q() {
        return this.f266v3;
    }

    /* renamed from: a */
    public static C0195f m24027a(Object obj) {
        if (obj != null && !(obj instanceof C0195f)) {
            if (obj instanceof AbstractC0263s) {
                return new C0195f((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid AuthenticatedData: " + obj.getClass().getName());
        }
        return (C0195f) obj;
    }

    /* renamed from: a */
    public static int m24029a(C0190c0 c0190c0) {
        int i = 0;
        if (c0190c0 == null) {
            return 0;
        }
        Enumeration m23578m = c0190c0.m24053j().m23578m();
        while (true) {
            if (!m23578m.hasMoreElements()) {
                break;
            }
            Object nextElement = m23578m.nextElement();
            if (nextElement instanceof AbstractC0494y) {
                AbstractC0494y abstractC0494y = (AbstractC0494y) nextElement;
                if (abstractC0494y.mo22994f() == 2) {
                    i = 1;
                } else if (abstractC0494y.mo22994f() == 3) {
                    i = 3;
                    break;
                }
            }
        }
        if (c0190c0.m24054i() != null) {
            Enumeration m23578m2 = c0190c0.m24054i().m23578m();
            while (m23578m2.hasMoreElements()) {
                Object nextElement2 = m23578m2.nextElement();
                if ((nextElement2 instanceof AbstractC0494y) && ((AbstractC0494y) nextElement2).mo22994f() == 1) {
                    return 3;
                }
            }
            return i;
        }
        return i;
    }

    public C0195f(AbstractC0263s abstractC0263s) {
        InterfaceC0136d interfaceC0136d;
        int i;
        this.f266v3 = (C0166k) abstractC0263s.mo23751a(0);
        InterfaceC0136d mo23751a = abstractC0263s.mo23751a(1);
        if (mo23751a instanceof AbstractC0494y) {
            this.f267w3 = C0190c0.m24056a((AbstractC0494y) mo23751a, false);
            i = 3;
            interfaceC0136d = abstractC0263s.mo23751a(2);
        } else {
            interfaceC0136d = mo23751a;
            i = 2;
        }
        this.f268x3 = AbstractC0338u.m23581a((Object) interfaceC0136d);
        int i2 = i + 1;
        this.f269y3 = C0377b.m23460a(abstractC0263s.mo23751a(i));
        int i3 = i2 + 1;
        InterfaceC0136d mo23751a2 = abstractC0263s.mo23751a(i2);
        if (mo23751a2 instanceof AbstractC0494y) {
            this.f270z3 = C0377b.m23461a((AbstractC0494y) mo23751a2, false);
            mo23751a2 = abstractC0263s.mo23751a(i3);
            i3++;
        }
        this.f262A3 = C0207l.m23973a(mo23751a2);
        int i4 = i3 + 1;
        InterfaceC0136d mo23751a3 = abstractC0263s.mo23751a(i3);
        if (mo23751a3 instanceof AbstractC0494y) {
            this.f263B3 = AbstractC0338u.m23582a((AbstractC0494y) mo23751a3, false);
            int i5 = i4 + 1;
            InterfaceC0136d mo23751a4 = abstractC0263s.mo23751a(i4);
            i4 = i5;
            mo23751a3 = mo23751a4;
        }
        this.f264C3 = AbstractC0182o.m24089a(mo23751a3);
        if (abstractC0263s.mo23745o() > i4) {
            this.f265D3 = AbstractC0338u.m23582a((AbstractC0494y) abstractC0263s.mo23751a(i4), false);
        }
    }
}
