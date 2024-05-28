package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* renamed from: a.a.a.a.a.e.a.b.s */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0263s extends AbstractC0258r {

    /* renamed from: v3 */
    public Vector f552v3;

    /* renamed from: a.a.a.a.a.e.a.b.s$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0264a implements InterfaceC0298t {

        /* renamed from: v3 */
        public final int f553v3;

        /* renamed from: w3 */
        public int f554w3;

        /* renamed from: x3 */
        public final /* synthetic */ AbstractC0263s f555x3;

        public C0264a(AbstractC0263s abstractC0263s) {
            this.f555x3 = abstractC0263s;
            this.f553v3 = AbstractC0263s.this.mo23745o();
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0298t
        /* renamed from: a */
        public InterfaceC0136d mo23612a() {
            int i = this.f554w3;
            if (i == this.f553v3) {
                return null;
            }
            AbstractC0263s abstractC0263s = AbstractC0263s.this;
            this.f554w3 = i + 1;
            InterfaceC0136d mo23751a = abstractC0263s.mo23751a(i);
            if (mo23751a instanceof AbstractC0263s) {
                return ((AbstractC0263s) mo23751a).m23746n();
            }
            return mo23751a instanceof AbstractC0338u ? ((AbstractC0338u) mo23751a).m23577n() : mo23751a;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
        /* renamed from: b */
        public AbstractC0258r mo23007b() {
            return this.f555x3;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
        /* renamed from: d */
        public AbstractC0258r mo23015d() {
            return this.f555x3;
        }
    }

    public AbstractC0263s() {
        this.f552v3 = new Vector();
    }

    /* renamed from: a */
    public static AbstractC0263s m23749a(Object obj) {
        if (obj != null && !(obj instanceof AbstractC0263s)) {
            if (obj instanceof InterfaceC0298t) {
                return m23749a((Object) ((InterfaceC0298t) obj).mo23015d());
            }
            if (obj instanceof byte[]) {
                try {
                    return m23749a((Object) AbstractC0258r.m23755a((byte[]) obj));
                } catch (IOException e) {
                    throw new IllegalArgumentException("failed to construct sequence from byte[]: " + e.getMessage());
                }
            }
            if (obj instanceof InterfaceC0136d) {
                AbstractC0258r mo23015d = ((InterfaceC0136d) obj).mo23015d();
                if (mo23015d instanceof AbstractC0263s) {
                    return (AbstractC0263s) mo23015d;
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        return (AbstractC0263s) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public abstract void mo22982a(C0252q c0252q);

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        Enumeration mo23747m = mo23747m();
        int mo23745o = mo23745o();
        while (mo23747m.hasMoreElements()) {
            mo23745o = (mo23745o * 17) ^ m23748a(mo23747m).hashCode();
        }
        return mo23745o;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return true;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: k */
    public AbstractC0258r mo23006k() {
        C0184o1 c0184o1 = new C0184o1();
        c0184o1.f552v3 = this.f552v3;
        return c0184o1;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: l */
    public AbstractC0258r mo23005l() {
        C0135c2 c0135c2 = new C0135c2();
        c0135c2.f552v3 = this.f552v3;
        return c0135c2;
    }

    /* renamed from: m */
    public Enumeration mo23747m() {
        return this.f552v3.elements();
    }

    /* renamed from: n */
    public InterfaceC0298t m23746n() {
        return new C0264a(this);
    }

    /* renamed from: o */
    public int mo23745o() {
        return this.f552v3.size();
    }

    /* renamed from: p */
    public InterfaceC0136d[] m23744p() {
        InterfaceC0136d[] interfaceC0136dArr = new InterfaceC0136d[mo23745o()];
        for (int i = 0; i != mo23745o(); i++) {
            interfaceC0136dArr[i] = mo23751a(i);
        }
        return interfaceC0136dArr;
    }

    public String toString() {
        return this.f552v3.toString();
    }

    public AbstractC0263s(InterfaceC0136d interfaceC0136d) {
        Vector vector = new Vector();
        this.f552v3 = vector;
        vector.addElement(interfaceC0136d);
    }

    /* renamed from: a */
    public static AbstractC0263s m23750a(AbstractC0494y abstractC0494y, boolean z) {
        if (z) {
            if (abstractC0494y.m23002o()) {
                return m23749a((Object) abstractC0494y.m23004m().mo23015d());
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        } else if (abstractC0494y.m23002o()) {
            if (abstractC0494y instanceof C0235p0) {
                return new C0167k0(abstractC0494y.m23004m());
            }
            return new C0135c2(abstractC0494y.m23004m());
        } else if (abstractC0494y.m23004m() instanceof AbstractC0263s) {
            return (AbstractC0263s) abstractC0494y.m23004m();
        } else {
            throw new IllegalArgumentException("unknown object in getInstance: " + abstractC0494y.getClass().getName());
        }
    }

    /* renamed from: a */
    public InterfaceC0136d mo23751a(int i) {
        return (InterfaceC0136d) this.f552v3.elementAt(i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof AbstractC0263s) {
            AbstractC0263s abstractC0263s = (AbstractC0263s) abstractC0258r;
            if (mo23745o() != abstractC0263s.mo23745o()) {
                return false;
            }
            Enumeration mo23747m = mo23747m();
            Enumeration mo23747m2 = abstractC0263s.mo23747m();
            while (mo23747m.hasMoreElements()) {
                InterfaceC0136d m23748a = m23748a(mo23747m);
                InterfaceC0136d m23748a2 = m23748a(mo23747m2);
                AbstractC0258r mo23015d = m23748a.mo23015d();
                AbstractC0258r mo23015d2 = m23748a2.mo23015d();
                if (mo23015d != mo23015d2 && !mo23015d.equals(mo23015d2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private InterfaceC0136d m23748a(Enumeration enumeration) {
        return (InterfaceC0136d) enumeration.nextElement();
    }

    public AbstractC0263s(C0140e c0140e) {
        this.f552v3 = new Vector();
        for (int i = 0; i != c0140e.m24172a(); i++) {
            this.f552v3.addElement(c0140e.m24171a(i));
        }
    }

    public AbstractC0263s(InterfaceC0136d[] interfaceC0136dArr) {
        this.f552v3 = new Vector();
        for (int i = 0; i != interfaceC0136dArr.length; i++) {
            this.f552v3.addElement(interfaceC0136dArr[i]);
        }
    }
}
