package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;

/* renamed from: a.a.a.a.a.e.a.b.o2.x */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0231x extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public C0225u f394v3;

    /* renamed from: w3 */
    public C0208l0 f395w3;

    public C0231x(C0225u c0225u) {
        this.f394v3 = c0225u;
        this.f395w3 = null;
    }

    /* renamed from: a */
    public static C0231x m23861a(AbstractC0494y abstractC0494y, boolean z) {
        return m23860a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0225u c0225u = this.f394v3;
        if (c0225u != null) {
            return c0225u.mo23015d();
        }
        return new C0360v1(false, 0, this.f395w3);
    }

    /* renamed from: i */
    public C0225u m23859i() {
        return this.f394v3;
    }

    /* renamed from: j */
    public C0208l0 m23858j() {
        return this.f395w3;
    }

    /* renamed from: a */
    public static C0231x m23860a(Object obj) {
        if (obj != null && !(obj instanceof C0231x)) {
            if (obj instanceof AbstractC0263s) {
                return new C0231x(C0225u.m23890a(obj));
            }
            if (obj instanceof AbstractC0494y) {
                AbstractC0494y abstractC0494y = (AbstractC0494y) obj;
                if (abstractC0494y.mo22994f() == 0) {
                    return new C0231x(C0208l0.m23970a(abstractC0494y, false));
                }
            }
            throw new IllegalArgumentException("Invalid KeyAgreeRecipientIdentifier: " + obj.getClass().getName());
        }
        return (C0231x) obj;
    }

    public C0231x(C0208l0 c0208l0) {
        this.f394v3 = null;
        this.f395w3 = c0208l0;
    }
}
