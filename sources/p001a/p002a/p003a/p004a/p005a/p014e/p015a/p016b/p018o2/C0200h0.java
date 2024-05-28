package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.o2.h0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0200h0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f284v3;

    /* renamed from: w3 */
    public C0377b f285w3;

    /* renamed from: x3 */
    public C0377b f286x3;

    /* renamed from: y3 */
    public AbstractC0182o f287y3;

    public C0200h0(C0377b c0377b, AbstractC0182o abstractC0182o) {
        this.f284v3 = new C0166k(0L);
        this.f286x3 = c0377b;
        this.f287y3 = abstractC0182o;
    }

    /* renamed from: a */
    public static C0200h0 m24000a(AbstractC0494y abstractC0494y, boolean z) {
        return m23999a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f284v3);
        C0377b c0377b = this.f285w3;
        if (c0377b != null) {
            c0140e.m24170a(new C0360v1(false, 0, c0377b));
        }
        c0140e.m24170a(this.f286x3);
        c0140e.m24170a(this.f287y3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public AbstractC0182o m23998i() {
        return this.f287y3;
    }

    /* renamed from: j */
    public C0377b m23997j() {
        return this.f285w3;
    }

    /* renamed from: k */
    public C0377b m23996k() {
        return this.f286x3;
    }

    /* renamed from: l */
    public C0166k m23995l() {
        return this.f284v3;
    }

    /* renamed from: a */
    public static C0200h0 m23999a(Object obj) {
        if (obj != null && !(obj instanceof C0200h0)) {
            if (obj instanceof AbstractC0263s) {
                return new C0200h0((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Invalid PasswordRecipientInfo: " + obj.getClass().getName());
        }
        return (C0200h0) obj;
    }

    public C0200h0(C0377b c0377b, C0377b c0377b2, AbstractC0182o abstractC0182o) {
        this.f284v3 = new C0166k(0L);
        this.f285w3 = c0377b;
        this.f286x3 = c0377b2;
        this.f287y3 = abstractC0182o;
    }

    public C0200h0(AbstractC0263s abstractC0263s) {
        this.f284v3 = (C0166k) abstractC0263s.mo23751a(0);
        if (abstractC0263s.mo23751a(1) instanceof AbstractC0494y) {
            this.f285w3 = C0377b.m23461a((AbstractC0494y) abstractC0263s.mo23751a(1), false);
            this.f286x3 = C0377b.m23460a(abstractC0263s.mo23751a(2));
            this.f287y3 = (AbstractC0182o) abstractC0263s.mo23751a(3);
            return;
        }
        this.f286x3 = C0377b.m23460a(abstractC0263s.mo23751a(1));
        this.f287y3 = (AbstractC0182o) abstractC0263s.mo23751a(2);
    }
}
