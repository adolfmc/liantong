package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p020p2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0372w0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.p2.l */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0248l extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: w3 */
    public static final int f455w3 = 0;

    /* renamed from: x3 */
    public static final int f456x3 = 1;

    /* renamed from: y3 */
    public static final int f457y3 = 2;

    /* renamed from: v3 */
    public InterfaceC0136d f458v3;

    public C0248l(AbstractC0494y abstractC0494y) {
        int mo22994f = abstractC0494y.mo22994f();
        if (mo22994f == 0) {
            this.f458v3 = C0245i.m23799a(abstractC0494y.m23004m());
        } else if (mo22994f == 1) {
            this.f458v3 = AbstractC0182o.m24090a(abstractC0494y, false);
        } else if (mo22994f == 2) {
            this.f458v3 = C0372w0.m23488a(abstractC0494y, false);
        } else {
            throw new IllegalArgumentException("unknown tag number: " + abstractC0494y.mo22994f());
        }
    }

    /* renamed from: a */
    public static C0248l m23785a(Object obj) {
        if (obj != null && !(obj instanceof C0248l)) {
            if (obj instanceof AbstractC0494y) {
                return new C0248l((AbstractC0494y) obj);
            }
            throw new IllegalArgumentException("unknown object: " + obj);
        }
        return (C0248l) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        InterfaceC0136d interfaceC0136d = this.f458v3;
        if (interfaceC0136d instanceof C0245i) {
            return new C0360v1(true, 0, interfaceC0136d);
        }
        if (interfaceC0136d instanceof AbstractC0182o) {
            return new C0360v1(false, 1, interfaceC0136d);
        }
        return new C0360v1(false, 2, interfaceC0136d);
    }

    /* renamed from: i */
    public int m23784i() {
        InterfaceC0136d interfaceC0136d = this.f458v3;
        if (interfaceC0136d instanceof C0245i) {
            return 0;
        }
        return interfaceC0136d instanceof AbstractC0182o ? 1 : 2;
    }

    /* renamed from: j */
    public InterfaceC0136d m23783j() {
        return this.f458v3;
    }

    public C0248l(C0245i c0245i) {
        this.f458v3 = c0245i;
    }

    public C0248l(AbstractC0182o abstractC0182o) {
        this.f458v3 = abstractC0182o;
    }

    public C0248l(boolean z) {
        this.f458v3 = C0372w0.m23486a(z);
    }
}
