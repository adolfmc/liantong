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
public class SRP6Server {

    /* renamed from: A */
    protected BigInteger f26395A;

    /* renamed from: B */
    protected BigInteger f26396B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f26397M1;

    /* renamed from: M2 */
    protected BigInteger f26398M2;

    /* renamed from: N */
    protected BigInteger f26399N;

    /* renamed from: S */
    protected BigInteger f26400S;

    /* renamed from: b */
    protected BigInteger f26401b;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f26402g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f26403u;

    /* renamed from: v */
    protected BigInteger f26404v;

    private BigInteger calculateS() {
        return this.f26404v.modPow(this.f26403u, this.f26399N).multiply(this.f26395A).mod(this.f26399N).modPow(this.f26401b, this.f26399N);
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        this.f26395A = SRP6Util.validatePublicValue(this.f26399N, bigInteger);
        this.f26403u = SRP6Util.calculateU(this.digest, this.f26399N, this.f26395A, this.f26396B);
        this.f26400S = calculateS();
        return this.f26400S;
    }

    public BigInteger calculateServerEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f26395A;
        if (bigInteger3 == null || (bigInteger = this.f26397M1) == null || (bigInteger2 = this.f26400S) == null) {
            throw new CryptoException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        this.f26398M2 = SRP6Util.calculateM2(this.digest, this.f26399N, bigInteger3, bigInteger, bigInteger2);
        return this.f26398M2;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f26400S;
        if (bigInteger == null || this.f26397M1 == null || this.f26398M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        this.Key = SRP6Util.calculateKey(this.digest, this.f26399N, bigInteger);
        return this.Key;
    }

    public BigInteger generateServerCredentials() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f26399N, this.f26402g);
        this.f26401b = selectPrivateValue();
        this.f26396B = calculateK.multiply(this.f26404v).mod(this.f26399N).add(this.f26402g.modPow(this.f26401b, this.f26399N)).mod(this.f26399N);
        return this.f26396B;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest, SecureRandom secureRandom) {
        this.f26399N = bigInteger;
        this.f26402g = bigInteger2;
        this.f26404v = bigInteger3;
        this.random = secureRandom;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, BigInteger bigInteger, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), bigInteger, digest, secureRandom);
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f26399N, this.f26402g, this.random);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f26395A;
        if (bigInteger4 == null || (bigInteger2 = this.f26396B) == null || (bigInteger3 = this.f26400S) == null) {
            throw new CryptoException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        }
        if (SRP6Util.calculateM1(this.digest, this.f26399N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            this.f26397M1 = bigInteger;
            return true;
        }
        return false;
    }
}
