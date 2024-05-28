package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0415n1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0416o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0430s1;

/* renamed from: a.a.a.a.a.e.a.b.o2.u */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0225u extends AbstractC0174m {

    /* renamed from: v3 */
    public C0364d f372v3;

    /* renamed from: w3 */
    public C0166k f373w3;

    public C0225u(AbstractC0263s abstractC0263s) {
        this.f372v3 = C0364d.m23537a(abstractC0263s.mo23751a(0));
        this.f373w3 = (C0166k) abstractC0263s.mo23751a(1);
    }

    /* renamed from: a */
    public static C0225u m23890a(Object obj) {
        if (obj instanceof C0225u) {
            return (C0225u) obj;
        }
        if (obj != null) {
            return new C0225u(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f372v3);
        c0140e.m24170a(this.f373w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0364d m23889i() {
        return this.f372v3;
    }

    /* renamed from: j */
    public C0166k m23888j() {
        return this.f373w3;
    }

    public C0225u(C0416o c0416o) {
        this.f372v3 = c0416o.m23236j();
        this.f373w3 = c0416o.m23235k();
    }

    public C0225u(C0415n1 c0415n1) {
        this.f372v3 = c0415n1.m23248j();
        this.f373w3 = c0415n1.m23247k();
    }

    public C0225u(C0364d c0364d, BigInteger bigInteger) {
        this.f372v3 = c0364d;
        this.f373w3 = new C0166k(bigInteger);
    }

    public C0225u(C0430s1 c0430s1, BigInteger bigInteger) {
        this.f372v3 = C0364d.m23537a(c0430s1);
        this.f373w3 = new C0166k(bigInteger);
    }

    public C0225u(C0430s1 c0430s1, C0166k c0166k) {
        this.f372v3 = C0364d.m23537a(c0430s1);
        this.f373w3 = c0166k;
    }
}
