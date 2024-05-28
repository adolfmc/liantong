package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.o2.w */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0229w extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f388v3;

    /* renamed from: w3 */
    public C0227v f389w3;

    /* renamed from: x3 */
    public C0377b f390x3;

    /* renamed from: y3 */
    public AbstractC0182o f391y3;

    public C0229w(C0227v c0227v, C0377b c0377b, AbstractC0182o abstractC0182o) {
        this.f388v3 = new C0166k(4L);
        this.f389w3 = c0227v;
        this.f390x3 = c0377b;
        this.f391y3 = abstractC0182o;
    }

    /* renamed from: a */
    public static C0229w m23871a(AbstractC0494y abstractC0494y, boolean z) {
        return m23870a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f388v3);
        c0140e.m24170a(this.f389w3);
        c0140e.m24170a(this.f390x3);
        c0140e.m24170a(this.f391y3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0182o m23869i() {
        return this.f391y3;
    }

    /* renamed from: j */
    public C0227v m23868j() {
        return this.f389w3;
    }

    /* renamed from: k */
    public C0377b m23867k() {
        return this.f390x3;
    }

    /* renamed from: l */
    public C0166k m23866l() {
        return this.f388v3;
    }

    /* renamed from: a */
    public static C0229w m23870a(Object obj) {
        if (obj != null && !(obj instanceof C0229w)) {
            if (obj instanceof AbstractC0263s) {
                return new C0229w((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid KEKRecipientInfo: " + obj.getClass().getName());
        }
        return (C0229w) obj;
    }

    public C0229w(AbstractC0263s abstractC0263s) {
        this.f388v3 = (C0166k) abstractC0263s.mo23751a(0);
        this.f389w3 = C0227v.m23881a(abstractC0263s.mo23751a(1));
        this.f390x3 = C0377b.m23460a(abstractC0263s.mo23751a(2));
        this.f391y3 = (AbstractC0182o) abstractC0263s.mo23751a(3);
    }
}
