package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0156h1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p022r2.InterfaceC0262b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.s2.x */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0295x extends AbstractC0174m {

    /* renamed from: v3 */
    public C0377b f775v3;

    /* renamed from: w3 */
    public C0377b f776w3;

    /* renamed from: x3 */
    public C0377b f777x3;

    /* renamed from: y3 */
    public static final C0377b f773y3 = new C0377b(InterfaceC0262b.f548i, (InterfaceC0136d) C0156h1.f189v3);

    /* renamed from: z3 */
    public static final C0377b f774z3 = new C0377b(InterfaceC0291t.f750v, (InterfaceC0136d) f773y3);

    /* renamed from: A3 */
    public static final C0377b f772A3 = new C0377b(InterfaceC0291t.f753w, (InterfaceC0136d) new C0168k1(new byte[0]));

    public C0295x() {
        this.f775v3 = f773y3;
        this.f776w3 = f774z3;
        this.f777x3 = f772A3;
    }

    /* renamed from: a */
    public static C0295x m23638a(Object obj) {
        if (obj instanceof C0295x) {
            return (C0295x) obj;
        }
        if (obj != null) {
            return new C0295x(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        if (!this.f775v3.equals(f773y3)) {
            c0140e.m24170a(new C0360v1(true, 0, this.f775v3));
        }
        if (!this.f776w3.equals(f774z3)) {
            c0140e.m24170a(new C0360v1(true, 1, this.f776w3));
        }
        if (!this.f777x3.equals(f772A3)) {
            c0140e.m24170a(new C0360v1(true, 2, this.f777x3));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0377b m23637i() {
        return this.f775v3;
    }

    /* renamed from: j */
    public C0377b m23636j() {
        return this.f776w3;
    }

    /* renamed from: k */
    public C0377b m23635k() {
        return this.f777x3;
    }

    public C0295x(C0377b c0377b, C0377b c0377b2, C0377b c0377b3) {
        this.f775v3 = c0377b;
        this.f776w3 = c0377b2;
        this.f777x3 = c0377b3;
    }

    public C0295x(AbstractC0263s abstractC0263s) {
        this.f775v3 = f773y3;
        this.f776w3 = f774z3;
        this.f777x3 = f772A3;
        for (int i = 0; i != abstractC0263s.mo23745o(); i++) {
            AbstractC0494y abstractC0494y = (AbstractC0494y) abstractC0263s.mo23751a(i);
            int mo22994f = abstractC0494y.mo22994f();
            if (mo22994f == 0) {
                this.f775v3 = C0377b.m23461a(abstractC0494y, true);
            } else if (mo22994f == 1) {
                this.f776w3 = C0377b.m23461a(abstractC0494y, true);
            } else if (mo22994f == 2) {
                this.f777x3 = C0377b.m23461a(abstractC0494y, true);
            } else {
                throw new IllegalArgumentException("unknown tag");
            }
        }
    }
}
