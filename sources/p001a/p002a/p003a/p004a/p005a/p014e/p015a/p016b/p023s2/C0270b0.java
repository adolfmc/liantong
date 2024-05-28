package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0156h1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p022r2.InterfaceC0262b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.s2.b0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0270b0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0377b f571v3;

    /* renamed from: w3 */
    public C0377b f572w3;

    /* renamed from: x3 */
    public C0166k f573x3;

    /* renamed from: y3 */
    public C0166k f574y3;

    /* renamed from: z3 */
    public static final C0377b f570z3 = new C0377b(InterfaceC0262b.f548i, (InterfaceC0136d) C0156h1.f189v3);

    /* renamed from: A3 */
    public static final C0377b f567A3 = new C0377b(InterfaceC0291t.f750v, (InterfaceC0136d) f570z3);

    /* renamed from: B3 */
    public static final C0166k f568B3 = new C0166k(20);

    /* renamed from: C3 */
    public static final C0166k f569C3 = new C0166k(1);

    public C0270b0() {
        this.f571v3 = f570z3;
        this.f572w3 = f567A3;
        this.f573x3 = f568B3;
        this.f574y3 = f569C3;
    }

    /* renamed from: a */
    public static C0270b0 m23733a(Object obj) {
        if (obj instanceof C0270b0) {
            return (C0270b0) obj;
        }
        if (obj != null) {
            return new C0270b0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        if (!this.f571v3.equals(f570z3)) {
            c0140e.m24170a(new C0360v1(true, 0, this.f571v3));
        }
        if (!this.f572w3.equals(f567A3)) {
            c0140e.m24170a(new C0360v1(true, 1, this.f572w3));
        }
        if (!this.f573x3.equals(f568B3)) {
            c0140e.m24170a(new C0360v1(true, 2, this.f573x3));
        }
        if (!this.f574y3.equals(f569C3)) {
            c0140e.m24170a(new C0360v1(true, 3, this.f574y3));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0377b m23732i() {
        return this.f571v3;
    }

    /* renamed from: j */
    public C0377b m23731j() {
        return this.f572w3;
    }

    /* renamed from: k */
    public BigInteger m23730k() {
        return this.f573x3.m24145n();
    }

    /* renamed from: l */
    public BigInteger m23729l() {
        return this.f574y3.m24145n();
    }

    public C0270b0(C0377b c0377b, C0377b c0377b2, C0166k c0166k, C0166k c0166k2) {
        this.f571v3 = c0377b;
        this.f572w3 = c0377b2;
        this.f573x3 = c0166k;
        this.f574y3 = c0166k2;
    }

    public C0270b0(AbstractC0263s abstractC0263s) {
        this.f571v3 = f570z3;
        this.f572w3 = f567A3;
        this.f573x3 = f568B3;
        this.f574y3 = f569C3;
        for (int i = 0; i != abstractC0263s.mo23745o(); i++) {
            AbstractC0494y abstractC0494y = (AbstractC0494y) abstractC0263s.mo23751a(i);
            switch (abstractC0494y.mo22994f()) {
                case 0:
                    this.f571v3 = C0377b.m23461a(abstractC0494y, true);
                    break;
                case 1:
                    this.f572w3 = C0377b.m23461a(abstractC0494y, true);
                    break;
                case 2:
                    this.f573x3 = C0151g1.m24148a(abstractC0494y, true);
                    break;
                case 3:
                    this.f574y3 = C0151g1.m24148a(abstractC0494y, true);
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag");
            }
        }
    }
}
