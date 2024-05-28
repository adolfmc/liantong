package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.x2.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0460f extends AbstractC0174m {

    /* renamed from: v3 */
    public C0459e f1496v3;

    /* renamed from: w3 */
    public AbstractC0182o f1497w3;

    /* renamed from: x3 */
    public AbstractC0182o f1498x3;

    public C0460f(C0459e c0459e, AbstractC0182o abstractC0182o, AbstractC0182o abstractC0182o2) {
        this.f1496v3 = c0459e;
        this.f1497w3 = abstractC0182o;
        this.f1498x3 = abstractC0182o2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1496v3);
        AbstractC0182o abstractC0182o = this.f1497w3;
        if (abstractC0182o != null) {
            c0140e.m24170a(new C0360v1(0, abstractC0182o));
        }
        c0140e.m24170a(new C0360v1(2, this.f1498x3));
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0459e m23042i() {
        return this.f1496v3;
    }

    /* renamed from: j */
    public AbstractC0182o m23041j() {
        return this.f1497w3;
    }

    /* renamed from: k */
    public AbstractC0182o m23040k() {
        return this.f1498x3;
    }

    public C0460f(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f1496v3 = new C0459e((AbstractC0263s) mo23747m.nextElement());
        while (mo23747m.hasMoreElements()) {
            C0360v1 c0360v1 = (C0360v1) mo23747m.nextElement();
            if (c0360v1.mo22994f() == 0) {
                this.f1497w3 = (AbstractC0182o) c0360v1.m23004m();
            } else if (c0360v1.mo22994f() == 2) {
                this.f1498x3 = (AbstractC0182o) c0360v1.m23004m();
            }
        }
    }
}
