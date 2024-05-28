package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.io.IOException;
import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.s2.v */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0293v extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0182o f767v3;

    /* renamed from: w3 */
    public C0377b f768w3;

    /* renamed from: x3 */
    public AbstractC0338u f769x3;

    public C0293v(C0377b c0377b, InterfaceC0136d interfaceC0136d) {
        this(c0377b, interfaceC0136d, null);
    }

    /* renamed from: a */
    public static C0293v m23648a(AbstractC0494y abstractC0494y, boolean z) {
        return m23647a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(0L));
        c0140e.m24170a(this.f768w3);
        c0140e.m24170a(this.f767v3);
        AbstractC0338u abstractC0338u = this.f769x3;
        if (abstractC0338u != null) {
            c0140e.m24170a(new C0360v1(false, 0, abstractC0338u));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0377b m23646i() {
        return this.f768w3;
    }

    /* renamed from: j */
    public AbstractC0338u m23645j() {
        return this.f769x3;
    }

    /* renamed from: k */
    public AbstractC0258r m23644k() {
        try {
            return m23642m().mo23015d();
        } catch (IOException unused) {
            throw new IllegalStateException("unable to parse private key");
        }
    }

    /* renamed from: l */
    public C0377b m23643l() {
        return this.f768w3;
    }

    /* renamed from: m */
    public InterfaceC0136d m23642m() {
        return AbstractC0258r.m23755a(this.f767v3.mo24088m());
    }

    public C0293v(C0377b c0377b, InterfaceC0136d interfaceC0136d, AbstractC0338u abstractC0338u) {
        this.f767v3 = new C0168k1(interfaceC0136d.mo23015d().m24102a("DER"));
        this.f768w3 = c0377b;
        this.f769x3 = abstractC0338u;
    }

    /* renamed from: a */
    public static C0293v m23647a(Object obj) {
        if (obj instanceof C0293v) {
            return (C0293v) obj;
        }
        if (obj != null) {
            return new C0293v(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0293v(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        if (((C0166k) mo23747m.nextElement()).m24145n().intValue() == 0) {
            this.f768w3 = C0377b.m23460a(mo23747m.nextElement());
            this.f767v3 = AbstractC0182o.m24089a(mo23747m.nextElement());
            if (mo23747m.hasMoreElements()) {
                this.f769x3 = AbstractC0338u.m23582a((AbstractC0494y) mo23747m.nextElement(), false);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("wrong version for private key info");
    }
}
