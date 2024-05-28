package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.c0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0381c0 extends AbstractC0174m {

    /* renamed from: v3 */
    public final C0378b0[] f1039v3;

    public C0381c0(C0378b0 c0378b0) {
        this.f1039v3 = new C0378b0[]{c0378b0};
    }

    /* renamed from: a */
    public static C0381c0 m23427a(Object obj) {
        if (obj instanceof C0381c0) {
            return (C0381c0) obj;
        }
        if (obj != null) {
            return new C0381c0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return new C0184o1(this.f1039v3);
    }

    /* renamed from: i */
    public C0378b0[] m23426i() {
        C0378b0[] c0378b0Arr = this.f1039v3;
        C0378b0[] c0378b0Arr2 = new C0378b0[c0378b0Arr.length];
        System.arraycopy(c0378b0Arr, 0, c0378b0Arr2, 0, c0378b0Arr.length);
        return c0378b0Arr2;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("GeneralNames:");
        stringBuffer.append(property);
        for (int i = 0; i != this.f1039v3.length; i++) {
            stringBuffer.append("    ");
            stringBuffer.append(this.f1039v3[i]);
            stringBuffer.append(property);
        }
        return stringBuffer.toString();
    }

    public C0381c0(C0378b0[] c0378b0Arr) {
        this.f1039v3 = c0378b0Arr;
    }

    public C0381c0(AbstractC0263s abstractC0263s) {
        this.f1039v3 = new C0378b0[abstractC0263s.mo23745o()];
        for (int i = 0; i != abstractC0263s.mo23745o(); i++) {
            this.f1039v3[i] = C0378b0.m23455a(abstractC0263s.mo23751a(i));
        }
    }

    /* renamed from: a */
    public static C0381c0 m23428a(AbstractC0494y abstractC0494y, boolean z) {
        return m23427a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    /* renamed from: a */
    public static C0381c0 m23429a(C0446z c0446z, C0178n c0178n) {
        return m23427a(c0446z.m23090b(c0178n));
    }
}
