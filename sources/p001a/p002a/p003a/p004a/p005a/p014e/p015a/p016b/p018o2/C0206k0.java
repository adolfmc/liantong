package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;

/* renamed from: a.a.a.a.a.e.a.b.o2.k0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0206k0 extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public InterfaceC0136d f310v3;

    public C0206k0(C0233z c0233z) {
        this.f310v3 = c0233z;
    }

    /* renamed from: a */
    public static C0206k0 m23978a(Object obj) {
        if (obj != null && !(obj instanceof C0206k0)) {
            if (obj instanceof AbstractC0263s) {
                return new C0206k0((AbstractC0263s) obj);
            }
            if (obj instanceof AbstractC0494y) {
                return new C0206k0((AbstractC0494y) obj);
            }
            throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
        }
        return (C0206k0) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f310v3.mo23015d();
    }

    /* renamed from: i */
    public InterfaceC0136d m23977i() {
        InterfaceC0136d interfaceC0136d = this.f310v3;
        if (interfaceC0136d instanceof AbstractC0494y) {
            AbstractC0494y abstractC0494y = (AbstractC0494y) interfaceC0136d;
            switch (abstractC0494y.mo22994f()) {
                case 1:
                    return C0232y.m23857a(abstractC0494y, false);
                case 2:
                    return m23979a(abstractC0494y);
                case 3:
                    return C0200h0.m24000a(abstractC0494y, false);
                case 4:
                    return C0196f0.m24017a(abstractC0494y, false);
                default:
                    throw new IllegalStateException("unknown tag");
            }
        }
        return C0233z.m23850a(interfaceC0136d);
    }

    /* renamed from: j */
    public C0166k m23976j() {
        InterfaceC0136d interfaceC0136d = this.f310v3;
        if (interfaceC0136d instanceof AbstractC0494y) {
            AbstractC0494y abstractC0494y = (AbstractC0494y) interfaceC0136d;
            switch (abstractC0494y.mo22994f()) {
                case 1:
                    return C0232y.m23857a(abstractC0494y, false).m23851m();
                case 2:
                    return m23979a(abstractC0494y).m23866l();
                case 3:
                    return C0200h0.m24000a(abstractC0494y, false).m23995l();
                case 4:
                    return new C0166k(0L);
                default:
                    throw new IllegalStateException("unknown tag");
            }
        }
        return C0233z.m23850a(interfaceC0136d).m23846l();
    }

    /* renamed from: k */
    public boolean m23975k() {
        return this.f310v3 instanceof AbstractC0494y;
    }

    public C0206k0(C0232y c0232y) {
        this.f310v3 = new C0360v1(false, 1, c0232y);
    }

    public C0206k0(C0229w c0229w) {
        this.f310v3 = new C0360v1(false, 2, c0229w);
    }

    public C0206k0(C0200h0 c0200h0) {
        this.f310v3 = new C0360v1(false, 3, c0200h0);
    }

    public C0206k0(C0196f0 c0196f0) {
        this.f310v3 = new C0360v1(false, 4, c0196f0);
    }

    public C0206k0(AbstractC0258r abstractC0258r) {
        this.f310v3 = abstractC0258r;
    }

    /* renamed from: a */
    private C0229w m23979a(AbstractC0494y abstractC0494y) {
        if (abstractC0494y.m23002o()) {
            return C0229w.m23871a(abstractC0494y, true);
        }
        return C0229w.m23871a(abstractC0494y, false);
    }
}
