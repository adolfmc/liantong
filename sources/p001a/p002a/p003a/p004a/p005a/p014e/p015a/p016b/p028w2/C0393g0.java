package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import java.util.Vector;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0496y1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.g0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0393g0 extends AbstractC0174m {

    /* renamed from: A3 */
    public static final int f1074A3 = 3;

    /* renamed from: y3 */
    public static final int f1075y3 = 1;

    /* renamed from: z3 */
    public static final int f1076z3 = 2;

    /* renamed from: v3 */
    public C0381c0 f1077v3;

    /* renamed from: w3 */
    public Vector f1078w3 = new Vector();

    /* renamed from: x3 */
    public int f1079x3;

    public C0393g0(AbstractC0263s abstractC0263s) {
        int i;
        this.f1077v3 = null;
        this.f1079x3 = -1;
        int i2 = 0;
        if (abstractC0263s.mo23751a(0) instanceof AbstractC0494y) {
            this.f1077v3 = C0381c0.m23428a((AbstractC0494y) abstractC0263s.mo23751a(0), false);
            i2 = 1;
        } else if (abstractC0263s.mo23745o() == 2) {
            this.f1077v3 = C0381c0.m23427a(abstractC0263s.mo23751a(0));
            i2 = 1;
        }
        if (abstractC0263s.mo23751a(i2) instanceof AbstractC0263s) {
            Enumeration mo23747m = ((AbstractC0263s) abstractC0263s.mo23751a(i2)).mo23747m();
            while (mo23747m.hasMoreElements()) {
                AbstractC0258r abstractC0258r = (AbstractC0258r) mo23747m.nextElement();
                if (abstractC0258r instanceof C0178n) {
                    i = 2;
                } else if (abstractC0258r instanceof C0496y1) {
                    i = 3;
                } else if (!(abstractC0258r instanceof C0168k1)) {
                    throw new IllegalArgumentException("Bad value type encoding IetfAttrSyntax");
                } else {
                    i = 1;
                }
                if (this.f1079x3 < 0) {
                    this.f1079x3 = i;
                }
                if (i == this.f1079x3) {
                    this.f1078w3.addElement(abstractC0258r);
                } else {
                    throw new IllegalArgumentException("Mix of value types in IetfAttrSyntax");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Non-IetfAttrSyntax encoding");
    }

    /* renamed from: a */
    public static C0393g0 m23379a(Object obj) {
        if (obj instanceof C0393g0) {
            return (C0393g0) obj;
        }
        if (obj != null) {
            return new C0393g0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0381c0 c0381c0 = this.f1077v3;
        if (c0381c0 != null) {
            c0140e.m24170a(new C0360v1(0, c0381c0));
        }
        C0140e c0140e2 = new C0140e();
        Enumeration elements = this.f1078w3.elements();
        while (elements.hasMoreElements()) {
            c0140e2.m24170a((InterfaceC0136d) elements.nextElement());
        }
        c0140e.m24170a(new C0184o1(c0140e2));
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0381c0 m23378i() {
        return this.f1077v3;
    }

    /* renamed from: j */
    public int m23377j() {
        return this.f1079x3;
    }

    /* renamed from: k */
    public Object[] m23376k() {
        int i = 0;
        if (m23377j() == 1) {
            int size = this.f1078w3.size();
            AbstractC0182o[] abstractC0182oArr = new AbstractC0182o[size];
            while (i != size) {
                abstractC0182oArr[i] = (AbstractC0182o) this.f1078w3.elementAt(i);
                i++;
            }
            return abstractC0182oArr;
        } else if (m23377j() == 2) {
            int size2 = this.f1078w3.size();
            C0178n[] c0178nArr = new C0178n[size2];
            while (i != size2) {
                c0178nArr[i] = (C0178n) this.f1078w3.elementAt(i);
                i++;
            }
            return c0178nArr;
        } else {
            int size3 = this.f1078w3.size();
            C0496y1[] c0496y1Arr = new C0496y1[size3];
            while (i != size3) {
                c0496y1Arr[i] = (C0496y1) this.f1078w3.elementAt(i);
                i++;
            }
            return c0496y1Arr;
        }
    }
}
