package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.w2.k */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0404k extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0263s f1142v3;

    public C0404k(AbstractC0263s abstractC0263s) {
        this.f1142v3 = null;
        this.f1142v3 = abstractC0263s;
    }

    /* renamed from: a */
    public static C0404k m23313a(AbstractC0494y abstractC0494y, boolean z) {
        return m23312a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1142v3;
    }

    /* renamed from: i */
    public C0437v[] m23311i() {
        C0437v[] c0437vArr = new C0437v[this.f1142v3.mo23745o()];
        for (int i = 0; i != this.f1142v3.mo23745o(); i++) {
            c0437vArr[i] = C0437v.m23139a(this.f1142v3.mo23751a(i));
        }
        return c0437vArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("CRLDistPoint:");
        stringBuffer.append(property);
        C0437v[] m23311i = m23311i();
        for (int i = 0; i != m23311i.length; i++) {
            stringBuffer.append("    ");
            stringBuffer.append(m23311i[i]);
            stringBuffer.append(property);
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static C0404k m23312a(Object obj) {
        if (obj instanceof C0404k) {
            return (C0404k) obj;
        }
        if (obj != null) {
            return new C0404k(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0404k(C0437v[] c0437vArr) {
        this.f1142v3 = null;
        C0140e c0140e = new C0140e();
        for (int i = 0; i != c0437vArr.length; i++) {
            c0140e.m24170a(c0437vArr[i]);
        }
        this.f1142v3 = new C0184o1(c0140e);
    }
}
