package org.p415a.p427d.p435h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12715k extends C12705a {

    /* renamed from: b */
    private C12716l f25909b;

    /* JADX INFO: Access modifiers changed from: protected */
    public C12715k(boolean z, C12716l c12716l) {
        super(z);
        this.f25909b = c12716l;
    }

    /* renamed from: b */
    public C12716l m1334b() {
        return this.f25909b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C12715k) {
            C12715k c12715k = (C12715k) obj;
            C12716l c12716l = this.f25909b;
            return c12716l == null ? c12715k.m1334b() == null : c12716l.equals(c12715k.m1334b());
        }
        return false;
    }

    public int hashCode() {
        C12716l c12716l = this.f25909b;
        if (c12716l != null) {
            return c12716l.hashCode();
        }
        return 0;
    }
}
