package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

/* renamed from: a.a.a.a.a.e.a.c.m0.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0565e extends C0557a {

    /* renamed from: b */
    public C0567f f1816b;

    public C0565e(boolean z, C0567f c0567f) {
        super(z);
        this.f1816b = c0567f;
    }

    /* renamed from: b */
    public C0567f m22827b() {
        return this.f1816b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0565e) {
            C0565e c0565e = (C0565e) obj;
            C0567f c0567f = this.f1816b;
            if (c0567f == null) {
                return c0565e.m22827b() == null;
            }
            return c0567f.equals(c0565e.m22827b());
        }
        return false;
    }

    public int hashCode() {
        int i = !m22845a();
        C0567f c0567f = this.f1816b;
        return c0567f != null ? i ^ c0567f.hashCode() : i;
    }
}
