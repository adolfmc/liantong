package org.bouncycastle.jcajce.spec;

import java.math.BigInteger;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHValidationParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DHDomainParameterSpec extends DHParameterSpec {

    /* renamed from: j */
    private final BigInteger f26894j;

    /* renamed from: m */
    private final int f26895m;

    /* renamed from: q */
    private final BigInteger f26896q;
    private DHValidationParameters validationParameters;

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, null, 0);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i) {
        this(bigInteger, bigInteger2, bigInteger3, null, i);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i) {
        this(bigInteger, bigInteger2, bigInteger3, bigInteger4, 0, i);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i, int i2) {
        super(bigInteger, bigInteger3, i2);
        this.f26896q = bigInteger2;
        this.f26894j = bigInteger4;
        this.f26895m = i;
    }

    public DHDomainParameterSpec(DHParameters dHParameters) {
        this(dHParameters.getP(), dHParameters.getQ(), dHParameters.getG(), dHParameters.getJ(), dHParameters.getM(), dHParameters.getL());
        this.validationParameters = dHParameters.getValidationParameters();
    }

    public DHParameters getDomainParameters() {
        return new DHParameters(getP(), getG(), this.f26896q, this.f26895m, getL(), this.f26894j, this.validationParameters);
    }

    public BigInteger getJ() {
        return this.f26894j;
    }

    public int getM() {
        return this.f26895m;
    }

    public BigInteger getQ() {
        return this.f26896q;
    }
}
