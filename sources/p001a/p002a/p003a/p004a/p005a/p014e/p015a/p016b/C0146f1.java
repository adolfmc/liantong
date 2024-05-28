package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;

/* renamed from: a.a.a.a.a.e.a.b.f1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0146f1 extends AbstractC0258r implements InterfaceC0452x {

    /* renamed from: v3 */
    public byte[] f176v3;

    public C0146f1(byte[] bArr) {
        this.f176v3 = bArr;
    }

    /* renamed from: a */
    public static C0146f1 m24157a(Object obj) {
        if (obj != null && !(obj instanceof C0146f1)) {
            if (obj instanceof byte[]) {
                try {
                    return (C0146f1) AbstractC0258r.m23755a((byte[]) obj);
                } catch (Exception e) {
                    throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0146f1) obj;
    }

    /* renamed from: b */
    public static boolean m24156b(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            if (str.charAt(length) > 127) {
                return false;
            }
        }
        return true;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0452x
    /* renamed from: e */
    public String mo22978e() {
        return C0678j.m22442b(this.f176v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return C0669a.m22472b(this.f176v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return C0177m2.m24099a(this.f176v3.length) + 1 + this.f176v3.length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    /* renamed from: m */
    public byte[] m24155m() {
        return C0669a.m22503a(this.f176v3);
    }

    public String toString() {
        return mo22978e();
    }

    public C0146f1(String str) {
        this(str, false);
    }

    public C0146f1(String str, boolean z) {
        if (str != null) {
            if (z && !m24156b(str)) {
                throw new IllegalArgumentException("string contains illegal characters");
            }
            this.f176v3 = C0678j.m22448a(str);
            return;
        }
        throw new NullPointerException("string cannot be null");
    }

    /* renamed from: a */
    public static C0146f1 m24158a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0146f1)) {
            return new C0146f1(((AbstractC0182o) m23004m).mo24088m());
        }
        return m24157a((Object) m23004m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.m23773a(22, this.f176v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0146f1) {
            return C0669a.m22499a(this.f176v3, ((C0146f1) abstractC0258r).f176v3);
        }
        return false;
    }
}
