package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2.C0219r;

/* renamed from: a.a.a.a.a.e.a.b.p2.i */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0245i extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public C0219r f445v3;

    /* renamed from: w3 */
    public C0246j f446w3;

    public C0245i(C0219r c0219r) {
        this.f445v3 = c0219r;
    }

    /* renamed from: a */
    public static C0245i m23799a(Object obj) {
        if (obj instanceof C0245i) {
            return (C0245i) obj;
        }
        if (obj instanceof AbstractC0494y) {
            return new C0245i(C0219r.m23915a((AbstractC0494y) obj, false));
        }
        if (obj instanceof C0246j) {
            return new C0245i((C0246j) obj);
        }
        return new C0245i(C0246j.m23795a(obj));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0246j c0246j = this.f446w3;
        if (c0246j != null) {
            return c0246j.mo23015d();
        }
        return new C0360v1(false, 0, this.f445v3);
    }

    /* renamed from: i */
    public InterfaceC0136d m23798i() {
        C0246j c0246j = this.f446w3;
        return c0246j != null ? c0246j : this.f445v3;
    }

    /* renamed from: j */
    public boolean m23797j() {
        return this.f446w3 != null;
    }

    public C0245i(C0246j c0246j) {
        this.f446w3 = c0246j;
    }
}
