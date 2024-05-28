package org.p415a.p418b;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.t */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12570t extends AbstractC12562n {
    /* renamed from: b */
    public static AbstractC12570t m1619b(byte[] bArr) {
        C12559k c12559k = new C12559k(bArr);
        try {
            AbstractC12570t m1647d = c12559k.m1647d();
            if (c12559k.available() == 0) {
                return m1647d;
            }
            throw new IOException("Extra data detected in stream");
        } catch (ClassCastException unused) {
            throw new IOException("cannot recognise object in stream");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract void mo1597a(C12567r c12567r);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract boolean mo1611a();

    /* renamed from: a */
    abstract boolean mo1596a(AbstractC12570t abstractC12570t);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public abstract int mo1618c();

    @Override // org.p415a.p418b.AbstractC12562n
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InterfaceC12554f) && mo1596a(((InterfaceC12554f) obj).mo1617h());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public AbstractC12570t mo1592f() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public AbstractC12570t mo1591g() {
        return this;
    }

    @Override // org.p415a.p418b.AbstractC12562n, org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    public AbstractC12570t mo1617h() {
        return this;
    }

    @Override // org.p415a.p418b.AbstractC12562n
    public abstract int hashCode();
}
