package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KeccakDigest implements ExtendedDigest {
    private static long[] KeccakRoundConstants = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
    protected int bitsInQueue;
    protected byte[] dataQueue;
    protected int fixedOutputLength;
    protected final CryptoServicePurpose purpose;
    protected int rate;
    protected boolean squeezing;
    protected long[] state;

    public KeccakDigest() {
        this(288, CryptoServicePurpose.ANY);
    }

    public KeccakDigest(int i) {
        this(i, CryptoServicePurpose.ANY);
    }

    public KeccakDigest(int i, CryptoServicePurpose cryptoServicePurpose) {
        this.state = new long[25];
        this.dataQueue = new byte[192];
        this.purpose = cryptoServicePurpose;
        init(i);
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
    }

    public KeccakDigest(CryptoServicePurpose cryptoServicePurpose) {
        this(288, cryptoServicePurpose);
    }

    public KeccakDigest(KeccakDigest keccakDigest) {
        this.state = new long[25];
        this.dataQueue = new byte[192];
        this.purpose = keccakDigest.purpose;
        long[] jArr = keccakDigest.state;
        System.arraycopy(jArr, 0, this.state, 0, jArr.length);
        byte[] bArr = keccakDigest.dataQueue;
        System.arraycopy(bArr, 0, this.dataQueue, 0, bArr.length);
        this.rate = keccakDigest.rate;
        this.bitsInQueue = keccakDigest.bitsInQueue;
        this.fixedOutputLength = keccakDigest.fixedOutputLength;
        this.squeezing = keccakDigest.squeezing;
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
    }

    private void KeccakAbsorb(byte[] bArr, int i) {
        int i2 = this.rate >>> 6;
        for (int i3 = 0; i3 < i2; i3++) {
            long[] jArr = this.state;
            jArr[i3] = jArr[i3] ^ Pack.littleEndianToLong(bArr, i);
            i += 8;
        }
        KeccakPermutation();
    }

    private void KeccakExtract() {
        KeccakPermutation();
        Pack.longToLittleEndian(this.state, 0, this.rate >>> 6, this.dataQueue, 0);
        this.bitsInQueue = this.rate;
    }

    private void KeccakPermutation() {
        long[] jArr = this.state;
        char c = 0;
        long j = jArr[0];
        char c2 = 1;
        long j2 = jArr[1];
        long j3 = jArr[2];
        char c3 = 3;
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = jArr[8];
        long j10 = jArr[9];
        long j11 = jArr[10];
        long j12 = jArr[11];
        long j13 = jArr[12];
        long j14 = jArr[13];
        long j15 = jArr[14];
        long j16 = jArr[15];
        long j17 = jArr[16];
        long j18 = jArr[17];
        long j19 = jArr[18];
        long j20 = jArr[19];
        long j21 = jArr[20];
        long j22 = jArr[21];
        long j23 = jArr[22];
        long j24 = jArr[23];
        long j25 = jArr[24];
        long j26 = j24;
        long j27 = j23;
        long j28 = j22;
        long j29 = j21;
        long j30 = j20;
        long j31 = j19;
        long j32 = j18;
        long j33 = j17;
        long j34 = j16;
        long j35 = j15;
        long j36 = j14;
        long j37 = j13;
        long j38 = j12;
        long j39 = j11;
        long j40 = j10;
        long j41 = j9;
        long j42 = j8;
        long j43 = j7;
        long j44 = j6;
        long j45 = j5;
        long j46 = j4;
        long j47 = j3;
        long j48 = j2;
        long j49 = j;
        int i = 0;
        while (i < 24) {
            long j50 = (((j49 ^ j44) ^ j39) ^ j34) ^ j29;
            long j51 = (((j48 ^ j43) ^ j38) ^ j33) ^ j28;
            long j52 = (((j47 ^ j42) ^ j37) ^ j32) ^ j27;
            long j53 = (((j46 ^ j41) ^ j36) ^ j31) ^ j26;
            long j54 = (((j45 ^ j40) ^ j35) ^ j30) ^ j25;
            long j55 = ((j51 << c2) | (j51 >>> (-1))) ^ j54;
            long j56 = ((j52 << c2) | (j52 >>> (-1))) ^ j50;
            long j57 = ((j53 << c2) | (j53 >>> (-1))) ^ j51;
            long j58 = ((j54 << c2) | (j54 >>> (-1))) ^ j52;
            long j59 = ((j50 << c2) | (j50 >>> (-1))) ^ j53;
            long j60 = j49 ^ j55;
            long j61 = j44 ^ j55;
            long j62 = j39 ^ j55;
            long j63 = j34 ^ j55;
            long j64 = j29 ^ j55;
            long j65 = j48 ^ j56;
            long j66 = j43 ^ j56;
            long j67 = j38 ^ j56;
            long j68 = j33 ^ j56;
            long j69 = j28 ^ j56;
            long j70 = j47 ^ j57;
            long j71 = j42 ^ j57;
            long j72 = j37 ^ j57;
            long j73 = j32 ^ j57;
            long j74 = j27 ^ j57;
            long j75 = j46 ^ j58;
            long j76 = j41 ^ j58;
            long j77 = j36 ^ j58;
            long j78 = j31 ^ j58;
            long j79 = j26 ^ j58;
            long j80 = j45 ^ j59;
            long j81 = j40 ^ j59;
            long j82 = j35 ^ j59;
            long j83 = j30 ^ j59;
            long j84 = j25 ^ j59;
            long j85 = (j65 << c2) | (j65 >>> 63);
            int i2 = i;
            long j86 = (j66 << 44) | (j66 >>> 20);
            long j87 = (j81 << 20) | (j81 >>> 44);
            long j88 = (j74 << 61) | (j74 >>> c3);
            long j89 = (j82 << 39) | (j82 >>> 25);
            long j90 = (j64 << 18) | (j64 >>> 46);
            long j91 = (j70 << 62) | (j70 >>> 2);
            long j92 = (j72 << 43) | (j72 >>> 21);
            long j93 = (j77 << 25) | (j77 >>> 39);
            long j94 = (j83 << 8) | (j83 >>> 56);
            long j95 = (j79 << 56) | (j79 >>> 8);
            long j96 = (j63 << 41) | (j63 >>> 23);
            long j97 = (j80 << 27) | (j80 >>> 37);
            long j98 = (j84 << 14) | (j84 >>> 50);
            long j99 = (j69 << 2) | (j69 >>> 62);
            long j100 = (j76 << 55) | (j76 >>> 9);
            long j101 = (j68 << 45) | (j68 >>> 19);
            long j102 = (j61 << 36) | (j61 >>> 28);
            long j103 = (j75 << 28) | (j75 >>> 36);
            long j104 = (j78 << 21) | (j78 >>> 43);
            long j105 = (j73 << 15) | (j73 >>> 49);
            long j106 = (j67 << 10) | (j67 >>> 54);
            long j107 = (j71 << 6) | (j71 >>> 58);
            long j108 = (j62 << 3) | (j62 >>> 61);
            long j109 = ((~j86) & j92) ^ j60;
            long j110 = ((~j92) & j104) ^ j86;
            j47 = j92 ^ ((~j104) & j98);
            j46 = ((~j98) & j60) ^ j104;
            long j111 = j98 ^ (j86 & (~j60));
            long j112 = j103 ^ ((~j87) & j108);
            long j113 = ((~j108) & j101) ^ j87;
            long j114 = ((~j101) & j88) ^ j108;
            long j115 = ((~j88) & j103) ^ j101;
            long j116 = (j87 & (~j103)) ^ j88;
            j39 = j85 ^ ((~j107) & j93);
            long j117 = ((~j93) & j94) ^ j107;
            long j118 = ((~j94) & j90) ^ j93;
            long j119 = j94 ^ ((~j90) & j85);
            long j120 = ((~j85) & j107) ^ j90;
            long j121 = j97 ^ ((~j102) & j106);
            long j122 = ((~j106) & j105) ^ j102;
            long j123 = j106 ^ ((~j105) & j95);
            long j124 = ((~j95) & j97) ^ j105;
            long j125 = ((~j97) & j102) ^ j95;
            j29 = j91 ^ ((~j100) & j89);
            long j126 = ((~j89) & j96) ^ j100;
            long j127 = ((~j96) & j99) ^ j89;
            long j128 = j96 ^ ((~j99) & j91);
            j25 = j99 ^ ((~j91) & j100);
            j37 = j118;
            j36 = j119;
            j44 = j112;
            c2 = 1;
            j30 = j125;
            j40 = j116;
            j31 = j124;
            j38 = j117;
            j35 = j120;
            j28 = j126;
            j33 = j122;
            j42 = j114;
            j27 = j127;
            j43 = j113;
            j41 = j115;
            j45 = j111;
            j49 = j109 ^ KeccakRoundConstants[i2];
            i = i2 + 1;
            j32 = j123;
            c = 0;
            j34 = j121;
            j48 = j110;
            c3 = 3;
            j26 = j128;
            jArr = jArr;
        }
        long[] jArr2 = jArr;
        jArr2[c] = j49;
        jArr2[1] = j48;
        jArr2[2] = j47;
        jArr2[3] = j46;
        jArr2[4] = j45;
        jArr2[5] = j44;
        jArr2[6] = j43;
        jArr2[7] = j42;
        jArr2[8] = j41;
        jArr2[9] = j40;
        jArr2[10] = j39;
        jArr2[11] = j38;
        jArr2[12] = j37;
        jArr2[13] = j36;
        jArr2[14] = j35;
        jArr2[15] = j34;
        jArr2[16] = j33;
        jArr2[17] = j32;
        jArr2[18] = j31;
        jArr2[19] = j30;
        jArr2[20] = j29;
        jArr2[21] = j28;
        jArr2[22] = j27;
        jArr2[23] = j26;
        jArr2[24] = j25;
    }

    private void init(int i) {
        if (i != 128 && i != 224 && i != 256 && i != 288 && i != 384 && i != 512) {
            throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
        }
        initSponge(1600 - (i << 1));
    }

    private void initSponge(int i) {
        if (i <= 0 || i >= 1600 || i % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        }
        this.rate = i;
        int i2 = 0;
        while (true) {
            long[] jArr = this.state;
            if (i2 >= jArr.length) {
                Arrays.fill(this.dataQueue, (byte) 0);
                this.bitsInQueue = 0;
                this.squeezing = false;
                this.fixedOutputLength = (1600 - i) / 2;
                return;
            }
            jArr[i2] = 0;
            i2++;
        }
    }

    private void padAndSwitchToSqueezingPhase() {
        byte[] bArr = this.dataQueue;
        int i = this.bitsInQueue;
        int i2 = i >>> 3;
        bArr[i2] = (byte) (bArr[i2] | ((byte) (1 << (i & 7))));
        int i3 = i + 1;
        this.bitsInQueue = i3;
        if (i3 == this.rate) {
            KeccakAbsorb(bArr, 0);
        } else {
            int i4 = this.bitsInQueue;
            int i5 = i4 >>> 6;
            int i6 = i4 & 63;
            int i7 = 0;
            for (int i8 = 0; i8 < i5; i8++) {
                long[] jArr = this.state;
                jArr[i8] = jArr[i8] ^ Pack.littleEndianToLong(this.dataQueue, i7);
                i7 += 8;
            }
            if (i6 > 0) {
                long[] jArr2 = this.state;
                jArr2[i5] = jArr2[i5] ^ (((1 << i6) - 1) & Pack.littleEndianToLong(this.dataQueue, i7));
            }
        }
        long[] jArr3 = this.state;
        int i9 = (this.rate - 1) >>> 6;
        jArr3[i9] = jArr3[i9] ^ Long.MIN_VALUE;
        this.bitsInQueue = 0;
        this.squeezing = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void absorb(byte b) {
        int i = this.bitsInQueue;
        if (i % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        byte[] bArr = this.dataQueue;
        bArr[i >>> 3] = b;
        int i2 = i + 8;
        this.bitsInQueue = i2;
        if (i2 == this.rate) {
            KeccakAbsorb(bArr, 0);
            this.bitsInQueue = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void absorb(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = this.bitsInQueue;
        if (i6 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        int i7 = i6 >>> 3;
        int i8 = this.rate >>> 3;
        int i9 = i8 - i7;
        if (i2 < i9) {
            System.arraycopy(bArr, i, this.dataQueue, i7, i2);
            i5 = this.bitsInQueue + (i2 << 3);
        } else {
            if (i7 > 0) {
                System.arraycopy(bArr, i, this.dataQueue, i7, i9);
                i3 = i9 + 0;
                KeccakAbsorb(this.dataQueue, 0);
            } else {
                i3 = 0;
            }
            while (true) {
                i4 = i2 - i3;
                if (i4 < i8) {
                    break;
                }
                KeccakAbsorb(bArr, i + i3);
                i3 += i8;
            }
            System.arraycopy(bArr, i + i3, this.dataQueue, 0, i4);
            i5 = i4 << 3;
        }
        this.bitsInQueue = i5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void absorbBits(int i, int i2) {
        if (i2 < 1 || i2 > 7) {
            throw new IllegalArgumentException("'bits' must be in the range 1 to 7");
        }
        int i3 = this.bitsInQueue;
        if (i3 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        this.dataQueue[i3 >>> 3] = (byte) (i & ((1 << i2) - 1));
        this.bitsInQueue = i3 + i2;
    }

    protected CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, getDigestSize() * 8, this.purpose);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        squeeze(bArr, i, this.fixedOutputLength);
        reset();
        return getDigestSize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int doFinal(byte[] bArr, int i, byte b, int i2) {
        if (i2 > 0) {
            absorbBits(b, i2);
        }
        squeeze(bArr, i, this.fixedOutputLength);
        reset();
        return getDigestSize();
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Keccak-" + this.fixedOutputLength;
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.rate / 8;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.fixedOutputLength / 8;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        init(this.fixedOutputLength);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void squeeze(byte[] bArr, int i, long j) {
        if (!this.squeezing) {
            padAndSwitchToSqueezingPhase();
        }
        long j2 = 0;
        if (j % 8 != 0) {
            throw new IllegalStateException("outputLength not a multiple of 8");
        }
        while (j2 < j) {
            if (this.bitsInQueue == 0) {
                KeccakExtract();
            }
            int min = (int) Math.min(this.bitsInQueue, j - j2);
            System.arraycopy(this.dataQueue, (this.rate - this.bitsInQueue) / 8, bArr, ((int) (j2 / 8)) + i, min / 8);
            this.bitsInQueue -= min;
            j2 += min;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        absorb(b);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        absorb(bArr, i, i2);
    }
}
