package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.s2.j */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0281j extends AbstractC0174m {

    /* renamed from: v3 */
    public C0377b f611v3;

    /* renamed from: w3 */
    public AbstractC0182o f612w3;

    public C0281j(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f611v3 = C0377b.m23460a(mo23747m.nextElement());
        this.f612w3 = AbstractC0182o.m24089a(mo23747m.nextElement());
    }

    /* renamed from: a */
    public static C0281j m23683a(Object obj) {
        if (obj instanceof C0281j) {
            return (C0281j) obj;
        }
        if (obj != null) {
            return new C0281j(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f611v3);
        c0140e.m24170a(this.f612w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public byte[] m23682i() {
        return this.f612w3.mo24088m();
    }

    /* renamed from: j */
    public C0377b m23681j() {
        return this.f611v3;
    }

    public C0281j(C0377b c0377b, byte[] bArr) {
        this.f611v3 = c0377b;
        this.f612w3 = new C0168k1(bArr);
    }
}
