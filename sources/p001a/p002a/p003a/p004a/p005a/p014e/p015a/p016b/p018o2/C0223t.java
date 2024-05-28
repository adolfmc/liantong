package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;

/* renamed from: a.a.a.a.a.e.a.b.o2.t */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0223t extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public C0224t0 f370v3;

    public C0223t(C0224t0 c0224t0) {
        this.f370v3 = c0224t0;
    }

    /* renamed from: a */
    public static C0223t m23895a(Object obj) {
        if (obj != null && !(obj instanceof C0223t)) {
            if (obj instanceof AbstractC0494y) {
                return new C0223t(AbstractC0494y.m23008a(obj));
            }
            throw new IllegalArgumentException("unknown object in getInstance");
        }
        return (C0223t) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0224t0 c0224t0 = this.f370v3;
        if (c0224t0 != null) {
            return new C0360v1(false, 0, c0224t0);
        }
        return null;
    }

    /* renamed from: i */
    public C0224t0 m23894i() {
        return this.f370v3;
    }

    public C0223t(AbstractC0494y abstractC0494y) {
        if (abstractC0494y.mo22994f() == 0) {
            this.f370v3 = C0224t0.m23893a(abstractC0494y, false);
        }
    }
}
