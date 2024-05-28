package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2.C0492o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d;

/* renamed from: a.a.a.a.a.e.a.d.a.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0655f {

    /* renamed from: g */
    public static C0492o f2006g = new C0492o();

    /* renamed from: a */
    public AbstractC0648c f2007a;

    /* renamed from: b */
    public AbstractC0651d f2008b;

    /* renamed from: c */
    public AbstractC0651d f2009c;

    /* renamed from: d */
    public boolean f2010d;

    /* renamed from: e */
    public InterfaceC0654e f2011e = null;

    /* renamed from: f */
    public InterfaceC0660i f2012f = null;

    /* renamed from: a.a.a.a.a.e.a.d.a.f$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class C0656a extends AbstractC0655f {
        public C0656a(AbstractC0648c abstractC0648c, AbstractC0651d abstractC0651d, AbstractC0651d abstractC0651d2) {
            this(abstractC0648c, abstractC0651d, abstractC0651d2, false);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: a */
        public byte[] mo22568a(boolean z) {
            if (!m22575g()) {
                int m23011a = AbstractC0655f.f2006g.m23011a(this.f2008b);
                byte[] m23010a = AbstractC0655f.f2006g.m23010a(m22578d().mo22584g(), m23011a);
                if (!z) {
                    byte[] m23010a2 = AbstractC0655f.f2006g.m23010a(m22577e().mo22584g(), m23011a);
                    byte[] bArr = new byte[m23011a + m23011a + 1];
                    bArr[0] = 4;
                    System.arraycopy(m23010a, 0, bArr, 1, m23011a);
                    System.arraycopy(m23010a2, 0, bArr, m23011a + 1, m23011a);
                    return bArr;
                }
                byte[] bArr2 = new byte[m23011a + 1];
                bArr2[0] = 2;
                if (!m22578d().mo22584g().equals(InterfaceC0647b.f1976a) && m22577e().mo22589c(m22578d().mo22590c()).mo22584g().testBit(0)) {
                    bArr2[0] = 3;
                }
                System.arraycopy(m23010a, 0, bArr2, 1, m23011a);
                return bArr2;
            }
            return new byte[1];
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: b */
        public AbstractC0655f mo22567b(AbstractC0655f abstractC0655f) {
            m22572a(this, abstractC0655f);
            return m22571b((C0656a) abstractC0655f);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: h */
        public AbstractC0655f mo22566h() {
            return new C0656a(this.f2007a, m22578d(), m22577e().mo22594a(m22578d()), this.f2010d);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: i */
        public AbstractC0655f mo22565i() {
            if (m22575g()) {
                return this;
            }
            if (this.f2008b.mo22584g().signum() == 0) {
                return this.f2007a.mo22603d();
            }
            AbstractC0651d abstractC0651d = this.f2008b;
            AbstractC0651d abstractC0651d2 = (AbstractC0651d.C0652a) abstractC0651d.mo22594a(this.f2009c.mo22591b(abstractC0651d));
            AbstractC0651d.C0652a c0652a = (AbstractC0651d.C0652a) abstractC0651d2.mo22585f().mo22594a(abstractC0651d2).mo22594a(this.f2007a.m22622a());
            AbstractC0651d mo22606a = this.f2007a.mo22606a(InterfaceC0647b.f1977b);
            return new C0656a(this.f2007a, c0652a, (AbstractC0651d.C0652a) this.f2008b.mo22585f().mo22594a(c0652a.mo22589c(abstractC0651d2.mo22594a(mo22606a))), this.f2010d);
        }

        public C0656a(AbstractC0648c abstractC0648c, AbstractC0651d abstractC0651d, AbstractC0651d abstractC0651d2, boolean z) {
            super(abstractC0648c, abstractC0651d, abstractC0651d2);
            if ((abstractC0651d != null && abstractC0651d2 == null) || (abstractC0651d == null && abstractC0651d2 != null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            if (abstractC0651d != null) {
                AbstractC0651d.C0652a.m22601a(this.f2008b, this.f2009c);
                if (abstractC0648c != null) {
                    AbstractC0651d.C0652a.m22601a(this.f2008b, this.f2007a.m22622a());
                }
            }
            this.f2010d = z;
        }

        /* renamed from: b */
        public C0656a m22571b(C0656a c0656a) {
            return c0656a.m22575g() ? this : m22573a((C0656a) c0656a.mo22566h());
        }

        /* renamed from: a */
        public static void m22572a(AbstractC0655f abstractC0655f, AbstractC0655f abstractC0655f2) {
            if (!abstractC0655f.f2007a.equals(abstractC0655f2.f2007a)) {
                throw new IllegalArgumentException("Only points on the same curve can be added or subtracted");
            }
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: a */
        public AbstractC0655f mo22569a(AbstractC0655f abstractC0655f) {
            m22572a(this, abstractC0655f);
            return m22573a((C0656a) abstractC0655f);
        }

        /* renamed from: a */
        public C0656a m22573a(C0656a c0656a) {
            if (m22575g()) {
                return c0656a;
            }
            if (c0656a.m22575g()) {
                return this;
            }
            AbstractC0651d.C0652a c0652a = (AbstractC0651d.C0652a) c0656a.m22578d();
            AbstractC0651d.C0652a c0652a2 = (AbstractC0651d.C0652a) c0656a.m22577e();
            if (this.f2008b.equals(c0652a)) {
                if (this.f2009c.equals(c0652a2)) {
                    return (C0656a) mo22565i();
                }
                return (C0656a) this.f2007a.mo22603d();
            }
            AbstractC0651d abstractC0651d = (AbstractC0651d.C0652a) this.f2009c.mo22594a(c0652a2).mo22591b(this.f2008b.mo22594a(c0652a));
            AbstractC0651d.C0652a c0652a3 = (AbstractC0651d.C0652a) abstractC0651d.mo22585f().mo22594a(abstractC0651d).mo22594a(this.f2008b).mo22594a(c0652a).mo22594a(this.f2007a.m22622a());
            return new C0656a(this.f2007a, c0652a3, (AbstractC0651d.C0652a) abstractC0651d.mo22589c(this.f2008b.mo22594a(c0652a3)).mo22594a(c0652a3).mo22594a(this.f2009c), this.f2010d);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: a */
        public synchronized void mo22570a() {
            if (this.f2011e == null) {
                if (((AbstractC0648c.C0649a) this.f2007a).m22609m()) {
                    this.f2011e = new C0666o();
                } else {
                    this.f2011e = new C0664m();
                }
            }
        }
    }

    /* renamed from: a.a.a.a.a.e.a.d.a.f$b */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class C0657b extends AbstractC0655f {
        public C0657b(AbstractC0648c abstractC0648c, AbstractC0651d abstractC0651d, AbstractC0651d abstractC0651d2) {
            this(abstractC0648c, abstractC0651d, abstractC0651d2, false);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: a */
        public byte[] mo22568a(boolean z) {
            if (!m22575g()) {
                int m23011a = AbstractC0655f.f2006g.m23011a(this.f2008b);
                if (!z) {
                    byte[] m23010a = AbstractC0655f.f2006g.m23010a(m22578d().mo22584g(), m23011a);
                    byte[] m23010a2 = AbstractC0655f.f2006g.m23010a(m22577e().mo22584g(), m23011a);
                    byte[] bArr = new byte[m23010a.length + m23010a2.length + 1];
                    bArr[0] = 4;
                    System.arraycopy(m23010a, 0, bArr, 1, m23010a.length);
                    System.arraycopy(m23010a2, 0, bArr, m23010a.length + 1, m23010a2.length);
                    return bArr;
                }
                byte b = m22577e().mo22584g().testBit(0) ? (byte) 3 : (byte) 2;
                byte[] m23010a3 = AbstractC0655f.f2006g.m23010a(m22578d().mo22584g(), m23011a);
                byte[] bArr2 = new byte[m23010a3.length + 1];
                bArr2[0] = b;
                System.arraycopy(m23010a3, 0, bArr2, 1, m23010a3.length);
                return bArr2;
            }
            return new byte[1];
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: b */
        public AbstractC0655f mo22567b(AbstractC0655f abstractC0655f) {
            return abstractC0655f.m22575g() ? this : mo22569a(abstractC0655f.mo22566h());
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: h */
        public AbstractC0655f mo22566h() {
            return new C0657b(this.f2007a, this.f2008b, this.f2009c.mo22588d(), this.f2010d);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: i */
        public AbstractC0655f mo22565i() {
            if (m22575g()) {
                return this;
            }
            if (this.f2009c.mo22584g().signum() == 0) {
                return this.f2007a.mo22603d();
            }
            AbstractC0651d mo22606a = this.f2007a.mo22606a(BigInteger.valueOf(2L));
            AbstractC0651d mo22591b = this.f2008b.mo22585f().mo22589c(this.f2007a.mo22606a(BigInteger.valueOf(3L))).mo22594a(this.f2007a.f1981a).mo22591b(this.f2009c.mo22589c(mo22606a));
            AbstractC0651d mo22587d = mo22591b.mo22585f().mo22587d(this.f2008b.mo22589c(mo22606a));
            return new C0657b(this.f2007a, mo22587d, mo22591b.mo22589c(this.f2008b.mo22587d(mo22587d)).mo22587d(this.f2009c), this.f2010d);
        }

        public C0657b(AbstractC0648c abstractC0648c, AbstractC0651d abstractC0651d, AbstractC0651d abstractC0651d2, boolean z) {
            super(abstractC0648c, abstractC0651d, abstractC0651d2);
            if ((abstractC0651d != null && abstractC0651d2 == null) || (abstractC0651d == null && abstractC0651d2 != null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            this.f2010d = z;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: a */
        public AbstractC0655f mo22569a(AbstractC0655f abstractC0655f) {
            if (m22575g()) {
                return abstractC0655f;
            }
            if (abstractC0655f.m22575g()) {
                return this;
            }
            if (this.f2008b.equals(abstractC0655f.f2008b)) {
                if (this.f2009c.equals(abstractC0655f.f2009c)) {
                    return mo22565i();
                }
                return this.f2007a.mo22603d();
            }
            AbstractC0651d mo22591b = abstractC0655f.f2009c.mo22587d(this.f2009c).mo22591b(abstractC0655f.f2008b.mo22587d(this.f2008b));
            AbstractC0651d mo22587d = mo22591b.mo22585f().mo22587d(this.f2008b).mo22587d(abstractC0655f.f2008b);
            return new C0657b(this.f2007a, mo22587d, mo22591b.mo22589c(this.f2008b.mo22587d(mo22587d)).mo22587d(this.f2009c), this.f2010d);
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f
        /* renamed from: a */
        public synchronized void mo22570a() {
            if (this.f2011e == null) {
                this.f2011e = new C0664m();
            }
        }
    }

    public AbstractC0655f(AbstractC0648c abstractC0648c, AbstractC0651d abstractC0651d, AbstractC0651d abstractC0651d2) {
        this.f2007a = abstractC0648c;
        this.f2008b = abstractC0651d;
        this.f2009c = abstractC0651d2;
    }

    /* renamed from: a */
    public abstract AbstractC0655f mo22569a(AbstractC0655f abstractC0655f);

    /* renamed from: a */
    public void m22582a(InterfaceC0660i interfaceC0660i) {
        this.f2012f = interfaceC0660i;
    }

    /* renamed from: a */
    public abstract byte[] mo22568a(boolean z);

    /* renamed from: b */
    public AbstractC0648c m22580b() {
        return this.f2007a;
    }

    /* renamed from: b */
    public abstract AbstractC0655f mo22567b(AbstractC0655f abstractC0655f);

    /* renamed from: c */
    public byte[] m22579c() {
        return mo22568a(this.f2010d);
    }

    /* renamed from: d */
    public AbstractC0651d m22578d() {
        return this.f2008b;
    }

    /* renamed from: e */
    public AbstractC0651d m22577e() {
        return this.f2009c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractC0655f) {
            AbstractC0655f abstractC0655f = (AbstractC0655f) obj;
            if (m22575g()) {
                return abstractC0655f.m22575g();
            }
            return this.f2008b.equals(abstractC0655f.f2008b) && this.f2009c.equals(abstractC0655f.f2009c);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m22576f() {
        return this.f2010d;
    }

    /* renamed from: g */
    public boolean m22575g() {
        return this.f2008b == null && this.f2009c == null;
    }

    /* renamed from: h */
    public abstract AbstractC0655f mo22566h();

    public int hashCode() {
        if (m22575g()) {
            return 0;
        }
        return this.f2008b.hashCode() ^ this.f2009c.hashCode();
    }

    /* renamed from: i */
    public abstract AbstractC0655f mo22565i();

    /* renamed from: a */
    public synchronized void mo22570a() {
        if (this.f2011e == null) {
            this.f2011e = new C0658g();
        }
    }

    /* renamed from: a */
    public AbstractC0655f m22581a(BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            if (m22575g()) {
                return this;
            }
            if (bigInteger.signum() == 0) {
                return this.f2007a.mo22603d();
            }
            mo22570a();
            return this.f2011e.mo22506a(this, bigInteger, this.f2012f);
        }
        throw new IllegalArgumentException("The multiplicator cannot be negative");
    }
}
