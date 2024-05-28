package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SRP6Client {

    /* renamed from: A */
    protected BigInteger f26385A;

    /* renamed from: B */
    protected BigInteger f26386B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f26387M1;

    /* renamed from: M2 */
    protected BigInteger f26388M2;

    /* renamed from: N */
    protected BigInteger f26389N;

    /* renamed from: S */
    protected BigInteger f26390S;

    /* renamed from: a */
    protected BigInteger f26391a;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f26392g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f26393u;

    /* renamed from: x */
    protected BigInteger f26394x;

    private BigInteger calculateS() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f26389N, this.f26392g);
        return this.f26386B.subtract(this.f26392g.modPow(this.f26394x, this.f26389N).multiply(calculateK).mod(this.f26389N)).mod(this.f26389N).modPow(this.f26393u.multiply(this.f26394x).add(this.f26391a), this.f26389N);
    }

    public BigInteger calculateClientEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f26385A;
        if (bigInteger3 == null || (bigInteger = this.f26386B) == null || (bigInteger2 = this.f26390S) == null) {
            throw new CryptoException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
        }
        this.f26387M1 = SRP6Util.calculateM1(this.digest, this.f26389N, bigInteger3, bigInteger, bigInteger2);
        return this.f26387M1;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        this.f26386B = SRP6Util.validatePublicValue(this.f26389N, bigInteger);
        this.f26393u = SRP6Util.calculateU(this.digest, this.f26389N, this.f26385A, this.f26386B);
        this.f26390S = calculateS();
        return this.f26390S;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f26390S;
        if (bigInteger == null || this.f26387M1 == null || this.f26388M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        this.Key = SRP6Util.calculateKey(this.digest, this.f26389N, bigInteger);
        return this.Key;
    }

    public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f26394x = SRP6Util.calculateX(this.digest, this.f26389N, bArr, bArr2, bArr3);
        this.f26391a = selectPrivateValue();
        this.f26385A = this.f26392g.modPow(this.f26391a, this.f26389N);
        return this.f26385A;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest, SecureRandom secureRandom) {
        this.f26389N = bigInteger;
        this.f26392g = bigInteger2;
        this.digest = digest;
        this.random = secureRandom;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), digest, secureRandom);
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f26389N, this.f26392g, this.random);
    }

    public boolean verifyServerEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f26385A;
        if (bigInteger4 == null || (bigInteger2 = this.f26387M1) == null || (bigInteger3 = this.f26390S) == null) {
            throw new CryptoException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
        }
        if (SRP6Util.calculateM2(this.digest, this.f26389N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            this.f26388M2 = bigInteger;
            return true;
        }
        return false;
    }
}
