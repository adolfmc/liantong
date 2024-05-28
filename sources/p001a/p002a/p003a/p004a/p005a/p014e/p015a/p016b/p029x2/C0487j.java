package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.e.a.b.x2.j */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0487j extends AbstractC0174m implements InterfaceC0493p {

    /* renamed from: B3 */
    public static final BigInteger f1529B3 = BigInteger.valueOf(1);

    /* renamed from: A3 */
    public byte[] f1530A3;

    /* renamed from: v3 */
    public C0491n f1531v3;

    /* renamed from: w3 */
    public AbstractC0648c f1532w3;

    /* renamed from: x3 */
    public AbstractC0655f f1533x3;

    /* renamed from: y3 */
    public BigInteger f1534y3;

    /* renamed from: z3 */
    public BigInteger f1535z3;

    public C0487j(AbstractC0263s abstractC0263s) {
        if ((abstractC0263s.mo23751a(0) instanceof C0166k) && ((C0166k) abstractC0263s.mo23751a(0)).m24145n().equals(f1529B3)) {
            C0486i c0486i = new C0486i(new C0491n((AbstractC0263s) abstractC0263s.mo23751a(1)), (AbstractC0263s) abstractC0263s.mo23751a(2));
            AbstractC0648c m23028i = c0486i.m23028i();
            this.f1532w3 = m23028i;
            this.f1533x3 = new C0489l(m23028i, (AbstractC0182o) abstractC0263s.mo23751a(3)).m23017i();
            this.f1534y3 = ((C0166k) abstractC0263s.mo23751a(4)).m24145n();
            this.f1530A3 = c0486i.m23027j();
            if (abstractC0263s.mo23745o() == 6) {
                this.f1535z3 = ((C0166k) abstractC0263s.mo23751a(5)).m24145n();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("bad version in X9ECParameters");
    }

    /* renamed from: a */
    public static C0487j m23025a(Object obj) {
        if (obj instanceof C0487j) {
            return (C0487j) obj;
        }
        if (obj != null) {
            return new C0487j(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(new C0166k(1L));
        c0140e.m24170a(this.f1531v3);
        c0140e.m24170a(new C0486i(this.f1532w3, this.f1530A3));
        c0140e.m24170a(new C0489l(this.f1533x3));
        c0140e.m24170a(new C0166k(this.f1534y3));
        BigInteger bigInteger = this.f1535z3;
        if (bigInteger != null) {
            c0140e.m24170a(new C0166k(bigInteger));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0648c m23024i() {
        return this.f1532w3;
    }

    /* renamed from: j */
    public AbstractC0655f m23023j() {
        return this.f1533x3;
    }

    /* renamed from: k */
    public BigInteger m23022k() {
        BigInteger bigInteger = this.f1535z3;
        return bigInteger == null ? f1529B3 : bigInteger;
    }

    /* renamed from: l */
    public BigInteger m23021l() {
        return this.f1534y3;
    }

    /* renamed from: m */
    public byte[] m23020m() {
        return this.f1530A3;
    }

    public C0487j(AbstractC0648c abstractC0648c, AbstractC0655f abstractC0655f, BigInteger bigInteger) {
        this(abstractC0648c, abstractC0655f, bigInteger, f1529B3, null);
    }

    public C0487j(AbstractC0648c abstractC0648c, AbstractC0655f abstractC0655f, BigInteger bigInteger, BigInteger bigInteger2) {
        this(abstractC0648c, abstractC0655f, bigInteger, bigInteger2, null);
    }

    public C0487j(AbstractC0648c abstractC0648c, AbstractC0655f abstractC0655f, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f1532w3 = abstractC0648c;
        this.f1533x3 = abstractC0655f;
        this.f1534y3 = bigInteger;
        this.f1535z3 = bigInteger2;
        this.f1530A3 = bArr;
        if (abstractC0648c instanceof AbstractC0648c.C0650b) {
            this.f1531v3 = new C0491n(((AbstractC0648c.C0650b) abstractC0648c).m22602e());
        } else if (abstractC0648c instanceof AbstractC0648c.C0649a) {
            AbstractC0648c.C0649a c0649a = (AbstractC0648c.C0649a) abstractC0648c;
            this.f1531v3 = new C0491n(c0649a.m22613i(), c0649a.m22616f(), c0649a.m22615g(), c0649a.m22614h());
        }
    }
}
