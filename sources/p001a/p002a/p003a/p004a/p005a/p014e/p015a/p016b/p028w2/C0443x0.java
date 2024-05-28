package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.C0512f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.w2.x0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0443x0 extends AbstractC0174m {

    /* renamed from: v3 */
    public byte[] f1434v3;

    public C0443x0(byte[] bArr) {
        this.f1434v3 = bArr;
    }

    /* renamed from: a */
    public static C0443x0 m23114a(AbstractC0494y abstractC0494y, boolean z) {
        return m23113a(AbstractC0182o.m24090a(abstractC0494y, z));
    }

    /* renamed from: b */
    public static C0443x0 m23112b(C0445y0 c0445y0) {
        byte[] m23111c = m23111c(c0445y0);
        byte[] bArr = new byte[8];
        System.arraycopy(m23111c, m23111c.length - 8, bArr, 0, 8);
        bArr[0] = (byte) (bArr[0] & 15);
        bArr[0] = (byte) (bArr[0] | 64);
        return new C0443x0(bArr);
    }

    /* renamed from: c */
    public static byte[] m23111c(C0445y0 c0445y0) {
        C0512f c0512f = new C0512f();
        byte[] bArr = new byte[c0512f.mo22743c()];
        byte[] m23554m = c0445y0.m23098l().m23554m();
        c0512f.mo22745a(m23554m, 0, m23554m.length);
        c0512f.mo22746a(bArr, 0);
        return bArr;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return new C0168k1(this.f1434v3);
    }

    /* renamed from: i */
    public byte[] m23110i() {
        return this.f1434v3;
    }

    /* renamed from: a */
    public static C0443x0 m23113a(Object obj) {
        if (obj instanceof C0443x0) {
            return (C0443x0) obj;
        }
        if (obj != null) {
            return new C0443x0(AbstractC0182o.m24089a(obj));
        }
        return null;
    }

    public C0443x0(AbstractC0182o abstractC0182o) {
        this.f1434v3 = abstractC0182o.mo24088m();
    }

    public C0443x0(C0445y0 c0445y0) {
        this.f1434v3 = m23111c(c0445y0);
    }

    /* renamed from: a */
    public static C0443x0 m23115a(C0446z c0446z) {
        return m23113a(c0446z.m23090b(C0444y.f1465z3));
    }

    /* renamed from: a */
    public static C0443x0 m23116a(C0445y0 c0445y0) {
        return new C0443x0(c0445y0);
    }
}
