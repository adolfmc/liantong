package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.p027g;

/* renamed from: a.a.a.a.a.e.a.b.v2.g.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0370d {

    /* renamed from: a */
    public String f981a;

    /* renamed from: b */
    public int f982b;

    /* renamed from: c */
    public char f983c;

    /* renamed from: d */
    public StringBuffer f984d;

    public C0370d(String str) {
        this(str, ',');
    }

    /* renamed from: a */
    public boolean m23497a() {
        return this.f982b != this.f981a.length();
    }

    /* renamed from: b */
    public String m23496b() {
        if (this.f982b == this.f981a.length()) {
            return null;
        }
        int i = this.f982b + 1;
        this.f984d.setLength(0);
        boolean z = false;
        boolean z2 = false;
        while (i != this.f981a.length()) {
            char charAt = this.f981a.charAt(i);
            if (charAt == '\"') {
                if (!z) {
                    z2 = !z2;
                }
                this.f984d.append(charAt);
                z = false;
            } else if (z || z2) {
                this.f984d.append(charAt);
                z = false;
            } else if (charAt == '\\') {
                this.f984d.append(charAt);
                z = true;
            } else if (charAt == this.f983c) {
                break;
            } else {
                this.f984d.append(charAt);
            }
            i++;
        }
        this.f982b = i;
        return this.f984d.toString();
    }

    public C0370d(String str, char c) {
        this.f984d = new StringBuffer();
        this.f981a = str;
        this.f982b = -1;
        this.f983c = c;
    }
}
