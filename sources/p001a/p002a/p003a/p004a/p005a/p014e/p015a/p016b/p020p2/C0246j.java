package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.p2.j */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0246j extends AbstractC0174m {

    /* renamed from: A3 */
    public C0359v0 f447A3;

    /* renamed from: v3 */
    public C0377b f448v3;

    /* renamed from: w3 */
    public C0377b f449w3;

    /* renamed from: x3 */
    public C0359v0 f450x3;

    /* renamed from: y3 */
    public C0377b f451y3;

    /* renamed from: z3 */
    public AbstractC0182o f452z3;

    public C0246j(AbstractC0263s abstractC0263s) {
        int i = 0;
        while (abstractC0263s.mo23751a(i) instanceof AbstractC0494y) {
            AbstractC0494y abstractC0494y = (AbstractC0494y) abstractC0263s.mo23751a(i);
            switch (abstractC0494y.mo22994f()) {
                case 0:
                    this.f448v3 = C0377b.m23461a(abstractC0494y, false);
                    break;
                case 1:
                    this.f449w3 = C0377b.m23461a(abstractC0494y, false);
                    break;
                case 2:
                    this.f450x3 = C0359v0.m23558a(abstractC0494y, false);
                    break;
                case 3:
                    this.f451y3 = C0377b.m23461a(abstractC0494y, false);
                    break;
                case 4:
                    this.f452z3 = AbstractC0182o.m24090a(abstractC0494y, false);
                    break;
            }
            i++;
        }
        this.f447A3 = C0359v0.m23557a(abstractC0263s.mo23751a(i));
    }

    /* renamed from: a */
    public static C0246j m23795a(Object obj) {
        if (obj instanceof C0246j) {
            return (C0246j) obj;
        }
        if (obj != null) {
            return new C0246j(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        m23796a(c0140e, 0, this.f448v3);
        m23796a(c0140e, 1, this.f449w3);
        m23796a(c0140e, 2, this.f450x3);
        m23796a(c0140e, 3, this.f451y3);
        m23796a(c0140e, 4, this.f452z3);
        c0140e.m24170a(this.f447A3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0359v0 m23794i() {
        return this.f450x3;
    }

    /* renamed from: j */
    public C0359v0 m23793j() {
        return this.f447A3;
    }

    /* renamed from: k */
    public C0377b m23792k() {
        return this.f448v3;
    }

    /* renamed from: l */
    public C0377b m23791l() {
        return this.f451y3;
    }

    /* renamed from: m */
    public C0377b m23790m() {
        return this.f449w3;
    }

    /* renamed from: n */
    public AbstractC0182o m23789n() {
        return this.f452z3;
    }

    /* renamed from: a */
    private void m23796a(C0140e c0140e, int i, InterfaceC0136d interfaceC0136d) {
        if (interfaceC0136d != null) {
            c0140e.m24170a(new C0360v1(false, i, interfaceC0136d));
        }
    }

    public C0246j(C0377b c0377b, C0377b c0377b2, C0359v0 c0359v0, C0377b c0377b3, AbstractC0182o abstractC0182o, C0359v0 c0359v02) {
        if (c0359v02 != null) {
            this.f448v3 = c0377b;
            this.f449w3 = c0377b2;
            this.f450x3 = c0359v0;
            this.f451y3 = c0377b3;
            this.f452z3 = abstractC0182o;
            this.f447A3 = c0359v02;
            return;
        }
        throw new IllegalArgumentException("'encValue' cannot be null");
    }
}
