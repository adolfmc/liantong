package org.bouncycastle.pqc.crypto.crystals.dilithium;

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
    final int stream128BlockBytes;
    final int stream256BlockBytes;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class AesSymmetric extends Symmetric {
        private final SICBlockCipher cipher;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AesSymmetric() {
            super(64, 64);
            this.cipher = new SICBlockCipher(new AESEngine());
        }

        private void aes128(byte[] bArr, int i, int i2) {
            this.cipher.processBytes(new byte[i2], 0, i2, bArr, i);
        }

        private void streamInit(byte[] bArr, short s) {
            byte[] bArr2 = new byte[12];
            bArr2[0] = (byte) s;
            bArr2[1] = (byte) (s >> 8);
            this.cipher.init(true, new ParametersWithIV(new KeyParameter(Arrays.copyOfRange(bArr, 0, 32)), bArr2));
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream128init(byte[] bArr, short s) {
            streamInit(bArr, s);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream128squeezeBlocks(byte[] bArr, int i, int i2) {
            aes128(bArr, i, i2);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream256init(byte[] bArr, short s) {
            streamInit(bArr, s);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream256squeezeBlocks(byte[] bArr, int i, int i2) {
            aes128(bArr, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class ShakeSymmetric extends Symmetric {
        private final SHAKEDigest digest128;
        private final SHAKEDigest digest256;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ShakeSymmetric() {
            super(168, 136);
            this.digest128 = new SHAKEDigest(128);
            this.digest256 = new SHAKEDigest(256);
        }

        private void streamInit(SHAKEDigest sHAKEDigest, byte[] bArr, short s) {
            sHAKEDigest.reset();
            byte[] bArr2 = {(byte) s, (byte) (s >> 8)};
            sHAKEDigest.update(bArr, 0, bArr.length);
            sHAKEDigest.update(bArr2, 0, bArr2.length);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream128init(byte[] bArr, short s) {
            streamInit(this.digest128, bArr, s);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream128squeezeBlocks(byte[] bArr, int i, int i2) {
            this.digest128.doOutput(bArr, i, i2);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream256init(byte[] bArr, short s) {
            streamInit(this.digest256, bArr, s);
        }

        @Override // org.bouncycastle.pqc.crypto.crystals.dilithium.Symmetric
        void stream256squeezeBlocks(byte[] bArr, int i, int i2) {
            this.digest256.doOutput(bArr, i, i2);
        }
    }

    Symmetric(int i, int i2) {
        this.stream128BlockBytes = i;
        this.stream256BlockBytes = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void stream128init(byte[] bArr, short s);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void stream128squeezeBlocks(byte[] bArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void stream256init(byte[] bArr, short s);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void stream256squeezeBlocks(byte[] bArr, int i, int i2);
}
