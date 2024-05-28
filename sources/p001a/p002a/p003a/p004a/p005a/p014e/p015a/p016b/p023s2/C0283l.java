package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0430s1;

/* renamed from: a.a.a.a.a.e.a.b.s2.l */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0283l extends AbstractC0174m {

    /* renamed from: v3 */
    public C0364d f614v3;

    /* renamed from: w3 */
    public C0166k f615w3;

    public C0283l(AbstractC0263s abstractC0263s) {
        this.f614v3 = C0364d.m23537a(abstractC0263s.mo23751a(0));
        this.f615w3 = (C0166k) abstractC0263s.mo23751a(1);
    }

    /* renamed from: a */
    public static C0283l m23677a(Object obj) {
        if (obj instanceof C0283l) {
            return (C0283l) obj;
        }
        if (obj != null) {
            return new C0283l(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f614v3);
        c0140e.m24170a(this.f615w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0166k m23676i() {
        return this.f615w3;
    }

    /* renamed from: j */
    public C0364d m23675j() {
        return this.f614v3;
    }

    public C0283l(C0430s1 c0430s1, BigInteger bigInteger) {
        this.f614v3 = C0364d.m23537a(c0430s1.mo23015d());
        this.f615w3 = new C0166k(bigInteger);
    }

    public C0283l(C0430s1 c0430s1, C0166k c0166k) {
        this.f614v3 = C0364d.m23537a(c0430s1.mo23015d());
        this.f615w3 = c0166k;
    }

    public C0283l(C0364d c0364d, BigInteger bigInteger) {
        this.f614v3 = c0364d;
        this.f615w3 = new C0166k(bigInteger);
    }
}
