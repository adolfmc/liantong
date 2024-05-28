package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0395h extends AbstractC0174m {

    /* renamed from: v3 */
    public C0374a[] f1082v3;

    public C0395h(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() >= 1) {
            this.f1082v3 = new C0374a[abstractC0263s.mo23745o()];
            for (int i = 0; i != abstractC0263s.mo23745o(); i++) {
                this.f1082v3[i] = C0374a.m23483a(abstractC0263s.mo23751a(i));
            }
            return;
        }
        throw new IllegalArgumentException("sequence may not be empty");
    }

    /* renamed from: a */
    public static C0395h m23372a(Object obj) {
        if (obj instanceof C0395h) {
            return (C0395h) obj;
        }
        if (obj != null) {
            return new C0395h(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        int i = 0;
        while (true) {
            C0374a[] c0374aArr = this.f1082v3;
            if (i != c0374aArr.length) {
                c0140e.m24170a(c0374aArr[i]);
                i++;
            } else {
                return new C0184o1(c0140e);
            }
        }
    }

    /* renamed from: i */
    public C0374a[] m23371i() {
        return this.f1082v3;
    }

    public String toString() {
        return "AuthorityInformationAccess: Oid(" + this.f1082v3[0].m23481j().m24113n() + ")";
    }

    public C0395h(C0178n c0178n, C0378b0 c0378b0) {
        this.f1082v3 = r0;
        C0374a[] c0374aArr = {new C0374a(c0178n, c0378b0)};
    }
}
