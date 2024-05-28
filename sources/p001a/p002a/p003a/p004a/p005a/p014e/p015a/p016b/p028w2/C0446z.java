package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.z */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0446z extends AbstractC0174m {

    /* renamed from: v3 */
    public Hashtable f1471v3;

    /* renamed from: w3 */
    public Vector f1472w3;

    public C0446z(AbstractC0263s abstractC0263s) {
        this.f1471v3 = new Hashtable();
        this.f1472w3 = new Vector();
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            C0444y m23108a = C0444y.m23108a(mo23747m.nextElement());
            this.f1471v3.put(m23108a.m23107i(), m23108a);
            this.f1472w3.addElement(m23108a.m23107i());
        }
    }

    /* renamed from: a */
    public static C0446z m23094a(AbstractC0494y abstractC0494y, boolean z) {
        return m23093a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    /* renamed from: b */
    public InterfaceC0136d m23090b(C0178n c0178n) {
        C0444y m23096a = m23096a(c0178n);
        if (m23096a != null) {
            return m23096a.m23105k();
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        Enumeration elements = this.f1472w3.elements();
        while (elements.hasMoreElements()) {
            c0140e.m24170a((C0444y) this.f1471v3.get((C0178n) elements.nextElement()));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n[] m23089i() {
        return m23091a(true);
    }

    /* renamed from: j */
    public C0178n[] m23088j() {
        return m23092a(this.f1472w3);
    }

    /* renamed from: k */
    public C0178n[] m23087k() {
        return m23091a(false);
    }

    /* renamed from: l */
    public Enumeration m23086l() {
        return this.f1472w3.elements();
    }

    /* renamed from: a */
    public static C0446z m23093a(Object obj) {
        if (obj instanceof C0446z) {
            return (C0446z) obj;
        }
        if (obj != null) {
            return new C0446z(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    /* renamed from: a */
    public C0444y m23096a(C0178n c0178n) {
        return (C0444y) this.f1471v3.get(c0178n);
    }

    /* renamed from: a */
    public boolean m23095a(C0446z c0446z) {
        if (this.f1471v3.size() != c0446z.f1471v3.size()) {
            return false;
        }
        Enumeration keys = this.f1471v3.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            if (!this.f1471v3.get(nextElement).equals(c0446z.f1471v3.get(nextElement))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private C0178n[] m23091a(boolean z) {
        Vector vector = new Vector();
        for (int i = 0; i != this.f1472w3.size(); i++) {
            Object elementAt = this.f1472w3.elementAt(i);
            if (((C0444y) this.f1471v3.get(elementAt)).m23104l() == z) {
                vector.addElement(elementAt);
            }
        }
        return m23092a(vector);
    }

    /* renamed from: a */
    private C0178n[] m23092a(Vector vector) {
        int size = vector.size();
        C0178n[] c0178nArr = new C0178n[size];
        for (int i = 0; i != size; i++) {
            c0178nArr[i] = (C0178n) vector.elementAt(i);
        }
        return c0178nArr;
    }

    public C0446z(C0444y c0444y) {
        this.f1471v3 = new Hashtable();
        Vector vector = new Vector();
        this.f1472w3 = vector;
        vector.addElement(c0444y.m23107i());
        this.f1471v3.put(c0444y.m23107i(), c0444y);
    }

    public C0446z(C0444y[] c0444yArr) {
        this.f1471v3 = new Hashtable();
        this.f1472w3 = new Vector();
        for (int i = 0; i != c0444yArr.length; i++) {
            C0444y c0444y = c0444yArr[i];
            this.f1472w3.addElement(c0444y.m23107i());
            this.f1471v3.put(c0444y.m23107i(), c0444y);
        }
    }
}
