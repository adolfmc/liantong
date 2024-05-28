package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.s2.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0284m extends AbstractC0174m {

    /* renamed from: v3 */
    public C0377b f616v3;

    public C0284m(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f616v3 = new C0377b(c0178n, interfaceC0136d);
    }

    /* renamed from: a */
    public static final C0284m m23674a(Object obj) {
        if (obj instanceof C0284m) {
            return (C0284m) obj;
        }
        if (obj != null) {
            return new C0284m(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f616v3.mo23015d();
    }

    /* renamed from: i */
    public C0178n m23673i() {
        return this.f616v3.m23459i();
    }

    /* renamed from: j */
    public InterfaceC0136d m23672j() {
        return this.f616v3.m23457k();
    }

    public C0284m(AbstractC0263s abstractC0263s) {
        this.f616v3 = C0377b.m23460a(abstractC0263s);
    }
}
