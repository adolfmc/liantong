package org.bouncycastle.crypto.prng.drbg;

import java.util.Hashtable;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HashSP800DRBG implements SP80090DRBG {
    private static final int MAX_BITS_REQUEST = 262144;
    private static final long RESEED_MAX = 140737488355328L;

    /* renamed from: _C */
    private byte[] f26853_C;

    /* renamed from: _V */
    private byte[] f26854_V;
    private Digest _digest;
    private EntropySource _entropySource;
    private long _reseedCounter;
    private int _securityStrength;
    private int _seedLength;
    private static final byte[] ONE = {1};
    private static final Hashtable seedlens = new Hashtable();

    static {
        seedlens.put("SHA-1", Integers.valueOf(440));
        seedlens.put("SHA-224", Integers.valueOf(440));
        seedlens.put("SHA-256", Integers.valueOf(440));
        seedlens.put("SHA-512/256", Integers.valueOf(440));
        seedlens.put("SHA-512/224", Integers.valueOf(440));
        seedlens.put("SHA-384", Integers.valueOf(888));
        seedlens.put("SHA-512", Integers.valueOf(888));
    }

    public HashSP800DRBG(Digest digest, int i, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        if (i > Utils.getMaxSecurityStrength(digest)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (entropySource.entropySize() < i) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        this._digest = digest;
        this._entropySource = entropySource;
        this._securityStrength = i;
        this._seedLength = ((Integer) seedlens.get(digest.getAlgorithmName())).intValue();
        this.f26854_V = Utils.hash_df(this._digest, Arrays.concatenate(getEntropy(), bArr2, bArr), this._seedLength);
        byte[] bArr3 = this.f26854_V;
        byte[] bArr4 = new byte[bArr3.length + 1];
        System.arraycopy(bArr3, 0, bArr4, 1, bArr3.length);
        this.f26853_C = Utils.hash_df(this._digest, bArr4, this._seedLength);
        this._reseedCounter = 1L;
    }

    private void addTo(byte[] bArr, byte[] bArr2) {
        int i = 0;
        for (int i2 = 1; i2 <= bArr2.length; i2++) {
            int i3 = (bArr[bArr.length - i2] & 255) + (bArr2[bArr2.length - i2] & 255) + i;
            i = i3 > 255 ? 1 : 0;
            bArr[bArr.length - i2] = (byte) i3;
        }
        for (int length = bArr2.length + 1; length <= bArr.length; length++) {
            int i4 = (bArr[bArr.length - length] & 255) + i;
            i = i4 > 255 ? 1 : 0;
            bArr[bArr.length - length] = (byte) i4;
        }
    }

    private void doHash(byte[] bArr, byte[] bArr2) {
        this._digest.update(bArr, 0, bArr.length);
        this._digest.doFinal(bArr2, 0);
    }

    private byte[] getEntropy() {
        byte[] entropy = this._entropySource.getEntropy();
        if (entropy.length >= (this._securityStrength + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    private byte[] hash(byte[] bArr) {
        byte[] bArr2 = new byte[this._digest.getDigestSize()];
        doHash(bArr, bArr2);
        return bArr2;
    }

    private byte[] hashgen(byte[] bArr, int i) {
        int i2 = i / 8;
        int digestSize = i2 / this._digest.getDigestSize();
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        byte[] bArr3 = new byte[i2];
        byte[] bArr4 = new byte[this._digest.getDigestSize()];
        for (int i3 = 0; i3 <= digestSize; i3++) {
            doHash(bArr2, bArr4);
            System.arraycopy(bArr4, 0, bArr3, bArr4.length * i3, bArr3.length - (bArr4.length * i3) > bArr4.length ? bArr4.length : bArr3.length - (bArr4.length * i3));
            addTo(bArr2, ONE);
        }
        return bArr3;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int generate(byte[] bArr, byte[] bArr2, boolean z) {
        long j;
        int length = bArr.length * 8;
        if (length <= 262144) {
            if (this._reseedCounter > 140737488355328L) {
                return -1;
            }
            if (z) {
                reseed(bArr2);
                bArr2 = null;
            }
            if (bArr2 != null) {
                byte[] bArr3 = this.f26854_V;
                byte[] bArr4 = new byte[bArr3.length + 1 + bArr2.length];
                bArr4[0] = 2;
                System.arraycopy(bArr3, 0, bArr4, 1, bArr3.length);
                System.arraycopy(bArr2, 0, bArr4, this.f26854_V.length + 1, bArr2.length);
                addTo(this.f26854_V, hash(bArr4));
            }
            byte[] hashgen = hashgen(this.f26854_V, length);
            byte[] bArr5 = this.f26854_V;
            byte[] bArr6 = new byte[bArr5.length + 1];
            System.arraycopy(bArr5, 0, bArr6, 1, bArr5.length);
            bArr6[0] = 3;
            addTo(this.f26854_V, hash(bArr6));
            addTo(this.f26854_V, this.f26853_C);
            addTo(this.f26854_V, new byte[]{(byte) (j >> 24), (byte) (j >> 16), (byte) (j >> 8), (byte) this._reseedCounter});
            this._reseedCounter++;
            System.arraycopy(hashgen, 0, bArr, 0, bArr.length);
            return length;
        }
        throw new IllegalArgumentException("Number of bits per request limited to 262144");
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int getBlockSize() {
        return this._digest.getDigestSize() * 8;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public void reseed(byte[] bArr) {
        this.f26854_V = Utils.hash_df(this._digest, Arrays.concatenate(ONE, this.f26854_V, getEntropy(), bArr), this._seedLength);
        byte[] bArr2 = this.f26854_V;
        byte[] bArr3 = new byte[bArr2.length + 1];
        bArr3[0] = 0;
        System.arraycopy(bArr2, 0, bArr3, 1, bArr2.length);
        this.f26853_C = Utils.hash_df(this._digest, bArr3, this._seedLength);
        this._reseedCounter = 1L;
    }
}
