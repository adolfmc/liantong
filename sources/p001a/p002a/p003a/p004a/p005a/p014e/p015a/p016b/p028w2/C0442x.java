package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.x */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0442x extends AbstractC0174m {

    /* renamed from: v3 */
    public Hashtable f1432v3 = new Hashtable();

    /* renamed from: w3 */
    public AbstractC0263s f1433w3;

    public C0442x(C0402j0 c0402j0) {
        this.f1433w3 = new C0184o1(c0402j0);
        this.f1432v3.put(c0402j0, c0402j0);
    }

    /* renamed from: a */
    public static C0442x m23120a(AbstractC0494y abstractC0494y, boolean z) {
        return m23119a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1433w3;
    }

    /* renamed from: i */
    public C0402j0[] m23118i() {
        C0402j0[] c0402j0Arr = new C0402j0[this.f1433w3.mo23745o()];
        Enumeration mo23747m = this.f1433w3.mo23747m();
        int i = 0;
        while (mo23747m.hasMoreElements()) {
            c0402j0Arr[i] = C0402j0.m23320a(mo23747m.nextElement());
            i++;
        }
        return c0402j0Arr;
    }

    /* renamed from: j */
    public int m23117j() {
        return this.f1432v3.size();
    }

    /* renamed from: a */
    public static C0442x m23119a(Object obj) {
        if (obj instanceof C0442x) {
            return (C0442x) obj;
        }
        if (obj != null) {
            return new C0442x(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    /* renamed from: a */
    public static C0442x m23121a(C0446z c0446z) {
        return m23119a(c0446z.m23090b(C0444y.f1453S3));
    }

    /* renamed from: a */
    public boolean m23122a(C0402j0 c0402j0) {
        return this.f1432v3.get(c0402j0) != null;
    }

    public C0442x(AbstractC0263s abstractC0263s) {
        this.f1433w3 = abstractC0263s;
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            InterfaceC0136d interfaceC0136d = (InterfaceC0136d) mo23747m.nextElement();
            if (interfaceC0136d.mo23015d() instanceof C0178n) {
                this.f1432v3.put(interfaceC0136d, interfaceC0136d);
            } else {
                throw new IllegalArgumentException("Only ASN1ObjectIdentifiers allowed in ExtendedKeyUsage.");
            }
        }
    }

    public C0442x(C0402j0[] c0402j0Arr) {
        C0140e c0140e = new C0140e();
        for (int i = 0; i != c0402j0Arr.length; i++) {
            c0140e.m24170a(c0402j0Arr[i]);
            this.f1432v3.put(c0402j0Arr[i], c0402j0Arr[i]);
        }
        this.f1433w3 = new C0184o1(c0140e);
    }

    public C0442x(Vector vector) {
        C0140e c0140e = new C0140e();
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            AbstractC0258r abstractC0258r = (AbstractC0258r) elements.nextElement();
            c0140e.m24170a(abstractC0258r);
            this.f1432v3.put(abstractC0258r, abstractC0258r);
        }
        this.f1433w3 = new C0184o1(c0140e);
    }
}
