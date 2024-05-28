package org.p415a.p445f.p446a;

import java.io.IOException;
import java.math.BigInteger;
import org.p415a.p424c.AbstractC12611h;
import org.p415a.p424c.C12620q;
import org.p415a.p424c.C12625v;
import org.p415a.p445f.C12934f;
import org.p415a.p445f.C12944o;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12925i extends AbstractC12922f {

    /* renamed from: a */
    private C12944o f26174a;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC12925i(C12944o c12944o) {
        this.f26174a = c12944o;
        int m452b = c12944o.m452b();
        switch (m452b) {
            case 1:
            case 2:
                return;
            case 3:
                throw new IllegalArgumentException("Can't use an RSA_SIGN key for encryption.");
            default:
                switch (m452b) {
                    case 16:
                    case 18:
                    case 20:
                        return;
                    case 17:
                        throw new IllegalArgumentException("Can't use DSA for encryption.");
                    case 19:
                        throw new IllegalArgumentException("Can't use ECDSA for encryption.");
                    default:
                        throw new IllegalArgumentException("unknown asymmetric algorithm: " + c12944o.m452b());
                }
        }
    }

    /* renamed from: b */
    private byte[] m473b(byte[] bArr) {
        try {
            return new C12620q(new BigInteger(1, bArr)).mo1536a();
        } catch (IOException e) {
            throw new C12934f("Invalid MPI encoding: " + e.getMessage(), e);
        }
    }

    @Override // org.p415a.p445f.p446a.AbstractC12922f
    /* renamed from: a */
    public AbstractC12611h mo476a(int i, byte[] bArr) {
        return new C12625v(this.f26174a.m454a(), this.f26174a.m452b(), m474a(mo475a(this.f26174a, bArr)));
    }

    /* renamed from: a */
    protected abstract byte[] mo475a(C12944o c12944o, byte[] bArr);

    /* renamed from: a */
    public byte[][] m474a(byte[] bArr) {
        int m452b = this.f26174a.m452b();
        if (m452b != 16) {
            if (m452b == 18) {
                return new byte[][]{bArr};
            }
            if (m452b != 20) {
                switch (m452b) {
                    case 1:
                    case 2:
                        return new byte[][]{m473b(bArr)};
                    default:
                        throw new C12934f("unknown asymmetric algorithm: " + this.f26174a.m452b());
                }
            }
        }
        byte[] bArr2 = new byte[bArr.length / 2];
        byte[] bArr3 = new byte[bArr.length / 2];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        System.arraycopy(bArr, bArr2.length, bArr3, 0, bArr3.length);
        return new byte[][]{m473b(bArr2), m473b(bArr3)};
    }
}
