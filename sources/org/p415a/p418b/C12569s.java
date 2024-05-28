package org.p415a.p418b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.s */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12569s extends IllegalStateException {

    /* renamed from: a */
    private Throwable f25514a;

    public C12569s(String str) {
        super(str);
    }

    public C12569s(String str, Throwable th) {
        super(str);
        this.f25514a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f25514a;
    }
}
