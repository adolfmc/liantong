package org.p415a.p436e.p437a;

import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Random;
import org.p415a.p436e.p437a.AbstractC12856e;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p436e.p437a.p442b.InterfaceC12845a;
import org.p415a.p436e.p437a.p442b.InterfaceC12846b;
import org.p415a.p436e.p443b.AbstractC12883c;
import org.p415a.p436e.p443b.InterfaceC12882b;
import org.p415a.p448g.C12966b;
import org.p415a.p448g.C12969c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12850d {

    /* renamed from: a */
    protected InterfaceC12882b f26075a;

    /* renamed from: b */
    protected AbstractC12856e f26076b;

    /* renamed from: c */
    protected AbstractC12856e f26077c;

    /* renamed from: d */
    protected BigInteger f26078d;

    /* renamed from: e */
    protected BigInteger f26079e;

    /* renamed from: f */
    protected int f26080f = 0;

    /* renamed from: g */
    protected InterfaceC12845a f26081g = null;

    /* renamed from: h */
    protected InterfaceC12859f f26082h = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractC12851a extends AbstractC12850d {

        /* renamed from: i */
        private BigInteger[] f26083i;

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractC12851a(int i, int i2, int i3, int i4) {
            super(m917a(i, i2, i3, i4));
            this.f26083i = null;
        }

        /* renamed from: a */
        private AbstractC12856e m916a(AbstractC12856e abstractC12856e) {
            AbstractC12856e abstractC12856e2;
            if (abstractC12856e.mo895j()) {
                return abstractC12856e;
            }
            AbstractC12856e a = mo906a(InterfaceC12849c.f26069c);
            int a2 = mo908a();
            Random random = new Random();
            do {
                AbstractC12856e a3 = mo906a(new BigInteger(a2, random));
                AbstractC12856e abstractC12856e3 = abstractC12856e;
                abstractC12856e2 = a;
                for (int i = 1; i < a2; i++) {
                    AbstractC12856e mo874e = abstractC12856e3.mo874e();
                    abstractC12856e2 = abstractC12856e2.mo874e().mo889a(mo874e.mo878c(a3));
                    abstractC12856e3 = mo874e.mo889a(abstractC12856e);
                }
                if (!abstractC12856e3.mo895j()) {
                    return null;
                }
            } while (abstractC12856e2.mo874e().mo889a(abstractC12856e2).mo895j());
            return abstractC12856e2;
        }

        /* renamed from: a */
        private static InterfaceC12882b m917a(int i, int i2, int i3, int i4) {
            if (i2 != 0) {
                if (i3 == 0) {
                    if (i4 == 0) {
                        return AbstractC12883c.m728a(new int[]{0, i2, i});
                    }
                    throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
                } else if (i3 > i2) {
                    if (i4 > i3) {
                        return AbstractC12883c.m728a(new int[]{0, i2, i3, i4, i});
                    }
                    throw new IllegalArgumentException("k3 must be > k2");
                } else {
                    throw new IllegalArgumentException("k2 must be > k1");
                }
            }
            throw new IllegalArgumentException("k1 must be > 0");
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        protected AbstractC12860g mo912a(int i, BigInteger bigInteger) {
            AbstractC12856e abstractC12856e;
            AbstractC12856e a = mo906a(bigInteger);
            if (a.mo895j()) {
                abstractC12856e = m922h().mo870g();
            } else {
                AbstractC12856e m916a = m916a(a.mo874e().mo871f().mo878c(m922h()).mo889a(m923g()).mo889a(a));
                if (m916a != null) {
                    if (m916a.mo894k() != (i == 1)) {
                        m916a = m916a.mo881c();
                    }
                    switch (m919k()) {
                        case 5:
                        case 6:
                            abstractC12856e = m916a.mo889a(a);
                            break;
                        default:
                            abstractC12856e = m916a.mo878c(a);
                            break;
                    }
                } else {
                    abstractC12856e = null;
                }
            }
            if (abstractC12856e != null) {
                return mo905a(a, abstractC12856e, true);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: b */
        public AbstractC12860g mo915b(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
            AbstractC12856e a = mo906a(bigInteger);
            AbstractC12856e a2 = mo906a(bigInteger2);
            switch (m919k()) {
                case 5:
                case 6:
                    if (!a.mo895j()) {
                        a2 = a2.mo875d(a).mo889a(a);
                        break;
                    } else if (!a2.mo874e().equals(m922h())) {
                        throw new IllegalArgumentException();
                    }
                    break;
            }
            return mo905a(a, a2, z);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: m */
        public synchronized BigInteger[] m914m() {
            if (this.f26083i == null) {
                this.f26083i = C12874q.m759a(this);
            }
            return this.f26083i;
        }

        /* renamed from: n */
        public boolean mo913n() {
            return this.f26078d != null && this.f26079e != null && this.f26077c.mo896i() && (this.f26076b.mo895j() || this.f26076b.mo896i());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.d$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractC12852b extends AbstractC12850d {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractC12852b(BigInteger bigInteger) {
            super(AbstractC12883c.m729a(bigInteger));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        protected AbstractC12860g mo912a(int i, BigInteger bigInteger) {
            AbstractC12856e a = mo906a(bigInteger);
            AbstractC12856e mo870g = a.mo874e().mo889a(this.f26076b).mo878c(a).mo889a(this.f26077c).mo870g();
            if (mo870g != null) {
                if (mo870g.mo894k() != (i == 1)) {
                    mo870g = mo870g.mo877d();
                }
                return mo905a(a, mo870g, true);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.d$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C12853c {

        /* renamed from: a */
        protected int f26084a;

        /* renamed from: b */
        protected InterfaceC12845a f26085b;

        /* renamed from: c */
        protected InterfaceC12859f f26086c;

        C12853c(int i, InterfaceC12845a interfaceC12845a, InterfaceC12859f interfaceC12859f) {
            this.f26084a = i;
            this.f26085b = interfaceC12845a;
            this.f26086c = interfaceC12859f;
        }

        /* renamed from: a */
        public C12853c m910a(InterfaceC12845a interfaceC12845a) {
            this.f26085b = interfaceC12845a;
            return this;
        }

        /* renamed from: a */
        public AbstractC12850d m911a() {
            if (AbstractC12850d.this.mo907a(this.f26084a)) {
                AbstractC12850d mo902c = AbstractC12850d.this.mo902c();
                if (mo902c != AbstractC12850d.this) {
                    synchronized (mo902c) {
                        mo902c.f26080f = this.f26084a;
                        mo902c.f26081g = this.f26085b;
                        mo902c.f26082h = this.f26086c;
                    }
                    return mo902c;
                }
                throw new IllegalStateException("implementation returned current curve");
            }
            throw new IllegalStateException("unsupported coordinate system");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.d$d */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12854d extends AbstractC12851a {

        /* renamed from: i */
        private int f26088i;

        /* renamed from: j */
        private int f26089j;

        /* renamed from: k */
        private int f26090k;

        /* renamed from: l */
        private int f26091l;

        /* renamed from: m */
        private AbstractC12860g.C12863c f26092m;

        public C12854d(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(i, i2, i3, i4);
            this.f26088i = i;
            this.f26089j = i2;
            this.f26090k = i3;
            this.f26091l = i4;
            this.f26078d = bigInteger3;
            this.f26079e = bigInteger4;
            this.f26092m = new AbstractC12860g.C12863c(this, null, null);
            this.f26076b = mo906a(bigInteger);
            this.f26077c = mo906a(bigInteger2);
            this.f26080f = 6;
        }

        protected C12854d(int i, int i2, int i3, int i4, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, BigInteger bigInteger, BigInteger bigInteger2) {
            super(i, i2, i3, i4);
            this.f26088i = i;
            this.f26089j = i2;
            this.f26090k = i3;
            this.f26091l = i4;
            this.f26078d = bigInteger;
            this.f26079e = bigInteger2;
            this.f26092m = new AbstractC12860g.C12863c(this, null, null);
            this.f26076b = abstractC12856e;
            this.f26077c = abstractC12856e2;
            this.f26080f = 6;
        }

        public C12854d(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this(i, i2, 0, 0, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        public int mo908a() {
            return this.f26088i;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        public AbstractC12856e mo906a(BigInteger bigInteger) {
            return new AbstractC12856e.C12857a(this.f26088i, this.f26089j, this.f26090k, this.f26091l, bigInteger);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        protected AbstractC12860g mo905a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
            return new AbstractC12860g.C12863c(this, abstractC12856e, abstractC12856e2, z);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        protected AbstractC12860g mo904a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
            return new AbstractC12860g.C12863c(this, abstractC12856e, abstractC12856e2, abstractC12856eArr, z);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        public boolean mo907a(int i) {
            if (i != 6) {
                switch (i) {
                    case 0:
                    case 1:
                        return true;
                    default:
                        return false;
                }
            }
            return true;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: c */
        protected AbstractC12850d mo902c() {
            return new C12854d(this.f26088i, this.f26089j, this.f26090k, this.f26091l, this.f26076b, this.f26077c, this.f26078d, this.f26079e);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: d */
        protected InterfaceC12859f mo909d() {
            return mo913n() ? new C12878u() : super.mo909d();
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: e */
        public AbstractC12860g mo901e() {
            return this.f26092m;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.d$e */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12855e extends AbstractC12852b {

        /* renamed from: i */
        BigInteger f26093i;

        /* renamed from: j */
        BigInteger f26094j;

        /* renamed from: k */
        AbstractC12860g.C12864d f26095k;

        public C12855e(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
            super(bigInteger);
            this.f26093i = bigInteger;
            this.f26094j = AbstractC12856e.C12858b.m892a(bigInteger);
            this.f26095k = new AbstractC12860g.C12864d(this, null, null);
            this.f26076b = mo906a(bigInteger2);
            this.f26077c = mo906a(bigInteger3);
            this.f26078d = bigInteger4;
            this.f26079e = bigInteger5;
            this.f26080f = 4;
        }

        protected C12855e(BigInteger bigInteger, BigInteger bigInteger2, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(bigInteger);
            this.f26093i = bigInteger;
            this.f26094j = bigInteger2;
            this.f26095k = new AbstractC12860g.C12864d(this, null, null);
            this.f26076b = abstractC12856e;
            this.f26077c = abstractC12856e2;
            this.f26078d = bigInteger3;
            this.f26079e = bigInteger4;
            this.f26080f = 4;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        public int mo908a() {
            return this.f26093i.bitLength();
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        public AbstractC12856e mo906a(BigInteger bigInteger) {
            return new AbstractC12856e.C12858b(this.f26093i, this.f26094j, bigInteger);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        protected AbstractC12860g mo905a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
            return new AbstractC12860g.C12864d(this, abstractC12856e, abstractC12856e2, z);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        protected AbstractC12860g mo904a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
            return new AbstractC12860g.C12864d(this, abstractC12856e, abstractC12856e2, abstractC12856eArr, z);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        public AbstractC12860g mo903a(AbstractC12860g abstractC12860g) {
            if (this != abstractC12860g.m861c() && m919k() == 2 && !abstractC12860g.m851n()) {
                switch (abstractC12860g.m861c().m919k()) {
                    case 2:
                    case 3:
                    case 4:
                        return new AbstractC12860g.C12864d(this, mo906a(abstractC12860g.f26105c.mo893a()), mo906a(abstractC12860g.f26106d.mo893a()), new AbstractC12856e[]{mo906a(abstractC12860g.f26107e[0].mo893a())}, abstractC12860g.f26108f);
                }
            }
            return super.mo903a(abstractC12860g);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: a */
        public boolean mo907a(int i) {
            if (i != 4) {
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                        return true;
                    default:
                        return false;
                }
            }
            return true;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: c */
        protected AbstractC12850d mo902c() {
            return new C12855e(this.f26093i, this.f26094j, this.f26076b, this.f26077c, this.f26078d, this.f26079e);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12850d
        /* renamed from: e */
        public AbstractC12860g mo901e() {
            return this.f26095k;
        }
    }

    protected AbstractC12850d(InterfaceC12882b interfaceC12882b) {
        this.f26075a = interfaceC12882b;
    }

    /* renamed from: a */
    public abstract int mo908a();

    /* renamed from: a */
    public abstract AbstractC12856e mo906a(BigInteger bigInteger);

    /* renamed from: a */
    protected abstract AbstractC12860g mo912a(int i, BigInteger bigInteger);

    /* renamed from: a */
    public AbstractC12860g m936a(BigInteger bigInteger, BigInteger bigInteger2) {
        AbstractC12860g m926b = m926b(bigInteger, bigInteger2);
        if (m926b.m850o()) {
            return m926b;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }

    /* renamed from: a */
    public AbstractC12860g m935a(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
        AbstractC12860g mo915b = mo915b(bigInteger, bigInteger2, z);
        if (mo915b.m850o()) {
            return mo915b;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public abstract AbstractC12860g mo905a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public abstract AbstractC12860g mo904a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z);

    /* renamed from: a */
    public AbstractC12860g mo903a(AbstractC12860g abstractC12860g) {
        if (this == abstractC12860g.m861c()) {
            return abstractC12860g;
        }
        if (abstractC12860g.m851n()) {
            return mo901e();
        }
        AbstractC12860g m852m = abstractC12860g.m852m();
        return m935a(m852m.m858f().mo893a(), m852m.mo844g().mo893a(), m852m.f26108f);
    }

    /* renamed from: a */
    public AbstractC12860g m931a(byte[] bArr) {
        AbstractC12860g mo901e;
        int mo908a = (mo908a() + 7) / 8;
        byte b = bArr[0];
        switch (b) {
            case 0:
                if (bArr.length == 1) {
                    mo901e = mo901e();
                    break;
                } else {
                    throw new IllegalArgumentException("Incorrect length for infinity encoding");
                }
            case 1:
            case 5:
            default:
                throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(b, 16));
            case 2:
            case 3:
                if (bArr.length != mo908a + 1) {
                    throw new IllegalArgumentException("Incorrect length for compressed encoding");
                }
                mo901e = mo912a(b & 1, C12966b.m406a(bArr, 1, mo908a));
                if (!mo901e.m868a()) {
                    throw new IllegalArgumentException("Invalid point");
                }
                break;
            case 4:
                if (bArr.length == (mo908a * 2) + 1) {
                    mo901e = m936a(C12966b.m406a(bArr, 1, mo908a), C12966b.m406a(bArr, mo908a + 1, mo908a));
                    break;
                } else {
                    throw new IllegalArgumentException("Incorrect length for uncompressed encoding");
                }
            case 6:
            case 7:
                if (bArr.length == (mo908a * 2) + 1) {
                    BigInteger m406a = C12966b.m406a(bArr, 1, mo908a);
                    BigInteger m406a2 = C12966b.m406a(bArr, mo908a + 1, mo908a);
                    if (m406a2.testBit(0) == (b == 7)) {
                        mo901e = m936a(m406a, m406a2);
                        break;
                    } else {
                        throw new IllegalArgumentException("Inconsistent Y coordinate in hybrid encoding");
                    }
                } else {
                    throw new IllegalArgumentException("Incorrect length for hybrid encoding");
                }
        }
        if (b == 0 || !mo901e.m851n()) {
            return mo901e;
        }
        throw new IllegalArgumentException("Invalid infinity encoding");
    }

    /* renamed from: a */
    public InterfaceC12871n m933a(AbstractC12860g abstractC12860g, String str) {
        InterfaceC12871n interfaceC12871n;
        m925b(abstractC12860g);
        synchronized (abstractC12860g) {
            Hashtable hashtable = abstractC12860g.f26109g;
            interfaceC12871n = hashtable == null ? null : (InterfaceC12871n) hashtable.get(str);
        }
        return interfaceC12871n;
    }

    /* renamed from: a */
    public void m932a(AbstractC12860g abstractC12860g, String str, InterfaceC12871n interfaceC12871n) {
        m925b(abstractC12860g);
        synchronized (abstractC12860g) {
            Hashtable hashtable = abstractC12860g.f26109g;
            if (hashtable == null) {
                hashtable = new Hashtable(4);
                abstractC12860g.f26109g = hashtable;
            }
            hashtable.put(str, interfaceC12871n);
        }
    }

    /* renamed from: a */
    public void m930a(AbstractC12860g[] abstractC12860gArr) {
        m928a(abstractC12860gArr, 0, abstractC12860gArr.length, (AbstractC12856e) null);
    }

    /* renamed from: a */
    protected void m929a(AbstractC12860g[] abstractC12860gArr, int i, int i2) {
        if (abstractC12860gArr == null) {
            throw new IllegalArgumentException("'points' cannot be null");
        }
        if (i < 0 || i2 < 0 || i > abstractC12860gArr.length - i2) {
            throw new IllegalArgumentException("invalid range specified for 'points'");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            AbstractC12860g abstractC12860g = abstractC12860gArr[i + i3];
            if (abstractC12860g != null && this != abstractC12860g.m861c()) {
                throw new IllegalArgumentException("'points' entries must be null or on this curve");
            }
        }
    }

    /* renamed from: a */
    public void m928a(AbstractC12860g[] abstractC12860gArr, int i, int i2, AbstractC12856e abstractC12856e) {
        m929a(abstractC12860gArr, i, i2);
        int m919k = m919k();
        if (m919k == 0 || m919k == 5) {
            if (abstractC12856e != null) {
                throw new IllegalArgumentException("'iso' not valid for affine coordinates");
            }
            return;
        }
        AbstractC12856e[] abstractC12856eArr = new AbstractC12856e[i2];
        int[] iArr = new int[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i + i4;
            AbstractC12860g abstractC12860g = abstractC12860gArr[i5];
            if (abstractC12860g != null && (abstractC12856e != null || !abstractC12860g.m853l())) {
                abstractC12856eArr[i3] = abstractC12860g.mo842a(0);
                iArr[i3] = i5;
                i3++;
            }
        }
        if (i3 == 0) {
            return;
        }
        C12844b.m953a(abstractC12856eArr, 0, i3, abstractC12856e);
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = iArr[i6];
            abstractC12860gArr[i7] = abstractC12860gArr[i7].m865a(abstractC12856eArr[i6]);
        }
    }

    /* renamed from: a */
    public boolean mo907a(int i) {
        return i == 0;
    }

    /* renamed from: a */
    public boolean m934a(AbstractC12850d abstractC12850d) {
        return this == abstractC12850d || (abstractC12850d != null && m924f().equals(abstractC12850d.m924f()) && m923g().mo893a().equals(abstractC12850d.m923g().mo893a()) && m922h().mo893a().equals(abstractC12850d.m922h().mo893a()));
    }

    /* renamed from: b */
    public synchronized C12853c m927b() {
        return new C12853c(this.f26080f, this.f26081g, this.f26082h);
    }

    /* renamed from: b */
    public AbstractC12860g m926b(BigInteger bigInteger, BigInteger bigInteger2) {
        return mo915b(bigInteger, bigInteger2, false);
    }

    /* renamed from: b */
    public AbstractC12860g mo915b(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
        return mo905a(mo906a(bigInteger), mo906a(bigInteger2), z);
    }

    /* renamed from: b */
    protected void m925b(AbstractC12860g abstractC12860g) {
        if (abstractC12860g == null || this != abstractC12860g.m861c()) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
    }

    /* renamed from: c */
    protected abstract AbstractC12850d mo902c();

    /* renamed from: d */
    protected InterfaceC12859f mo909d() {
        InterfaceC12845a interfaceC12845a = this.f26081g;
        return interfaceC12845a instanceof InterfaceC12846b ? new C12869l(this, (InterfaceC12846b) interfaceC12845a) : new C12875r();
    }

    /* renamed from: e */
    public abstract AbstractC12860g mo901e();

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof AbstractC12850d) && m934a((AbstractC12850d) obj));
    }

    /* renamed from: f */
    public InterfaceC12882b m924f() {
        return this.f26075a;
    }

    /* renamed from: g */
    public AbstractC12856e m923g() {
        return this.f26076b;
    }

    /* renamed from: h */
    public AbstractC12856e m922h() {
        return this.f26077c;
    }

    public int hashCode() {
        return (m924f().hashCode() ^ C12969c.m401a(m923g().mo893a().hashCode(), 8)) ^ C12969c.m401a(m922h().mo893a().hashCode(), 16);
    }

    /* renamed from: i */
    public BigInteger m921i() {
        return this.f26078d;
    }

    /* renamed from: j */
    public BigInteger m920j() {
        return this.f26079e;
    }

    /* renamed from: k */
    public int m919k() {
        return this.f26080f;
    }

    /* renamed from: l */
    public synchronized InterfaceC12859f m918l() {
        if (this.f26082h == null) {
            this.f26082h = mo909d();
        }
        return this.f26082h;
    }
}
