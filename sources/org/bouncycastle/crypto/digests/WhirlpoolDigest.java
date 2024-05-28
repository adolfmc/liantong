package org.bouncycastle.crypto.digests;

import com.cjt2325.cameralibrary.CameraInterface;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class WhirlpoolDigest implements ExtendedDigest, Memoable {
    private static final int BITCOUNT_ARRAY_SIZE = 32;
    private static final int BYTE_LENGTH = 64;
    private static final int DIGEST_LENGTH_BYTES = 64;
    private static final int REDUCTION_POLYNOMIAL = 285;
    private static final int ROUNDS = 10;

    /* renamed from: _K */
    private long[] f26548_K;

    /* renamed from: _L */
    private long[] f26549_L;
    private short[] _bitCount;
    private long[] _block;
    private byte[] _buffer;
    private int _bufferPos;
    private long[] _hash;
    private final long[] _rc;
    private long[] _state;
    private final CryptoServicePurpose purpose;
    private static final int[] SBOX = {24, 35, 198, 232, 135, 184, 1, 79, 54, 166, 210, 245, 121, 111, CameraInterface.TYPE_CAPTURE, 82, 96, 188, 155, 142, 163, 12, 123, 53, 29, 224, 215, 194, 46, 75, 254, 87, 21, 119, 55, 229, 159, 240, 74, 218, 88, 201, 41, 10, 177, C0567f.f1819h, 107, 133, 189, 93, 16, 244, 203, 62, 5, 103, 228, 39, 65, 139, 167, 125, 149, 216, 251, 238, 124, 102, 221, 23, 71, 158, 202, 45, 191, 7, 173, 90, 131, 51, 99, 2, 170, 113, 200, 25, 73, 217, 242, 227, 91, 136, 154, 38, 50, 176, 233, 15, 213, 128, 190, 205, 52, 72, 255, 122, 144, 95, 32, 104, 26, 174, 180, 84, 147, 34, 100, 241, 115, 18, 64, 8, 195, 236, 219, 161, 141, 61, 151, 0, 207, 43, 118, 130, 214, 27, 181, 175, 106, 80, 69, 243, 48, 239, 63, 85, 162, 234, 101, 186, 47, 192, 222, 28, 253, 77, 146, 117, 6, 138, 178, 230, 14, 31, 98, 212, 168, 150, 249, 197, 37, 89, 132, 114, 57, 76, 94, 120, 56, 140, 209, 165, 226, 97, 179, 33, 156, 30, 67, 199, 252, 4, 81, 153, 109, 13, 250, 223, 126, 36, 59, 171, 206, 17, 143, 78, 183, 235, 60, 129, 148, 247, 185, 19, 44, 211, 231, 110, 196, 3, 86, 68, 127, 169, 42, 187, 193, 83, 220, 11, 157, 108, 49, 116, 246, 70, 172, 137, 20, 225, 22, 58, 105, 9, 112, 182, 208, 237, 204, 66, 152, 164, 40, 92, 248, 134};

    /* renamed from: C0 */
    private static final long[] f26540C0 = new long[256];

    /* renamed from: C1 */
    private static final long[] f26541C1 = new long[256];

    /* renamed from: C2 */
    private static final long[] f26542C2 = new long[256];

    /* renamed from: C3 */
    private static final long[] f26543C3 = new long[256];

    /* renamed from: C4 */
    private static final long[] f26544C4 = new long[256];

    /* renamed from: C5 */
    private static final long[] f26545C5 = new long[256];

    /* renamed from: C6 */
    private static final long[] f26546C6 = new long[256];

    /* renamed from: C7 */
    private static final long[] f26547C7 = new long[256];
    private static final short[] EIGHT = new short[32];

    static {
        EIGHT[31] = 8;
        for (int i = 0; i < 256; i++) {
            int i2 = SBOX[i];
            int mulX = mulX(i2);
            int mulX2 = mulX(mulX);
            int i3 = mulX2 ^ i2;
            int mulX3 = mulX(mulX2);
            int i4 = mulX3 ^ i2;
            f26540C0[i] = packIntoLong(i2, i2, mulX2, i2, mulX3, i3, mulX, i4);
            f26541C1[i] = packIntoLong(i4, i2, i2, mulX2, i2, mulX3, i3, mulX);
            f26542C2[i] = packIntoLong(mulX, i4, i2, i2, mulX2, i2, mulX3, i3);
            f26543C3[i] = packIntoLong(i3, mulX, i4, i2, i2, mulX2, i2, mulX3);
            f26544C4[i] = packIntoLong(mulX3, i3, mulX, i4, i2, i2, mulX2, i2);
            f26545C5[i] = packIntoLong(i2, mulX3, i3, mulX, i4, i2, i2, mulX2);
            f26546C6[i] = packIntoLong(mulX2, i2, mulX3, i3, mulX, i4, i2, i2);
            f26547C7[i] = packIntoLong(i2, mulX2, i2, mulX3, i3, mulX, i4, i2);
        }
    }

    public WhirlpoolDigest() {
        this(CryptoServicePurpose.ANY);
    }

    public WhirlpoolDigest(CryptoServicePurpose cryptoServicePurpose) {
        this._rc = new long[11];
        this._buffer = new byte[64];
        this._bufferPos = 0;
        this._bitCount = new short[32];
        this._hash = new long[8];
        this.f26548_K = new long[8];
        this.f26549_L = new long[8];
        this._block = new long[8];
        this._state = new long[8];
        this._rc[0] = 0;
        for (int i = 1; i <= 10; i++) {
            int i2 = (i - 1) * 8;
            this._rc[i] = (((((((f26540C0[i2] & (-72057594037927936L)) ^ (f26541C1[i2 + 1] & 71776119061217280L)) ^ (f26542C2[i2 + 2] & 280375465082880L)) ^ (f26543C3[i2 + 3] & 1095216660480L)) ^ (f26544C4[i2 + 4] & 4278190080L)) ^ (f26545C5[i2 + 5] & 16711680)) ^ (f26546C6[i2 + 6] & 65280)) ^ (f26547C7[i2 + 7] & 255);
        }
        this.purpose = cryptoServicePurpose;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, getDigestSize(), cryptoServicePurpose));
    }

    public WhirlpoolDigest(WhirlpoolDigest whirlpoolDigest) {
        this._rc = new long[11];
        this._buffer = new byte[64];
        this._bufferPos = 0;
        this._bitCount = new short[32];
        this._hash = new long[8];
        this.f26548_K = new long[8];
        this.f26549_L = new long[8];
        this._block = new long[8];
        this._state = new long[8];
        this.purpose = whirlpoolDigest.purpose;
        reset(whirlpoolDigest);
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, getDigestSize(), this.purpose));
    }

    private byte[] copyBitLength() {
        byte[] bArr = new byte[32];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (this._bitCount[i] & 255);
        }
        return bArr;
    }

    private void finish() {
        byte[] copyBitLength = copyBitLength();
        byte[] bArr = this._buffer;
        int i = this._bufferPos;
        bArr[i] = (byte) (bArr[i] | 128);
        int i2 = i + 1;
        this._bufferPos = i2;
        if (i2 == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        if (this._bufferPos > 32) {
            while (this._bufferPos != 0) {
                update((byte) 0);
            }
        }
        while (this._bufferPos <= 32) {
            update((byte) 0);
        }
        System.arraycopy(copyBitLength, 0, this._buffer, 32, copyBitLength.length);
        processFilledBuffer(this._buffer, 0);
    }

    private void increment() {
        int i = 0;
        for (int length = this._bitCount.length - 1; length >= 0; length--) {
            short[] sArr = this._bitCount;
            int i2 = (sArr[length] & 255) + EIGHT[length] + i;
            i = i2 >>> 8;
            sArr[length] = (short) (i2 & 255);
        }
    }

    private static int mulX(int i) {
        return ((-(i >>> 7)) & 285) ^ (i << 1);
    }

    private static long packIntoLong(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return (((((((i2 << 48) ^ (i << 56)) ^ (i3 << 40)) ^ (i4 << 32)) ^ (i5 << 24)) ^ (i6 << 16)) ^ (i7 << 8)) ^ i8;
    }

    private void processFilledBuffer(byte[] bArr, int i) {
        Pack.bigEndianToLong(this._buffer, 0, this._block);
        processBlock();
        this._bufferPos = 0;
        Arrays.fill(this._buffer, (byte) 0);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new WhirlpoolDigest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.longToBigEndian(this._hash, bArr, i);
        reset();
        return getDigestSize();
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Whirlpool";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 64;
    }

    protected void processBlock() {
        for (int i = 0; i < 8; i++) {
            long[] jArr = this._state;
            long j = this._block[i];
            long[] jArr2 = this.f26548_K;
            long j2 = this._hash[i];
            jArr2[i] = j2;
            jArr[i] = j ^ j2;
        }
        int i2 = 1;
        while (i2 <= 10) {
            int i3 = 0;
            while (i3 < 8) {
                long[] jArr3 = this.f26549_L;
                jArr3[i3] = 0;
                long j3 = jArr3[i3];
                long[] jArr4 = f26540C0;
                long[] jArr5 = this.f26548_K;
                jArr3[i3] = jArr4[((int) (jArr5[(i3 + 0) & 7] >>> 56)) & 255] ^ j3;
                jArr3[i3] = jArr3[i3] ^ f26541C1[((int) (jArr5[(i3 - 1) & 7] >>> 48)) & 255];
                jArr3[i3] = jArr3[i3] ^ f26542C2[((int) (jArr5[(i3 - 2) & 7] >>> 40)) & 255];
                jArr3[i3] = jArr3[i3] ^ f26543C3[((int) (jArr5[(i3 - 3) & 7] >>> 32)) & 255];
                jArr3[i3] = jArr3[i3] ^ f26544C4[((int) (jArr5[(i3 - 4) & 7] >>> 24)) & 255];
                jArr3[i3] = jArr3[i3] ^ f26545C5[((int) (jArr5[(i3 - 5) & 7] >>> 16)) & 255];
                jArr3[i3] = jArr3[i3] ^ f26546C6[((int) (jArr5[(i3 - 6) & 7] >>> 8)) & 255];
                jArr3[i3] = jArr3[i3] ^ f26547C7[((int) jArr5[(i3 - 7) & 7]) & 255];
                i3++;
                i2 = i2;
            }
            int i4 = i2;
            long[] jArr6 = this.f26549_L;
            long[] jArr7 = this.f26548_K;
            System.arraycopy(jArr6, 0, jArr7, 0, jArr7.length);
            long[] jArr8 = this.f26548_K;
            jArr8[0] = jArr8[0] ^ this._rc[i4];
            for (int i5 = 0; i5 < 8; i5++) {
                long[] jArr9 = this.f26549_L;
                jArr9[i5] = this.f26548_K[i5];
                long j4 = jArr9[i5];
                long[] jArr10 = f26540C0;
                long[] jArr11 = this._state;
                jArr9[i5] = j4 ^ jArr10[((int) (jArr11[(i5 + 0) & 7] >>> 56)) & 255];
                jArr9[i5] = jArr9[i5] ^ f26541C1[((int) (jArr11[(i5 - 1) & 7] >>> 48)) & 255];
                jArr9[i5] = jArr9[i5] ^ f26542C2[((int) (jArr11[(i5 - 2) & 7] >>> 40)) & 255];
                jArr9[i5] = jArr9[i5] ^ f26543C3[((int) (jArr11[(i5 - 3) & 7] >>> 32)) & 255];
                jArr9[i5] = jArr9[i5] ^ f26544C4[((int) (jArr11[(i5 - 4) & 7] >>> 24)) & 255];
                jArr9[i5] = jArr9[i5] ^ f26545C5[((int) (jArr11[(i5 - 5) & 7] >>> 16)) & 255];
                jArr9[i5] = jArr9[i5] ^ f26546C6[((int) (jArr11[(i5 - 6) & 7] >>> 8)) & 255];
                jArr9[i5] = jArr9[i5] ^ f26547C7[((int) jArr11[(i5 - 7) & 7]) & 255];
            }
            long[] jArr12 = this.f26549_L;
            long[] jArr13 = this._state;
            System.arraycopy(jArr12, 0, jArr13, 0, jArr13.length);
            i2 = i4 + 1;
        }
        for (int i6 = 0; i6 < 8; i6++) {
            long[] jArr14 = this._hash;
            jArr14[i6] = jArr14[i6] ^ (this._state[i6] ^ this._block[i6]);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this._bufferPos = 0;
        Arrays.fill(this._bitCount, (short) 0);
        Arrays.fill(this._buffer, (byte) 0);
        Arrays.fill(this._hash, 0L);
        Arrays.fill(this.f26548_K, 0L);
        Arrays.fill(this.f26549_L, 0L);
        Arrays.fill(this._block, 0L);
        Arrays.fill(this._state, 0L);
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        WhirlpoolDigest whirlpoolDigest = (WhirlpoolDigest) memoable;
        long[] jArr = whirlpoolDigest._rc;
        long[] jArr2 = this._rc;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        byte[] bArr = whirlpoolDigest._buffer;
        byte[] bArr2 = this._buffer;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this._bufferPos = whirlpoolDigest._bufferPos;
        short[] sArr = whirlpoolDigest._bitCount;
        short[] sArr2 = this._bitCount;
        System.arraycopy(sArr, 0, sArr2, 0, sArr2.length);
        long[] jArr3 = whirlpoolDigest._hash;
        long[] jArr4 = this._hash;
        System.arraycopy(jArr3, 0, jArr4, 0, jArr4.length);
        long[] jArr5 = whirlpoolDigest.f26548_K;
        long[] jArr6 = this.f26548_K;
        System.arraycopy(jArr5, 0, jArr6, 0, jArr6.length);
        long[] jArr7 = whirlpoolDigest.f26549_L;
        long[] jArr8 = this.f26549_L;
        System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
        long[] jArr9 = whirlpoolDigest._block;
        long[] jArr10 = this._block;
        System.arraycopy(jArr9, 0, jArr10, 0, jArr10.length);
        long[] jArr11 = whirlpoolDigest._state;
        long[] jArr12 = this._state;
        System.arraycopy(jArr11, 0, jArr12, 0, jArr12.length);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this._buffer;
        int i = this._bufferPos;
        bArr[i] = b;
        int i2 = i + 1;
        this._bufferPos = i2;
        if (i2 == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        increment();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
