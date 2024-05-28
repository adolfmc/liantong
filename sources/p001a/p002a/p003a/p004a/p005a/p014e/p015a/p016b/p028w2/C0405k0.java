package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;

/* renamed from: a.a.a.a.a.e.a.b.w2.k0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0405k0 extends AbstractC0174m {

    /* renamed from: A3 */
    public static final int f1143A3 = 8;

    /* renamed from: B3 */
    public static final int f1144B3 = 4;

    /* renamed from: C3 */
    public static final int f1145C3 = 2;

    /* renamed from: D3 */
    public static final int f1146D3 = 1;

    /* renamed from: E3 */
    public static final int f1147E3 = 32768;

    /* renamed from: w3 */
    public static final int f1148w3 = 128;

    /* renamed from: x3 */
    public static final int f1149x3 = 64;

    /* renamed from: y3 */
    public static final int f1150y3 = 32;

    /* renamed from: z3 */
    public static final int f1151z3 = 16;

    /* renamed from: v3 */
    public C0359v0 f1152v3;

    public C0405k0(int i) {
        this.f1152v3 = new C0359v0(i);
    }

    /* renamed from: a */
    public static C0405k0 m23309a(Object obj) {
        if (obj instanceof C0405k0) {
            return (C0405k0) obj;
        }
        if (obj != null) {
            return new C0405k0(C0359v0.m23557a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1152v3;
    }

    /* renamed from: i */
    public byte[] m23308i() {
        return this.f1152v3.m23554m();
    }

    /* renamed from: j */
    public int m23307j() {
        return this.f1152v3.m23553n();
    }

    public String toString() {
        byte[] m23554m = this.f1152v3.m23554m();
        if (m23554m.length == 1) {
            return "KeyUsage: 0x" + Integer.toHexString(m23554m[0] & 255);
        }
        return "KeyUsage: 0x" + Integer.toHexString((m23554m[0] & 255) | ((m23554m[1] & 255) << 8));
    }

    public C0405k0(C0359v0 c0359v0) {
        this.f1152v3 = c0359v0;
    }

    /* renamed from: a */
    public static C0405k0 m23310a(C0446z c0446z) {
        return m23309a(c0446z.m23090b(C0444y.f1435A3));
    }
}
