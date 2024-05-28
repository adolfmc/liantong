package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

/* renamed from: a.a.a.a.a.e.a.b.w2.u1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0436u1 {

    /* renamed from: a */
    public String f1397a;

    /* renamed from: b */
    public int f1398b;

    /* renamed from: c */
    public char f1399c;

    /* renamed from: d */
    public StringBuffer f1400d;

    public C0436u1(String str) {
        this(str, ',');
    }

    /* renamed from: a */
    public boolean m23142a() {
        return this.f1398b != this.f1397a.length();
    }

    /* renamed from: b */
    public String m23141b() {
        if (this.f1398b == this.f1397a.length()) {
            return null;
        }
        int i = this.f1398b + 1;
        this.f1400d.setLength(0);
        boolean z = false;
        boolean z2 = false;
        while (i != this.f1397a.length()) {
            char charAt = this.f1397a.charAt(i);
            if (charAt == '\"') {
                if (!z) {
                    z2 = !z2;
                }
                this.f1400d.append(charAt);
                z = false;
            } else if (z || z2) {
                this.f1400d.append(charAt);
                z = false;
            } else if (charAt == '\\') {
                this.f1400d.append(charAt);
                z = true;
            } else if (charAt == this.f1399c) {
                break;
            } else {
                this.f1400d.append(charAt);
            }
            i++;
        }
        this.f1398b = i;
        return this.f1400d.toString();
    }

    public C0436u1(String str, char c) {
        this.f1400d = new StringBuffer();
        this.f1397a = str;
        this.f1398b = -1;
        this.f1399c = c;
    }
}
