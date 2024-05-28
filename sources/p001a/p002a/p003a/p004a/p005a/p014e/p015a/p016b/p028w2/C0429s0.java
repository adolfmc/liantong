package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0138d1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;

/* renamed from: a.a.a.a.a.e.a.b.w2.s0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0429s0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0138d1 f1315v3;

    /* renamed from: w3 */
    public C0138d1 f1316w3;

    public C0429s0(AbstractC0263s abstractC0263s) {
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            AbstractC0494y abstractC0494y = (AbstractC0494y) mo23747m.nextElement();
            if (abstractC0494y.mo22994f() == 0) {
                this.f1315v3 = C0138d1.m24180a(abstractC0494y, false);
            } else if (abstractC0494y.mo22994f() == 1) {
                this.f1316w3 = C0138d1.m24180a(abstractC0494y, false);
            }
        }
    }

    /* renamed from: a */
    public static C0429s0 m23175a(Object obj) {
        if (obj instanceof C0429s0) {
            return (C0429s0) obj;
        }
        if (obj != null) {
            return new C0429s0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        C0138d1 c0138d1 = this.f1315v3;
        if (c0138d1 != null) {
            c0140e.m24170a(new C0360v1(false, 0, c0138d1));
        }
        C0138d1 c0138d12 = this.f1316w3;
        if (c0138d12 != null) {
            c0140e.m24170a(new C0360v1(false, 1, c0138d12));
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0138d1 m23174i() {
        return this.f1316w3;
    }

    /* renamed from: j */
    public C0138d1 m23173j() {
        return this.f1315v3;
    }
}
