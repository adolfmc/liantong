package org.p415a.p448g.p449a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12961c extends IllegalStateException {

    /* renamed from: a */
    private Throwable f26242a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12961c(String str, Throwable th) {
        super(str);
        this.f26242a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f26242a;
    }
}
