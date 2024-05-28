package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.p2.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0243g extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0263s f442v3;

    public C0243g(AbstractC0263s abstractC0263s) {
        this.f442v3 = abstractC0263s;
    }

    /* renamed from: a */
    public static C0243g m23806a(Object obj) {
        if (obj instanceof C0243g) {
            return (C0243g) obj;
        }
        if (obj != null) {
            return new C0243g(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f442v3;
    }

    /* renamed from: i */
    public C0237a[] m23805i() {
        int mo23745o = this.f442v3.mo23745o();
        C0237a[] c0237aArr = new C0237a[mo23745o];
        for (int i = 0; i != mo23745o; i++) {
            c0237aArr[i] = C0237a.m23841a(this.f442v3.mo23751a(i));
        }
        return c0237aArr;
    }

    public C0243g(C0237a c0237a) {
        this.f442v3 = new C0184o1(c0237a);
    }

    public C0243g(C0237a[] c0237aArr) {
        C0140e c0140e = new C0140e();
        for (C0237a c0237a : c0237aArr) {
            c0140e.m24170a(c0237a);
        }
        this.f442v3 = new C0184o1(c0140e);
    }
}
