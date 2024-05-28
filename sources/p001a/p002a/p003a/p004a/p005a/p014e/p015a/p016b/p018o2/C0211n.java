package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0167k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.o2.n */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0211n extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f320v3;

    /* renamed from: w3 */
    public C0377b f321w3;

    /* renamed from: x3 */
    public C0207l f322x3;

    /* renamed from: y3 */
    public AbstractC0182o f323y3;

    public C0211n(C0377b c0377b, C0207l c0207l, byte[] bArr) {
        this.f320v3 = new C0166k(0L);
        this.f321w3 = c0377b;
        this.f322x3 = c0207l;
        this.f323y3 = new C0168k1(bArr);
    }

    /* renamed from: a */
    public static C0211n m23960a(AbstractC0494y abstractC0494y, boolean z) {
        return m23959a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f320v3);
        c0140e.m24170a(this.f321w3);
        c0140e.m24170a(this.f322x3);
        c0140e.m24170a(this.f323y3);
        return new C0167k0(c0140e);
    }

    /* renamed from: i */
    public byte[] m23958i() {
        return this.f323y3.mo24088m();
    }

    /* renamed from: j */
    public C0377b m23957j() {
        return this.f321w3;
    }

    /* renamed from: k */
    public C0207l m23956k() {
        return this.f322x3;
    }

    /* renamed from: l */
    public C0166k m23955l() {
        return this.f320v3;
    }

    /* renamed from: a */
    public static C0211n m23959a(Object obj) {
        if (obj instanceof C0211n) {
            return (C0211n) obj;
        }
        if (obj != null) {
            return new C0211n(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    public C0211n(AbstractC0263s abstractC0263s) {
        this.f320v3 = (C0166k) abstractC0263s.mo23751a(0);
        this.f321w3 = C0377b.m23460a(abstractC0263s.mo23751a(1));
        this.f322x3 = C0207l.m23973a(abstractC0263s.mo23751a(2));
        this.f323y3 = AbstractC0182o.m24089a(abstractC0263s.mo23751a(3));
    }
}
