package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0146f1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.r0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0426r0 extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f1308v3;

    /* renamed from: w3 */
    public InterfaceC0136d f1309w3;

    public C0426r0(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f1308v3 = c0178n;
        this.f1309w3 = interfaceC0136d;
    }

    /* renamed from: a */
    public static C0426r0 m23190a(Object obj) {
        if (obj instanceof C0426r0) {
            return (C0426r0) obj;
        }
        if (obj != null) {
            return new C0426r0(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1308v3);
        c0140e.m24170a(this.f1309w3);
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m23189i() {
        return this.f1308v3;
    }

    /* renamed from: j */
    public InterfaceC0136d m23188j() {
        return this.f1309w3;
    }

    public C0426r0(String str) {
        this.f1308v3 = C0423q0.f1271A3;
        this.f1309w3 = new C0146f1(str);
    }

    public C0426r0(AbstractC0263s abstractC0263s) {
        if (abstractC0263s.mo23745o() == 2) {
            this.f1308v3 = C0164j1.m24118a(abstractC0263s.mo23751a(0));
            this.f1309w3 = abstractC0263s.mo23751a(1);
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
