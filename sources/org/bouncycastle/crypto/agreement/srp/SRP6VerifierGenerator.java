package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SRP6VerifierGenerator {

    /* renamed from: N */
    protected BigInteger f26405N;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f26406g;

    public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return this.f26406g.modPow(SRP6Util.calculateX(this.digest, this.f26405N, bArr, bArr2, bArr3), this.f26405N);
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest) {
        this.f26405N = bigInteger;
        this.f26406g = bigInteger2;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest) {
        this.f26405N = sRP6GroupParameters.getN();
        this.f26406g = sRP6GroupParameters.getG();
        this.digest = digest;
    }
}
