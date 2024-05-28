package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.w2.v */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0437v extends AbstractC0174m {

    /* renamed from: v3 */
    public C0440w f1401v3;

    /* renamed from: w3 */
    public C0435u0 f1402w3;

    /* renamed from: x3 */
    public C0381c0 f1403x3;

    public C0437v(AbstractC0263s abstractC0263s) {
        for (int i = 0; i != abstractC0263s.mo23745o(); i++) {
            AbstractC0494y m23008a = AbstractC0494y.m23008a(abstractC0263s.mo23751a(i));
            int mo22994f = m23008a.mo22994f();
            if (mo22994f == 0) {
                this.f1401v3 = C0440w.m23129a(m23008a, true);
            } else if (mo22994f == 1) {
                this.f1402w3 = new C0435u0(C0359v0.m23558a(m23008a, false));
            } else if (mo22994f == 2) {
                this.f1403x3 = C0381c0.m23428a(m23008a, false);
            }
        }
    }

    /* renamed from: a */
    public static C0437v m23140a(AbstractC0494y abstractC0494y, boolean z) {
        return m23139a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0440w c0440w = this.f1401v3;
        if (c0440w != null) {
            c0140e.m24170a(new C0360v1(0, c0440w));
        }
        C0435u0 c0435u0 = this.f1402w3;
        if (c0435u0 != null) {
            c0140e.m24170a(new C0360v1(false, 1, c0435u0));
        }
        C0381c0 c0381c0 = this.f1403x3;
        if (c0381c0 != null) {
            c0140e.m24170a(new C0360v1(false, 2, c0381c0));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0381c0 m23137i() {
        return this.f1403x3;
    }

    /* renamed from: j */
    public C0440w m23136j() {
        return this.f1401v3;
    }

    /* renamed from: k */
    public C0435u0 m23135k() {
        return this.f1402w3;
    }

    public String toString() {
        String property = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPoint: [");
        stringBuffer.append(property);
        C0440w c0440w = this.f1401v3;
        if (c0440w != null) {
            m23138a(stringBuffer, property, "distributionPoint", c0440w.toString());
        }
        C0435u0 c0435u0 = this.f1402w3;
        if (c0435u0 != null) {
            m23138a(stringBuffer, property, "reasons", c0435u0.toString());
        }
        C0381c0 c0381c0 = this.f1403x3;
        if (c0381c0 != null) {
            m23138a(stringBuffer, property, "cRLIssuer", c0381c0.toString());
        }
        stringBuffer.append("]");
        stringBuffer.append(property);
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static C0437v m23139a(Object obj) {
        if (obj != null && !(obj instanceof C0437v)) {
            if (obj instanceof AbstractC0263s) {
                return new C0437v((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid DistributionPoint: " + obj.getClass().getName());
        }
        return (C0437v) obj;
    }

    /* renamed from: a */
    private void m23138a(StringBuffer stringBuffer, String str, String str2, String str3) {
        stringBuffer.append("    ");
        stringBuffer.append(str2);
        stringBuffer.append(":");
        stringBuffer.append(str);
        stringBuffer.append("    ");
        stringBuffer.append("    ");
        stringBuffer.append(str3);
        stringBuffer.append(str);
    }

    public C0437v(C0440w c0440w, C0435u0 c0435u0, C0381c0 c0381c0) {
        this.f1401v3 = c0440w;
        this.f1402w3 = c0435u0;
        this.f1403x3 = c0381c0;
    }
}
