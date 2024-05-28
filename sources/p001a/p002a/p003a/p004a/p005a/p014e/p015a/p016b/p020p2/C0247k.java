package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0391f1;

/* renamed from: a.a.a.a.a.e.a.b.p2.k */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0247k extends AbstractC0174m {

    /* renamed from: v3 */
    public C0391f1 f453v3;

    /* renamed from: w3 */
    public C0391f1 f454w3;

    public C0247k(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            AbstractC0494y abstractC0494y = (AbstractC0494y) mo23747m.nextElement();
            if (abstractC0494y.mo22994f() == 0) {
                this.f453v3 = C0391f1.m23394a(abstractC0494y, true);
            } else {
                this.f454w3 = C0391f1.m23394a(abstractC0494y, true);
            }
        }
    }

    /* renamed from: a */
    public static C0247k m23788a(Object obj) {
        if (obj instanceof C0247k) {
            return (C0247k) obj;
        }
        if (obj != null) {
            return new C0247k(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0391f1 c0391f1 = this.f453v3;
        if (c0391f1 != null) {
            c0140e.m24170a(new C0360v1(true, 0, c0391f1));
        }
        C0391f1 c0391f12 = this.f454w3;
        if (c0391f12 != null) {
            c0140e.m24170a(new C0360v1(true, 1, c0391f12));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0391f1 m23787i() {
        return this.f454w3;
    }

    /* renamed from: j */
    public C0391f1 m23786j() {
        return this.f453v3;
    }

    public C0247k(C0391f1 c0391f1, C0391f1 c0391f12) {
        if (c0391f1 == null && c0391f12 == null) {
            throw new IllegalArgumentException("at least one of notBefore/notAfter must not be null.");
        }
        this.f453v3 = c0391f1;
        this.f454w3 = c0391f12;
    }
}
