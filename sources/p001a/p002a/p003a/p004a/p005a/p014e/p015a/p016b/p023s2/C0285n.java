package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0431t;

/* renamed from: a.a.a.a.a.e.a.b.s2.n */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0285n extends AbstractC0174m {

    /* renamed from: y3 */
    public static final BigInteger f617y3 = BigInteger.valueOf(1);

    /* renamed from: v3 */
    public C0431t f618v3;

    /* renamed from: w3 */
    public byte[] f619w3;

    /* renamed from: x3 */
    public BigInteger f620x3;

    public C0285n(AbstractC0263s abstractC0263s) {
        this.f618v3 = C0431t.m23155a(abstractC0263s.mo23751a(0));
        this.f619w3 = ((AbstractC0182o) abstractC0263s.mo23751a(1)).mo24088m();
        if (abstractC0263s.mo23745o() == 3) {
            this.f620x3 = ((C0166k) abstractC0263s.mo23751a(2)).m24145n();
        } else {
            this.f620x3 = f617y3;
        }
    }

    /* renamed from: a */
    public static C0285n m23671a(Object obj) {
        if (obj instanceof C0285n) {
            return (C0285n) obj;
        }
        if (obj != null) {
            return new C0285n(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f618v3);
        c0140e.m24170a(new C0168k1(this.f619w3));
        if (!this.f620x3.equals(f617y3)) {
            c0140e.m24170a(new C0166k(this.f620x3));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public BigInteger m23670i() {
        return this.f620x3;
    }

    /* renamed from: j */
    public C0431t m23669j() {
        return this.f618v3;
    }

    /* renamed from: k */
    public byte[] m23668k() {
        return this.f619w3;
    }

    public C0285n(C0431t c0431t, byte[] bArr, int i) {
        this.f618v3 = c0431t;
        this.f619w3 = bArr;
        this.f620x3 = BigInteger.valueOf(i);
    }
}
