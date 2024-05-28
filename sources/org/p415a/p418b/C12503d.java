package org.p415a.p418b;

import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12503d extends AbstractC12570t {

    /* renamed from: e */
    private final byte[] f25339e;

    /* renamed from: c */
    private static final byte[] f25337c = {-1};

    /* renamed from: d */
    private static final byte[] f25338d = {0};

    /* renamed from: a */
    public static final C12503d f25335a = new C12503d(false);

    /* renamed from: b */
    public static final C12503d f25336b = new C12503d(true);

    public C12503d(boolean z) {
        this.f25339e = z ? f25337c : f25338d;
    }

    C12503d(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("byte value should have 1 byte in it");
        }
        this.f25339e = bArr[0] == 0 ? f25338d : (bArr[0] & 255) == 255 ? f25337c : C12957a.m430b(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C12503d m1678a(byte[] bArr) {
        if (bArr.length == 1) {
            return bArr[0] == 0 ? f25335a : (bArr[0] & 255) == 255 ? f25336b : new C12503d(bArr);
        }
        throw new IllegalArgumentException("BOOLEAN value should have 1 byte in it");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.m1626a(1, this.f25339e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return false;
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    protected boolean mo1596a(AbstractC12570t abstractC12570t) {
        return (abstractC12570t instanceof C12503d) && this.f25339e[0] == ((C12503d) abstractC12570t).f25339e[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        return 3;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        return this.f25339e[0];
    }

    public String toString() {
        return this.f25339e[0] != 0 ? "TRUE" : "FALSE";
    }
}
