package org.p415a.p418b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.aq */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12426aq extends AbstractC12467c {
    public C12426aq(byte[] bArr) {
        this(bArr, 0);
    }

    public C12426aq(byte[] bArr, int i) {
        super(bArr, i);
    }

    @Override // org.p415a.p418b.AbstractC12467c, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    void mo1597a(C12567r c12567r) {
        byte[] a = m1690a(this.f25246a, this.f25247b);
        byte[] bArr = new byte[a.length + 1];
        bArr[0] = (byte) m1687e();
        System.arraycopy(a, 0, bArr, 1, bArr.length - 1);
        c12567r.m1626a(3, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        return C12466bz.m1694a(this.f25246a.length + 1) + 1 + this.f25246a.length + 1;
    }
}
