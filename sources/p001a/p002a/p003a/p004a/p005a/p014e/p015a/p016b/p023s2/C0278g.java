package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0135c2;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0235p0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.s2.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0278g extends AbstractC0174m implements InterfaceC0291t {

    /* renamed from: v3 */
    public C0178n f602v3;

    /* renamed from: w3 */
    public InterfaceC0136d f603w3;

    /* renamed from: x3 */
    public boolean f604x3;

    public C0278g(AbstractC0263s abstractC0263s) {
        this.f604x3 = true;
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f602v3 = (C0178n) mo23747m.nextElement();
        if (mo23747m.hasMoreElements()) {
            this.f603w3 = ((AbstractC0494y) mo23747m.nextElement()).m23004m();
        }
        this.f604x3 = abstractC0263s instanceof C0167k0;
    }

    /* renamed from: a */
    public static C0278g m23694a(Object obj) {
        if (obj instanceof C0278g) {
            return (C0278g) obj;
        }
        if (obj != null) {
            return new C0278g(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f602v3);
        InterfaceC0136d interfaceC0136d = this.f603w3;
        if (interfaceC0136d != null) {
            c0140e.m24170a(new C0235p0(true, 0, interfaceC0136d));
        }
        if (this.f604x3) {
            return new C0167k0(c0140e);
        }
        return new C0135c2(c0140e);
    }

    /* renamed from: i */
    public InterfaceC0136d m23693i() {
        return this.f603w3;
    }

    /* renamed from: j */
    public C0178n m23692j() {
        return this.f602v3;
    }

    public C0278g(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f604x3 = true;
        this.f602v3 = c0178n;
        this.f603w3 = interfaceC0136d;
    }
}
