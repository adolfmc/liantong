package org.p415a.p445f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.r */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12947r extends RuntimeException {

    /* renamed from: a */
    private final Throwable f26223a;

    public C12947r(String str, Throwable th) {
        super(str);
        this.f26223a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f26223a;
    }
}
