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
/* renamed from: a.a.a.a.a.e.a.b.s2.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0282k extends AbstractC0174m {

    /* renamed from: v3 */
    public C0377b f613v3;

    public C0282k(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f613v3 = new C0377b(c0178n, interfaceC0136d);
    }

    /* renamed from: a */
    public static final C0282k m23680a(Object obj) {
        if (obj instanceof C0282k) {
            return (C0282k) obj;
        }
        if (obj != null) {
            return new C0282k(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f613v3.mo23015d();
    }

    /* renamed from: i */
    public C0178n m23679i() {
        return this.f613v3.m23459i();
    }

    /* renamed from: j */
    public InterfaceC0136d m23678j() {
        return this.f613v3.m23457k();
    }

    public C0282k(AbstractC0263s abstractC0263s) {
        this.f613v3 = C0377b.m23460a(abstractC0263s);
    }
}
