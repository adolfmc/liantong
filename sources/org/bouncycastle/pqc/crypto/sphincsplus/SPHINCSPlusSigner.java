package org.bouncycastle.pqc.crypto.sphincsplus;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SPHINCSPlusSigner implements MessageSigner {
    private SPHINCSPlusPrivateKeyParameters privKey;
    private SPHINCSPlusPublicKeyParameters pubKey;
    private SecureRandom random;

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        SPHINCSPlusEngine engine = this.privKey.getParameters().getEngine();
        engine.init(this.privKey.f27271pk.seed);
        byte[] bArr2 = new byte[engine.f27250N];
        SecureRandom secureRandom = this.random;
        int i = 0;
        if (secureRandom != null) {
            secureRandom.nextBytes(bArr2);
        } else {
            System.arraycopy(this.privKey.f27271pk.seed, 0, bArr2, 0, bArr2.length);
        }
        Fors fors = new Fors(engine);
        byte[] PRF_msg = engine.PRF_msg(this.privKey.f27272sk.prf, bArr2, bArr);
        IndexedDigest H_msg = engine.H_msg(PRF_msg, this.privKey.f27271pk.seed, this.privKey.f27271pk.root, bArr);
        byte[] bArr3 = H_msg.digest;
        long j = H_msg.idx_tree;
        int i2 = H_msg.idx_leaf;
        ADRS adrs = new ADRS();
        adrs.setType(3);
        adrs.setTreeAddress(j);
        adrs.setKeyPairAddress(i2);
        SIG_FORS[] sign = fors.sign(bArr3, this.privKey.f27272sk.seed, this.privKey.f27271pk.seed, adrs);
        ADRS adrs2 = new ADRS();
        adrs2.setType(3);
        adrs2.setTreeAddress(j);
        adrs2.setKeyPairAddress(i2);
        byte[] pkFromSig = fors.pkFromSig(sign, bArr3, this.privKey.f27271pk.seed, adrs2);
        new ADRS().setType(2);
        byte[] sign2 = new C13370HT(engine, this.privKey.getSeed(), this.privKey.getPublicSeed()).sign(pkFromSig, j, i2);
        byte[][] bArr4 = new byte[sign.length + 2];
        bArr4[0] = PRF_msg;
        while (i != sign.length) {
            int i3 = i + 1;
            bArr4[i3] = Arrays.concatenate(sign[i].f27245sk, Arrays.concatenate(sign[i].authPath));
            i = i3;
        }
        bArr4[bArr4.length - 1] = sign2;
        return Arrays.concatenate(bArr4);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.pubKey = (SPHINCSPlusPublicKeyParameters) cipherParameters;
        } else if (!(cipherParameters instanceof ParametersWithRandom)) {
            this.privKey = (SPHINCSPlusPrivateKeyParameters) cipherParameters;
        } else {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.privKey = (SPHINCSPlusPrivateKeyParameters) parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        SPHINCSPlusEngine engine = this.pubKey.getParameters().getEngine();
        engine.init(this.pubKey.getSeed());
        ADRS adrs = new ADRS();
        SIG sig = new SIG(engine.f27250N, engine.f27249K, engine.f27246A, engine.f27247D, engine.H_PRIME, engine.WOTS_LEN, bArr2);
        byte[] r = sig.getR();
        SIG_FORS[] sig_fors = sig.getSIG_FORS();
        SIG_XMSS[] sig_ht = sig.getSIG_HT();
        IndexedDigest H_msg = engine.H_msg(r, this.pubKey.getSeed(), this.pubKey.getRoot(), bArr);
        byte[] bArr3 = H_msg.digest;
        long j = H_msg.idx_tree;
        int i = H_msg.idx_leaf;
        adrs.setType(3);
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j);
        adrs.setKeyPairAddress(i);
        byte[] pkFromSig = new Fors(engine).pkFromSig(sig_fors, bArr3, this.pubKey.getSeed(), adrs);
        adrs.setType(2);
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j);
        adrs.setKeyPairAddress(i);
        return new C13370HT(engine, null, this.pubKey.getSeed()).verify(pkFromSig, sig_ht, this.pubKey.getSeed(), j, i, this.pubKey.getRoot());
    }
}
