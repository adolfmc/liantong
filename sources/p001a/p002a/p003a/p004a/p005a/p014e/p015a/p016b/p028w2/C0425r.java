package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.r */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0425r extends AbstractC0174m {

    /* renamed from: v3 */
    public final C0417o0[] f1307v3;

    public C0425r(C0417o0 c0417o0) {
        this.f1307v3 = new C0417o0[]{c0417o0};
    }

    /* renamed from: a */
    public static C0425r m23192a(Object obj) {
        if (obj instanceof C0425r) {
            return (C0425r) obj;
        }
        if (obj != null) {
            return new C0425r(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return new C0184o1(this.f1307v3);
    }

    /* renamed from: i */
    public C0417o0[] m23191i() {
        C0417o0[] c0417o0Arr = this.f1307v3;
        C0417o0[] c0417o0Arr2 = new C0417o0[c0417o0Arr.length];
        System.arraycopy(c0417o0Arr, 0, c0417o0Arr2, 0, c0417o0Arr.length);
        return c0417o0Arr2;
    }

    public String toString() {
        String str = null;
        for (int i = 0; i < this.f1307v3.length; i++) {
            if (str != null) {
                str = str + ", ";
            }
            str = str + this.f1307v3[i];
        }
        return "CertificatePolicies: " + str;
    }

    public C0425r(C0417o0[] c0417o0Arr) {
        this.f1307v3 = c0417o0Arr;
    }

    public C0425r(AbstractC0263s abstractC0263s) {
        this.f1307v3 = new C0417o0[abstractC0263s.mo23745o()];
        for (int i = 0; i != abstractC0263s.mo23745o(); i++) {
            this.f1307v3[i] = C0417o0.m23226a(abstractC0263s.mo23751a(i));
        }
    }

    /* renamed from: a */
    public static C0425r m23193a(AbstractC0494y abstractC0494y, boolean z) {
        return m23192a(AbstractC0263s.m23750a(abstractC0494y, z));
    }
}
