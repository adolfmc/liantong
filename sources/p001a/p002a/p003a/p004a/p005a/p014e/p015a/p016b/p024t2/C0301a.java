package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p024t2;

import java.math.BigInteger;
import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0670b;

/* renamed from: a.a.a.a.a.e.a.b.t2.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0301a extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0263s f802v3;

    public C0301a(AbstractC0263s abstractC0263s) {
        this.f802v3 = abstractC0263s;
    }

    /* renamed from: a */
    public static C0301a m23600a(Object obj) {
        if (obj instanceof C0301a) {
            return (C0301a) obj;
        }
        if (obj != null) {
            return new C0301a(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f802v3;
    }

    /* renamed from: i */
    public BigInteger m23599i() {
        return new BigInteger(1, ((AbstractC0182o) this.f802v3.mo23751a(1)).mo24088m());
    }

    /* renamed from: j */
    public AbstractC0258r m23598j() {
        return m23601a(0);
    }

    /* renamed from: k */
    public C0359v0 m23597k() {
        return (C0359v0) m23601a(1);
    }

    public C0301a(BigInteger bigInteger) {
        byte[] m22464a = C0670b.m22464a(bigInteger);
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(1L));
        c0140e.m24170a(new C0168k1(m22464a));
        this.f802v3 = new C0184o1(c0140e);
    }

    /* renamed from: a */
    private AbstractC0258r m23601a(int i) {
        Enumeration mo23747m = this.f802v3.mo23747m();
        while (mo23747m.hasMoreElements()) {
            InterfaceC0136d interfaceC0136d = (InterfaceC0136d) mo23747m.nextElement();
            if (interfaceC0136d instanceof AbstractC0494y) {
                AbstractC0494y abstractC0494y = (AbstractC0494y) interfaceC0136d;
                if (abstractC0494y.mo22994f() == i) {
                    return abstractC0494y.m23004m().mo23015d();
                }
            }
        }
        return null;
    }

    public C0301a(BigInteger bigInteger, AbstractC0174m abstractC0174m) {
        this(bigInteger, null, abstractC0174m);
    }

    public C0301a(BigInteger bigInteger, C0359v0 c0359v0, AbstractC0174m abstractC0174m) {
        byte[] m22464a = C0670b.m22464a(bigInteger);
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(1L));
        c0140e.m24170a(new C0168k1(m22464a));
        if (abstractC0174m != null) {
            c0140e.m24170a(new C0360v1(true, 0, abstractC0174m));
        }
        if (c0359v0 != null) {
            c0140e.m24170a(new C0360v1(true, 1, c0359v0));
        }
        this.f802v3 = new C0184o1(c0140e);
    }
}
