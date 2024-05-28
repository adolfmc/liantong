package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.util.Enumeration;
import java.util.Hashtable;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.w2.p0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0420p0 extends AbstractC0174m {

    /* renamed from: v3 */
    public AbstractC0263s f1235v3;

    public C0420p0(AbstractC0263s abstractC0263s) {
        this.f1235v3 = null;
        this.f1235v3 = abstractC0263s;
    }

    /* renamed from: a */
    public static C0420p0 m23212a(Object obj) {
        if (obj instanceof C0420p0) {
            return (C0420p0) obj;
        }
        if (obj != null) {
            return new C0420p0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1235v3;
    }

    public C0420p0(Hashtable hashtable) {
        this.f1235v3 = null;
        C0140e c0140e = new C0140e();
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            C0140e c0140e2 = new C0140e();
            c0140e2.m24170a(new C0178n(str));
            c0140e2.m24170a(new C0178n((String) hashtable.get(str)));
            c0140e.m24170a(new C0184o1(c0140e2));
        }
        this.f1235v3 = new C0184o1(c0140e);
    }

    public C0420p0(C0413n c0413n, C0413n c0413n2) {
        this.f1235v3 = null;
        C0140e c0140e = new C0140e();
        c0140e.m24170a(c0413n);
        c0140e.m24170a(c0413n2);
        this.f1235v3 = new C0184o1(new C0184o1(c0140e));
    }

    public C0420p0(C0413n[] c0413nArr, C0413n[] c0413nArr2) {
        this.f1235v3 = null;
        C0140e c0140e = new C0140e();
        for (int i = 0; i != c0413nArr.length; i++) {
            C0140e c0140e2 = new C0140e();
            c0140e2.m24170a(c0413nArr[i]);
            c0140e2.m24170a(c0413nArr2[i]);
            c0140e.m24170a(new C0184o1(c0140e2));
        }
        this.f1235v3 = new C0184o1(c0140e);
    }
}
