package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;

/* renamed from: a.a.a.a.a.e.a.b.w2.c1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0382c1 extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: x3 */
    public static final int f1040x3 = 0;

    /* renamed from: y3 */
    public static final int f1041y3 = 1;

    /* renamed from: v3 */
    public C0378b0 f1042v3;

    /* renamed from: w3 */
    public C0378b0 f1043w3;

    public C0382c1(AbstractC0494y abstractC0494y) {
        int mo22994f = abstractC0494y.mo22994f();
        if (mo22994f == 0) {
            this.f1042v3 = C0378b0.m23456a(abstractC0494y, true);
        } else if (mo22994f == 1) {
            this.f1043w3 = C0378b0.m23456a(abstractC0494y, true);
        } else {
            throw new IllegalArgumentException("unknown tag: " + abstractC0494y.mo22994f());
        }
    }

    /* renamed from: a */
    public static C0382c1 m23425a(Object obj) {
        if (obj != null && !(obj instanceof C0382c1)) {
            if (obj instanceof AbstractC0494y) {
                return new C0382c1((AbstractC0494y) obj);
            }
            throw new IllegalArgumentException("unknown object in factory: " + obj.getClass());
        }
        return (C0382c1) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        C0378b0 c0378b0 = this.f1042v3;
        if (c0378b0 != null) {
            return new C0360v1(true, 0, c0378b0);
        }
        return new C0360v1(true, 1, this.f1043w3);
    }

    /* renamed from: i */
    public C0378b0 m23424i() {
        return this.f1043w3;
    }

    /* renamed from: j */
    public C0378b0 m23423j() {
        return this.f1042v3;
    }

    public C0382c1(int i, C0378b0 c0378b0) {
        this(new C0360v1(i, c0378b0));
    }
}
