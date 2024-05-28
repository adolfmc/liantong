package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0140e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0156h1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0184o1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.w2.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0377b extends AbstractC0174m {

    /* renamed from: v3 */
    public C0178n f1011v3;

    /* renamed from: w3 */
    public InterfaceC0136d f1012w3;

    /* renamed from: x3 */
    public boolean f1013x3;

    public C0377b(C0178n c0178n) {
        this.f1013x3 = false;
        this.f1011v3 = c0178n;
    }

    /* renamed from: a */
    public static C0377b m23461a(AbstractC0494y abstractC0494y, boolean z) {
        return m23460a(AbstractC0263s.m23750a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0140e c0140e = new C0140e();
        c0140e.m24170a(this.f1011v3);
        if (this.f1013x3) {
            InterfaceC0136d interfaceC0136d = this.f1012w3;
            if (interfaceC0136d != null) {
                c0140e.m24170a(interfaceC0136d);
            } else {
                c0140e.m24170a(C0156h1.f189v3);
            }
        }
        return new C0184o1(c0140e);
    }

    /* renamed from: i */
    public C0178n m23459i() {
        return new C0178n(this.f1011v3.m24113n());
    }

    /* renamed from: j */
    public C0178n mo23458j() {
        return this.f1011v3;
    }

    /* renamed from: k */
    public InterfaceC0136d m23457k() {
        return this.f1012w3;
    }

    /* renamed from: a */
    public static C0377b m23460a(Object obj) {
        if (obj != null && !(obj instanceof C0377b)) {
            if (obj instanceof C0178n) {
                return new C0377b((C0178n) obj);
            }
            if (obj instanceof String) {
                return new C0377b((String) obj);
            }
            return new C0377b(AbstractC0263s.m23749a(obj));
        }
        return (C0377b) obj;
    }

    public C0377b(String str) {
        this.f1013x3 = false;
        this.f1011v3 = new C0178n(str);
    }

    public C0377b(C0164j1 c0164j1) {
        this.f1013x3 = false;
        this.f1011v3 = new C0178n(c0164j1.m24113n());
    }

    public C0377b(C0164j1 c0164j1, InterfaceC0136d interfaceC0136d) {
        this.f1013x3 = false;
        this.f1013x3 = true;
        this.f1011v3 = new C0178n(c0164j1.m24113n());
        this.f1012w3 = interfaceC0136d;
    }

    public C0377b(C0178n c0178n, InterfaceC0136d interfaceC0136d) {
        this.f1013x3 = false;
        this.f1013x3 = true;
        this.f1011v3 = c0178n;
        this.f1012w3 = interfaceC0136d;
    }

    public C0377b(AbstractC0263s abstractC0263s) {
        this.f1013x3 = false;
        if (abstractC0263s.mo23745o() >= 1 && abstractC0263s.mo23745o() <= 2) {
            this.f1011v3 = C0164j1.m24118a(abstractC0263s.mo23751a(0));
            if (abstractC0263s.mo23745o() == 2) {
                this.f1013x3 = true;
                this.f1012w3 = abstractC0263s.mo23751a(1);
                return;
            }
            this.f1012w3 = null;
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + abstractC0263s.mo23745o());
    }
}
