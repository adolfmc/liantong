package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.o2.p0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0216p0 extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public InterfaceC0136d f347v3;

    public C0216p0(C0225u c0225u) {
        this.f347v3 = c0225u;
    }

    /* renamed from: a */
    public static C0216p0 m23931a(Object obj) {
        if (obj != null && !(obj instanceof C0216p0)) {
            if (obj instanceof C0225u) {
                return new C0216p0((C0225u) obj);
            }
            if (obj instanceof AbstractC0182o) {
                return new C0216p0((AbstractC0182o) obj);
            }
            if (obj instanceof AbstractC0258r) {
                return new C0216p0((AbstractC0258r) obj);
            }
            throw new IllegalArgumentException("Illegal object in SignerIdentifier: " + obj.getClass().getName());
        }
        return (C0216p0) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f347v3.mo23015d();
    }

    /* renamed from: i */
    public InterfaceC0136d m23930i() {
        InterfaceC0136d interfaceC0136d = this.f347v3;
        return interfaceC0136d instanceof AbstractC0494y ? AbstractC0182o.m24090a((AbstractC0494y) interfaceC0136d, false) : interfaceC0136d;
    }

    /* renamed from: j */
    public boolean m23929j() {
        return this.f347v3 instanceof AbstractC0494y;
    }

    public C0216p0(AbstractC0182o abstractC0182o) {
        this.f347v3 = new C0360v1(false, 0, abstractC0182o);
    }

    public C0216p0(AbstractC0258r abstractC0258r) {
        this.f347v3 = abstractC0258r;
    }
}
