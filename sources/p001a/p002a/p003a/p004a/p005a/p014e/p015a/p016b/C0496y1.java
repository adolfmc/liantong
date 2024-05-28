package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;

/* renamed from: a.a.a.a.a.e.a.b.y1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0496y1 extends AbstractC0258r implements InterfaceC0452x {

    /* renamed from: v3 */
    public byte[] f1611v3;

    public C0496y1(byte[] bArr) {
        this.f1611v3 = bArr;
    }

    /* renamed from: a */
    public static C0496y1 m22996a(Object obj) {
        if (obj != null && !(obj instanceof C0496y1)) {
            if (obj instanceof byte[]) {
                try {
                    return (C0496y1) AbstractC0258r.m23755a((byte[]) obj);
                } catch (Exception e) {
                    throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0496y1) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0452x
    /* renamed from: e */
    public String mo22978e() {
        return C0678j.m22439c(this.f1611v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return C0669a.m22472b(this.f1611v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return C0177m2.m24099a(this.f1611v3.length) + 1 + this.f1611v3.length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    public String toString() {
        return mo22978e();
    }

    public C0496y1(String str) {
        this.f1611v3 = C0678j.m22440c(str);
    }

    /* renamed from: a */
    public static C0496y1 m22997a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0496y1)) {
            return new C0496y1(AbstractC0182o.m24089a((Object) m23004m).mo24088m());
        }
        return m22996a((Object) m23004m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0496y1) {
            return C0669a.m22499a(this.f1611v3, ((C0496y1) abstractC0258r).f1611v3);
        }
        return false;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.m23773a(12, this.f1611v3);
    }
}
