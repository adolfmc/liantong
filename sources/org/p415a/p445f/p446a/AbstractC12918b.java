package org.p415a.p445f.p446a;

import java.security.SecureRandom;
import org.p415a.p424c.AbstractC12611h;
import org.p415a.p424c.C12586ah;
import org.p415a.p424c.C12629z;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12918b extends AbstractC12922f {

    /* renamed from: a */
    private char[] f26169a;

    /* renamed from: b */
    private InterfaceC12921e f26170b;

    /* renamed from: c */
    private C12629z f26171c;

    /* renamed from: d */
    private SecureRandom f26172d;

    /* renamed from: e */
    private int f26173e;

    @Override // org.p415a.p445f.p446a.AbstractC12922f
    /* renamed from: a */
    public AbstractC12611h mo476a(int i, byte[] bArr) {
        byte[] m489a = m489a(i);
        if (bArr == null) {
            return new C12586ah(i, this.f26171c, null);
        }
        byte[] bArr2 = new byte[bArr.length - 2];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return new C12586ah(i, this.f26171c, m488a(i, m489a, bArr2));
    }

    /* renamed from: a */
    public byte[] m489a(int i) {
        if (this.f26171c == null) {
            byte[] bArr = new byte[8];
            if (this.f26172d == null) {
                this.f26172d = new SecureRandom();
            }
            this.f26172d.nextBytes(bArr);
            this.f26171c = new C12629z(this.f26170b.mo481a(), bArr, this.f26173e);
        }
        return C12924h.m477a(this.f26170b, i, this.f26171c, this.f26169a);
    }

    /* renamed from: a */
    protected abstract byte[] m488a(int i, byte[] bArr, byte[] bArr2);
}
