package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

/* renamed from: a.a.a.a.a.e.a.b.l2 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0173l2 {

    /* renamed from: a */
    public String f211a;

    /* renamed from: b */
    public int f212b = 0;

    public C0173l2(String str) {
        this.f211a = str;
    }

    /* renamed from: a */
    public boolean m24105a() {
        return this.f212b != -1;
    }

    /* renamed from: b */
    public String m24104b() {
        int i = this.f212b;
        if (i == -1) {
            return null;
        }
        int indexOf = this.f211a.indexOf(46, i);
        if (indexOf == -1) {
            String substring = this.f211a.substring(this.f212b);
            this.f212b = -1;
            return substring;
        }
        String substring2 = this.f211a.substring(this.f212b, indexOf);
        this.f212b = indexOf + 1;
        return substring2;
    }
}
