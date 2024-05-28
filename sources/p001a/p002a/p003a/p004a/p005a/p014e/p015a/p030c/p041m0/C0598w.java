package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

/* renamed from: a.a.a.a.a.e.a.c.m0.w */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0598w extends C0557a {

    /* renamed from: b */
    public C0599x f1890b;

    public C0598w(boolean z, C0599x c0599x) {
        super(z);
        this.f1890b = c0599x;
    }

    /* renamed from: b */
    public C0599x m22754b() {
        return this.f1890b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0598w) {
            C0598w c0598w = (C0598w) obj;
            C0599x c0599x = this.f1890b;
            if (c0599x == null) {
                return c0598w.m22754b() == null;
            }
            return c0599x.equals(c0598w.m22754b());
        }
        return false;
    }

    public int hashCode() {
        C0599x c0599x = this.f1890b;
        if (c0599x != null) {
            return c0599x.hashCode();
        }
        return 0;
    }
}
