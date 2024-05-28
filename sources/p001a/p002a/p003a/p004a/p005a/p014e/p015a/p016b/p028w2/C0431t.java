package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.t */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0431t extends AbstractC0174m {

    /* renamed from: v3 */
    public byte[] f1368v3;

    /* renamed from: w3 */
    public C0377b f1369w3;

    public C0431t(C0377b c0377b, byte[] bArr) {
        this.f1368v3 = bArr;
        this.f1369w3 = c0377b;
    }

    /* renamed from: a */
    public static C0431t m23156a(AbstractC0494y abstractC0494y, boolean z) {
        return m23155a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1369w3);
        c0140e.m24170a(new C0168k1(this.f1368v3));
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0377b m23154i() {
        return this.f1369w3;
    }

    /* renamed from: j */
    public byte[] m23153j() {
        return this.f1368v3;
    }

    /* renamed from: a */
    public static C0431t m23155a(Object obj) {
        if (obj instanceof C0431t) {
            return (C0431t) obj;
        }
        if (obj != null) {
            return new C0431t(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0431t(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f1369w3 = C0377b.m23460a(mo23747m.nextElement());
        this.f1368v3 = AbstractC0182o.m24089a(mo23747m.nextElement()).mo24088m();
    }
}
