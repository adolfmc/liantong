package org.p415a.p436e.p437a;

import java.math.BigInteger;
import java.util.Hashtable;
import org.p415a.p436e.p437a.AbstractC12856e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.a.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12860g {

    /* renamed from: a */
    protected static AbstractC12856e[] f26103a = new AbstractC12856e[0];

    /* renamed from: b */
    protected AbstractC12850d f26104b;

    /* renamed from: c */
    protected AbstractC12856e f26105c;

    /* renamed from: d */
    protected AbstractC12856e f26106d;

    /* renamed from: e */
    protected AbstractC12856e[] f26107e;

    /* renamed from: f */
    protected boolean f26108f;

    /* renamed from: g */
    protected Hashtable f26109g;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.g$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractC12861a extends AbstractC12860g {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractC12861a(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
            super(abstractC12850d, abstractC12856e, abstractC12856e2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractC12861a(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr) {
            super(abstractC12850d, abstractC12856e, abstractC12856e2, abstractC12856eArr);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: b */
        public AbstractC12860g mo849b(AbstractC12856e abstractC12856e) {
            if (m851n()) {
                return this;
            }
            switch (m860d()) {
                case 5:
                    AbstractC12856e h = m857h();
                    return m861c().mo904a(h, m856i().mo889a(h).mo875d(abstractC12856e).mo889a(h.mo878c(abstractC12856e)), m855j(), this.f26108f);
                case 6:
                    AbstractC12856e h2 = m857h();
                    AbstractC12856e i = m856i();
                    AbstractC12856e abstractC12856e2 = m855j()[0];
                    AbstractC12856e mo878c = h2.mo878c(abstractC12856e.mo874e());
                    return m861c().mo904a(mo878c, i.mo889a(h2).mo889a(mo878c), new AbstractC12856e[]{abstractC12856e2.mo878c(abstractC12856e)}, this.f26108f);
                default:
                    return super.mo849b(abstractC12856e);
            }
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: b */
        protected boolean mo846b() {
            AbstractC12856e mo882b;
            AbstractC12856e mo888a;
            AbstractC12850d c = m861c();
            AbstractC12856e abstractC12856e = this.f26105c;
            AbstractC12856e m923g = c.m923g();
            AbstractC12856e m922h = c.m922h();
            int m919k = c.m919k();
            if (m919k != 6) {
                AbstractC12856e abstractC12856e2 = this.f26106d;
                AbstractC12856e mo878c = abstractC12856e2.mo889a(abstractC12856e).mo878c(abstractC12856e2);
                switch (m919k) {
                    case 0:
                        break;
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                    case 1:
                        AbstractC12856e abstractC12856e3 = this.f26107e[0];
                        if (!abstractC12856e3.mo896i()) {
                            AbstractC12856e mo878c2 = abstractC12856e3.mo878c(abstractC12856e3.mo874e());
                            mo878c = mo878c.mo878c(abstractC12856e3);
                            m923g = m923g.mo878c(abstractC12856e3);
                            m922h = m922h.mo878c(mo878c2);
                            break;
                        }
                        break;
                }
                return mo878c.equals(abstractC12856e.mo889a(m923g).mo878c(abstractC12856e.mo874e()).mo889a(m922h));
            }
            AbstractC12856e abstractC12856e4 = this.f26107e[0];
            boolean mo896i = abstractC12856e4.mo896i();
            if (abstractC12856e.mo895j()) {
                AbstractC12856e mo874e = this.f26106d.mo874e();
                if (!mo896i) {
                    m922h = m922h.mo878c(abstractC12856e4.mo874e());
                }
                return mo874e.equals(m922h);
            }
            AbstractC12856e abstractC12856e5 = this.f26106d;
            AbstractC12856e mo874e2 = abstractC12856e.mo874e();
            if (mo896i) {
                mo882b = abstractC12856e5.mo874e().mo889a(abstractC12856e5).mo889a(m923g);
                mo888a = mo874e2.mo874e().mo889a(m922h);
            } else {
                AbstractC12856e mo874e3 = abstractC12856e4.mo874e();
                AbstractC12856e mo874e4 = mo874e3.mo874e();
                mo882b = abstractC12856e5.mo889a(abstractC12856e4).mo882b(abstractC12856e5, m923g, mo874e3);
                mo888a = mo874e2.mo888a(m922h, mo874e4);
            }
            return mo882b.mo878c(mo874e2).equals(mo888a);
        }

        /* renamed from: c */
        public AbstractC12861a m848c(int i) {
            AbstractC12860g mo905a;
            if (m851n()) {
                return this;
            }
            AbstractC12850d c = m861c();
            int m919k = c.m919k();
            AbstractC12856e abstractC12856e = this.f26105c;
            switch (m919k) {
                case 0:
                case 5:
                    mo905a = c.mo905a(abstractC12856e.mo899a(i), this.f26106d.mo899a(i), this.f26108f);
                    break;
                case 1:
                case 6:
                    mo905a = c.mo904a(abstractC12856e.mo899a(i), this.f26106d.mo899a(i), new AbstractC12856e[]{this.f26107e[0].mo899a(i)}, this.f26108f);
                    break;
                case 2:
                case 3:
                case 4:
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
            return (AbstractC12861a) mo905a;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: c */
        public AbstractC12860g mo847c(AbstractC12856e abstractC12856e) {
            if (m851n()) {
                return this;
            }
            switch (m860d()) {
                case 5:
                case 6:
                    AbstractC12856e h = m857h();
                    return m861c().mo904a(h, m856i().mo889a(h).mo878c(abstractC12856e).mo889a(h), m855j(), this.f26108f);
                default:
                    return super.mo847c(abstractC12856e);
            }
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: c */
        public AbstractC12860g mo845c(AbstractC12860g abstractC12860g) {
            return abstractC12860g.m851n() ? this : mo839b(abstractC12860g.mo832q());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.g$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractC12862b extends AbstractC12860g {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractC12862b(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
            super(abstractC12850d, abstractC12856e, abstractC12856e2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractC12862b(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr) {
            super(abstractC12850d, abstractC12856e, abstractC12856e2, abstractC12856eArr);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: b */
        protected boolean mo846b() {
            AbstractC12856e abstractC12856e = this.f26105c;
            AbstractC12856e abstractC12856e2 = this.f26106d;
            AbstractC12856e m923g = this.f26104b.m923g();
            AbstractC12856e m922h = this.f26104b.m922h();
            AbstractC12856e mo874e = abstractC12856e2.mo874e();
            switch (m860d()) {
                case 0:
                    break;
                case 1:
                    AbstractC12856e abstractC12856e3 = this.f26107e[0];
                    if (!abstractC12856e3.mo896i()) {
                        AbstractC12856e mo874e2 = abstractC12856e3.mo874e();
                        AbstractC12856e mo878c = abstractC12856e3.mo878c(mo874e2);
                        mo874e = mo874e.mo878c(abstractC12856e3);
                        m923g = m923g.mo878c(mo874e2);
                        m922h = m922h.mo878c(mo878c);
                        break;
                    }
                    break;
                case 2:
                case 3:
                case 4:
                    AbstractC12856e abstractC12856e4 = this.f26107e[0];
                    if (!abstractC12856e4.mo896i()) {
                        AbstractC12856e mo874e3 = abstractC12856e4.mo874e();
                        AbstractC12856e mo874e4 = mo874e3.mo874e();
                        AbstractC12856e mo878c2 = mo874e3.mo878c(mo874e4);
                        m923g = m923g.mo878c(mo874e4);
                        m922h = m922h.mo878c(mo878c2);
                        break;
                    }
                    break;
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
            return mo874e.equals(abstractC12856e.mo874e().mo889a(m923g).mo878c(abstractC12856e).mo889a(m922h));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: c */
        public AbstractC12860g mo845c(AbstractC12860g abstractC12860g) {
            return abstractC12860g.m851n() ? this : mo839b(abstractC12860g.mo832q());
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: p */
        protected boolean mo843p() {
            return m859e().mo894k();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.g$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12863c extends AbstractC12861a {
        public C12863c(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
            this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
        }

        public C12863c(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
            super(abstractC12850d, abstractC12856e, abstractC12856e2);
            if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            if (abstractC12856e != null) {
                AbstractC12856e.C12857a.m898b(this.f26105c, this.f26106d);
                if (abstractC12850d != null) {
                    AbstractC12856e.C12857a.m898b(this.f26105c, this.f26104b.m923g());
                }
            }
            this.f26108f = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C12863c(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
            super(abstractC12850d, abstractC12856e, abstractC12856e2, abstractC12856eArr);
            this.f26108f = z;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: b */
        public AbstractC12860g mo839b(AbstractC12860g abstractC12860g) {
            AbstractC12856e abstractC12856e;
            AbstractC12856e abstractC12856e2;
            AbstractC12856e abstractC12856e3;
            AbstractC12856e mo878c;
            AbstractC12856e abstractC12856e4;
            AbstractC12856e abstractC12856e5;
            if (m851n()) {
                return abstractC12860g;
            }
            if (abstractC12860g.m851n()) {
                return this;
            }
            AbstractC12850d c = m861c();
            int m919k = c.m919k();
            AbstractC12856e abstractC12856e6 = this.f26105c;
            AbstractC12856e abstractC12856e7 = abstractC12860g.f26105c;
            if (m919k != 6) {
                switch (m919k) {
                    case 0:
                        AbstractC12856e abstractC12856e8 = this.f26106d;
                        AbstractC12856e abstractC12856e9 = abstractC12860g.f26106d;
                        AbstractC12856e mo889a = abstractC12856e6.mo889a(abstractC12856e7);
                        AbstractC12856e mo889a2 = abstractC12856e8.mo889a(abstractC12856e9);
                        if (mo889a.mo895j()) {
                            return mo889a2.mo895j() ? mo831r() : c.mo901e();
                        }
                        AbstractC12856e mo875d = mo889a2.mo875d(mo889a);
                        AbstractC12856e mo889a3 = mo875d.mo874e().mo889a(mo875d).mo889a(mo889a).mo889a(c.m923g());
                        return new C12863c(c, mo889a3, mo875d.mo878c(abstractC12856e6.mo889a(mo889a3)).mo889a(mo889a3).mo889a(abstractC12856e8), this.f26108f);
                    case 1:
                        AbstractC12856e abstractC12856e10 = this.f26106d;
                        AbstractC12856e abstractC12856e11 = this.f26107e[0];
                        AbstractC12856e abstractC12856e12 = abstractC12860g.f26106d;
                        AbstractC12856e abstractC12856e13 = abstractC12860g.f26107e[0];
                        boolean mo896i = abstractC12856e13.mo896i();
                        AbstractC12856e mo889a4 = abstractC12856e11.mo878c(abstractC12856e12).mo889a(mo896i ? abstractC12856e10 : abstractC12856e10.mo878c(abstractC12856e13));
                        AbstractC12856e mo889a5 = abstractC12856e11.mo878c(abstractC12856e7).mo889a(mo896i ? abstractC12856e6 : abstractC12856e6.mo878c(abstractC12856e13));
                        if (mo889a5.mo895j()) {
                            return mo889a4.mo895j() ? mo831r() : c.mo901e();
                        }
                        AbstractC12856e mo874e = mo889a5.mo874e();
                        AbstractC12856e mo878c2 = mo874e.mo878c(mo889a5);
                        if (!mo896i) {
                            abstractC12856e11 = abstractC12856e11.mo878c(abstractC12856e13);
                        }
                        AbstractC12856e mo889a6 = mo889a4.mo889a(mo889a5);
                        AbstractC12856e mo889a7 = mo889a6.mo882b(mo889a4, mo874e, c.m923g()).mo878c(abstractC12856e11).mo889a(mo878c2);
                        AbstractC12856e mo878c3 = mo889a5.mo878c(mo889a7);
                        if (!mo896i) {
                            mo874e = mo874e.mo878c(abstractC12856e13);
                        }
                        return new C12863c(c, mo878c3, mo889a4.mo882b(abstractC12856e6, mo889a5, abstractC12856e10).mo882b(mo874e, mo889a6, mo889a7), new AbstractC12856e[]{mo878c2.mo878c(abstractC12856e11)}, this.f26108f);
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            } else if (abstractC12856e6.mo895j()) {
                return abstractC12856e7.mo895j() ? c.mo901e() : abstractC12860g.mo839b(this);
            } else {
                AbstractC12856e abstractC12856e14 = this.f26106d;
                AbstractC12856e abstractC12856e15 = this.f26107e[0];
                AbstractC12856e abstractC12856e16 = abstractC12860g.f26106d;
                AbstractC12856e abstractC12856e17 = abstractC12860g.f26107e[0];
                boolean mo896i2 = abstractC12856e15.mo896i();
                if (mo896i2) {
                    abstractC12856e = abstractC12856e7;
                    abstractC12856e2 = abstractC12856e16;
                } else {
                    abstractC12856e = abstractC12856e7.mo878c(abstractC12856e15);
                    abstractC12856e2 = abstractC12856e16.mo878c(abstractC12856e15);
                }
                boolean mo896i3 = abstractC12856e17.mo896i();
                if (mo896i3) {
                    abstractC12856e3 = abstractC12856e14;
                } else {
                    abstractC12856e6 = abstractC12856e6.mo878c(abstractC12856e17);
                    abstractC12856e3 = abstractC12856e14.mo878c(abstractC12856e17);
                }
                AbstractC12856e mo889a8 = abstractC12856e3.mo889a(abstractC12856e2);
                AbstractC12856e mo889a9 = abstractC12856e6.mo889a(abstractC12856e);
                if (mo889a9.mo895j()) {
                    return mo889a8.mo895j() ? mo831r() : c.mo901e();
                }
                if (abstractC12856e7.mo895j()) {
                    AbstractC12860g m = m852m();
                    AbstractC12856e m858f = m.m858f();
                    AbstractC12856e mo844g = m.mo844g();
                    AbstractC12856e mo875d2 = mo844g.mo889a(abstractC12856e16).mo875d(m858f);
                    abstractC12856e5 = mo875d2.mo874e().mo889a(mo875d2).mo889a(m858f).mo889a(c.m923g());
                    if (abstractC12856e5.mo895j()) {
                        return new C12863c(c, abstractC12856e5, c.m922h().mo870g(), this.f26108f);
                    }
                    abstractC12856e4 = mo875d2.mo878c(m858f.mo889a(abstractC12856e5)).mo889a(abstractC12856e5).mo889a(mo844g).mo875d(abstractC12856e5).mo889a(abstractC12856e5);
                    mo878c = c.mo906a(InterfaceC12849c.f26070d);
                } else {
                    AbstractC12856e mo874e2 = mo889a9.mo874e();
                    AbstractC12856e mo878c4 = mo889a8.mo878c(abstractC12856e6);
                    AbstractC12856e mo878c5 = mo889a8.mo878c(abstractC12856e);
                    AbstractC12856e mo878c6 = mo878c4.mo878c(mo878c5);
                    if (mo878c6.mo895j()) {
                        return new C12863c(c, mo878c6, c.m922h().mo870g(), this.f26108f);
                    }
                    AbstractC12856e mo878c7 = mo889a8.mo878c(mo874e2);
                    mo878c = !mo896i3 ? mo878c7.mo878c(abstractC12856e17) : mo878c7;
                    AbstractC12856e mo888a = mo878c5.mo889a(mo874e2).mo888a(mo878c, abstractC12856e14.mo889a(abstractC12856e15));
                    if (!mo896i2) {
                        mo878c = mo878c.mo878c(abstractC12856e15);
                    }
                    abstractC12856e4 = mo888a;
                    abstractC12856e5 = mo878c6;
                }
                return new C12863c(c, abstractC12856e5, abstractC12856e4, new AbstractC12856e[]{mo878c}, this.f26108f);
            }
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: d */
        public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
            if (m851n()) {
                return abstractC12860g;
            }
            if (abstractC12860g.m851n()) {
                return mo831r();
            }
            AbstractC12850d c = m861c();
            AbstractC12856e abstractC12856e = this.f26105c;
            if (abstractC12856e.mo895j()) {
                return abstractC12860g;
            }
            if (c.m919k() != 6) {
                return mo831r().mo839b(abstractC12860g);
            }
            AbstractC12856e abstractC12856e2 = abstractC12860g.f26105c;
            AbstractC12856e abstractC12856e3 = abstractC12860g.f26107e[0];
            if (abstractC12856e2.mo895j() || !abstractC12856e3.mo896i()) {
                return mo831r().mo839b(abstractC12860g);
            }
            AbstractC12856e abstractC12856e4 = this.f26106d;
            AbstractC12856e abstractC12856e5 = this.f26107e[0];
            AbstractC12856e abstractC12856e6 = abstractC12860g.f26106d;
            AbstractC12856e mo874e = abstractC12856e.mo874e();
            AbstractC12856e mo874e2 = abstractC12856e4.mo874e();
            AbstractC12856e mo874e3 = abstractC12856e5.mo874e();
            AbstractC12856e mo889a = c.m923g().mo878c(mo874e3).mo889a(mo874e2).mo889a(abstractC12856e4.mo878c(abstractC12856e5));
            AbstractC12856e mo881c = abstractC12856e6.mo881c();
            AbstractC12856e mo882b = c.m923g().mo889a(mo881c).mo878c(mo874e3).mo889a(mo874e2).mo882b(mo889a, mo874e, mo874e3);
            AbstractC12856e mo878c = abstractC12856e2.mo878c(mo874e3);
            AbstractC12856e mo874e4 = mo878c.mo889a(mo889a).mo874e();
            if (mo874e4.mo895j()) {
                return mo882b.mo895j() ? abstractC12860g.mo831r() : c.mo901e();
            } else if (mo882b.mo895j()) {
                return new C12863c(c, mo882b, c.m922h().mo870g(), this.f26108f);
            } else {
                AbstractC12856e mo878c2 = mo882b.mo874e().mo878c(mo878c);
                AbstractC12856e mo878c3 = mo882b.mo878c(mo874e4).mo878c(mo874e3);
                return new C12863c(c, mo878c2, mo882b.mo889a(mo874e4).mo874e().mo882b(mo889a, mo881c, mo878c3), new AbstractC12856e[]{mo878c3}, this.f26108f);
            }
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: g */
        public AbstractC12856e mo844g() {
            int d = m860d();
            switch (d) {
                case 5:
                case 6:
                    AbstractC12856e abstractC12856e = this.f26105c;
                    AbstractC12856e abstractC12856e2 = this.f26106d;
                    if (m851n() || abstractC12856e.mo895j()) {
                        return abstractC12856e2;
                    }
                    AbstractC12856e mo878c = abstractC12856e2.mo889a(abstractC12856e).mo878c(abstractC12856e);
                    if (6 == d) {
                        AbstractC12856e abstractC12856e3 = this.f26107e[0];
                        return !abstractC12856e3.mo896i() ? mo878c.mo875d(abstractC12856e3) : mo878c;
                    }
                    return mo878c;
                default:
                    return this.f26106d;
            }
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: p */
        protected boolean mo843p() {
            AbstractC12856e h = m857h();
            if (h.mo895j()) {
                return false;
            }
            AbstractC12856e i = m856i();
            switch (m860d()) {
                case 5:
                case 6:
                    return i.mo894k() != h.mo894k();
                default:
                    return i.mo875d(h).mo894k();
            }
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: q */
        public AbstractC12860g mo832q() {
            if (m851n()) {
                return this;
            }
            AbstractC12856e abstractC12856e = this.f26105c;
            if (abstractC12856e.mo895j()) {
                return this;
            }
            switch (m860d()) {
                case 0:
                    return new C12863c(this.f26104b, abstractC12856e, this.f26106d.mo889a(abstractC12856e), this.f26108f);
                case 1:
                    return new C12863c(this.f26104b, abstractC12856e, this.f26106d.mo889a(abstractC12856e), new AbstractC12856e[]{this.f26107e[0]}, this.f26108f);
                case 2:
                case 3:
                case 4:
                default:
                    throw new IllegalStateException("unsupported coordinate system");
                case 5:
                    return new C12863c(this.f26104b, abstractC12856e, this.f26106d.mo881c(), this.f26108f);
                case 6:
                    AbstractC12856e abstractC12856e2 = this.f26106d;
                    AbstractC12856e abstractC12856e3 = this.f26107e[0];
                    return new C12863c(this.f26104b, abstractC12856e, abstractC12856e2.mo889a(abstractC12856e3), new AbstractC12856e[]{abstractC12856e3}, this.f26108f);
            }
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: r */
        public AbstractC12860g mo831r() {
            AbstractC12856e mo889a;
            if (m851n()) {
                return this;
            }
            AbstractC12850d c = m861c();
            AbstractC12856e abstractC12856e = this.f26105c;
            if (abstractC12856e.mo895j()) {
                return c.mo901e();
            }
            int m919k = c.m919k();
            if (m919k != 6) {
                switch (m919k) {
                    case 0:
                        AbstractC12856e mo889a2 = this.f26106d.mo875d(abstractC12856e).mo889a(abstractC12856e);
                        AbstractC12856e mo889a3 = mo889a2.mo874e().mo889a(mo889a2).mo889a(c.m923g());
                        return new C12863c(c, mo889a3, abstractC12856e.mo888a(mo889a3, mo889a2.mo881c()), this.f26108f);
                    case 1:
                        AbstractC12856e abstractC12856e2 = this.f26106d;
                        AbstractC12856e abstractC12856e3 = this.f26107e[0];
                        boolean mo896i = abstractC12856e3.mo896i();
                        AbstractC12856e mo878c = mo896i ? abstractC12856e : abstractC12856e.mo878c(abstractC12856e3);
                        if (!mo896i) {
                            abstractC12856e2 = abstractC12856e2.mo878c(abstractC12856e3);
                        }
                        AbstractC12856e mo874e = abstractC12856e.mo874e();
                        AbstractC12856e mo889a4 = mo874e.mo889a(abstractC12856e2);
                        AbstractC12856e mo874e2 = mo878c.mo874e();
                        AbstractC12856e mo889a5 = mo889a4.mo889a(mo878c);
                        AbstractC12856e mo882b = mo889a5.mo882b(mo889a4, mo874e2, c.m923g());
                        return new C12863c(c, mo878c.mo878c(mo882b), mo874e.mo874e().mo882b(mo878c, mo882b, mo889a5), new AbstractC12856e[]{mo878c.mo878c(mo874e2)}, this.f26108f);
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            }
            AbstractC12856e abstractC12856e4 = this.f26106d;
            AbstractC12856e abstractC12856e5 = this.f26107e[0];
            boolean mo896i2 = abstractC12856e5.mo896i();
            AbstractC12856e mo878c2 = mo896i2 ? abstractC12856e4 : abstractC12856e4.mo878c(abstractC12856e5);
            AbstractC12856e mo874e3 = mo896i2 ? abstractC12856e5 : abstractC12856e5.mo874e();
            AbstractC12856e m923g = c.m923g();
            AbstractC12856e mo878c3 = mo896i2 ? m923g : m923g.mo878c(mo874e3);
            AbstractC12856e mo889a6 = abstractC12856e4.mo874e().mo889a(mo878c2).mo889a(mo878c3);
            if (mo889a6.mo895j()) {
                return new C12863c(c, mo889a6, c.m922h().mo870g(), this.f26108f);
            }
            AbstractC12856e mo874e4 = mo889a6.mo874e();
            AbstractC12856e mo878c4 = mo896i2 ? mo889a6 : mo889a6.mo878c(mo874e3);
            AbstractC12856e m922h = c.m922h();
            if (m922h.mo897h() < (c.mo908a() >> 1)) {
                AbstractC12856e mo874e5 = abstractC12856e4.mo889a(abstractC12856e).mo874e();
                mo889a = mo874e5.mo889a(mo889a6).mo889a(mo874e3).mo878c(mo874e5).mo889a(m922h.mo896i() ? mo878c3.mo889a(mo874e3).mo874e() : mo878c3.mo888a(m922h, mo874e3.mo874e())).mo889a(mo874e4);
                if (!m923g.mo895j()) {
                    if (!m923g.mo896i()) {
                        mo889a = mo889a.mo889a(m923g.mo881c().mo878c(mo878c4));
                    }
                    return new C12863c(c, mo874e4, mo889a, new AbstractC12856e[]{mo878c4}, this.f26108f);
                }
            } else {
                if (!mo896i2) {
                    abstractC12856e = abstractC12856e.mo878c(abstractC12856e5);
                }
                mo889a = abstractC12856e.mo888a(mo889a6, mo878c2).mo889a(mo874e4);
            }
            mo889a = mo889a.mo889a(mo878c4);
            return new C12863c(c, mo874e4, mo889a, new AbstractC12856e[]{mo878c4}, this.f26108f);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.e.a.g$d */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12864d extends AbstractC12862b {
        public C12864d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
            this(abstractC12850d, abstractC12856e, abstractC12856e2, false);
        }

        public C12864d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, boolean z) {
            super(abstractC12850d, abstractC12856e, abstractC12856e2);
            if ((abstractC12856e == null) != (abstractC12856e2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            this.f26108f = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C12864d(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr, boolean z) {
            super(abstractC12850d, abstractC12856e, abstractC12856e2, abstractC12856eArr);
            this.f26108f = z;
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: a */
        public AbstractC12856e mo842a(int i) {
            return (i == 1 && 4 == m860d()) ? m829t() : super.mo842a(i);
        }

        /* renamed from: b */
        protected AbstractC12856e m840b(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
            AbstractC12856e m923g = m861c().m923g();
            if (m923g.mo895j() || abstractC12856e.mo896i()) {
                return m923g;
            }
            if (abstractC12856e2 == null) {
                abstractC12856e2 = abstractC12856e.mo874e();
            }
            AbstractC12856e mo874e = abstractC12856e2.mo874e();
            AbstractC12856e mo877d = m923g.mo877d();
            return mo877d.mo897h() < m923g.mo897h() ? mo874e.mo878c(mo877d).mo877d() : mo874e.mo878c(m923g);
        }

        /* renamed from: b */
        protected C12864d m838b(boolean z) {
            AbstractC12856e abstractC12856e = this.f26105c;
            AbstractC12856e abstractC12856e2 = this.f26106d;
            AbstractC12856e abstractC12856e3 = this.f26107e[0];
            AbstractC12856e m829t = m829t();
            AbstractC12856e mo889a = m835e(abstractC12856e.mo874e()).mo889a(m829t);
            AbstractC12856e m837d = m837d(abstractC12856e2);
            AbstractC12856e mo878c = m837d.mo878c(abstractC12856e2);
            AbstractC12856e m837d2 = m837d(abstractC12856e.mo878c(mo878c));
            AbstractC12856e mo883b = mo889a.mo874e().mo883b(m837d(m837d2));
            AbstractC12856e m837d3 = m837d(mo878c.mo874e());
            AbstractC12856e mo883b2 = mo889a.mo878c(m837d2.mo883b(mo883b)).mo883b(m837d3);
            AbstractC12856e m837d4 = z ? m837d(m837d3.mo878c(m829t)) : null;
            if (!abstractC12856e3.mo896i()) {
                m837d = m837d.mo878c(abstractC12856e3);
            }
            return new C12864d(m861c(), mo883b, mo883b2, new AbstractC12856e[]{m837d, m837d4}, this.f26108f);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: b */
        public AbstractC12860g mo841b(int i) {
            AbstractC12856e mo874e;
            if (i >= 0) {
                if (i == 0 || m851n()) {
                    return this;
                }
                if (i == 1) {
                    return mo831r();
                }
                AbstractC12850d c = m861c();
                AbstractC12856e abstractC12856e = this.f26106d;
                if (abstractC12856e.mo895j()) {
                    return c.mo901e();
                }
                int m919k = c.m919k();
                AbstractC12856e m923g = c.m923g();
                AbstractC12856e abstractC12856e2 = this.f26105c;
                AbstractC12856e mo906a = this.f26107e.length < 1 ? c.mo906a(InterfaceC12849c.f26070d) : this.f26107e[0];
                if (!mo906a.mo896i()) {
                    if (m919k != 4) {
                        switch (m919k) {
                            case 0:
                                break;
                            case 1:
                                mo874e = mo906a.mo874e();
                                abstractC12856e2 = abstractC12856e2.mo878c(mo906a);
                                abstractC12856e = abstractC12856e.mo878c(mo874e);
                                m923g = m840b(mo906a, mo874e);
                                break;
                            case 2:
                                mo874e = null;
                                m923g = m840b(mo906a, mo874e);
                                break;
                            default:
                                throw new IllegalStateException("unsupported coordinate system");
                        }
                    } else {
                        m923g = m829t();
                    }
                }
                AbstractC12856e abstractC12856e3 = m923g;
                AbstractC12856e abstractC12856e4 = abstractC12856e;
                int i2 = 0;
                while (i2 < i) {
                    if (abstractC12856e4.mo895j()) {
                        return c.mo901e();
                    }
                    AbstractC12856e m835e = m835e(abstractC12856e2.mo874e());
                    AbstractC12856e m837d = m837d(abstractC12856e4);
                    AbstractC12856e mo878c = m837d.mo878c(abstractC12856e4);
                    AbstractC12856e m837d2 = m837d(abstractC12856e2.mo878c(mo878c));
                    AbstractC12856e m837d3 = m837d(mo878c.mo874e());
                    if (!abstractC12856e3.mo895j()) {
                        m835e = m835e.mo889a(abstractC12856e3);
                        abstractC12856e3 = m837d(m837d3.mo878c(abstractC12856e3));
                    }
                    AbstractC12856e mo883b = m835e.mo874e().mo883b(m837d(m837d2));
                    abstractC12856e4 = m835e.mo878c(m837d2.mo883b(mo883b)).mo883b(m837d3);
                    mo906a = mo906a.mo896i() ? m837d : m837d.mo878c(mo906a);
                    i2++;
                    abstractC12856e2 = mo883b;
                }
                if (m919k != 4) {
                    switch (m919k) {
                        case 0:
                            AbstractC12856e mo871f = mo906a.mo871f();
                            AbstractC12856e mo874e2 = mo871f.mo874e();
                            return new C12864d(c, abstractC12856e2.mo878c(mo874e2), abstractC12856e4.mo878c(mo874e2.mo878c(mo871f)), this.f26108f);
                        case 1:
                            return new C12864d(c, abstractC12856e2.mo878c(mo906a), abstractC12856e4, new AbstractC12856e[]{mo906a.mo878c(mo906a.mo874e())}, this.f26108f);
                        case 2:
                            return new C12864d(c, abstractC12856e2, abstractC12856e4, new AbstractC12856e[]{mo906a}, this.f26108f);
                        default:
                            throw new IllegalStateException("unsupported coordinate system");
                    }
                }
                return new C12864d(c, abstractC12856e2, abstractC12856e4, new AbstractC12856e[]{mo906a, abstractC12856e3}, this.f26108f);
            }
            throw new IllegalArgumentException("'e' cannot be negative");
        }

        /* JADX WARN: Removed duplicated region for block: B:95:0x01fe  */
        /* JADX WARN: Removed duplicated region for block: B:96:0x020c  */
        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public org.p415a.p436e.p437a.AbstractC12860g mo839b(org.p415a.p436e.p437a.AbstractC12860g r17) {
            /*
                Method dump skipped, instructions count: 550
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.p415a.p436e.p437a.AbstractC12860g.C12864d.mo839b(org.a.e.a.g):org.a.e.a.g");
        }

        /* renamed from: d */
        protected AbstractC12856e m837d(AbstractC12856e abstractC12856e) {
            return abstractC12856e.mo889a(abstractC12856e);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: d */
        public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
            if (this == abstractC12860g) {
                return mo830s();
            }
            if (m851n()) {
                return abstractC12860g;
            }
            if (abstractC12860g.m851n()) {
                return mo831r();
            }
            AbstractC12856e abstractC12856e = this.f26106d;
            if (abstractC12856e.mo895j()) {
                return abstractC12860g;
            }
            AbstractC12850d c = m861c();
            int m919k = c.m919k();
            if (m919k != 0) {
                return m919k != 4 ? mo831r().mo839b(abstractC12860g) : m838b(false).mo839b(abstractC12860g);
            }
            AbstractC12856e abstractC12856e2 = this.f26105c;
            AbstractC12856e abstractC12856e3 = abstractC12860g.f26105c;
            AbstractC12856e abstractC12856e4 = abstractC12860g.f26106d;
            AbstractC12856e mo883b = abstractC12856e3.mo883b(abstractC12856e2);
            AbstractC12856e mo883b2 = abstractC12856e4.mo883b(abstractC12856e);
            if (mo883b.mo895j()) {
                return mo883b2.mo895j() ? mo830s() : this;
            }
            AbstractC12856e mo874e = mo883b.mo874e();
            AbstractC12856e mo883b3 = mo874e.mo878c(m837d(abstractC12856e2).mo889a(abstractC12856e3)).mo883b(mo883b2.mo874e());
            if (mo883b3.mo895j()) {
                return c.mo901e();
            }
            AbstractC12856e mo871f = mo883b3.mo878c(mo883b).mo871f();
            AbstractC12856e mo878c = mo883b3.mo878c(mo871f).mo878c(mo883b2);
            AbstractC12856e mo883b4 = m837d(abstractC12856e).mo878c(mo874e).mo878c(mo883b).mo878c(mo871f).mo883b(mo878c);
            AbstractC12856e mo889a = mo883b4.mo883b(mo878c).mo878c(mo878c.mo889a(mo883b4)).mo889a(abstractC12856e3);
            return new C12864d(c, mo889a, abstractC12856e2.mo883b(mo889a).mo878c(mo883b4).mo883b(abstractC12856e), this.f26108f);
        }

        /* renamed from: e */
        protected AbstractC12856e m835e(AbstractC12856e abstractC12856e) {
            return m837d(abstractC12856e).mo889a(abstractC12856e);
        }

        /* renamed from: f */
        protected AbstractC12856e m834f(AbstractC12856e abstractC12856e) {
            return m837d(m837d(abstractC12856e));
        }

        /* renamed from: g */
        protected AbstractC12856e m833g(AbstractC12856e abstractC12856e) {
            return m834f(m837d(abstractC12856e));
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: q */
        public AbstractC12860g mo832q() {
            if (m851n()) {
                return this;
            }
            AbstractC12850d c = m861c();
            return c.m919k() != 0 ? new C12864d(c, this.f26105c, this.f26106d.mo877d(), this.f26107e, this.f26108f) : new C12864d(c, this.f26105c, this.f26106d.mo877d(), this.f26108f);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: r */
        public AbstractC12860g mo831r() {
            AbstractC12856e abstractC12856e;
            AbstractC12856e mo878c;
            if (m851n()) {
                return this;
            }
            AbstractC12850d c = m861c();
            AbstractC12856e abstractC12856e2 = this.f26106d;
            if (abstractC12856e2.mo895j()) {
                return c.mo901e();
            }
            int m919k = c.m919k();
            AbstractC12856e abstractC12856e3 = this.f26105c;
            if (m919k != 4) {
                switch (m919k) {
                    case 0:
                        AbstractC12856e mo875d = m835e(abstractC12856e3.mo874e()).mo889a(m861c().m923g()).mo875d(m837d(abstractC12856e2));
                        AbstractC12856e mo883b = mo875d.mo874e().mo883b(m837d(abstractC12856e3));
                        return new C12864d(c, mo883b, mo875d.mo878c(abstractC12856e3.mo883b(mo883b)).mo883b(abstractC12856e2), this.f26108f);
                    case 1:
                        AbstractC12856e abstractC12856e4 = this.f26107e[0];
                        boolean mo896i = abstractC12856e4.mo896i();
                        AbstractC12856e m923g = c.m923g();
                        if (!m923g.mo895j() && !mo896i) {
                            m923g = m923g.mo878c(abstractC12856e4.mo874e());
                        }
                        AbstractC12856e mo889a = m923g.mo889a(m835e(abstractC12856e3.mo874e()));
                        AbstractC12856e mo878c2 = mo896i ? abstractC12856e2 : abstractC12856e2.mo878c(abstractC12856e4);
                        AbstractC12856e mo874e = mo896i ? abstractC12856e2.mo874e() : mo878c2.mo878c(abstractC12856e2);
                        AbstractC12856e m834f = m834f(abstractC12856e3.mo878c(mo874e));
                        AbstractC12856e mo883b2 = mo889a.mo874e().mo883b(m837d(m834f));
                        AbstractC12856e m837d = m837d(mo878c2);
                        AbstractC12856e mo878c3 = mo883b2.mo878c(m837d);
                        AbstractC12856e m837d2 = m837d(mo874e);
                        return new C12864d(c, mo878c3, m834f.mo883b(mo883b2).mo878c(mo889a).mo883b(m837d(m837d2.mo874e())), new AbstractC12856e[]{m837d(mo896i ? m837d(m837d2) : m837d.mo874e()).mo878c(mo878c2)}, this.f26108f);
                    case 2:
                        AbstractC12856e abstractC12856e5 = this.f26107e[0];
                        boolean mo896i2 = abstractC12856e5.mo896i();
                        AbstractC12856e mo874e2 = abstractC12856e2.mo874e();
                        AbstractC12856e mo874e3 = mo874e2.mo874e();
                        AbstractC12856e m923g2 = c.m923g();
                        AbstractC12856e mo877d = m923g2.mo877d();
                        if (mo877d.mo893a().equals(BigInteger.valueOf(3L))) {
                            AbstractC12856e mo874e4 = mo896i2 ? abstractC12856e5 : abstractC12856e5.mo874e();
                            abstractC12856e = m835e(abstractC12856e3.mo889a(mo874e4).mo878c(abstractC12856e3.mo883b(mo874e4)));
                            mo878c = mo874e2.mo878c(abstractC12856e3);
                        } else {
                            AbstractC12856e m835e = m835e(abstractC12856e3.mo874e());
                            if (!mo896i2) {
                                if (m923g2.mo895j()) {
                                    abstractC12856e = m835e;
                                } else {
                                    AbstractC12856e mo874e5 = abstractC12856e5.mo874e().mo874e();
                                    if (mo877d.mo897h() < m923g2.mo897h()) {
                                        abstractC12856e = m835e.mo883b(mo874e5.mo878c(mo877d));
                                    } else {
                                        m923g2 = mo874e5.mo878c(m923g2);
                                    }
                                }
                                mo878c = abstractC12856e3.mo878c(mo874e2);
                            }
                            abstractC12856e = m835e.mo889a(m923g2);
                            mo878c = abstractC12856e3.mo878c(mo874e2);
                        }
                        AbstractC12856e m834f2 = m834f(mo878c);
                        AbstractC12856e mo883b3 = abstractC12856e.mo874e().mo883b(m837d(m834f2));
                        AbstractC12856e mo883b4 = m834f2.mo883b(mo883b3).mo878c(abstractC12856e).mo883b(m833g(mo874e3));
                        AbstractC12856e m837d3 = m837d(abstractC12856e2);
                        if (!mo896i2) {
                            m837d3 = m837d3.mo878c(abstractC12856e5);
                        }
                        return new C12864d(c, mo883b3, mo883b4, new AbstractC12856e[]{m837d3}, this.f26108f);
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return m838b(true);
        }

        @Override // org.p415a.p436e.p437a.AbstractC12860g
        /* renamed from: s */
        public AbstractC12860g mo830s() {
            if (m851n()) {
                return this;
            }
            AbstractC12856e abstractC12856e = this.f26106d;
            if (abstractC12856e.mo895j()) {
                return this;
            }
            AbstractC12850d c = m861c();
            int m919k = c.m919k();
            if (m919k != 0) {
                return m919k != 4 ? mo831r().mo839b(this) : m838b(false).mo839b(this);
            }
            AbstractC12856e abstractC12856e2 = this.f26105c;
            AbstractC12856e m837d = m837d(abstractC12856e);
            AbstractC12856e mo874e = m837d.mo874e();
            AbstractC12856e mo889a = m835e(abstractC12856e2.mo874e()).mo889a(m861c().m923g());
            AbstractC12856e mo883b = m835e(abstractC12856e2).mo878c(mo874e).mo883b(mo889a.mo874e());
            if (mo883b.mo895j()) {
                return m861c().mo901e();
            }
            AbstractC12856e mo871f = mo883b.mo878c(m837d).mo871f();
            AbstractC12856e mo878c = mo883b.mo878c(mo871f).mo878c(mo889a);
            AbstractC12856e mo883b2 = mo874e.mo874e().mo878c(mo871f).mo883b(mo878c);
            AbstractC12856e mo889a2 = mo883b2.mo883b(mo878c).mo878c(mo878c.mo889a(mo883b2)).mo889a(abstractC12856e2);
            return new C12864d(c, mo889a2, abstractC12856e2.mo883b(mo889a2).mo878c(mo883b2).mo883b(abstractC12856e), this.f26108f);
        }

        /* renamed from: t */
        protected AbstractC12856e m829t() {
            AbstractC12856e abstractC12856e = this.f26107e[1];
            if (abstractC12856e == null) {
                AbstractC12856e[] abstractC12856eArr = this.f26107e;
                AbstractC12856e m840b = m840b(this.f26107e[0], null);
                abstractC12856eArr[1] = m840b;
                return m840b;
            }
            return abstractC12856e;
        }
    }

    protected AbstractC12860g(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        this(abstractC12850d, abstractC12856e, abstractC12856e2, m866a(abstractC12850d));
    }

    protected AbstractC12860g(AbstractC12850d abstractC12850d, AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2, AbstractC12856e[] abstractC12856eArr) {
        this.f26109g = null;
        this.f26104b = abstractC12850d;
        this.f26105c = abstractC12856e;
        this.f26106d = abstractC12856e2;
        this.f26107e = abstractC12856eArr;
    }

    /* renamed from: a */
    protected static AbstractC12856e[] m866a(AbstractC12850d abstractC12850d) {
        int m919k = abstractC12850d == null ? 0 : abstractC12850d.m919k();
        if (m919k == 0 || m919k == 5) {
            return f26103a;
        }
        AbstractC12856e mo906a = abstractC12850d.mo906a(InterfaceC12849c.f26070d);
        if (m919k != 6) {
            switch (m919k) {
                case 1:
                case 2:
                    break;
                case 3:
                    return new AbstractC12856e[]{mo906a, mo906a, mo906a};
                case 4:
                    return new AbstractC12856e[]{mo906a, abstractC12850d.m923g()};
                default:
                    throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new AbstractC12856e[]{mo906a};
    }

    /* renamed from: a */
    public AbstractC12856e mo842a(int i) {
        if (i >= 0) {
            AbstractC12856e[] abstractC12856eArr = this.f26107e;
            if (i < abstractC12856eArr.length) {
                return abstractC12856eArr[i];
            }
        }
        return null;
    }

    /* renamed from: a */
    public AbstractC12860g m867a(BigInteger bigInteger) {
        return m861c().m918l().mo869a(this, bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public AbstractC12860g m865a(AbstractC12856e abstractC12856e) {
        int m860d = m860d();
        if (m860d != 6) {
            switch (m860d) {
                case 1:
                    break;
                case 2:
                case 3:
                case 4:
                    AbstractC12856e mo874e = abstractC12856e.mo874e();
                    return m864a(mo874e, mo874e.mo878c(abstractC12856e));
                default:
                    throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return m864a(abstractC12856e, abstractC12856e);
    }

    /* renamed from: a */
    protected AbstractC12860g m864a(AbstractC12856e abstractC12856e, AbstractC12856e abstractC12856e2) {
        return m861c().mo905a(m857h().mo878c(abstractC12856e), m856i().mo878c(abstractC12856e2), this.f26108f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m868a() {
        BigInteger m920j = this.f26104b.m920j();
        return m920j == null || m920j.equals(InterfaceC12849c.f26070d) || !C12844b.m957a(this, m920j).m851n();
    }

    /* renamed from: a */
    public boolean m863a(AbstractC12860g abstractC12860g) {
        AbstractC12860g abstractC12860g2;
        AbstractC12860g abstractC12860g3;
        if (abstractC12860g == null) {
            return false;
        }
        AbstractC12850d m861c = m861c();
        AbstractC12850d m861c2 = abstractC12860g.m861c();
        boolean z = m861c == null;
        boolean z2 = m861c2 == null;
        boolean m851n = m851n();
        boolean m851n2 = abstractC12860g.m851n();
        if (m851n || m851n2) {
            if (m851n && m851n2) {
                return z || z2 || m861c.m934a(m861c2);
            }
            return false;
        }
        if (!z || !z2) {
            if (!z) {
                if (z2) {
                    abstractC12860g3 = abstractC12860g;
                    abstractC12860g2 = m852m();
                } else if (!m861c.m934a(m861c2)) {
                    return false;
                } else {
                    AbstractC12860g[] abstractC12860gArr = {this, m861c.mo903a(abstractC12860g)};
                    m861c.m930a(abstractC12860gArr);
                    abstractC12860g2 = abstractC12860gArr[0];
                    abstractC12860g3 = abstractC12860gArr[1];
                }
                return abstractC12860g2.m858f().equals(abstractC12860g3.m858f()) && abstractC12860g2.mo844g().equals(abstractC12860g3.mo844g());
            }
            abstractC12860g = abstractC12860g.m852m();
        }
        abstractC12860g3 = abstractC12860g;
        abstractC12860g2 = this;
        if (abstractC12860g2.m858f().equals(abstractC12860g3.m858f())) {
            return false;
        }
    }

    /* renamed from: a */
    public byte[] m862a(boolean z) {
        if (m851n()) {
            return new byte[1];
        }
        AbstractC12860g m852m = m852m();
        byte[] m900l = m852m.m858f().m900l();
        if (z) {
            byte[] bArr = new byte[m900l.length + 1];
            bArr[0] = (byte) (m852m.mo843p() ? 3 : 2);
            System.arraycopy(m900l, 0, bArr, 1, m900l.length);
            return bArr;
        }
        byte[] m900l2 = m852m.mo844g().m900l();
        byte[] bArr2 = new byte[m900l.length + m900l2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(m900l, 0, bArr2, 1, m900l.length);
        System.arraycopy(m900l2, 0, bArr2, m900l.length + 1, m900l2.length);
        return bArr2;
    }

    /* renamed from: b */
    public AbstractC12860g mo841b(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        AbstractC12860g abstractC12860g = this;
        while (true) {
            i--;
            if (i < 0) {
                return abstractC12860g;
            }
            abstractC12860g = abstractC12860g.mo831r();
        }
    }

    /* renamed from: b */
    public AbstractC12860g mo849b(AbstractC12856e abstractC12856e) {
        return m851n() ? this : m861c().mo904a(m857h().mo878c(abstractC12856e), m856i(), m855j(), this.f26108f);
    }

    /* renamed from: b */
    public abstract AbstractC12860g mo839b(AbstractC12860g abstractC12860g);

    /* renamed from: b */
    protected abstract boolean mo846b();

    /* renamed from: c */
    public AbstractC12850d m861c() {
        return this.f26104b;
    }

    /* renamed from: c */
    public AbstractC12860g mo847c(AbstractC12856e abstractC12856e) {
        return m851n() ? this : m861c().mo904a(m857h(), m856i().mo878c(abstractC12856e), m855j(), this.f26108f);
    }

    /* renamed from: c */
    public abstract AbstractC12860g mo845c(AbstractC12860g abstractC12860g);

    /* renamed from: d */
    protected int m860d() {
        AbstractC12850d abstractC12850d = this.f26104b;
        if (abstractC12850d == null) {
            return 0;
        }
        return abstractC12850d.m919k();
    }

    /* renamed from: d */
    public AbstractC12860g mo836d(AbstractC12860g abstractC12860g) {
        return mo831r().mo839b(abstractC12860g);
    }

    /* renamed from: e */
    public AbstractC12856e m859e() {
        m854k();
        return mo844g();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractC12860g) {
            return m863a((AbstractC12860g) obj);
        }
        return false;
    }

    /* renamed from: f */
    public AbstractC12856e m858f() {
        return this.f26105c;
    }

    /* renamed from: g */
    public AbstractC12856e mo844g() {
        return this.f26106d;
    }

    /* renamed from: h */
    public final AbstractC12856e m857h() {
        return this.f26105c;
    }

    public int hashCode() {
        AbstractC12850d m861c = m861c();
        int i = m861c == null ? 0 : ~m861c.hashCode();
        if (m851n()) {
            return i;
        }
        AbstractC12860g m852m = m852m();
        return (i ^ (m852m.m858f().hashCode() * 17)) ^ (m852m.mo844g().hashCode() * 257);
    }

    /* renamed from: i */
    public final AbstractC12856e m856i() {
        return this.f26106d;
    }

    /* renamed from: j */
    protected final AbstractC12856e[] m855j() {
        return this.f26107e;
    }

    /* renamed from: k */
    protected void m854k() {
        if (!m853l()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    /* renamed from: l */
    public boolean m853l() {
        int m860d = m860d();
        return m860d == 0 || m860d == 5 || m851n() || this.f26107e[0].mo896i();
    }

    /* renamed from: m */
    public AbstractC12860g m852m() {
        int m860d;
        if (m851n() || (m860d = m860d()) == 0 || m860d == 5) {
            return this;
        }
        AbstractC12856e mo842a = mo842a(0);
        return mo842a.mo896i() ? this : m865a(mo842a.mo871f());
    }

    /* renamed from: n */
    public boolean m851n() {
        if (this.f26105c != null && this.f26106d != null) {
            AbstractC12856e[] abstractC12856eArr = this.f26107e;
            if (abstractC12856eArr.length <= 0 || !abstractC12856eArr[0].mo895j()) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: o */
    public boolean m850o() {
        return m851n() || m861c() == null || (mo846b() && m868a());
    }

    /* renamed from: p */
    protected abstract boolean mo843p();

    /* renamed from: q */
    public abstract AbstractC12860g mo832q();

    /* renamed from: r */
    public abstract AbstractC12860g mo831r();

    /* renamed from: s */
    public AbstractC12860g mo830s() {
        return mo836d(this);
    }

    public String toString() {
        if (m851n()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(m857h());
        stringBuffer.append(',');
        stringBuffer.append(m856i());
        for (int i = 0; i < this.f26107e.length; i++) {
            stringBuffer.append(',');
            stringBuffer.append(this.f26107e[i]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }
}
