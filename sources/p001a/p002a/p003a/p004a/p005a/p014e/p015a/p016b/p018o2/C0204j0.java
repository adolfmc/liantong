package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.o2.j0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0204j0 extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public InterfaceC0136d f306v3;

    public C0204j0(C0225u c0225u) {
        this.f306v3 = c0225u;
    }

    /* renamed from: a */
    public static C0204j0 m23985a(Object obj) {
        if (obj != null && !(obj instanceof C0204j0)) {
            if (obj instanceof C0225u) {
                return new C0204j0((C0225u) obj);
            }
            if (obj instanceof AbstractC0182o) {
                return new C0204j0((AbstractC0182o) obj);
            }
            if (obj instanceof AbstractC0258r) {
                return new C0204j0((AbstractC0258r) obj);
            }
            throw new IllegalArgumentException("Illegal object in RecipientIdentifier: " + obj.getClass().getName());
        }
        return (C0204j0) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f306v3.mo23015d();
    }

    /* renamed from: i */
    public InterfaceC0136d m23984i() {
        InterfaceC0136d interfaceC0136d = this.f306v3;
        if (interfaceC0136d instanceof AbstractC0494y) {
            return AbstractC0182o.m24090a((AbstractC0494y) interfaceC0136d, false);
        }
        return C0225u.m23890a(interfaceC0136d);
    }

    /* renamed from: j */
    public boolean m23983j() {
        return this.f306v3 instanceof AbstractC0494y;
    }

    public C0204j0(AbstractC0182o abstractC0182o) {
        this.f306v3 = new C0360v1(false, 0, abstractC0182o);
    }

    public C0204j0(AbstractC0258r abstractC0258r) {
        this.f306v3 = abstractC0258r;
    }
}
