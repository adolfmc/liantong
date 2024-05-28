package org.p415a.p418b;

import java.math.BigInteger;
import org.p415a.p448g.C12957a;
import org.p415a.p448g.C12972f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.l */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12560l extends AbstractC12570t {

    /* renamed from: a */
    private final byte[] f25504a;

    public C12560l(long j) {
        this.f25504a = BigInteger.valueOf(j).toByteArray();
    }

    public C12560l(BigInteger bigInteger) {
        this.f25504a = bigInteger.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12560l(byte[] bArr, boolean z) {
        if (!C12972f.m394a("org.bouncycastle1.asn1.allow_unsafe_integer") && m1646a(bArr)) {
            throw new IllegalArgumentException("malformed integer");
        }
        this.f25504a = z ? C12957a.m430b(bArr) : bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m1646a(byte[] bArr) {
        if (bArr.length > 1) {
            if (bArr[0] == 0 && (bArr[1] & 128) == 0) {
                return true;
            }
            if (bArr[0] == -1 && (bArr[1] & 128) != 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.m1626a(2, this.f25504a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return false;
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t instanceof C12560l) {
            return C12957a.m438a(this.f25504a, ((C12560l) abstractC12570t).f25504a);
        }
        return false;
    }

    /* renamed from: b */
    public BigInteger m1645b() {
        return new BigInteger(this.f25504a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        return C12466bz.m1694a(this.f25504a.length) + 1 + this.f25504a.length;
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f25504a;
            if (i == bArr.length) {
                return i2;
            }
            i2 ^= (bArr[i] & 255) << (i % 4);
            i++;
        }
    }

    public String toString() {
        return m1645b().toString();
    }
}
