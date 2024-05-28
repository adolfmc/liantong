package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* renamed from: a.a.a.a.a.e.a.b.u */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0338u extends AbstractC0258r {

    /* renamed from: v3 */
    public Vector f874v3;

    /* renamed from: w3 */
    public boolean f875w3;

    /* renamed from: a.a.a.a.a.e.a.b.u$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0339a implements InterfaceC0358v {

        /* renamed from: v3 */
        public final int f876v3;

        /* renamed from: w3 */
        public int f877w3;

        /* renamed from: x3 */
        public final /* synthetic */ AbstractC0338u f878x3;

        public C0339a(AbstractC0338u abstractC0338u) {
            this.f878x3 = abstractC0338u;
            this.f876v3 = AbstractC0338u.this.m23576o();
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0358v
        /* renamed from: a */
        public InterfaceC0136d mo23561a() {
            int i = this.f877w3;
            if (i == this.f876v3) {
                return null;
            }
            AbstractC0338u abstractC0338u = AbstractC0338u.this;
            this.f877w3 = i + 1;
            InterfaceC0136d m23584a = abstractC0338u.m23584a(i);
            if (m23584a instanceof AbstractC0263s) {
                return ((AbstractC0263s) m23584a).m23746n();
            }
            return m23584a instanceof AbstractC0338u ? ((AbstractC0338u) m23584a).m23577n() : m23584a;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
        /* renamed from: b */
        public AbstractC0258r mo23007b() {
            return this.f878x3;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
        /* renamed from: d */
        public AbstractC0258r mo23015d() {
            return this.f878x3;
        }
    }

    public AbstractC0338u() {
        this.f874v3 = new Vector();
        this.f875w3 = false;
    }

    /* renamed from: a */
    public static AbstractC0338u m23581a(Object obj) {
        if (obj != null && !(obj instanceof AbstractC0338u)) {
            if (obj instanceof InterfaceC0358v) {
                return m23581a((Object) ((InterfaceC0358v) obj).mo23015d());
            }
            if (obj instanceof byte[]) {
                try {
                    return m23581a((Object) AbstractC0258r.m23755a((byte[]) obj));
                } catch (IOException e) {
                    throw new IllegalArgumentException("failed to construct set from byte[]: " + e.getMessage());
                }
            }
            if (obj instanceof InterfaceC0136d) {
                AbstractC0258r mo23015d = ((InterfaceC0136d) obj).mo23015d();
                if (mo23015d instanceof AbstractC0338u) {
                    return (AbstractC0338u) mo23015d;
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        return (AbstractC0338u) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public abstract void mo22982a(C0252q c0252q);

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        Enumeration m23578m = m23578m();
        int m23576o = m23576o();
        while (m23578m.hasMoreElements()) {
            m23576o = (m23576o * 17) ^ m23580a(m23578m).hashCode();
        }
        return m23576o;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return true;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: k */
    public AbstractC0258r mo23006k() {
        if (this.f875w3) {
            C0260r1 c0260r1 = new C0260r1();
            c0260r1.f874v3 = this.f874v3;
            return c0260r1;
        }
        Vector vector = new Vector();
        for (int i = 0; i != this.f874v3.size(); i++) {
            vector.addElement(this.f874v3.elementAt(i));
        }
        C0260r1 c0260r12 = new C0260r1();
        c0260r12.f874v3 = vector;
        c0260r12.m23575p();
        return c0260r12;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: l */
    public AbstractC0258r mo23005l() {
        C0139d2 c0139d2 = new C0139d2();
        c0139d2.f874v3 = this.f874v3;
        return c0139d2;
    }

    /* renamed from: m */
    public Enumeration m23578m() {
        return this.f874v3.elements();
    }

    /* renamed from: n */
    public InterfaceC0358v m23577n() {
        return new C0339a(this);
    }

    /* renamed from: o */
    public int m23576o() {
        return this.f874v3.size();
    }

    /* renamed from: p */
    public void m23575p() {
        if (this.f875w3) {
            return;
        }
        this.f875w3 = true;
        if (this.f874v3.size() > 1) {
            int size = this.f874v3.size() - 1;
            boolean z = true;
            while (z) {
                int i = 0;
                byte[] m23583a = m23583a((InterfaceC0136d) this.f874v3.elementAt(0));
                z = false;
                int i2 = 0;
                while (i != size) {
                    int i3 = i + 1;
                    byte[] m23583a2 = m23583a((InterfaceC0136d) this.f874v3.elementAt(i3));
                    if (m23579a(m23583a, m23583a2)) {
                        m23583a = m23583a2;
                    } else {
                        Object elementAt = this.f874v3.elementAt(i);
                        Vector vector = this.f874v3;
                        vector.setElementAt(vector.elementAt(i3), i);
                        this.f874v3.setElementAt(elementAt, i3);
                        z = true;
                        i2 = i;
                    }
                    i = i3;
                }
                size = i2;
            }
        }
    }

    /* renamed from: q */
    public InterfaceC0136d[] m23574q() {
        InterfaceC0136d[] interfaceC0136dArr = new InterfaceC0136d[m23576o()];
        for (int i = 0; i != m23576o(); i++) {
            interfaceC0136dArr[i] = m23584a(i);
        }
        return interfaceC0136dArr;
    }

    public String toString() {
        return this.f874v3.toString();
    }

    public AbstractC0338u(InterfaceC0136d interfaceC0136d) {
        Vector vector = new Vector();
        this.f874v3 = vector;
        this.f875w3 = false;
        vector.addElement(interfaceC0136d);
    }

    /* renamed from: a */
    public static AbstractC0338u m23582a(AbstractC0494y abstractC0494y, boolean z) {
        if (z) {
            if (abstractC0494y.m23002o()) {
                return (AbstractC0338u) abstractC0494y.m23004m();
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        } else if (abstractC0494y.m23002o()) {
            if (abstractC0494y instanceof C0235p0) {
                return new C0179n0(abstractC0494y.m23004m());
            }
            return new C0139d2(abstractC0494y.m23004m());
        } else if (abstractC0494y.m23004m() instanceof AbstractC0338u) {
            return (AbstractC0338u) abstractC0494y.m23004m();
        } else {
            if (abstractC0494y.m23004m() instanceof AbstractC0263s) {
                AbstractC0263s abstractC0263s = (AbstractC0263s) abstractC0494y.m23004m();
                if (abstractC0494y instanceof C0235p0) {
                    return new C0179n0(abstractC0263s.m23744p());
                }
                return new C0139d2(abstractC0263s.m23744p());
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + abstractC0494y.getClass().getName());
        }
    }

    /* renamed from: a */
    public InterfaceC0136d m23584a(int i) {
        return (InterfaceC0136d) this.f874v3.elementAt(i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof AbstractC0338u) {
            AbstractC0338u abstractC0338u = (AbstractC0338u) abstractC0258r;
            if (m23576o() != abstractC0338u.m23576o()) {
                return false;
            }
            Enumeration m23578m = m23578m();
            Enumeration m23578m2 = abstractC0338u.m23578m();
            while (m23578m.hasMoreElements()) {
                InterfaceC0136d m23580a = m23580a(m23578m);
                InterfaceC0136d m23580a2 = m23580a(m23578m2);
                AbstractC0258r mo23015d = m23580a.mo23015d();
                AbstractC0258r mo23015d2 = m23580a2.mo23015d();
                if (mo23015d != mo23015d2 && !mo23015d.equals(mo23015d2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private InterfaceC0136d m23580a(Enumeration enumeration) {
        InterfaceC0136d interfaceC0136d = (InterfaceC0136d) enumeration.nextElement();
        return interfaceC0136d == null ? C0156h1.f189v3 : interfaceC0136d;
    }

    /* renamed from: a */
    private boolean m23579a(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        for (int i = 0; i != min; i++) {
            if (bArr[i] != bArr2[i]) {
                return (bArr[i] & 255) < (bArr2[i] & 255);
            }
        }
        return min == bArr.length;
    }

    /* renamed from: a */
    private byte[] m23583a(InterfaceC0136d interfaceC0136d) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new C0252q(byteArrayOutputStream).mo23772a(interfaceC0136d);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    public AbstractC0338u(C0140e c0140e, boolean z) {
        this.f874v3 = new Vector();
        this.f875w3 = false;
        for (int i = 0; i != c0140e.m24172a(); i++) {
            this.f874v3.addElement(c0140e.m24171a(i));
        }
        if (z) {
            m23575p();
        }
    }

    public AbstractC0338u(InterfaceC0136d[] interfaceC0136dArr, boolean z) {
        this.f874v3 = new Vector();
        this.f875w3 = false;
        for (int i = 0; i != interfaceC0136dArr.length; i++) {
            this.f874v3.addElement(interfaceC0136dArr[i]);
        }
        if (z) {
            m23575p();
        }
    }
}
