package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;

/* renamed from: a.a.a.a.a.e.a.b.o2.i0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0202i0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0231x f301v3;

    /* renamed from: w3 */
    public AbstractC0182o f302w3;

    public C0202i0(AbstractC0263s abstractC0263s) {
        this.f301v3 = C0231x.m23860a(abstractC0263s.mo23751a(0));
        this.f302w3 = (AbstractC0182o) abstractC0263s.mo23751a(1);
    }

    /* renamed from: a */
    public static C0202i0 m23994a(AbstractC0494y abstractC0494y, boolean z) {
        return m23993a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f301v3);
        c0140e.m24170a(this.f302w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0182o m23992i() {
        return this.f302w3;
    }

    /* renamed from: j */
    public C0231x m23991j() {
        return this.f301v3;
    }

    /* renamed from: a */
    public static C0202i0 m23993a(Object obj) {
        if (obj != null && !(obj instanceof C0202i0)) {
            if (obj instanceof AbstractC0263s) {
                return new C0202i0((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid RecipientEncryptedKey: " + obj.getClass().getName());
        }
        return (C0202i0) obj;
    }

    public C0202i0(C0231x c0231x, AbstractC0182o abstractC0182o) {
        this.f301v3 = c0231x;
        this.f302w3 = abstractC0182o;
    }
}
