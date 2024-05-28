package org.p415a.p418b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.by */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12465by {

    /* renamed from: a */
    private String f25242a;

    /* renamed from: b */
    private int f25243b = 0;

    public C12465by(String str) {
        this.f25242a = str;
    }

    /* renamed from: a */
    public boolean m1696a() {
        return this.f25243b != -1;
    }

    /* renamed from: b */
    public String m1695b() {
        int i = this.f25243b;
        if (i == -1) {
            return null;
        }
        int indexOf = this.f25242a.indexOf(46, i);
        if (indexOf == -1) {
            String substring = this.f25242a.substring(this.f25243b);
            this.f25243b = -1;
            return substring;
        }
        String substring2 = this.f25242a.substring(this.f25243b, indexOf);
        this.f25243b = indexOf + 1;
        return substring2;
    }
}
