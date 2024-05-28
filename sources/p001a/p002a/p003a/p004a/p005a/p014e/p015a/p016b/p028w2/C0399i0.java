package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0372w0;

/* renamed from: a.a.a.a.a.e.a.b.w2.i0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0399i0 extends AbstractC0174m {

    /* renamed from: A3 */
    public boolean f1097A3;

    /* renamed from: B3 */
    public AbstractC0263s f1098B3;

    /* renamed from: v3 */
    public C0440w f1099v3;

    /* renamed from: w3 */
    public boolean f1100w3;

    /* renamed from: x3 */
    public boolean f1101x3;

    /* renamed from: y3 */
    public C0435u0 f1102y3;

    /* renamed from: z3 */
    public boolean f1103z3;

    public C0399i0(C0440w c0440w, boolean z, boolean z2, C0435u0 c0435u0, boolean z3, boolean z4) {
        this.f1099v3 = c0440w;
        this.f1103z3 = z3;
        this.f1097A3 = z4;
        this.f1101x3 = z2;
        this.f1100w3 = z;
        this.f1102y3 = c0435u0;
        C0140e c0140e = new C0140e();
        if (c0440w != null) {
            c0140e.m24170a(new C0360v1(true, 0, c0440w));
        }
        if (z) {
            c0140e.m24170a(new C0360v1(false, 1, C0372w0.m23486a(true)));
        }
        if (z2) {
            c0140e.m24170a(new C0360v1(false, 2, C0372w0.m23486a(true)));
        }
        if (c0435u0 != null) {
            c0140e.m24170a(new C0360v1(false, 3, c0435u0));
        }
        if (z3) {
            c0140e.m24170a(new C0360v1(false, 4, C0372w0.m23486a(true)));
        }
        if (z4) {
            c0140e.m24170a(new C0360v1(false, 5, C0372w0.m23486a(true)));
        }
        this.f1098B3 = new C0184o1(c0140e);
    }

    /* renamed from: a */
    public static C0399i0 m23347a(AbstractC0494y abstractC0494y, boolean z) {
        return m23346a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    /* renamed from: a */
    private String m23344a(boolean z) {
        return z ? "true" : "false";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1098B3;
    }

    /* renamed from: i */
    public C0440w m23343i() {
        return this.f1099v3;
    }

    /* renamed from: j */
    public C0435u0 m23342j() {
        return this.f1102y3;
    }

    /* renamed from: k */
    public boolean m23341k() {
        return this.f1103z3;
    }

    /* renamed from: l */
    public boolean m23340l() {
        return this.f1097A3;
    }

    /* renamed from: m */
    public boolean m23339m() {
        return this.f1101x3;
    }

    /* renamed from: n */
    public boolean m23338n() {
        return this.f1100w3;
    }

    public String toString() {
        String property = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("IssuingDistributionPoint: [");
        stringBuffer.append(property);
        C0440w c0440w = this.f1099v3;
        if (c0440w != null) {
            m23345a(stringBuffer, property, "distributionPoint", c0440w.toString());
        }
        boolean z = this.f1100w3;
        if (z) {
            m23345a(stringBuffer, property, "onlyContainsUserCerts", m23344a(z));
        }
        boolean z2 = this.f1101x3;
        if (z2) {
            m23345a(stringBuffer, property, "onlyContainsCACerts", m23344a(z2));
        }
        C0435u0 c0435u0 = this.f1102y3;
        if (c0435u0 != null) {
            m23345a(stringBuffer, property, "onlySomeReasons", c0435u0.toString());
        }
        boolean z3 = this.f1097A3;
        if (z3) {
            m23345a(stringBuffer, property, "onlyContainsAttributeCerts", m23344a(z3));
        }
        boolean z4 = this.f1103z3;
        if (z4) {
            m23345a(stringBuffer, property, "indirectCRL", m23344a(z4));
        }
        stringBuffer.append("]");
        stringBuffer.append(property);
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static C0399i0 m23346a(Object obj) {
        if (obj instanceof C0399i0) {
            return (C0399i0) obj;
        }
        if (obj != null) {
            return new C0399i0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    /* renamed from: a */
    private void m23345a(StringBuffer stringBuffer, String str, String str2, String str3) {
        stringBuffer.append("    ");
        stringBuffer.append(str2);
        stringBuffer.append(":");
        stringBuffer.append(str);
        stringBuffer.append("    ");
        stringBuffer.append("    ");
        stringBuffer.append(str3);
        stringBuffer.append(str);
    }

    public C0399i0(C0440w c0440w, boolean z, boolean z2) {
        this(c0440w, false, false, null, z, z2);
    }

    public C0399i0(AbstractC0263s abstractC0263s) {
        this.f1098B3 = abstractC0263s;
        for (int i = 0; i != abstractC0263s.mo23745o(); i++) {
            AbstractC0494y m23008a = AbstractC0494y.m23008a(abstractC0263s.mo23751a(i));
            switch (m23008a.mo22994f()) {
                case 0:
                    this.f1099v3 = C0440w.m23129a(m23008a, true);
                    break;
                case 1:
                    this.f1100w3 = C0372w0.m23488a(m23008a, false).m23484m();
                    break;
                case 2:
                    this.f1101x3 = C0372w0.m23488a(m23008a, false).m23484m();
                    break;
                case 3:
                    this.f1102y3 = new C0435u0(C0359v0.m23558a(m23008a, false));
                    break;
                case 4:
                    this.f1103z3 = C0372w0.m23488a(m23008a, false).m23484m();
                    break;
                case 5:
                    this.f1097A3 = C0372w0.m23488a(m23008a, false).m23484m();
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag in IssuingDistributionPoint");
            }
        }
    }
}
