package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.w */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0440w extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: x3 */
    public static final int f1427x3 = 0;

    /* renamed from: y3 */
    public static final int f1428y3 = 1;

    /* renamed from: v3 */
    public InterfaceC0136d f1429v3;

    /* renamed from: w3 */
    public int f1430w3;

    public C0440w(int i, InterfaceC0136d interfaceC0136d) {
        this.f1430w3 = i;
        this.f1429v3 = interfaceC0136d;
    }

    /* renamed from: a */
    public static C0440w m23129a(AbstractC0494y abstractC0494y, boolean z) {
        return m23128a(AbstractC0494y.m23009a(abstractC0494y, true));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return new C0360v1(false, this.f1430w3, this.f1429v3);
    }

    /* renamed from: i */
    public InterfaceC0136d m23126i() {
        return this.f1429v3;
    }

    /* renamed from: j */
    public int m23125j() {
        return this.f1430w3;
    }

    public String toString() {
        String property = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPointName: [");
        stringBuffer.append(property);
        if (this.f1430w3 == 0) {
            m23127a(stringBuffer, property, "fullName", this.f1429v3.toString());
        } else {
            m23127a(stringBuffer, property, "nameRelativeToCRLIssuer", this.f1429v3.toString());
        }
        stringBuffer.append("]");
        stringBuffer.append(property);
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static C0440w m23128a(Object obj) {
        if (obj != null && !(obj instanceof C0440w)) {
            if (obj instanceof AbstractC0494y) {
                return new C0440w((AbstractC0494y) obj);
            }
            throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
        }
        return (C0440w) obj;
    }

    public C0440w(C0381c0 c0381c0) {
        this(0, c0381c0);
    }

    public C0440w(AbstractC0494y abstractC0494y) {
        int mo22994f = abstractC0494y.mo22994f();
        this.f1430w3 = mo22994f;
        if (mo22994f == 0) {
            this.f1429v3 = C0381c0.m23428a(abstractC0494y, false);
        } else {
            this.f1429v3 = AbstractC0338u.m23582a(abstractC0494y, false);
        }
    }

    /* renamed from: a */
    private void m23127a(StringBuffer stringBuffer, String str, String str2, String str3) {
        stringBuffer.append("    ");
        stringBuffer.append(str2);
        stringBuffer.append(":");
        stringBuffer.append(str);
        stringBuffer.append("    ");
        stringBuffer.append("    ");
        stringBuffer.append(str3);
        stringBuffer.append(str);
    }
}
