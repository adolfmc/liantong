package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p023s2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.s2.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0271c extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f575v3;

    /* renamed from: w3 */
    public InterfaceC0136d f576w3;

    public C0271c(AbstractC0263s abstractC0263s) {
        this.f575v3 = (C0178n) abstractC0263s.mo23751a(0);
        this.f576w3 = ((C0360v1) abstractC0263s.mo23751a(1)).m23004m();
    }

    /* renamed from: a */
    public static C0271c m23728a(Object obj) {
        if (obj instanceof C0271c) {
            return (C0271c) obj;
        }
        if (obj != null) {
            return new C0271c(AbstractC0263s.m23749a(obj));
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f575v3);
        c0140e.m24170a(new C0360v1(0, this.f576w3));
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public InterfaceC0136d m23727i() {
        return this.f576w3;
    }

    /* renamed from: j */
    public C0178n m23726j() {
        return this.f575v3;
    }

    public C0271c(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f575v3 = c0178n;
        this.f576w3 = interfaceC0136d;
    }
}
