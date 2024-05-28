package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0127a2;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0146f1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0340u0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0496y1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0452x;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.w2.u */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0434u extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: A3 */
    public static final int f1372A3 = 3;

    /* renamed from: B3 */
    public static final int f1373B3 = 200;

    /* renamed from: x3 */
    public static final int f1374x3 = 0;

    /* renamed from: y3 */
    public static final int f1375y3 = 1;

    /* renamed from: z3 */
    public static final int f1376z3 = 2;

    /* renamed from: v3 */
    public int f1377v3;

    /* renamed from: w3 */
    public InterfaceC0452x f1378w3;

    public C0434u(int i, String str) {
        str = str.length() > 200 ? str.substring(0, 200) : str;
        this.f1377v3 = i;
        switch (i) {
            case 0:
                this.f1378w3 = new C0146f1(str);
                return;
            case 1:
                this.f1378w3 = new C0340u0(str);
                return;
            case 2:
                this.f1378w3 = new C0496y1(str);
                return;
            case 3:
                this.f1378w3 = new C0127a2(str);
                return;
            default:
                this.f1378w3 = new C0496y1(str);
                return;
        }
    }

    /* renamed from: a */
    public static C0434u m23144a(Object obj) {
        if (obj instanceof InterfaceC0452x) {
            return new C0434u((InterfaceC0452x) obj);
        }
        if (obj != null && !(obj instanceof C0434u)) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0434u) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return (AbstractC0258r) this.f1378w3;
    }

    /* renamed from: e */
    public String m23143e() {
        return this.f1378w3.mo22978e();
    }

    /* renamed from: a */
    public static C0434u m23145a(AbstractC0494y abstractC0494y, boolean z) {
        return m23144a(abstractC0494y.m23004m());
    }

    public C0434u(String str) {
        str = str.length() > 200 ? str.substring(0, 200) : str;
        this.f1377v3 = 2;
        this.f1378w3 = new C0496y1(str);
    }

    public C0434u(InterfaceC0452x interfaceC0452x) {
        this.f1378w3 = interfaceC0452x;
    }
}
