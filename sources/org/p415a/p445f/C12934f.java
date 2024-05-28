package org.p415a.p445f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12934f extends Exception {

    /* renamed from: a */
    Exception f26194a;

    public C12934f(String str) {
        super(str);
    }

    public C12934f(String str, Exception exc) {
        super(str);
        this.f26194a = exc;
    }

    /* renamed from: a */
    public Exception m463a() {
        return this.f26194a;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f26194a;
    }
}
