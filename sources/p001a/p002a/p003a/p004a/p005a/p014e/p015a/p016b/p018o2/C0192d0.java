package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0359v0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.o2.d0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0192d0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0377b f254v3;

    /* renamed from: w3 */
    public C0359v0 f255w3;

    public C0192d0(C0377b c0377b, byte[] bArr) {
        this.f254v3 = c0377b;
        this.f255w3 = new C0359v0(bArr);
    }

    /* renamed from: a */
    public static C0192d0 m24043a(AbstractC0494y abstractC0494y, boolean z) {
        return m24042a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f254v3);
        c0140e.m24170a(this.f255w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0377b m24041i() {
        return this.f254v3;
    }

    /* renamed from: j */
    public C0359v0 m24040j() {
        return this.f255w3;
    }

    /* renamed from: a */
    public static C0192d0 m24042a(Object obj) {
        if (obj != null && !(obj instanceof C0192d0)) {
            if (obj instanceof AbstractC0263s) {
                return new C0192d0((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid OriginatorPublicKey: " + obj.getClass().getName());
        }
        return (C0192d0) obj;
    }

    public C0192d0(AbstractC0263s abstractC0263s) {
        this.f254v3 = C0377b.m23460a(abstractC0263s.mo23751a(0));
        this.f255w3 = (C0359v0) abstractC0263s.mo23751a(1);
    }
}
