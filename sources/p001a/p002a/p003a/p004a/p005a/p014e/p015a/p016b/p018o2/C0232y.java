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

/* renamed from: a.a.a.a.a.e.a.b.o2.y */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0232y extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f396v3;

    /* renamed from: w3 */
    public C0188b0 f397w3;

    /* renamed from: x3 */
    public AbstractC0182o f398x3;

    /* renamed from: y3 */
    public C0377b f399y3;

    /* renamed from: z3 */
    public AbstractC0263s f400z3;

    public C0232y(C0188b0 c0188b0, AbstractC0182o abstractC0182o, C0377b c0377b, AbstractC0263s abstractC0263s) {
        this.f396v3 = new C0166k(3L);
        this.f397w3 = c0188b0;
        this.f398x3 = abstractC0182o;
        this.f399y3 = c0377b;
        this.f400z3 = abstractC0263s;
    }

    /* renamed from: a */
    public static C0232y m23857a(AbstractC0494y abstractC0494y, boolean z) {
        return m23856a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f396v3);
        c0140e.m24170a(new C0360v1(true, 0, this.f397w3));
        AbstractC0182o abstractC0182o = this.f398x3;
        if (abstractC0182o != null) {
            c0140e.m24170a(new C0360v1(true, 1, abstractC0182o));
        }
        c0140e.m24170a(this.f399y3);
        c0140e.m24170a(this.f400z3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0377b m23855i() {
        return this.f399y3;
    }

    /* renamed from: j */
    public C0188b0 m23854j() {
        return this.f397w3;
    }

    /* renamed from: k */
    public AbstractC0263s m23853k() {
        return this.f400z3;
    }

    /* renamed from: l */
    public AbstractC0182o m23852l() {
        return this.f398x3;
    }

    /* renamed from: m */
    public C0166k m23851m() {
        return this.f396v3;
    }

    /* renamed from: a */
    public static C0232y m23856a(Object obj) {
        if (obj != null && !(obj instanceof C0232y)) {
            if (obj instanceof AbstractC0263s) {
                return new C0232y((AbstractC0263s) obj);
            }
            throw new IllegalArgumentException("Illegal object in KeyAgreeRecipientInfo: " + obj.getClass().getName());
        }
        return (C0232y) obj;
    }

    public C0232y(AbstractC0263s abstractC0263s) {
        this.f396v3 = (C0166k) abstractC0263s.mo23751a(0);
        this.f397w3 = C0188b0.m24064a((AbstractC0494y) abstractC0263s.mo23751a(1), true);
        int i = 2;
        if (abstractC0263s.mo23751a(2) instanceof AbstractC0494y) {
            this.f398x3 = AbstractC0182o.m24090a((AbstractC0494y) abstractC0263s.mo23751a(2), true);
            i = 3;
        }
        this.f399y3 = C0377b.m23460a(abstractC0263s.mo23751a(i));
        this.f400z3 = (AbstractC0263s) abstractC0263s.mo23751a(i + 1);
    }
}
