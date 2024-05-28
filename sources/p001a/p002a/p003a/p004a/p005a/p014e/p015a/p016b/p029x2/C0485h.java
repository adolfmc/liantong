package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0170l;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.x2.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0485h extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public AbstractC0258r f1525v3;

    public C0485h(C0487j c0487j) {
        this.f1525v3 = null;
        this.f1525v3 = c0487j.mo23015d();
    }

    /* renamed from: a */
    public static C0485h m23032a(Object obj) {
        if (obj != null && !(obj instanceof C0485h)) {
            if (obj instanceof AbstractC0258r) {
                return new C0485h((AbstractC0258r) obj);
            }
            throw new IllegalArgumentException("unknown object in getInstance()");
        }
        return (C0485h) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1525v3;
    }

    /* renamed from: i */
    public AbstractC0258r m23031i() {
        return this.f1525v3;
    }

    /* renamed from: j */
    public boolean m23030j() {
        return this.f1525v3 instanceof AbstractC0170l;
    }

    /* renamed from: k */
    public boolean m23029k() {
        return this.f1525v3 instanceof C0178n;
    }

    /* renamed from: a */
    public static C0485h m23033a(AbstractC0494y abstractC0494y, boolean z) {
        return m23032a(abstractC0494y.m23004m());
    }

    public C0485h(C0178n c0178n) {
        this.f1525v3 = null;
        this.f1525v3 = c0178n;
    }

    public C0485h(AbstractC0258r abstractC0258r) {
        this.f1525v3 = null;
        this.f1525v3 = abstractC0258r;
    }
}
