package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0443x0;

/* renamed from: a.a.a.a.a.e.a.b.o2.b0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0188b0 extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public InterfaceC0136d f243v3;

    public C0188b0(C0225u c0225u) {
        this.f243v3 = c0225u;
    }

    /* renamed from: a */
    public static C0188b0 m24064a(AbstractC0494y abstractC0494y, boolean z) {
        if (z) {
            return m24063a(abstractC0494y.m23004m());
        }
        throw new IllegalArgumentException("Can't implicitly tag OriginatorIdentifierOrKey");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f243v3.mo23015d();
    }

    /* renamed from: i */
    public InterfaceC0136d m24062i() {
        return this.f243v3;
    }

    /* renamed from: j */
    public C0225u m24061j() {
        InterfaceC0136d interfaceC0136d = this.f243v3;
        if (interfaceC0136d instanceof C0225u) {
            return (C0225u) interfaceC0136d;
        }
        return null;
    }

    /* renamed from: k */
    public C0192d0 m24060k() {
        InterfaceC0136d interfaceC0136d = this.f243v3;
        if ((interfaceC0136d instanceof AbstractC0494y) && ((AbstractC0494y) interfaceC0136d).mo22994f() == 1) {
            return C0192d0.m24043a((AbstractC0494y) this.f243v3, false);
        }
        return null;
    }

    /* renamed from: l */
    public C0443x0 m24059l() {
        InterfaceC0136d interfaceC0136d = this.f243v3;
        if ((interfaceC0136d instanceof AbstractC0494y) && ((AbstractC0494y) interfaceC0136d).mo22994f() == 0) {
            return C0443x0.m23114a((AbstractC0494y) this.f243v3, false);
        }
        return null;
    }

    public C0188b0(AbstractC0182o abstractC0182o) {
        this(new C0443x0(abstractC0182o.mo24088m()));
    }

    /* renamed from: a */
    public static C0188b0 m24063a(Object obj) {
        if (obj != null && !(obj instanceof C0188b0)) {
            if (obj instanceof C0225u) {
                return new C0188b0((C0225u) obj);
            }
            if (obj instanceof C0443x0) {
                return new C0188b0((C0443x0) obj);
            }
            if (obj instanceof C0192d0) {
                return new C0188b0((C0192d0) obj);
            }
            if (obj instanceof AbstractC0494y) {
                return new C0188b0((AbstractC0494y) obj);
            }
            throw new IllegalArgumentException("Invalid OriginatorIdentifierOrKey: " + obj.getClass().getName());
        }
        return (C0188b0) obj;
    }

    public C0188b0(C0443x0 c0443x0) {
        this.f243v3 = new C0360v1(false, 0, c0443x0);
    }

    public C0188b0(C0192d0 c0192d0) {
        this.f243v3 = new C0360v1(false, 1, c0192d0);
    }

    public C0188b0(AbstractC0258r abstractC0258r) {
        this.f243v3 = abstractC0258r;
    }
}
