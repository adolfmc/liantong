package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SPHINCSPlusPublicKeyParameters extends SPHINCSPlusKeyParameters {

    /* renamed from: pk */
    private final C13371PK f27273pk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SPHINCSPlusPublicKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, C13371PK c13371pk) {
        super(false, sPHINCSPlusParameters);
        this.f27273pk = c13371pk;
    }

    public SPHINCSPlusPublicKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr) {
        super(false, sPHINCSPlusParameters);
        int n = sPHINCSPlusParameters.getN();
        int i = n * 2;
        if (bArr.length != i) {
            throw new IllegalArgumentException("public key encoding does not match parameters");
        }
        this.f27273pk = new C13371PK(Arrays.copyOfRange(bArr, 0, n), Arrays.copyOfRange(bArr, n, i));
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(Pack.intToBigEndian(SPHINCSPlusParameters.getID(getParameters()).intValue()), this.f27273pk.seed, this.f27273pk.root);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.f27273pk.root);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.f27273pk.seed);
    }
}
