package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SPHINCSPlusPrivateKeyParameters extends SPHINCSPlusKeyParameters {

    /* renamed from: pk */
    final C13371PK f27271pk;

    /* renamed from: sk */
    final C13372SK f27272sk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, C13372SK c13372sk, C13371PK c13371pk) {
        super(true, sPHINCSPlusParameters);
        this.f27272sk = c13372sk;
        this.f27271pk = c13371pk;
    }

    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr) {
        super(true, sPHINCSPlusParameters);
        int n = sPHINCSPlusParameters.getN();
        int i = n * 4;
        if (bArr.length != i) {
            throw new IllegalArgumentException("private key encoding does not match parameters");
        }
        int i2 = n * 2;
        this.f27272sk = new C13372SK(Arrays.copyOfRange(bArr, 0, n), Arrays.copyOfRange(bArr, n, i2));
        int i3 = n * 3;
        this.f27271pk = new C13371PK(Arrays.copyOfRange(bArr, i2, i3), Arrays.copyOfRange(bArr, i3, i));
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(Pack.intToBigEndian(SPHINCSPlusParameters.getID(getParameters()).intValue()), Arrays.concatenate(this.f27272sk.seed, this.f27272sk.prf, this.f27271pk.seed, this.f27271pk.root));
    }

    public byte[] getEncodedPublicKey() {
        return Arrays.concatenate(Pack.intToBigEndian(SPHINCSPlusParameters.getID(getParameters()).intValue()), this.f27271pk.seed, this.f27271pk.root);
    }

    public byte[] getPrf() {
        return Arrays.clone(this.f27272sk.prf);
    }

    public byte[] getPublicKey() {
        return Arrays.concatenate(this.f27271pk.seed, this.f27271pk.root);
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.f27271pk.seed);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.f27272sk.seed);
    }
}
