package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.w2.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0380c extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public InterfaceC0136d f1037v3;

    /* renamed from: w3 */
    public AbstractC0258r f1038w3;

    public C0380c(C0381c0 c0381c0) {
        this.f1037v3 = c0381c0;
        this.f1038w3 = c0381c0.mo23015d();
    }

    /* renamed from: a */
    public static C0380c m23431a(Object obj) {
        if (obj != null && !(obj instanceof C0380c)) {
            if (obj instanceof C0403j1) {
                return new C0380c(C0403j1.m23317a(obj));
            }
            if (obj instanceof C0381c0) {
                return new C0380c((C0381c0) obj);
            }
            if (obj instanceof AbstractC0494y) {
                return new C0380c(C0403j1.m23318a((AbstractC0494y) obj, false));
            }
            if (obj instanceof AbstractC0263s) {
                return new C0380c(C0381c0.m23427a(obj));
            }
            throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
        }
        return (C0380c) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1038w3;
    }

    /* renamed from: i */
    public InterfaceC0136d m23430i() {
        return this.f1037v3;
    }

    public C0380c(C0403j1 c0403j1) {
        this.f1037v3 = c0403j1;
        this.f1038w3 = new C0360v1(false, 0, this.f1037v3);
    }

    /* renamed from: a */
    public static C0380c m23432a(AbstractC0494y abstractC0494y, boolean z) {
        return m23431a(abstractC0494y.m23004m());
    }
}
