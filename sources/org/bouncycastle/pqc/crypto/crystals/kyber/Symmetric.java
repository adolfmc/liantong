package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Symmetric {
    final int xofBlockBytes;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class AesSymmetric extends Symmetric {
        private final SICBlockCipher cipher;
        private final SHA256Digest sha256Digest;
        private final SHA512Digest sha512Digest;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AesSymmetric() {
            super(64);
            this.sha256Digest = new SHA256Digest();
            this.sha512Digest = new SHA512Digest();
            this.cipher = new SICBlockCipher(new AESEngine());
        }

        private void aes128(byte[] bArr, int i, int i2) {
            this.cipher.processBytes(new byte[i2], 0, i2, bArr, i);
        }

        private void doDigest(ExtendedDigest extendedDigest, byte[] bArr, byte[] bArr2, int i) {
            extendedDigest.update(bArr2, 0, bArr2.length);
            extendedDigest.doFinal(bArr, i);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void hash_g(byte[] bArr, byte[] bArr2) {
            doDigest(this.sha512Digest, bArr, bArr2, 0);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void hash_h(byte[] bArr, byte[] bArr2, int i) {
            doDigest(this.sha256Digest, bArr, bArr2, i);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void kdf(byte[] bArr, byte[] bArr2) {
            byte[] bArr3 = new byte[32];
            doDigest(this.sha256Digest, bArr3, bArr2, 0);
            System.arraycopy(bArr3, 0, bArr, 0, bArr.length);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void prf(byte[] bArr, byte[] bArr2, byte b) {
            byte[] bArr3 = new byte[12];
            bArr3[0] = b;
            this.cipher.init(true, new ParametersWithIV(new KeyParameter(Arrays.copyOfRange(bArr2, 0, 32)), bArr3));
            aes128(bArr, 0, bArr.length);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void xofAbsorb(byte[] bArr, byte b, byte b2) {
            byte[] bArr2 = new byte[12];
            bArr2[0] = b;
            bArr2[1] = b2;
            this.cipher.init(true, new ParametersWithIV(new KeyParameter(Arrays.copyOfRange(bArr, 0, 32)), bArr2));
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void xofSqueezeBlocks(byte[] bArr, int i, int i2) {
            aes128(bArr, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class ShakeSymmetric extends Symmetric {
        private final SHA3Digest sha3Digest256;
        private final SHA3Digest sha3Digest512;
        private final SHAKEDigest shakeDigest;
        private final SHAKEDigest xof;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ShakeSymmetric() {
            super(168);
            this.xof = new SHAKEDigest(128);
            this.shakeDigest = new SHAKEDigest(256);
            this.sha3Digest256 = new SHA3Digest(256);
            this.sha3Digest512 = new SHA3Digest(512);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void hash_g(byte[] bArr, byte[] bArr2) {
            this.sha3Digest512.update(bArr2, 0, bArr2.length);
            this.sha3Digest512.doFinal(bArr, 0);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void hash_h(byte[] bArr, byte[] bArr2, int i) {
            this.sha3Digest256.update(bArr2, 0, bArr2.length);
            this.sha3Digest256.doFinal(bArr, i);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void kdf(byte[] bArr, byte[] bArr2) {
            this.shakeDigest.update(bArr2, 0, bArr2.length);
            this.shakeDigest.doFinal(bArr, 0, bArr.length);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void prf(byte[] bArr, byte[] bArr2, byte b) {
            byte[] bArr3 = new byte[bArr2.length + 1];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            bArr3[bArr2.length] = b;
            this.shakeDigest.update(bArr3, 0, bArr3.length);
            this.shakeDigest.doFinal(bArr, 0, bArr.length);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void xofAbsorb(byte[] bArr, byte b, byte b2) {
            this.xof.reset();
            byte[] bArr2 = new byte[bArr.length + 2];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr2[bArr.length] = b;
            bArr2[bArr.length + 1] = b2;
            this.xof.update(bArr2, 0, bArr.length + 2);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.kyber.Symmetric
        void xofSqueezeBlocks(byte[] bArr, int i, int i2) {
            this.xof.doOutput(bArr, i, i2);
        }
    }

    Symmetric(int i) {
        this.xofBlockBytes = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void hash_g(byte[] bArr, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void hash_h(byte[] bArr, byte[] bArr2, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void kdf(byte[] bArr, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void prf(byte[] bArr, byte[] bArr2, byte b);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void xofAbsorb(byte[] bArr, byte b, byte b2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void xofSqueezeBlocks(byte[] bArr, int i, int i2);
}
