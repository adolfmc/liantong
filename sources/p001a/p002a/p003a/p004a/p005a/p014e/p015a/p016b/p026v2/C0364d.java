package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.p027g.C0368b;

/* renamed from: a.a.a.a.a.e.a.b.v2.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0364d extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: z3 */
    public static InterfaceC0366f f935z3 = C0368b.f955a;

    /* renamed from: v3 */
    public boolean f936v3;

    /* renamed from: w3 */
    public int f937w3;

    /* renamed from: x3 */
    public InterfaceC0366f f938x3;

    /* renamed from: y3 */
    public C0363c[] f939y3;

    public C0364d(InterfaceC0366f interfaceC0366f, C0364d c0364d) {
        this.f939y3 = c0364d.f939y3;
        this.f938x3 = interfaceC0366f;
    }

    /* renamed from: a */
    public static C0364d m23538a(AbstractC0494y abstractC0494y, boolean z) {
        return m23537a(AbstractC0263s.m23750a(abstractC0494y, true));
    }

    /* renamed from: k */
    public static InterfaceC0366f m23534k() {
        return f935z3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return new C0184o1(this.f939y3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof C0364d) || (obj instanceof AbstractC0263s)) {
            if (mo23015d().equals(((InterfaceC0136d) obj).mo23015d())) {
                return true;
            }
            try {
                return this.f938x3.mo23521a(this, new C0364d(AbstractC0263s.m23749a((Object) ((InterfaceC0136d) obj).mo23015d())));
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        if (this.f936v3) {
            return this.f937w3;
        }
        this.f936v3 = true;
        int mo23517b = this.f938x3.mo23517b(this);
        this.f937w3 = mo23517b;
        return mo23517b;
    }

    /* renamed from: i */
    public C0178n[] m23536i() {
        int i = 0;
        int i2 = 0;
        while (true) {
            C0363c[] c0363cArr = this.f939y3;
            if (i == c0363cArr.length) {
                break;
            }
            i2 += c0363cArr[i].m23542l();
            i++;
        }
        C0178n[] c0178nArr = new C0178n[i2];
        int i3 = 0;
        int i4 = 0;
        while (true) {
            C0363c[] c0363cArr2 = this.f939y3;
            if (i3 == c0363cArr2.length) {
                return c0178nArr;
            }
            C0363c c0363c = c0363cArr2[i3];
            if (c0363c.m23543k()) {
                C0361a[] m23544j = c0363c.m23544j();
                int i5 = i4;
                int i6 = 0;
                while (i6 != m23544j.length) {
                    c0178nArr[i5] = m23544j[i6].m23550i();
                    i6++;
                    i5++;
                }
                i4 = i5;
            } else if (c0363c.m23542l() != 0) {
                c0178nArr[i4] = c0363c.m23545i().m23550i();
                i4++;
            }
            i3++;
        }
    }

    /* renamed from: j */
    public C0363c[] m23535j() {
        C0363c[] c0363cArr = this.f939y3;
        int length = c0363cArr.length;
        C0363c[] c0363cArr2 = new C0363c[length];
        System.arraycopy(c0363cArr, 0, c0363cArr2, 0, length);
        return c0363cArr2;
    }

    public String toString() {
        return this.f938x3.mo23522a(this);
    }

    /* renamed from: a */
    public static C0364d m23537a(Object obj) {
        if (obj instanceof C0364d) {
            return (C0364d) obj;
        }
        if (obj != null) {
            return new C0364d(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0364d(AbstractC0263s abstractC0263s) {
        this(f935z3, abstractC0263s);
    }

    public C0364d(InterfaceC0366f interfaceC0366f, AbstractC0263s abstractC0263s) {
        this.f938x3 = interfaceC0366f;
        this.f939y3 = new C0363c[abstractC0263s.mo23745o()];
        Enumeration mo23747m = abstractC0263s.mo23747m();
        int i = 0;
        while (mo23747m.hasMoreElements()) {
            this.f939y3[i] = C0363c.m23546a(mo23747m.nextElement());
            i++;
        }
    }

    /* renamed from: a */
    public static C0364d m23539a(InterfaceC0366f interfaceC0366f, Object obj) {
        if (obj instanceof C0364d) {
            return m23539a(interfaceC0366f, ((C0364d) obj).mo23015d());
        }
        if (obj != null) {
            return new C0364d(interfaceC0366f, AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0364d(C0363c[] c0363cArr) {
        this(f935z3, c0363cArr);
    }

    public C0364d(InterfaceC0366f interfaceC0366f, C0363c[] c0363cArr) {
        this.f939y3 = c0363cArr;
        this.f938x3 = interfaceC0366f;
    }

    /* renamed from: a */
    public C0363c[] m23541a(C0178n c0178n) {
        C0363c[] c0363cArr = new C0363c[this.f939y3.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            C0363c[] c0363cArr2 = this.f939y3;
            if (i != c0363cArr2.length) {
                C0363c c0363c = c0363cArr2[i];
                if (c0363c.m23543k()) {
                    C0361a[] m23544j = c0363c.m23544j();
                    int i3 = 0;
                    while (true) {
                        if (i3 == m23544j.length) {
                            break;
                        } else if (m23544j[i3].m23550i().equals(c0178n)) {
                            c0363cArr[i2] = c0363c;
                            i2++;
                            break;
                        } else {
                            i3++;
                        }
                    }
                } else if (c0363c.m23545i().m23550i().equals(c0178n)) {
                    c0363cArr[i2] = c0363c;
                    i2++;
                }
                i++;
            } else {
                C0363c[] c0363cArr3 = new C0363c[i2];
                System.arraycopy(c0363cArr, 0, c0363cArr3, 0, i2);
                return c0363cArr3;
            }
        }
    }

    public C0364d(String str) {
        this(f935z3, str);
    }

    public C0364d(InterfaceC0366f interfaceC0366f, String str) {
        this(interfaceC0366f.mo23520a(str));
        this.f938x3 = interfaceC0366f;
    }

    /* renamed from: a */
    public static void m23540a(InterfaceC0366f interfaceC0366f) {
        if (interfaceC0366f != null) {
            f935z3 = interfaceC0366f;
            return;
        }
        throw new NullPointerException("cannot set style to null");
    }
}
