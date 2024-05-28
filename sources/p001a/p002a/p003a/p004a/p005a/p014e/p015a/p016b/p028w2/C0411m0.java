package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.m0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0411m0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0434u f1199v3;

    /* renamed from: w3 */
    public AbstractC0263s f1200w3;

    public C0411m0(String str, Vector vector) {
        this(str, m23262a(vector));
    }

    /* renamed from: a */
    public static C0140e m23262a(Vector vector) {
        C0166k c0166k;
        C0140e c0140e = new C0140e();
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            Object nextElement = elements.nextElement();
            if (nextElement instanceof BigInteger) {
                c0166k = new C0166k((BigInteger) nextElement);
            } else if (nextElement instanceof Integer) {
                c0166k = new C0166k(((Integer) nextElement).intValue());
            } else {
                throw new IllegalArgumentException();
            }
            c0140e.m24170a(c0166k);
        }
        return c0140e;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1199v3);
        c0140e.m24170a(this.f1200w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0166k[] m23261i() {
        C0166k[] c0166kArr = new C0166k[this.f1200w3.mo23745o()];
        for (int i = 0; i != this.f1200w3.mo23745o(); i++) {
            c0166kArr[i] = C0151g1.m24147a(this.f1200w3.mo23751a(i));
        }
        return c0166kArr;
    }

    /* renamed from: j */
    public C0434u m23260j() {
        return this.f1199v3;
    }

    public C0411m0(String str, C0140e c0140e) {
        this(new C0434u(str), c0140e);
    }

    public C0411m0(C0434u c0434u, C0140e c0140e) {
        this.f1199v3 = c0434u;
        this.f1200w3 = new C0184o1(c0140e);
    }

    public C0411m0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 2) {
            this.f1199v3 = C0434u.m23144a(abstractC0263s.mo23751a(0));
            this.f1200w3 = AbstractC0263s.m23749a(abstractC0263s.mo23751a(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }

    /* renamed from: a */
    public static C0411m0 m23263a(Object obj) {
        if (obj instanceof C0411m0) {
            return (C0411m0) obj;
        }
        if (obj != null) {
            return new C0411m0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }
}
