package org.p415a.p418b;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12557i extends IOException {

    /* renamed from: a */
    private Throwable f25499a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12557i(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12557i(String str, Throwable th) {
        super(str);
        this.f25499a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f25499a;
    }
}
