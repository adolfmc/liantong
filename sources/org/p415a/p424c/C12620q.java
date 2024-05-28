package org.p415a.p424c;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.q */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12620q extends AbstractC12607d {

    /* renamed from: a */
    BigInteger f25610a;

    public C12620q(BigInteger bigInteger) {
        this.f25610a = null;
        if (bigInteger == null || bigInteger.signum() < 0) {
            throw new IllegalArgumentException("value must not be null, or negative");
        }
        this.f25610a = bigInteger;
    }

    public C12620q(C12592b c12592b) {
        this.f25610a = null;
        byte[] bArr = new byte[(((c12592b.read() << 8) | c12592b.read()) + 7) / 8];
        c12592b.m1576a(bArr);
        this.f25610a = new BigInteger(1, bArr);
    }

    @Override // org.p415a.p424c.AbstractC12607d
    /* renamed from: a */
    public void mo1533a(C12608e c12608e) {
        int bitLength = this.f25610a.bitLength();
        c12608e.write(bitLength >> 8);
        c12608e.write(bitLength);
        byte[] byteArray = this.f25610a.toByteArray();
        if (byteArray[0] == 0) {
            c12608e.write(byteArray, 1, byteArray.length - 1);
        } else {
            c12608e.write(byteArray, 0, byteArray.length);
        }
    }

    /* renamed from: b */
    public BigInteger m1543b() {
        return this.f25610a;
    }
}
