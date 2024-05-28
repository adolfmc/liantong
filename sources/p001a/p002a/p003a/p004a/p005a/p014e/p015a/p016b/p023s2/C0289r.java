package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.s2.r */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0289r extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0182o f628v3;

    /* renamed from: w3 */
    public C0166k f629w3;

    /* renamed from: x3 */
    public C0166k f630x3;

    public C0289r(byte[] bArr, int i) {
        this.f628v3 = new C0168k1(bArr);
        this.f629w3 = new C0166k(i);
    }

    /* renamed from: a */
    public static C0289r m23658a(Object obj) {
        if (obj instanceof C0289r) {
            return (C0289r) obj;
        }
        if (obj != null) {
            return new C0289r(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f628v3);
        c0140e.m24170a(this.f629w3);
        C0166k c0166k = this.f630x3;
        if (c0166k != null) {
            c0140e.m24170a(c0166k);
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23657i() {
        return this.f629w3.m24145n();
    }

    /* renamed from: j */
    public BigInteger m23656j() {
        C0166k c0166k = this.f630x3;
        if (c0166k != null) {
            return c0166k.m24145n();
        }
        return null;
    }

    /* renamed from: k */
    public byte[] m23655k() {
        return this.f628v3.mo24088m();
    }

    public C0289r(byte[] bArr, int i, int i2) {
        this(bArr, i);
        this.f630x3 = new C0166k(i2);
    }

    public C0289r(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        this.f628v3 = (AbstractC0182o) mo23747m.nextElement();
        this.f629w3 = (C0166k) mo23747m.nextElement();
        if (mo23747m.hasMoreElements()) {
            this.f630x3 = (C0166k) mo23747m.nextElement();
        } else {
            this.f630x3 = null;
        }
    }
}
