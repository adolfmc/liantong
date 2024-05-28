package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0452x;

/* renamed from: a.a.a.a.a.e.a.b.w2.v0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0438v0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0381c0 f1404v3;

    /* renamed from: w3 */
    public C0378b0 f1405w3;

    public C0438v0(C0381c0 c0381c0, C0378b0 c0378b0) {
        if (c0378b0 != null && c0378b0.m23448f() == 6 && !((InterfaceC0452x) c0378b0.m23447i()).mo22978e().equals("")) {
            this.f1404v3 = c0381c0;
            this.f1405w3 = c0378b0;
            return;
        }
        throw new IllegalArgumentException("the role name MUST be non empty and MUST use the URI option of GeneralName");
    }

    /* renamed from: a */
    public static C0438v0 m23134a(Object obj) {
        if (obj instanceof C0438v0) {
            return (C0438v0) obj;
        }
        if (obj != null) {
            return new C0438v0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0381c0 c0381c0 = this.f1404v3;
        if (c0381c0 != null) {
            c0140e.m24170a(new C0360v1(false, 0, c0381c0));
        }
        c0140e.m24170a(new C0360v1(true, 1, this.f1405w3));
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0381c0 m23133i() {
        return this.f1404v3;
    }

    /* renamed from: j */
    public String[] m23132j() {
        C0381c0 c0381c0 = this.f1404v3;
        if (c0381c0 == null) {
            return new String[0];
        }
        C0378b0[] m23426i = c0381c0.m23426i();
        String[] strArr = new String[m23426i.length];
        for (int i = 0; i < m23426i.length; i++) {
            InterfaceC0136d m23447i = m23426i[i].m23447i();
            if (m23447i instanceof InterfaceC0452x) {
                strArr[i] = ((InterfaceC0452x) m23447i).mo22978e();
            } else {
                strArr[i] = m23447i.toString();
            }
        }
        return strArr;
    }

    /* renamed from: k */
    public C0378b0 m23131k() {
        return this.f1405w3;
    }

    /* renamed from: l */
    public String m23130l() {
        return ((InterfaceC0452x) this.f1405w3.m23447i()).mo22978e();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Name: " + m23130l() + " - Auth: ");
        C0381c0 c0381c0 = this.f1404v3;
        if (c0381c0 != null && c0381c0.m23426i().length != 0) {
            String[] m23132j = m23132j();
            stringBuffer.append('[');
            stringBuffer.append(m23132j[0]);
            for (int i = 1; i < m23132j.length; i++) {
                stringBuffer.append(", ");
                stringBuffer.append(m23132j[i]);
            }
            stringBuffer.append(']');
        } else {
            stringBuffer.append("N/A");
        }
        return stringBuffer.toString();
    }

    public C0438v0(C0378b0 c0378b0) {
        this(null, c0378b0);
    }

    public C0438v0(String str) {
        this(new C0378b0(6, str == null ? "" : str));
    }

    public C0438v0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() >= 1 && abstractC0263s.mo23745o() <= 2) {
            for (int i = 0; i != abstractC0263s.mo23745o(); i++) {
                AbstractC0494y m23008a = AbstractC0494y.m23008a(abstractC0263s.mo23751a(i));
                int mo22994f = m23008a.mo22994f();
                if (mo22994f == 0) {
                    this.f1404v3 = C0381c0.m23428a(m23008a, false);
                } else if (mo22994f == 1) {
                    this.f1405w3 = C0378b0.m23456a(m23008a, true);
                } else {
                    throw new IllegalArgumentException("Unknown tag in RoleSyntax");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
