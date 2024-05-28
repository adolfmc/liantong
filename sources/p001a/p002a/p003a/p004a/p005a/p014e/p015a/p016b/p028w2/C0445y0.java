package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0162j;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.y0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0445y0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0377b f1469v3;

    /* renamed from: w3 */
    public C0359v0 f1470w3;

    public C0445y0(C0377b c0377b, InterfaceC0136d interfaceC0136d) {
        this.f1470w3 = new C0359v0(interfaceC0136d);
        this.f1469v3 = c0377b;
    }

    /* renamed from: a */
    public static C0445y0 m23103a(AbstractC0494y abstractC0494y, boolean z) {
        return m23102a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1469v3);
        c0140e.m24170a(this.f1470w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0377b m23101i() {
        return this.f1469v3;
    }

    /* renamed from: j */
    public C0377b m23100j() {
        return this.f1469v3;
    }

    /* renamed from: k */
    public AbstractC0258r m23099k() {
        return new C0162j(this.f1470w3.m23554m()).m24124d();
    }

    /* renamed from: l */
    public C0359v0 m23098l() {
        return this.f1470w3;
    }

    /* renamed from: m */
    public AbstractC0258r m23097m() {
        return new C0162j(this.f1470w3.m23554m()).m24124d();
    }

    /* renamed from: a */
    public static C0445y0 m23102a(Object obj) {
        if (obj instanceof C0445y0) {
            return (C0445y0) obj;
        }
        if (obj != null) {
            return new C0445y0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0445y0(C0377b c0377b, byte[] bArr) {
        this.f1470w3 = new C0359v0(bArr);
        this.f1469v3 = c0377b;
    }

    public C0445y0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 2) {
            Enumeration mo23747m = abstractC0263s.mo23747m();
            this.f1469v3 = C0377b.m23460a(mo23747m.nextElement());
            this.f1470w3 = C0359v0.m23557a(mo23747m.nextElement());
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
