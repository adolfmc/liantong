package org.p415a.p427d.p433f;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.f.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12697a extends IOException {

    /* renamed from: a */
    private final Throwable f25869a;

    public C12697a(String str, Throwable th) {
        super(str);
        this.f25869a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f25869a;
    }
}
