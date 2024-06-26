package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Blake2bDigest implements ExtendedDigest {
    private static final int BLOCK_LENGTH_BYTES = 128;
    private byte[] buffer;
    private int bufferPos;
    private long[] chainValue;
    private int digestLength;

    /* renamed from: f0 */
    private long f26407f0;
    private long[] internalState;
    private byte[] key;
    private int keyLength;
    private byte[] personalization;
    private final CryptoServicePurpose purpose;
    private byte[] salt;

    /* renamed from: t0 */
    private long f26408t0;

    /* renamed from: t1 */
    private long f26409t1;
    private static final long[] blake2b_IV = {7640891576956012808L, -4942790177534073029L, 4354685564936845355L, -6534734903238641935L, 5840696475078001361L, -7276294671716946913L, 2270897969802886507L, 6620516959819538809L};
    private static final byte[][] blake2b_sigma = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}, new byte[]{11, 8, 12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, 13, 12, 11, 14, 2, 6, 5, 10, 4, 0, 15, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, 15, 14, 1, 11, 12, 6, 8, 3, 13}, new byte[]{2, 12, 6, 10, 0, 11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9}, new byte[]{12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, 11}, new byte[]{13, 11, 7, 14, 12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10}, new byte[]{6, 15, 14, 9, 11, 3, 0, 8, 12, 2, 13, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, 15, 11, 9, 14, 3, 12, 13, 0}, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}};
    private static int ROUNDS = 12;

    public Blake2bDigest() {
        this(512, CryptoServicePurpose.ANY);
    }

    public Blake2bDigest(int i) {
        this(i, CryptoServicePurpose.ANY);
    }

    public Blake2bDigest(int i, CryptoServicePurpose cryptoServicePurpose) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f26408t0 = 0L;
        this.f26409t1 = 0L;
        this.f26407f0 = 0L;
        this.purpose = cryptoServicePurpose;
        if (i < 8 || i > 512 || i % 8 != 0) {
            throw new IllegalArgumentException("BLAKE2b digest bit length must be a multiple of 8 and not greater than 512");
        }
        this.buffer = new byte[128];
        this.keyLength = 0;
        this.digestLength = i / 8;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, i, cryptoServicePurpose));
        init();
    }

    public Blake2bDigest(Blake2bDigest blake2bDigest) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f26408t0 = 0L;
        this.f26409t1 = 0L;
        this.f26407f0 = 0L;
        this.bufferPos = blake2bDigest.bufferPos;
        this.buffer = Arrays.clone(blake2bDigest.buffer);
        this.keyLength = blake2bDigest.keyLength;
        this.key = Arrays.clone(blake2bDigest.key);
        this.digestLength = blake2bDigest.digestLength;
        this.chainValue = Arrays.clone(blake2bDigest.chainValue);
        this.personalization = Arrays.clone(blake2bDigest.personalization);
        this.salt = Arrays.clone(blake2bDigest.salt);
        this.f26408t0 = blake2bDigest.f26408t0;
        this.f26409t1 = blake2bDigest.f26409t1;
        this.f26407f0 = blake2bDigest.f26407f0;
        this.purpose = blake2bDigest.purpose;
    }

    public Blake2bDigest(byte[] bArr) {
        this(bArr, CryptoServicePurpose.ANY);
    }

    public Blake2bDigest(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        this(bArr, i, bArr2, bArr3, CryptoServicePurpose.ANY);
    }

    public Blake2bDigest(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, CryptoServicePurpose cryptoServicePurpose) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f26408t0 = 0L;
        this.f26409t1 = 0L;
        this.f26407f0 = 0L;
        this.purpose = cryptoServicePurpose;
        this.buffer = new byte[128];
        if (i < 1 || i > 64) {
            throw new IllegalArgumentException("Invalid digest length (required: 1 - 64)");
        }
        this.digestLength = i;
        if (bArr2 != null) {
            if (bArr2.length != 16) {
                throw new IllegalArgumentException("salt length must be exactly 16 bytes");
            }
            this.salt = new byte[16];
            System.arraycopy(bArr2, 0, this.salt, 0, bArr2.length);
        }
        if (bArr3 != null) {
            if (bArr3.length != 16) {
                throw new IllegalArgumentException("personalization length must be exactly 16 bytes");
            }
            this.personalization = new byte[16];
            System.arraycopy(bArr3, 0, this.personalization, 0, bArr3.length);
        }
        if (bArr != null) {
            this.key = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.key, 0, bArr.length);
            if (bArr.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = bArr.length;
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, i * 8, cryptoServicePurpose));
        init();
    }

    public Blake2bDigest(byte[] bArr, CryptoServicePurpose cryptoServicePurpose) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f26408t0 = 0L;
        this.f26409t1 = 0L;
        this.f26407f0 = 0L;
        this.buffer = new byte[128];
        if (bArr != null) {
            this.key = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.key, 0, bArr.length);
            if (bArr.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = bArr.length;
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        this.purpose = cryptoServicePurpose;
        this.digestLength = 64;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, this.digestLength * 8, cryptoServicePurpose));
        init();
    }

    /* renamed from: G */
    private void m362G(long j, long j2, int i, int i2, int i3, int i4) {
        long[] jArr = this.internalState;
        jArr[i] = jArr[i] + jArr[i2] + j;
        jArr[i4] = Longs.rotateRight(jArr[i4] ^ jArr[i], 32);
        long[] jArr2 = this.internalState;
        jArr2[i3] = jArr2[i3] + jArr2[i4];
        jArr2[i2] = Longs.rotateRight(jArr2[i2] ^ jArr2[i3], 24);
        long[] jArr3 = this.internalState;
        jArr3[i] = jArr3[i] + jArr3[i2] + j2;
        jArr3[i4] = Longs.rotateRight(jArr3[i4] ^ jArr3[i], 16);
        long[] jArr4 = this.internalState;
        jArr4[i3] = jArr4[i3] + jArr4[i4];
        jArr4[i2] = Longs.rotateRight(jArr4[i2] ^ jArr4[i3], 63);
    }

    private void compress(byte[] bArr, int i) {
        initializeInternalState();
        long[] jArr = new long[16];
        Pack.littleEndianToLong(bArr, i, jArr);
        int i2 = 0;
        for (int i3 = 0; i3 < ROUNDS; i3++) {
            byte[][] bArr2 = blake2b_sigma;
            m362G(jArr[bArr2[i3][0]], jArr[bArr2[i3][1]], 0, 4, 8, 12);
            byte[][] bArr3 = blake2b_sigma;
            m362G(jArr[bArr3[i3][2]], jArr[bArr3[i3][3]], 1, 5, 9, 13);
            byte[][] bArr4 = blake2b_sigma;
            m362G(jArr[bArr4[i3][4]], jArr[bArr4[i3][5]], 2, 6, 10, 14);
            byte[][] bArr5 = blake2b_sigma;
            m362G(jArr[bArr5[i3][6]], jArr[bArr5[i3][7]], 3, 7, 11, 15);
            byte[][] bArr6 = blake2b_sigma;
            m362G(jArr[bArr6[i3][8]], jArr[bArr6[i3][9]], 0, 5, 10, 15);
            byte[][] bArr7 = blake2b_sigma;
            m362G(jArr[bArr7[i3][10]], jArr[bArr7[i3][11]], 1, 6, 11, 12);
            byte[][] bArr8 = blake2b_sigma;
            m362G(jArr[bArr8[i3][12]], jArr[bArr8[i3][13]], 2, 7, 8, 13);
            byte[][] bArr9 = blake2b_sigma;
            m362G(jArr[bArr9[i3][14]], jArr[bArr9[i3][15]], 3, 4, 9, 14);
        }
        while (true) {
            long[] jArr2 = this.chainValue;
            if (i2 >= jArr2.length) {
                return;
            }
            long j = jArr2[i2];
            long[] jArr3 = this.internalState;
            jArr2[i2] = (j ^ jArr3[i2]) ^ jArr3[i2 + 8];
            i2++;
        }
    }

    private void init() {
        if (this.chainValue == null) {
            this.chainValue = new long[8];
            long[] jArr = this.chainValue;
            long[] jArr2 = blake2b_IV;
            jArr[0] = jArr2[0] ^ ((this.digestLength | (this.keyLength << 8)) | 16842752);
            jArr[1] = jArr2[1];
            jArr[2] = jArr2[2];
            jArr[3] = jArr2[3];
            jArr[4] = jArr2[4];
            jArr[5] = jArr2[5];
            byte[] bArr = this.salt;
            if (bArr != null) {
                jArr[4] = jArr[4] ^ Pack.littleEndianToLong(bArr, 0);
                long[] jArr3 = this.chainValue;
                jArr3[5] = jArr3[5] ^ Pack.littleEndianToLong(this.salt, 8);
            }
            long[] jArr4 = this.chainValue;
            long[] jArr5 = blake2b_IV;
            jArr4[6] = jArr5[6];
            jArr4[7] = jArr5[7];
            byte[] bArr2 = this.personalization;
            if (bArr2 != null) {
                jArr4[6] = Pack.littleEndianToLong(bArr2, 0) ^ jArr4[6];
                long[] jArr6 = this.chainValue;
                jArr6[7] = jArr6[7] ^ Pack.littleEndianToLong(this.personalization, 8);
            }
        }
    }

    private void initializeInternalState() {
        long[] jArr = this.chainValue;
        System.arraycopy(jArr, 0, this.internalState, 0, jArr.length);
        System.arraycopy(blake2b_IV, 0, this.internalState, this.chainValue.length, 4);
        long[] jArr2 = this.internalState;
        long j = this.f26408t0;
        long[] jArr3 = blake2b_IV;
        jArr2[12] = j ^ jArr3[4];
        jArr2[13] = this.f26409t1 ^ jArr3[5];
        jArr2[14] = this.f26407f0 ^ jArr3[6];
        jArr2[15] = jArr3[7];
    }

    public void clearKey() {
        byte[] bArr = this.key;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            Arrays.fill(this.buffer, (byte) 0);
        }
    }

    public void clearSalt() {
        byte[] bArr = this.salt;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        this.f26407f0 = -1L;
        long j = this.f26408t0;
        int i2 = this.bufferPos;
        this.f26408t0 = j + i2;
        if (i2 > 0 && this.f26408t0 == 0) {
            this.f26409t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        Arrays.fill(this.internalState, 0L);
        int i3 = this.digestLength;
        int i4 = i3 >>> 3;
        int i5 = i3 & 7;
        Pack.longToLittleEndian(this.chainValue, 0, i4, bArr, i);
        if (i5 > 0) {
            byte[] bArr2 = new byte[8];
            Pack.longToLittleEndian(this.chainValue[i4], bArr2, 0);
            System.arraycopy(bArr2, 0, bArr, (i + this.digestLength) - i5, i5);
        }
        Arrays.fill(this.chainValue, 0L);
        reset();
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "BLAKE2b";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 128;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.bufferPos = 0;
        this.f26407f0 = 0L;
        this.f26408t0 = 0L;
        this.f26409t1 = 0L;
        this.chainValue = null;
        Arrays.fill(this.buffer, (byte) 0);
        byte[] bArr = this.key;
        if (bArr != null) {
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        init();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int i = this.bufferPos;
        if (128 - i != 0) {
            this.buffer[i] = b;
            this.bufferPos = i + 1;
            return;
        }
        this.f26408t0 += 128;
        if (this.f26408t0 == 0) {
            this.f26409t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        this.buffer[0] = b;
        this.bufferPos = 1;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr == null || i2 == 0) {
            return;
        }
        int i4 = this.bufferPos;
        if (i4 != 0) {
            i3 = 128 - i4;
            if (i3 >= i2) {
                System.arraycopy(bArr, i, this.buffer, i4, i2);
                this.bufferPos += i2;
            }
            System.arraycopy(bArr, i, this.buffer, i4, i3);
            this.f26408t0 += 128;
            if (this.f26408t0 == 0) {
                this.f26409t1++;
            }
            compress(this.buffer, 0);
            this.bufferPos = 0;
            Arrays.fill(this.buffer, (byte) 0);
        } else {
            i3 = 0;
        }
        int i5 = i2 + i;
        int i6 = i5 - 128;
        int i7 = i + i3;
        while (i7 < i6) {
            this.f26408t0 += 128;
            if (this.f26408t0 == 0) {
                this.f26409t1++;
            }
            compress(bArr, i7);
            i7 += 128;
        }
        i2 = i5 - i7;
        System.arraycopy(bArr, i7, this.buffer, 0, i2);
        this.bufferPos += i2;
    }
}
