package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class Kangaroo {
    private static final int DIGESTLEN = 32;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static abstract class KangarooBase implements ExtendedDigest, Xof {
        private static final int BLKSIZE = 8192;
        private final CryptoServicePurpose purpose;
        private final byte[] singleByte = new byte[1];
        private boolean squeezing;
        private final int theChainLen;
        private int theCurrNode;
        private final KangarooSponge theLeaf;
        private byte[] thePersonal;
        private int theProcessed;
        private final KangarooSponge theTree;
        private static final byte[] SINGLE = {7};
        private static final byte[] INTERMEDIATE = {11};
        private static final byte[] FINAL = {-1, -1, 6};
        private static final byte[] FIRST = {3, 0, 0, 0, 0, 0, 0, 0};

        KangarooBase(int i, int i2, int i3, CryptoServicePurpose cryptoServicePurpose) {
            this.theTree = new KangarooSponge(i, i2);
            this.theLeaf = new KangarooSponge(i, i2);
            this.theChainLen = i >> 2;
            buildPersonal(null);
            this.purpose = cryptoServicePurpose;
            CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, i, cryptoServicePurpose));
        }

        private void buildPersonal(byte[] bArr) {
            int length = bArr == null ? 0 : bArr.length;
            byte[] lengthEncode = lengthEncode(length);
            this.thePersonal = bArr == null ? new byte[lengthEncode.length + length] : Arrays.copyOf(bArr, lengthEncode.length + length);
            System.arraycopy(lengthEncode, 0, this.thePersonal, length, lengthEncode.length);
        }

        private static byte[] lengthEncode(long j) {
            byte b;
            if (j != 0) {
                long j2 = j;
                b = 1;
                while (true) {
                    j2 >>= 8;
                    if (j2 == 0) {
                        break;
                    }
                    b = (byte) (b + 1);
                }
            } else {
                b = 0;
            }
            byte[] bArr = new byte[b + 1];
            bArr[b] = b;
            for (int i = 0; i < b; i++) {
                bArr[i] = (byte) (j >> (((b - i) - 1) * 8));
            }
            return bArr;
        }

        private void processData(byte[] bArr, int i, int i2) {
            if (this.squeezing) {
                throw new IllegalStateException("attempt to absorb while squeezing");
            }
            KangarooSponge kangarooSponge = this.theCurrNode == 0 ? this.theTree : this.theLeaf;
            int i3 = 8192 - this.theProcessed;
            if (i3 >= i2) {
                kangarooSponge.absorb(bArr, i, i2);
                this.theProcessed += i2;
                return;
            }
            if (i3 > 0) {
                kangarooSponge.absorb(bArr, i, i3);
                this.theProcessed += i3;
            }
            while (i3 < i2) {
                if (this.theProcessed == 8192) {
                    switchLeaf(true);
                }
                int min = Math.min(i2 - i3, 8192);
                this.theLeaf.absorb(bArr, i + i3, min);
                this.theProcessed += min;
                i3 += min;
            }
        }

        private void switchFinal() {
            switchLeaf(false);
            byte[] lengthEncode = lengthEncode(this.theCurrNode);
            this.theTree.absorb(lengthEncode, 0, lengthEncode.length);
            KangarooSponge kangarooSponge = this.theTree;
            byte[] bArr = FINAL;
            kangarooSponge.absorb(bArr, 0, bArr.length);
            this.theTree.padAndSwitchToSqueezingPhase();
        }

        private void switchLeaf(boolean z) {
            if (this.theCurrNode == 0) {
                KangarooSponge kangarooSponge = this.theTree;
                byte[] bArr = FIRST;
                kangarooSponge.absorb(bArr, 0, bArr.length);
            } else {
                KangarooSponge kangarooSponge2 = this.theLeaf;
                byte[] bArr2 = INTERMEDIATE;
                kangarooSponge2.absorb(bArr2, 0, bArr2.length);
                int i = this.theChainLen;
                byte[] bArr3 = new byte[i];
                this.theLeaf.squeeze(bArr3, 0, i);
                this.theTree.absorb(bArr3, 0, this.theChainLen);
                this.theLeaf.initSponge();
            }
            if (z) {
                this.theCurrNode++;
            }
            this.theProcessed = 0;
        }

        private void switchSingle() {
            this.theTree.absorb(SINGLE, 0, 1);
            this.theTree.padAndSwitchToSqueezingPhase();
        }

        private void switchToSqueezing() {
            byte[] bArr = this.thePersonal;
            processData(bArr, 0, bArr.length);
            if (this.theCurrNode == 0) {
                switchSingle();
            } else {
                switchFinal();
            }
        }

        @Override // org.bouncycastle.crypto.Digest
        public int doFinal(byte[] bArr, int i) {
            return doFinal(bArr, i, getDigestSize());
        }

        @Override // org.bouncycastle.crypto.Xof
        public int doFinal(byte[] bArr, int i, int i2) {
            if (this.squeezing) {
                throw new IllegalStateException("Already outputting");
            }
            int doOutput = doOutput(bArr, i, i2);
            reset();
            return doOutput;
        }

        @Override // org.bouncycastle.crypto.Xof
        public int doOutput(byte[] bArr, int i, int i2) {
            if (!this.squeezing) {
                switchToSqueezing();
            }
            if (i2 >= 0) {
                this.theTree.squeeze(bArr, i, i2);
                return i2;
            }
            throw new IllegalArgumentException("Invalid output length");
        }

        @Override // org.bouncycastle.crypto.ExtendedDigest
        public int getByteLength() {
            return this.theTree.theRateBytes;
        }

        @Override // org.bouncycastle.crypto.Digest
        public int getDigestSize() {
            return this.theChainLen >> 1;
        }

        public void init(KangarooParameters kangarooParameters) {
            buildPersonal(kangarooParameters.getPersonalisation());
            reset();
        }

        @Override // org.bouncycastle.crypto.Digest
        public void reset() {
            this.theTree.initSponge();
            this.theLeaf.initSponge();
            this.theCurrNode = 0;
            this.theProcessed = 0;
            this.squeezing = false;
        }

        @Override // org.bouncycastle.crypto.Digest
        public void update(byte b) {
            byte[] bArr = this.singleByte;
            bArr[0] = b;
            update(bArr, 0, 1);
        }

        @Override // org.bouncycastle.crypto.Digest
        public void update(byte[] bArr, int i, int i2) {
            processData(bArr, i, i2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class KangarooParameters implements CipherParameters {
        private byte[] thePersonal;

        /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\9227576_dexfile_execute.dex */
        public static class Builder {
            private byte[] thePersonal;

            public KangarooParameters build() {
                KangarooParameters kangarooParameters = new KangarooParameters();
                byte[] bArr = this.thePersonal;
                if (bArr != null) {
                    kangarooParameters.thePersonal = bArr;
                }
                return kangarooParameters;
            }

            public Builder setPersonalisation(byte[] bArr) {
                this.thePersonal = Arrays.clone(bArr);
                return this;
            }
        }

        public byte[] getPersonalisation() {
            return Arrays.clone(this.thePersonal);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class KangarooSponge {
        private static long[] KeccakRoundConstants = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
        private int bytesInQueue;
        private boolean squeezing;
        private final byte[] theQueue;
        private final int theRateBytes;
        private final int theRounds;
        private final long[] theState = new long[25];

        KangarooSponge(int i, int i2) {
            this.theRateBytes = (1600 - (i << 1)) >> 3;
            this.theRounds = i2;
            this.theQueue = new byte[this.theRateBytes];
            initSponge();
        }

        private void KangarooAbsorb(byte[] bArr, int i) {
            int i2 = this.theRateBytes >> 3;
            for (int i3 = 0; i3 < i2; i3++) {
                long[] jArr = this.theState;
                jArr[i3] = jArr[i3] ^ Pack.littleEndianToLong(bArr, i);
                i += 8;
            }
            KangarooPermutation();
        }

        private void KangarooExtract() {
            Pack.longToLittleEndian(this.theState, 0, this.theRateBytes >> 3, this.theQueue, 0);
        }

        private void KangarooPermutation() {
            KangarooSponge kangarooSponge = this;
            long[] jArr = kangarooSponge.theState;
            long j = jArr[0];
            char c = 1;
            long j2 = jArr[1];
            long j3 = jArr[2];
            char c2 = 3;
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
            int length = KeccakRoundConstants.length - kangarooSponge.theRounds;
            long j26 = j25;
            long j27 = j24;
            long j28 = j23;
            long j29 = j22;
            long j30 = j21;
            long j31 = j20;
            long j32 = j19;
            long j33 = j18;
            long j34 = j17;
            long j35 = j16;
            long j36 = j15;
            long j37 = j14;
            long j38 = j13;
            long j39 = j12;
            long j40 = j11;
            long j41 = j10;
            long j42 = j9;
            long j43 = j8;
            long j44 = j7;
            long j45 = j6;
            long j46 = j5;
            long j47 = j4;
            long j48 = j3;
            long j49 = j2;
            long j50 = j;
            int i = 0;
            while (i < kangarooSponge.theRounds) {
                long j51 = (((j50 ^ j45) ^ j40) ^ j35) ^ j30;
                long j52 = (((j49 ^ j44) ^ j39) ^ j34) ^ j29;
                long j53 = (((j48 ^ j43) ^ j38) ^ j33) ^ j28;
                long j54 = (((j47 ^ j42) ^ j37) ^ j32) ^ j27;
                long j55 = (((j46 ^ j41) ^ j36) ^ j31) ^ j26;
                long j56 = ((j52 << c) | (j52 >>> (-1))) ^ j55;
                long j57 = ((j53 << c) | (j53 >>> (-1))) ^ j51;
                long j58 = ((j54 << c) | (j54 >>> (-1))) ^ j52;
                long j59 = ((j55 << c) | (j55 >>> (-1))) ^ j53;
                long j60 = ((j51 << c) | (j51 >>> (-1))) ^ j54;
                long j61 = j50 ^ j56;
                long j62 = j45 ^ j56;
                long j63 = j40 ^ j56;
                long j64 = j35 ^ j56;
                long j65 = j30 ^ j56;
                long j66 = j49 ^ j57;
                long j67 = j44 ^ j57;
                long j68 = j39 ^ j57;
                long j69 = j34 ^ j57;
                long j70 = j29 ^ j57;
                long j71 = j48 ^ j58;
                long j72 = j43 ^ j58;
                long j73 = j38 ^ j58;
                long j74 = j33 ^ j58;
                long j75 = j28 ^ j58;
                long j76 = j47 ^ j59;
                long j77 = j42 ^ j59;
                long j78 = j37 ^ j59;
                long j79 = j32 ^ j59;
                long j80 = j27 ^ j59;
                long j81 = j46 ^ j60;
                long j82 = j41 ^ j60;
                long j83 = j36 ^ j60;
                long j84 = j31 ^ j60;
                long j85 = j26 ^ j60;
                long j86 = (j66 << c) | (j66 >>> 63);
                long j87 = (j67 << 44) | (j67 >>> 20);
                long j88 = (j82 << 20) | (j82 >>> 44);
                long j89 = (j75 << 61) | (j75 >>> c2);
                long j90 = (j83 << 39) | (j83 >>> 25);
                long j91 = (j65 << 18) | (j65 >>> 46);
                long j92 = (j71 << 62) | (j71 >>> 2);
                int i2 = length;
                int i3 = i;
                long j93 = (j73 << 43) | (j73 >>> 21);
                long j94 = (j78 << 25) | (j78 >>> 39);
                long j95 = (j84 << 8) | (j84 >>> 56);
                long j96 = (j80 << 56) | (j80 >>> 8);
                long j97 = (j64 << 41) | (j64 >>> 23);
                long j98 = (j81 << 27) | (j81 >>> 37);
                long j99 = (j85 << 14) | (j85 >>> 50);
                long j100 = (j70 << 2) | (j70 >>> 62);
                long j101 = (j77 << 55) | (j77 >>> 9);
                long j102 = (j69 << 45) | (j69 >>> 19);
                long j103 = (j62 << 36) | (j62 >>> 28);
                long j104 = (j76 << 28) | (j76 >>> 36);
                long j105 = (j79 << 21) | (j79 >>> 43);
                long j106 = (j74 << 15) | (j74 >>> 49);
                long j107 = (j68 << 10) | (j68 >>> 54);
                long j108 = (j72 << 6) | (j72 >>> 58);
                long j109 = (j63 << 3) | (j63 >>> 61);
                long j110 = j61 ^ ((~j87) & j93);
                long j111 = ((~j93) & j105) ^ j87;
                long j112 = j93 ^ ((~j105) & j99);
                long j113 = j105 ^ ((~j99) & j61);
                long j114 = j99 ^ (j87 & (~j61));
                long j115 = ((~j88) & j109) ^ j104;
                long j116 = ((~j109) & j102) ^ j88;
                long j117 = ((~j102) & j89) ^ j109;
                long j118 = j102 ^ ((~j89) & j104);
                long j119 = ((~j104) & j88) ^ j89;
                j40 = j86 ^ ((~j108) & j94);
                long j120 = ((~j94) & j95) ^ j108;
                long j121 = ((~j95) & j91) ^ j94;
                long j122 = j95 ^ ((~j91) & j86);
                long j123 = ((~j86) & j108) ^ j91;
                long j124 = j98 ^ ((~j103) & j107);
                long j125 = ((~j107) & j106) ^ j103;
                long j126 = j107 ^ ((~j106) & j96);
                long j127 = ((~j96) & j98) ^ j106;
                j31 = j96 ^ ((~j98) & j103);
                long j128 = j92 ^ ((~j101) & j90);
                long j129 = ((~j90) & j97) ^ j101;
                long j130 = ((~j97) & j100) ^ j90;
                long j131 = j97 ^ ((~j100) & j92);
                j26 = j100 ^ (j101 & (~j92));
                j50 = j110 ^ KeccakRoundConstants[i2 + i3];
                i = i3 + 1;
                j38 = j121;
                j29 = j129;
                j34 = j125;
                length = i2;
                c = 1;
                j36 = j123;
                j32 = j127;
                j43 = j117;
                j37 = j122;
                j46 = j114;
                j27 = j131;
                j44 = j116;
                jArr = jArr;
                kangarooSponge = this;
                c2 = 3;
                j49 = j111;
                j28 = j130;
                j42 = j118;
                j33 = j126;
                j35 = j124;
                j48 = j112;
                j45 = j115;
                j39 = j120;
                j47 = j113;
                j41 = j119;
                j30 = j128;
            }
            long[] jArr2 = jArr;
            jArr2[0] = j50;
            jArr2[1] = j49;
            jArr2[2] = j48;
            jArr2[3] = j47;
            jArr2[4] = j46;
            jArr2[5] = j45;
            jArr2[6] = j44;
            jArr2[7] = j43;
            jArr2[8] = j42;
            jArr2[9] = j41;
            jArr2[10] = j40;
            jArr2[11] = j39;
            jArr2[12] = j38;
            jArr2[13] = j37;
            jArr2[14] = j36;
            jArr2[15] = j35;
            jArr2[16] = j34;
            jArr2[17] = j33;
            jArr2[18] = j32;
            jArr2[19] = j31;
            jArr2[20] = j30;
            jArr2[21] = j29;
            jArr2[22] = j28;
            jArr2[23] = j27;
            jArr2[24] = j26;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void absorb(byte[] bArr, int i, int i2) {
            int i3;
            if (this.squeezing) {
                throw new IllegalStateException("attempt to absorb while squeezing");
            }
            int i4 = 0;
            while (i4 < i2) {
                if (this.bytesInQueue != 0 || i4 > i2 - this.theRateBytes) {
                    int min = Math.min(this.theRateBytes - this.bytesInQueue, i2 - i4);
                    System.arraycopy(bArr, i + i4, this.theQueue, this.bytesInQueue, min);
                    this.bytesInQueue += min;
                    i4 += min;
                    if (this.bytesInQueue == this.theRateBytes) {
                        KangarooAbsorb(this.theQueue, 0);
                        this.bytesInQueue = 0;
                    }
                } else {
                    do {
                        KangarooAbsorb(bArr, i + i4);
                        i3 = this.theRateBytes;
                        i4 += i3;
                    } while (i4 <= i2 - i3);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void initSponge() {
            Arrays.fill(this.theState, 0L);
            Arrays.fill(this.theQueue, (byte) 0);
            this.bytesInQueue = 0;
            this.squeezing = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void padAndSwitchToSqueezingPhase() {
            int i = this.bytesInQueue;
            while (true) {
                int i2 = this.theRateBytes;
                if (i >= i2) {
                    byte[] bArr = this.theQueue;
                    int i3 = i2 - 1;
                    bArr[i3] = (byte) (bArr[i3] ^ 128);
                    KangarooAbsorb(bArr, 0);
                    KangarooExtract();
                    this.bytesInQueue = this.theRateBytes;
                    this.squeezing = true;
                    return;
                }
                this.theQueue[i] = 0;
                i++;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void squeeze(byte[] bArr, int i, int i2) {
            if (!this.squeezing) {
                padAndSwitchToSqueezingPhase();
            }
            int i3 = 0;
            while (i3 < i2) {
                if (this.bytesInQueue == 0) {
                    KangarooPermutation();
                    KangarooExtract();
                    this.bytesInQueue = this.theRateBytes;
                }
                int min = Math.min(this.bytesInQueue, i2 - i3);
                System.arraycopy(this.theQueue, this.theRateBytes - this.bytesInQueue, bArr, i + i3, min);
                this.bytesInQueue -= min;
                i3 += min;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class KangarooTwelve extends KangarooBase {
        public KangarooTwelve() {
            this(32, CryptoServicePurpose.ANY);
        }

        public KangarooTwelve(int i, CryptoServicePurpose cryptoServicePurpose) {
            super(128, 12, i, cryptoServicePurpose);
        }

        public KangarooTwelve(CryptoServicePurpose cryptoServicePurpose) {
            this(32, cryptoServicePurpose);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) {
            return super.doFinal(bArr, i);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Xof
        public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i, int i2) {
            return super.doFinal(bArr, i, i2);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Xof
        public /* bridge */ /* synthetic */ int doOutput(byte[] bArr, int i, int i2) {
            return super.doOutput(bArr, i, i2);
        }

        @Override // org.bouncycastle.crypto.Digest
        public String getAlgorithmName() {
            return "KangarooTwelve";
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.ExtendedDigest
        public /* bridge */ /* synthetic */ int getByteLength() {
            return super.getByteLength();
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ int getDigestSize() {
            return super.getDigestSize();
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase
        public /* bridge */ /* synthetic */ void init(KangarooParameters kangarooParameters) {
            super.init(kangarooParameters);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ void update(byte b) {
            super.update(b);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ void update(byte[] bArr, int i, int i2) {
            super.update(bArr, i, i2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class MarsupilamiFourteen extends KangarooBase {
        public MarsupilamiFourteen() {
            this(32, CryptoServicePurpose.ANY);
        }

        public MarsupilamiFourteen(int i, CryptoServicePurpose cryptoServicePurpose) {
            super(256, 14, i, cryptoServicePurpose);
        }

        public MarsupilamiFourteen(CryptoServicePurpose cryptoServicePurpose) {
            this(32, cryptoServicePurpose);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) {
            return super.doFinal(bArr, i);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Xof
        public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i, int i2) {
            return super.doFinal(bArr, i, i2);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Xof
        public /* bridge */ /* synthetic */ int doOutput(byte[] bArr, int i, int i2) {
            return super.doOutput(bArr, i, i2);
        }

        @Override // org.bouncycastle.crypto.Digest
        public String getAlgorithmName() {
            return "MarsupilamiFourteen";
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.ExtendedDigest
        public /* bridge */ /* synthetic */ int getByteLength() {
            return super.getByteLength();
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ int getDigestSize() {
            return super.getDigestSize();
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase
        public /* bridge */ /* synthetic */ void init(KangarooParameters kangarooParameters) {
            super.init(kangarooParameters);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ void update(byte b) {
            super.update(b);
        }

        @Override // org.bouncycastle.crypto.digests.Kangaroo.KangarooBase, org.bouncycastle.crypto.Digest
        public /* bridge */ /* synthetic */ void update(byte[] bArr, int i, int i2) {
            super.update(bArr, i, i2);
        }
    }
}
