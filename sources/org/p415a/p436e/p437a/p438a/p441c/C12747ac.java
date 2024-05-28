package org.p415a.p436e.p437a.p438a.p441c;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p444c.AbstractC12890b;
import org.p415a.p436e.p444c.AbstractC12891c;
import org.p415a.p436e.p444c.AbstractC12895g;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.a.c.ac */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12747ac extends AbstractC12856e {

    /* renamed from: a */
    public static final BigInteger f25954a = C12745aa.f25949i;

    /* renamed from: b */
    protected int[] f25955b;

    public C12747ac() {
        this.f25955b = AbstractC12895g.m590a();
    }

    public C12747ac(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f25954a) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224R1FieldElement");
        }
        this.f25955b = C12746ab.m1268a(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C12747ac(int[] iArr) {
        this.f25955b = iArr;
    }

    /* renamed from: a */
    private static void m1253a(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        C12746ab.m1261b(iArr2, iArr, iArr2);
        C12746ab.m1256e(iArr2, iArr2);
        C12746ab.m1258d(iArr, iArr4);
        C12746ab.m1264a(iArr3, iArr4, iArr);
        C12746ab.m1261b(iArr3, iArr4, iArr3);
        C12746ab.m1269a(AbstractC12891c.m680c(7, iArr3, 2, 0), iArr3);
    }

    /* renamed from: a */
    private static void m1252a(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5) {
        AbstractC12895g.m583a(iArr, iArr4);
        int[] m590a = AbstractC12895g.m590a();
        int[] m590a2 = AbstractC12895g.m590a();
        for (int i = 0; i < 7; i++) {
            AbstractC12895g.m583a(iArr2, m590a);
            AbstractC12895g.m583a(iArr3, m590a2);
            int i2 = 1 << i;
            while (true) {
                i2--;
                if (i2 >= 0) {
                    m1253a(iArr2, iArr3, iArr4, iArr5);
                }
            }
            m1251a(iArr, m590a, m590a2, iArr2, iArr3, iArr4, iArr5);
        }
    }

    /* renamed from: a */
    private static void m1251a(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6, int[] iArr7) {
        C12746ab.m1261b(iArr5, iArr3, iArr7);
        C12746ab.m1261b(iArr7, iArr, iArr7);
        C12746ab.m1261b(iArr4, iArr2, iArr6);
        C12746ab.m1264a(iArr6, iArr7, iArr6);
        C12746ab.m1261b(iArr4, iArr3, iArr7);
        AbstractC12895g.m583a(iArr6, iArr4);
        C12746ab.m1261b(iArr5, iArr2, iArr5);
        C12746ab.m1264a(iArr5, iArr7, iArr5);
        C12746ab.m1258d(iArr5, iArr6);
        C12746ab.m1261b(iArr6, iArr, iArr6);
    }

    /* renamed from: a */
    private static boolean m1255a(int[] iArr) {
        int[] m590a = AbstractC12895g.m590a();
        int[] m590a2 = AbstractC12895g.m590a();
        AbstractC12895g.m583a(iArr, m590a);
        for (int i = 0; i < 7; i++) {
            AbstractC12895g.m583a(m590a, m590a2);
            C12746ab.m1266a(m590a, 1 << i, m590a);
            C12746ab.m1261b(m590a, m590a2, m590a);
        }
        C12746ab.m1266a(m590a, 95, m590a);
        return AbstractC12895g.m585a(m590a);
    }

    /* renamed from: a */
    private static boolean m1254a(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] m590a = AbstractC12895g.m590a();
        AbstractC12895g.m583a(iArr2, m590a);
        int[] m590a2 = AbstractC12895g.m590a();
        m590a2[0] = 1;
        int[] m590a3 = AbstractC12895g.m590a();
        m1252a(iArr, m590a, m590a2, m590a3, iArr3);
        int[] m590a4 = AbstractC12895g.m590a();
        int[] m590a5 = AbstractC12895g.m590a();
        for (int i = 1; i < 96; i++) {
            AbstractC12895g.m583a(m590a, m590a4);
            AbstractC12895g.m583a(m590a2, m590a5);
            m1253a(m590a, m590a2, m590a3, iArr3);
            if (AbstractC12895g.m580b(m590a)) {
                AbstractC12890b.m713a(C12746ab.f25951a, m590a5, iArr3);
                C12746ab.m1261b(iArr3, m590a4, iArr3);
                return true;
            }
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public BigInteger mo893a() {
        return AbstractC12895g.m577c(this.f25955b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: a */
    public AbstractC12856e mo889a(AbstractC12856e abstractC12856e) {
        int[] m590a = AbstractC12895g.m590a();
        C12746ab.m1264a(this.f25955b, ((C12747ac) abstractC12856e).f25955b, m590a);
        return new C12747ac(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public int mo886b() {
        return f25954a.bitLength();
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: b */
    public AbstractC12856e mo883b(AbstractC12856e abstractC12856e) {
        int[] m590a = AbstractC12895g.m590a();
        C12746ab.m1257d(this.f25955b, ((C12747ac) abstractC12856e).f25955b, m590a);
        return new C12747ac(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo881c() {
        int[] m590a = AbstractC12895g.m590a();
        C12746ab.m1265a(this.f25955b, m590a);
        return new C12747ac(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: c */
    public AbstractC12856e mo878c(AbstractC12856e abstractC12856e) {
        int[] m590a = AbstractC12895g.m590a();
        C12746ab.m1261b(this.f25955b, ((C12747ac) abstractC12856e).f25955b, m590a);
        return new C12747ac(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo877d() {
        int[] m590a = AbstractC12895g.m590a();
        C12746ab.m1262b(this.f25955b, m590a);
        return new C12747ac(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: d */
    public AbstractC12856e mo875d(AbstractC12856e abstractC12856e) {
        int[] m590a = AbstractC12895g.m590a();
        AbstractC12890b.m713a(C12746ab.f25951a, ((C12747ac) abstractC12856e).f25955b, m590a);
        C12746ab.m1261b(m590a, this.f25955b, m590a);
        return new C12747ac(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: e */
    public AbstractC12856e mo874e() {
        int[] m590a = AbstractC12895g.m590a();
        C12746ab.m1258d(this.f25955b, m590a);
        return new C12747ac(m590a);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12747ac) {
            return AbstractC12895g.m579b(this.f25955b, ((C12747ac) obj).f25955b);
        }
        return false;
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: f */
    public AbstractC12856e mo871f() {
        int[] m590a = AbstractC12895g.m590a();
        AbstractC12890b.m713a(C12746ab.f25951a, this.f25955b, m590a);
        return new C12747ac(m590a);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: g */
    public AbstractC12856e mo870g() {
        int[] iArr = this.f25955b;
        if (AbstractC12895g.m580b(iArr) || AbstractC12895g.m585a(iArr)) {
            return this;
        }
        int[] m590a = AbstractC12895g.m590a();
        C12746ab.m1262b(iArr, m590a);
        int[] m716a = AbstractC12890b.m716a(C12746ab.f25951a);
        int[] m590a2 = AbstractC12895g.m590a();
        if (m1255a(iArr)) {
            while (!m1254a(m590a, m716a, m590a2)) {
                C12746ab.m1265a(m716a, m716a);
            }
            C12746ab.m1258d(m590a2, m716a);
            if (AbstractC12895g.m579b(iArr, m716a)) {
                return new C12747ac(m590a2);
            }
            return null;
        }
        return null;
    }

    public int hashCode() {
        return f25954a.hashCode() ^ C12957a.m434a(this.f25955b, 0, 7);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: i */
    public boolean mo896i() {
        return AbstractC12895g.m585a(this.f25955b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: j */
    public boolean mo895j() {
        return AbstractC12895g.m580b(this.f25955b);
    }

    @Override // org.p415a.p436e.p437a.AbstractC12856e
    /* renamed from: k */
    public boolean mo894k() {
        return AbstractC12895g.m584a(this.f25955b, 0) == 1;
    }
}
