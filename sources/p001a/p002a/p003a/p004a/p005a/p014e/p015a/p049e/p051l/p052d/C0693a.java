package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.p052d;

/* renamed from: a.a.a.a.a.e.a.e.l.d.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0693a {

    /* renamed from: a */
    public String f2054a;

    /* renamed from: b */
    public String f2055b;

    public C0693a(String str, String str2) {
        this.f2054a = str;
        this.f2055b = str2;
    }

    /* renamed from: a */
    public String m22387a() {
        return this.f2054a;
    }

    /* renamed from: b */
    public String m22384b() {
        return this.f2055b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0693a) {
            C0693a c0693a = (C0693a) obj;
            return c0693a == this || (m22385a(this.f2054a, c0693a.f2054a) && m22385a(this.f2055b, c0693a.f2055b));
        }
        return false;
    }

    public int hashCode() {
        return m22386a(this.f2054a) + (m22386a(this.f2055b) * 31);
    }

    /* renamed from: a */
    private int m22386a(String str) {
        if (str == null) {
            return 1;
        }
        return str.hashCode();
    }

    /* renamed from: a */
    private boolean m22385a(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }
}
