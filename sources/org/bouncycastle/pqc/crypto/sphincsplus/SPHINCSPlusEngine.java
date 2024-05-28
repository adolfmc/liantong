package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.generators.MGF1BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.MGFParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class SPHINCSPlusEngine {

    /* renamed from: A */
    final int f27246A;

    /* renamed from: D */
    final int f27247D;

    /* renamed from: H */
    final int f27248H;
    final int H_PRIME;

    /* renamed from: K */
    final int f27249K;

    /* renamed from: N */
    final int f27250N;

    /* renamed from: T */
    final int f27251T;
    final int WOTS_LEN;
    final int WOTS_LEN1;
    final int WOTS_LEN2;
    final int WOTS_LOGW;
    final int WOTS_W;
    final boolean robust;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class HarakaSEngine extends SPHINCSPlusEngine {
        private HarakaS256Digest harakaS256Digest;
        private HarakaS512Digest harakaS512Digest;
        private HarakaSXof harakaSXof;

        public HarakaSEngine(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            super(z, i, i2, i3, i4, i5, i6);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: F */
        public byte[] mo245F(byte[] bArr, ADRS adrs, byte[] bArr2) {
            byte[] bArr3 = new byte[64];
            this.harakaS512Digest.update(adrs.value, 0, adrs.value.length);
            if (this.robust) {
                byte[] bArr4 = new byte[bArr2.length];
                this.harakaS256Digest.update(adrs.value, 0, adrs.value.length);
                this.harakaS256Digest.doFinal(bArr4, 0);
                for (int i = 0; i < bArr2.length; i++) {
                    bArr4[i] = (byte) (bArr4[i] ^ bArr2[i]);
                }
                this.harakaS512Digest.update(bArr4, 0, bArr4.length);
            } else {
                this.harakaS512Digest.update(bArr2, 0, bArr2.length);
            }
            this.harakaS512Digest.doFinal(bArr3, 0);
            return Arrays.copyOf(bArr3, this.f27250N);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: H */
        public byte[] mo244H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3) {
            byte[] bArr4 = new byte[this.f27250N];
            byte[] bArr5 = new byte[bArr2.length + bArr3.length];
            System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
            System.arraycopy(bArr3, 0, bArr5, bArr2.length, bArr3.length);
            byte[] bitmask = bitmask(adrs, bArr5);
            this.harakaSXof.update(adrs.value, 0, adrs.value.length);
            this.harakaSXof.update(bitmask, 0, bitmask.length);
            this.harakaSXof.doFinal(bArr4, 0, bArr4.length);
            return bArr4;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        IndexedDigest H_msg(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            int i = ((this.f27246A * this.f27249K) + 7) >> 3;
            int i2 = this.f27248H / this.f27247D;
            int i3 = this.f27248H - i2;
            int i4 = (i2 + 7) >> 3;
            int i5 = (i3 + 7) >> 3;
            byte[] bArr5 = new byte[i + i4 + i5];
            this.harakaSXof.update(bArr, 0, bArr.length);
            this.harakaSXof.update(bArr3, 0, bArr3.length);
            this.harakaSXof.update(bArr4, 0, bArr4.length);
            this.harakaSXof.doFinal(bArr5, 0, bArr5.length);
            byte[] bArr6 = new byte[8];
            System.arraycopy(bArr5, i, bArr6, 8 - i5, i5);
            byte[] bArr7 = new byte[4];
            System.arraycopy(bArr5, i5 + i, bArr7, 4 - i4, i4);
            return new IndexedDigest(Pack.bigEndianToLong(bArr6, 0) & ((-1) >>> (64 - i3)), Pack.bigEndianToInt(bArr7, 0) & ((-1) >>> (32 - i2)), Arrays.copyOfRange(bArr5, 0, i));
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] PRF(byte[] bArr, byte[] bArr2, ADRS adrs) {
            byte[] bArr3 = new byte[64];
            this.harakaS512Digest.update(adrs.value, 0, adrs.value.length);
            this.harakaS512Digest.update(bArr2, 0, bArr2.length);
            this.harakaS512Digest.doFinal(bArr3, 0);
            return Arrays.copyOf(bArr3, this.f27250N);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            byte[] bArr4 = new byte[this.f27250N];
            this.harakaSXof.update(bArr, 0, bArr.length);
            this.harakaSXof.update(bArr2, 0, bArr2.length);
            this.harakaSXof.update(bArr3, 0, bArr3.length);
            this.harakaSXof.doFinal(bArr4, 0, bArr4.length);
            return bArr4;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2) {
            byte[] bArr3 = new byte[this.f27250N];
            byte[] bitmask = bitmask(adrs, bArr2);
            this.harakaSXof.update(adrs.value, 0, adrs.value.length);
            this.harakaSXof.update(bitmask, 0, bitmask.length);
            this.harakaSXof.doFinal(bArr3, 0, bArr3.length);
            return bArr3;
        }

        protected byte[] bitmask(ADRS adrs, byte[] bArr) {
            if (this.robust) {
                byte[] bArr2 = new byte[bArr.length];
                this.harakaSXof.update(adrs.value, 0, adrs.value.length);
                this.harakaSXof.doFinal(bArr2, 0, bArr2.length);
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
                }
            }
            return bArr;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        void init(byte[] bArr) {
            this.harakaSXof = new HarakaSXof(bArr);
            this.harakaS256Digest = new HarakaS256Digest(this.harakaSXof);
            this.harakaS512Digest = new HarakaS512Digest(this.harakaSXof);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Sha2Engine extends SPHINCSPlusEngine {

        /* renamed from: bl */
        private final int f27252bl;
        private final byte[] hmacBuf;
        private final MGF1BytesGenerator mgf1;
        private final Digest msgDigest;
        private final byte[] msgDigestBuf;
        private Memoable msgMemo;
        private final Digest sha256;
        private final byte[] sha256Buf;
        private Memoable sha256Memo;
        private final HMac treeHMac;

        public Sha2Engine(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            super(z, i, i2, i3, i4, i5, i6);
            int i7;
            this.sha256 = new SHA256Digest();
            this.sha256Buf = new byte[this.sha256.getDigestSize()];
            if (i == 16) {
                this.msgDigest = new SHA256Digest();
                this.treeHMac = new HMac(new SHA256Digest());
                this.mgf1 = new MGF1BytesGenerator(new SHA256Digest());
                i7 = 64;
            } else {
                this.msgDigest = new SHA512Digest();
                this.treeHMac = new HMac(new SHA512Digest());
                this.mgf1 = new MGF1BytesGenerator(new SHA512Digest());
                i7 = 128;
            }
            this.f27252bl = i7;
            this.hmacBuf = new byte[this.treeHMac.getMacSize()];
            this.msgDigestBuf = new byte[this.msgDigest.getDigestSize()];
        }

        private byte[] compressedADRS(ADRS adrs) {
            byte[] bArr = new byte[22];
            System.arraycopy(adrs.value, 3, bArr, 0, 1);
            System.arraycopy(adrs.value, 8, bArr, 1, 8);
            System.arraycopy(adrs.value, 19, bArr, 9, 1);
            System.arraycopy(adrs.value, 20, bArr, 10, 12);
            return bArr;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: F */
        public byte[] mo245F(byte[] bArr, ADRS adrs, byte[] bArr2) {
            byte[] compressedADRS = compressedADRS(adrs);
            if (this.robust) {
                bArr2 = bitmask256(Arrays.concatenate(bArr, compressedADRS), bArr2);
            }
            ((Memoable) this.sha256).reset(this.sha256Memo);
            this.sha256.update(compressedADRS, 0, compressedADRS.length);
            this.sha256.update(bArr2, 0, bArr2.length);
            this.sha256.doFinal(this.sha256Buf, 0);
            return Arrays.copyOfRange(this.sha256Buf, 0, this.f27250N);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: H */
        public byte[] mo244H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3) {
            byte[] compressedADRS = compressedADRS(adrs);
            ((Memoable) this.msgDigest).reset(this.msgMemo);
            this.msgDigest.update(compressedADRS, 0, compressedADRS.length);
            if (this.robust) {
                byte[] bitmask = bitmask(Arrays.concatenate(bArr, compressedADRS), bArr2, bArr3);
                this.msgDigest.update(bitmask, 0, bitmask.length);
            } else {
                this.msgDigest.update(bArr2, 0, bArr2.length);
                this.msgDigest.update(bArr3, 0, bArr3.length);
            }
            this.msgDigest.doFinal(this.msgDigestBuf, 0);
            return Arrays.copyOfRange(this.msgDigestBuf, 0, this.f27250N);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        IndexedDigest H_msg(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            int i = ((this.f27246A * this.f27249K) + 7) / 8;
            int i2 = this.f27248H / this.f27247D;
            int i3 = this.f27248H - i2;
            int i4 = (i2 + 7) / 8;
            int i5 = (i3 + 7) / 8;
            byte[] bArr5 = new byte[this.msgDigest.getDigestSize()];
            this.msgDigest.update(bArr, 0, bArr.length);
            this.msgDigest.update(bArr2, 0, bArr2.length);
            this.msgDigest.update(bArr3, 0, bArr3.length);
            this.msgDigest.update(bArr4, 0, bArr4.length);
            this.msgDigest.doFinal(bArr5, 0);
            byte[] bitmask = bitmask(Arrays.concatenate(bArr, bArr2, bArr5), new byte[i + i4 + i5]);
            byte[] bArr6 = new byte[8];
            System.arraycopy(bitmask, i, bArr6, 8 - i5, i5);
            byte[] bArr7 = new byte[4];
            System.arraycopy(bitmask, i5 + i, bArr7, 4 - i4, i4);
            return new IndexedDigest(Pack.bigEndianToLong(bArr6, 0) & ((-1) >>> (64 - i3)), Pack.bigEndianToInt(bArr7, 0) & ((-1) >>> (32 - i2)), Arrays.copyOfRange(bitmask, 0, i));
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] PRF(byte[] bArr, byte[] bArr2, ADRS adrs) {
            int length = bArr2.length;
            ((Memoable) this.sha256).reset(this.sha256Memo);
            byte[] compressedADRS = compressedADRS(adrs);
            this.sha256.update(compressedADRS, 0, compressedADRS.length);
            this.sha256.update(bArr2, 0, bArr2.length);
            this.sha256.doFinal(this.sha256Buf, 0);
            return Arrays.copyOfRange(this.sha256Buf, 0, length);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.treeHMac.init(new KeyParameter(bArr));
            this.treeHMac.update(bArr2, 0, bArr2.length);
            this.treeHMac.update(bArr3, 0, bArr3.length);
            this.treeHMac.doFinal(this.hmacBuf, 0);
            return Arrays.copyOfRange(this.hmacBuf, 0, this.f27250N);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2) {
            byte[] compressedADRS = compressedADRS(adrs);
            if (this.robust) {
                bArr2 = bitmask(Arrays.concatenate(bArr, compressedADRS), bArr2);
            }
            ((Memoable) this.msgDigest).reset(this.msgMemo);
            this.msgDigest.update(compressedADRS, 0, compressedADRS.length);
            this.msgDigest.update(bArr2, 0, bArr2.length);
            this.msgDigest.doFinal(this.msgDigestBuf, 0);
            return Arrays.copyOfRange(this.msgDigestBuf, 0, this.f27250N);
        }

        protected byte[] bitmask(byte[] bArr, byte[] bArr2) {
            byte[] bArr3 = new byte[bArr2.length];
            this.mgf1.init(new MGFParameters(bArr));
            this.mgf1.generateBytes(bArr3, 0, bArr3.length);
            for (int i = 0; i < bArr2.length; i++) {
                bArr3[i] = (byte) (bArr3[i] ^ bArr2[i]);
            }
            return bArr3;
        }

        protected byte[] bitmask(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            byte[] bArr4 = new byte[bArr2.length + bArr3.length];
            this.mgf1.init(new MGFParameters(bArr));
            this.mgf1.generateBytes(bArr4, 0, bArr4.length);
            for (int i = 0; i < bArr2.length; i++) {
                bArr4[i] = (byte) (bArr4[i] ^ bArr2[i]);
            }
            for (int i2 = 0; i2 < bArr3.length; i2++) {
                int length = bArr2.length + i2;
                bArr4[length] = (byte) (bArr4[length] ^ bArr3[i2]);
            }
            return bArr4;
        }

        protected byte[] bitmask256(byte[] bArr, byte[] bArr2) {
            byte[] bArr3 = new byte[bArr2.length];
            MGF1BytesGenerator mGF1BytesGenerator = new MGF1BytesGenerator(new SHA256Digest());
            mGF1BytesGenerator.init(new MGFParameters(bArr));
            mGF1BytesGenerator.generateBytes(bArr3, 0, bArr3.length);
            for (int i = 0; i < bArr2.length; i++) {
                bArr3[i] = (byte) (bArr3[i] ^ bArr2[i]);
            }
            return bArr3;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        void init(byte[] bArr) {
            byte[] bArr2 = new byte[this.f27252bl];
            this.msgDigest.update(bArr, 0, bArr.length);
            this.msgDigest.update(bArr2, 0, this.f27252bl - this.f27250N);
            this.msgMemo = ((Memoable) this.msgDigest).copy();
            this.msgDigest.reset();
            this.sha256.update(bArr, 0, bArr.length);
            this.sha256.update(bArr2, 0, 64 - bArr.length);
            this.sha256Memo = ((Memoable) this.sha256).copy();
            this.sha256.reset();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Shake256Engine extends SPHINCSPlusEngine {
        private final Xof maskDigest;
        private final Xof treeDigest;

        public Shake256Engine(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
            super(z, i, i2, i3, i4, i5, i6);
            this.treeDigest = new SHAKEDigest(256);
            this.maskDigest = new SHAKEDigest(256);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: F */
        byte[] mo245F(byte[] bArr, ADRS adrs, byte[] bArr2) {
            if (this.robust) {
                bArr2 = bitmask(bArr, adrs, bArr2);
            }
            byte[] bArr3 = new byte[this.f27250N];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(adrs.value, 0, adrs.value.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(bArr3, 0, bArr3.length);
            return bArr3;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: H */
        byte[] mo244H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3) {
            byte[] bArr4 = new byte[this.f27250N];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(adrs.value, 0, adrs.value.length);
            if (this.robust) {
                byte[] bitmask = bitmask(bArr, adrs, bArr2, bArr3);
                this.treeDigest.update(bitmask, 0, bitmask.length);
            } else {
                this.treeDigest.update(bArr2, 0, bArr2.length);
                this.treeDigest.update(bArr3, 0, bArr3.length);
            }
            this.treeDigest.doFinal(bArr4, 0, bArr4.length);
            return bArr4;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        IndexedDigest H_msg(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            int i = ((this.f27246A * this.f27249K) + 7) / 8;
            int i2 = this.f27248H / this.f27247D;
            int i3 = this.f27248H - i2;
            int i4 = (i2 + 7) / 8;
            int i5 = (i3 + 7) / 8;
            byte[] bArr5 = new byte[i + i4 + i5];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.update(bArr3, 0, bArr3.length);
            this.treeDigest.update(bArr4, 0, bArr4.length);
            this.treeDigest.doFinal(bArr5, 0, bArr5.length);
            byte[] bArr6 = new byte[8];
            System.arraycopy(bArr5, i, bArr6, 8 - i5, i5);
            long bigEndianToLong = Pack.bigEndianToLong(bArr6, 0) & ((-1) >>> (64 - i3));
            byte[] bArr7 = new byte[4];
            System.arraycopy(bArr5, i5 + i, bArr7, 4 - i4, i4);
            return new IndexedDigest(bigEndianToLong, Pack.bigEndianToInt(bArr7, 0) & ((-1) >>> (32 - i2)), Arrays.copyOfRange(bArr5, 0, i));
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] PRF(byte[] bArr, byte[] bArr2, ADRS adrs) {
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(adrs.value, 0, adrs.value.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            byte[] bArr3 = new byte[this.f27250N];
            this.treeDigest.doFinal(bArr3, 0, this.f27250N);
            return bArr3;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.update(bArr3, 0, bArr3.length);
            byte[] bArr4 = new byte[this.f27250N];
            this.treeDigest.doFinal(bArr4, 0, bArr4.length);
            return bArr4;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2) {
            if (this.robust) {
                bArr2 = bitmask(bArr, adrs, bArr2);
            }
            byte[] bArr3 = new byte[this.f27250N];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(adrs.value, 0, adrs.value.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(bArr3, 0, bArr3.length);
            return bArr3;
        }

        protected byte[] bitmask(byte[] bArr, ADRS adrs, byte[] bArr2) {
            byte[] bArr3 = new byte[bArr2.length];
            this.maskDigest.update(bArr, 0, bArr.length);
            this.maskDigest.update(adrs.value, 0, adrs.value.length);
            this.maskDigest.doFinal(bArr3, 0, bArr3.length);
            for (int i = 0; i < bArr2.length; i++) {
                bArr3[i] = (byte) (bArr3[i] ^ bArr2[i]);
            }
            return bArr3;
        }

        protected byte[] bitmask(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3) {
            byte[] bArr4 = new byte[bArr2.length + bArr3.length];
            this.maskDigest.update(bArr, 0, bArr.length);
            this.maskDigest.update(adrs.value, 0, adrs.value.length);
            this.maskDigest.doFinal(bArr4, 0, bArr4.length);
            for (int i = 0; i < bArr2.length; i++) {
                bArr4[i] = (byte) (bArr4[i] ^ bArr2[i]);
            }
            for (int i2 = 0; i2 < bArr3.length; i2++) {
                int length = bArr2.length + i2;
                bArr4[length] = (byte) (bArr4[length] ^ bArr3[i2]);
            }
            return bArr4;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        void init(byte[] bArr) {
        }
    }

    public SPHINCSPlusEngine(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
        this.f27250N = i;
        int i7 = 2;
        if (i2 == 16) {
            this.WOTS_LOGW = 4;
            int i8 = this.f27250N;
            this.WOTS_LEN1 = (i8 * 8) / this.WOTS_LOGW;
            if (i8 > 8) {
                if (i8 > 136) {
                    if (i8 > 256) {
                        throw new IllegalArgumentException("cannot precompute SPX_WOTS_LEN2 for n outside {2, .., 256}");
                    }
                    this.WOTS_LEN2 = 4;
                    this.WOTS_W = i2;
                    this.WOTS_LEN = this.WOTS_LEN1 + this.WOTS_LEN2;
                    this.robust = z;
                    this.f27247D = i3;
                    this.f27246A = i4;
                    this.f27249K = i5;
                    this.f27248H = i6;
                    this.H_PRIME = i6 / i3;
                    this.f27251T = 1 << i4;
                }
                i7 = 3;
            }
            this.WOTS_LEN2 = i7;
            this.WOTS_W = i2;
            this.WOTS_LEN = this.WOTS_LEN1 + this.WOTS_LEN2;
            this.robust = z;
            this.f27247D = i3;
            this.f27246A = i4;
            this.f27249K = i5;
            this.f27248H = i6;
            this.H_PRIME = i6 / i3;
            this.f27251T = 1 << i4;
        } else if (i2 != 256) {
            throw new IllegalArgumentException("wots_w assumed 16 or 256");
        } else {
            this.WOTS_LOGW = 8;
            int i9 = this.f27250N;
            this.WOTS_LEN1 = (i9 * 8) / this.WOTS_LOGW;
            if (i9 <= 1) {
                this.WOTS_LEN2 = 1;
                this.WOTS_W = i2;
                this.WOTS_LEN = this.WOTS_LEN1 + this.WOTS_LEN2;
                this.robust = z;
                this.f27247D = i3;
                this.f27246A = i4;
                this.f27249K = i5;
                this.f27248H = i6;
                this.H_PRIME = i6 / i3;
                this.f27251T = 1 << i4;
            }
            if (i9 > 256) {
                throw new IllegalArgumentException("cannot precompute SPX_WOTS_LEN2 for n outside {2, .., 256}");
            }
            this.WOTS_LEN2 = i7;
            this.WOTS_W = i2;
            this.WOTS_LEN = this.WOTS_LEN1 + this.WOTS_LEN2;
            this.robust = z;
            this.f27247D = i3;
            this.f27246A = i4;
            this.f27249K = i5;
            this.f27248H = i6;
            this.H_PRIME = i6 / i3;
            this.f27251T = 1 << i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: F */
    public abstract byte[] mo245F(byte[] bArr, ADRS adrs, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: H */
    public abstract byte[] mo244H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract IndexedDigest H_msg(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] PRF(byte[] bArr, byte[] bArr2, ADRS adrs);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void init(byte[] bArr);
}
