package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0378b0;

/* renamed from: a.a.a.a.a.e.a.b.p2.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0239c extends AbstractC0174m {

    /* renamed from: v3 */
    public C0378b0 f416v3;

    /* renamed from: w3 */
    public C0166k f417w3;

    public C0239c(AbstractC0263s abstractC0263s) {
        this.f416v3 = C0378b0.m23455a(abstractC0263s.mo23751a(0));
        this.f417w3 = C0151g1.m24147a(abstractC0263s.mo23751a(1));
    }

    /* renamed from: a */
    public static C0239c m23837a(Object obj) {
        if (obj instanceof C0239c) {
            return (C0239c) obj;
        }
        if (obj != null) {
            return new C0239c(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f416v3);
        c0140e.m24170a(this.f417w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0378b0 m23836i() {
        return this.f416v3;
    }

    /* renamed from: j */
    public C0166k m23835j() {
        return this.f417w3;
    }

    public C0239c(C0378b0 c0378b0, BigInteger bigInteger) {
        this(c0378b0, new C0166k(bigInteger));
    }

    public C0239c(C0378b0 c0378b0, C0166k c0166k) {
        this.f416v3 = c0378b0;
        this.f417w3 = c0166k;
    }

    /* renamed from: a */
    public static C0239c m23838a(AbstractC0494y abstractC0494y, boolean z) {
        return m23837a(AbstractC0263s.m23750a(abstractC0494y, z));
    }
}
