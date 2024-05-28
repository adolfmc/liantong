package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.BlockCipher;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class X931RNG {
    private static final int BLOCK128_MAX_BITS_REQUEST = 262144;
    private static final long BLOCK128_RESEED_MAX = 8388608;
    private static final int BLOCK64_MAX_BITS_REQUEST = 4096;
    private static final long BLOCK64_RESEED_MAX = 32768;

    /* renamed from: DT */
    private final byte[] f26841DT;

    /* renamed from: I */
    private final byte[] f26842I;

    /* renamed from: R */
    private final byte[] f26843R;

    /* renamed from: V */
    private byte[] f26844V;
    private final BlockCipher engine;
    private final EntropySource entropySource;
    private long reseedCounter = 1;

    public X931RNG(BlockCipher blockCipher, byte[] bArr, EntropySource entropySource) {
        this.engine = blockCipher;
        this.entropySource = entropySource;
        this.f26841DT = new byte[blockCipher.getBlockSize()];
        byte[] bArr2 = this.f26841DT;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.f26842I = new byte[blockCipher.getBlockSize()];
        this.f26843R = new byte[blockCipher.getBlockSize()];
    }

    private void increment(byte[] bArr) {
        for (int length = bArr.length - 1; length >= 0; length--) {
            byte b = (byte) (bArr[length] + 1);
            bArr[length] = b;
            if (b != 0) {
                return;
            }
        }
    }

    private static boolean isTooLarge(byte[] bArr, int i) {
        return bArr != null && bArr.length > i;
    }

    private void process(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) (bArr2[i] ^ bArr3[i]);
        }
        this.engine.processBlock(bArr, 0, bArr, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int generate(byte[] bArr, boolean z) {
        if (this.f26843R.length == 8) {
            if (this.reseedCounter > 32768) {
                return -1;
            }
            if (isTooLarge(bArr, 512)) {
                throw new IllegalArgumentException("Number of bits per request limited to 4096");
            }
        } else if (this.reseedCounter > 8388608) {
            return -1;
        } else {
            if (isTooLarge(bArr, 32768)) {
                throw new IllegalArgumentException("Number of bits per request limited to 262144");
            }
        }
        if (z || this.f26844V == null) {
            this.f26844V = this.entropySource.getEntropy();
            if (this.f26844V.length != this.engine.getBlockSize()) {
                throw new IllegalStateException("Insufficient entropy returned");
            }
        }
        int length = bArr.length / this.f26843R.length;
        for (int i = 0; i < length; i++) {
            this.engine.processBlock(this.f26841DT, 0, this.f26842I, 0);
            process(this.f26843R, this.f26842I, this.f26844V);
            process(this.f26844V, this.f26843R, this.f26842I);
            byte[] bArr2 = this.f26843R;
            System.arraycopy(bArr2, 0, bArr, bArr2.length * i, bArr2.length);
            increment(this.f26841DT);
        }
        int length2 = bArr.length - (this.f26843R.length * length);
        if (length2 > 0) {
            this.engine.processBlock(this.f26841DT, 0, this.f26842I, 0);
            process(this.f26843R, this.f26842I, this.f26844V);
            process(this.f26844V, this.f26843R, this.f26842I);
            byte[] bArr3 = this.f26843R;
            System.arraycopy(bArr3, 0, bArr, length * bArr3.length, length2);
            increment(this.f26841DT);
        }
        this.reseedCounter++;
        return bArr.length * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EntropySource getEntropySource() {
        return this.entropySource;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reseed() {
        this.f26844V = this.entropySource.getEntropy();
        if (this.f26844V.length != this.engine.getBlockSize()) {
            throw new IllegalStateException("Insufficient entropy returned");
        }
        this.reseedCounter = 1L;
    }
}
