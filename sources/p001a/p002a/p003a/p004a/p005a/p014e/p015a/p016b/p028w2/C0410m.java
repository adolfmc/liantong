package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.math.BigInteger;
import java.util.Hashtable;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0148g;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0495y0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0673e;

/* renamed from: a.a.a.a.a.e.a.b.w2.m */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0410m extends AbstractC0174m {

    /* renamed from: A3 */
    public static final int f1176A3 = 4;

    /* renamed from: B3 */
    public static final int f1177B3 = 5;

    /* renamed from: C3 */
    public static final int f1178C3 = 6;

    /* renamed from: D3 */
    public static final int f1179D3 = 8;

    /* renamed from: E3 */
    public static final int f1180E3 = 9;

    /* renamed from: F3 */
    public static final int f1181F3 = 10;

    /* renamed from: G3 */
    public static final int f1182G3 = 0;

    /* renamed from: H3 */
    public static final int f1183H3 = 1;

    /* renamed from: I3 */
    public static final int f1184I3 = 2;

    /* renamed from: J3 */
    public static final int f1185J3 = 3;

    /* renamed from: K3 */
    public static final int f1186K3 = 4;

    /* renamed from: L3 */
    public static final int f1187L3 = 5;

    /* renamed from: M3 */
    public static final int f1188M3 = 6;

    /* renamed from: N3 */
    public static final int f1189N3 = 8;

    /* renamed from: O3 */
    public static final int f1190O3 = 9;

    /* renamed from: P3 */
    public static final int f1191P3 = 10;

    /* renamed from: Q3 */
    public static final String[] f1192Q3 = {"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};

    /* renamed from: R3 */
    public static final Hashtable f1193R3 = new Hashtable();

    /* renamed from: w3 */
    public static final int f1194w3 = 0;

    /* renamed from: x3 */
    public static final int f1195x3 = 1;

    /* renamed from: y3 */
    public static final int f1196y3 = 2;

    /* renamed from: z3 */
    public static final int f1197z3 = 3;

    /* renamed from: v3 */
    public C0148g f1198v3;

    public C0410m(int i) {
        this.f1198v3 = new C0148g(i);
    }

    /* renamed from: a */
    public static C0410m m23265a(Object obj) {
        if (obj instanceof C0410m) {
            return (C0410m) obj;
        }
        if (obj != null) {
            return m23266a(C0495y0.m23000a(obj).m22998m().intValue());
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1198v3;
    }

    /* renamed from: i */
    public BigInteger m23264i() {
        return this.f1198v3.m22998m();
    }

    public String toString() {
        int intValue = m23264i().intValue();
        String str = (intValue < 0 || intValue > 10) ? "invalid" : f1192Q3[intValue];
        return "CRLReason: " + str;
    }

    /* renamed from: a */
    public static C0410m m23266a(int i) {
        Integer m22455a = C0673e.m22455a(i);
        if (!f1193R3.containsKey(m22455a)) {
            f1193R3.put(m22455a, new C0410m(i));
        }
        return (C0410m) f1193R3.get(m22455a);
    }
}
