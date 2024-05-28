package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.IOException;

/* renamed from: a.a.a.a.a.e.a.b.y */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0494y extends AbstractC0258r implements InterfaceC0497z {

    /* renamed from: v3 */
    public int f1605v3;

    /* renamed from: w3 */
    public boolean f1606w3 = false;

    /* renamed from: x3 */
    public boolean f1607x3;

    /* renamed from: y3 */
    public InterfaceC0136d f1608y3;

    public AbstractC0494y(boolean z, int i, InterfaceC0136d interfaceC0136d) {
        this.f1607x3 = true;
        this.f1608y3 = null;
        if (interfaceC0136d instanceof InterfaceC0132c) {
            this.f1607x3 = true;
        } else {
            this.f1607x3 = z;
        }
        this.f1605v3 = i;
        if (this.f1607x3) {
            this.f1608y3 = interfaceC0136d;
            return;
        }
        boolean z2 = interfaceC0136d.mo23015d() instanceof AbstractC0338u;
        this.f1608y3 = interfaceC0136d;
    }

    /* renamed from: a */
    public static AbstractC0494y m23009a(AbstractC0494y abstractC0494y, boolean z) {
        if (z) {
            return (AbstractC0494y) abstractC0494y.m23004m();
        }
        throw new IllegalArgumentException("implicitly tagged tagged object");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public abstract void mo22982a(C0252q c0252q);

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        return mo23015d();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0497z
    /* renamed from: f */
    public int mo22994f() {
        return this.f1605v3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        int i = this.f1605v3;
        InterfaceC0136d interfaceC0136d = this.f1608y3;
        return interfaceC0136d != null ? i ^ interfaceC0136d.hashCode() : i;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: k */
    public AbstractC0258r mo23006k() {
        return new C0360v1(this.f1607x3, this.f1605v3, this.f1608y3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: l */
    public AbstractC0258r mo23005l() {
        return new C0143e2(this.f1607x3, this.f1605v3, this.f1608y3);
    }

    /* renamed from: m */
    public AbstractC0258r m23004m() {
        InterfaceC0136d interfaceC0136d = this.f1608y3;
        if (interfaceC0136d != null) {
            return interfaceC0136d.mo23015d();
        }
        return null;
    }

    /* renamed from: n */
    public boolean m23003n() {
        return this.f1606w3;
    }

    /* renamed from: o */
    public boolean m23002o() {
        return this.f1607x3;
    }

    public String toString() {
        return "[" + this.f1605v3 + "]" + this.f1608y3;
    }

    /* renamed from: a */
    public static AbstractC0494y m23008a(Object obj) {
        if (obj != null && !(obj instanceof AbstractC0494y)) {
            if (obj instanceof byte[]) {
                try {
                    return m23008a((Object) AbstractC0258r.m23755a((byte[]) obj));
                } catch (IOException e) {
                    throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e.getMessage());
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        return (AbstractC0494y) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof AbstractC0494y) {
            AbstractC0494y abstractC0494y = (AbstractC0494y) abstractC0258r;
            if (this.f1605v3 == abstractC0494y.f1605v3 && this.f1606w3 == abstractC0494y.f1606w3 && this.f1607x3 == abstractC0494y.f1607x3) {
                InterfaceC0136d interfaceC0136d = this.f1608y3;
                return interfaceC0136d == null ? abstractC0494y.f1608y3 == null : interfaceC0136d.mo23015d().equals(abstractC0494y.f1608y3.mo23015d());
            }
            return false;
        }
        return false;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0497z
    /* renamed from: a */
    public InterfaceC0136d mo22995a(int i, boolean z) {
        if (i != 4) {
            if (i != 16) {
                if (i != 17) {
                    if (z) {
                        return m23004m();
                    }
                    throw new RuntimeException("implicit tagging not implemented for tag: " + i);
                }
                return AbstractC0338u.m23582a(this, z).m23577n();
            }
            return AbstractC0263s.m23750a(this, z).m23746n();
        }
        return AbstractC0182o.m24090a(this, z).m24087n();
    }
}
