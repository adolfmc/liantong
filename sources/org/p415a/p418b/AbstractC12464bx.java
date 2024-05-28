package org.p415a.p418b;

import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bx */
/* loaded from: E:\11617560_dexfile_execute.dex */
abstract class AbstractC12464bx extends InputStream {

    /* renamed from: a */
    protected final InputStream f25240a;

    /* renamed from: b */
    private int f25241b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC12464bx(InputStream inputStream, int i) {
        this.f25240a = inputStream;
        this.f25241b = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo1698a() {
        return this.f25241b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void m1697b(boolean z) {
        InputStream inputStream = this.f25240a;
        if (inputStream instanceof C12461bu) {
            ((C12461bu) inputStream).m1702a(z);
        }
    }
}
