package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.modes.gcm.BasicGCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.GCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.GCMMultiplier;
import org.bouncycastle.crypto.modes.gcm.GCMUtil;
import org.bouncycastle.crypto.modes.gcm.Tables4kGCMMultiplier;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GCMBlockCipher implements GCMModeCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: H */
    private byte[] f26738H;

    /* renamed from: J0 */
    private byte[] f26739J0;

    /* renamed from: S */
    private byte[] f26740S;
    private byte[] S_at;
    private byte[] S_atPre;
    private byte[] atBlock;
    private int atBlockPos;
    private long atLength;
    private long atLengthPre;
    private int blocksRemaining;
    private byte[] bufBlock;
    private int bufOff;
    private BlockCipher cipher;
    private byte[] counter;
    private GCMExponentiator exp;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private boolean initialised;
    private byte[] lastKey;
    private byte[] macBlock;
    private int macSize;
    private GCMMultiplier multiplier;
    private byte[] nonce;
    private long totalLength;

    public GCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, null);
    }

    public GCMBlockCipher(BlockCipher blockCipher, GCMMultiplier gCMMultiplier) {
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
        gCMMultiplier = gCMMultiplier == null ? new Tables4kGCMMultiplier() : gCMMultiplier;
        this.cipher = blockCipher;
        this.multiplier = gCMMultiplier;
    }

    private void checkStatus() {
        if (this.initialised) {
            return;
        }
        if (!this.forEncryption) {
            throw new IllegalStateException("GCM cipher needs to be initialised");
        }
        throw new IllegalStateException("GCM cipher cannot be reused for encryption");
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (bArr2.length - i2 < 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.totalLength == 0) {
            initCipher();
        }
        byte[] bArr3 = new byte[16];
        getNextCTRBlock(bArr3);
        gHASHBlock(this.f26740S, bArr, i);
        GCMUtil.xor(bArr3, 0, bArr, i, bArr2, i2);
        this.totalLength += 16;
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (bArr2.length - i2 < 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.totalLength == 0) {
            initCipher();
        }
        byte[] bArr3 = new byte[16];
        getNextCTRBlock(bArr3);
        GCMUtil.xor(bArr3, bArr, i);
        gHASHBlock(this.f26740S, bArr3);
        System.arraycopy(bArr3, 0, bArr2, i2, 16);
        this.totalLength += 16;
    }

    private void gHASH(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2 += 16) {
            gHASHPartial(bArr, bArr2, i2, Math.min(i - i2, 16));
        }
    }

    private void gHASHBlock(byte[] bArr, byte[] bArr2) {
        GCMUtil.xor(bArr, bArr2);
        this.multiplier.multiplyH(bArr);
    }

    private void gHASHBlock(byte[] bArr, byte[] bArr2, int i) {
        GCMUtil.xor(bArr, bArr2, i);
        this.multiplier.multiplyH(bArr);
    }

    private void gHASHPartial(byte[] bArr, byte[] bArr2, int i, int i2) {
        GCMUtil.xor(bArr, bArr2, i, i2);
        this.multiplier.multiplyH(bArr);
    }

    private void getNextCTRBlock(byte[] bArr) {
        int i = this.blocksRemaining;
        if (i == 0) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        this.blocksRemaining = i - 1;
        byte[] bArr2 = this.counter;
        int i2 = (bArr2[15] & 255) + 1;
        bArr2[15] = (byte) i2;
        int i3 = (i2 >>> 8) + (bArr2[14] & 255);
        bArr2[14] = (byte) i3;
        int i4 = (i3 >>> 8) + (bArr2[13] & 255);
        bArr2[13] = (byte) i4;
        bArr2[12] = (byte) ((i4 >>> 8) + (bArr2[12] & 255));
        this.cipher.processBlock(bArr2, 0, bArr, 0);
    }

    private void initCipher() {
        if (this.atLength > 0) {
            System.arraycopy(this.S_at, 0, this.S_atPre, 0, 16);
            this.atLengthPre = this.atLength;
        }
        int i = this.atBlockPos;
        if (i > 0) {
            gHASHPartial(this.S_atPre, this.atBlock, 0, i);
            this.atLengthPre += this.atBlockPos;
        }
        if (this.atLengthPre > 0) {
            System.arraycopy(this.S_atPre, 0, this.f26740S, 0, 16);
        }
    }

    public static GCMModeCipher newInstance(BlockCipher blockCipher) {
        return new GCMBlockCipher(blockCipher);
    }

    public static GCMModeCipher newInstance(BlockCipher blockCipher, GCMMultiplier gCMMultiplier) {
        return new GCMBlockCipher(blockCipher, gCMMultiplier);
    }

    private void processPartial(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        byte[] bArr3 = new byte[16];
        getNextCTRBlock(bArr3);
        if (this.forEncryption) {
            GCMUtil.xor(bArr, i, bArr3, 0, i2);
            gHASHPartial(this.f26740S, bArr, i, i2);
        } else {
            gHASHPartial(this.f26740S, bArr, i, i2);
            GCMUtil.xor(bArr, i, bArr3, 0, i2);
        }
        System.arraycopy(bArr, i, bArr2, i3, i2);
        this.totalLength += i2;
    }

    private void reset(boolean z) {
        this.cipher.reset();
        this.f26740S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0L;
        this.atLengthPre = 0L;
        this.counter = Arrays.clone(this.f26739J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0L;
        byte[] bArr = this.bufBlock;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
        if (z) {
            this.macBlock = null;
        }
        if (this.forEncryption) {
            this.initialised = false;
            return;
        }
        byte[] bArr2 = this.initialAssociatedText;
        if (bArr2 != null) {
            processAADBytes(bArr2, 0, bArr2.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        checkStatus();
        if (this.totalLength == 0) {
            initCipher();
        }
        int i2 = this.bufOff;
        if (!this.forEncryption) {
            int i3 = this.macSize;
            if (i2 < i3) {
                throw new InvalidCipherTextException("data too short");
            }
            i2 -= i3;
            if (bArr.length - i < i2) {
                throw new OutputLengthException("Output buffer too short");
            }
        } else if (bArr.length - i < this.macSize + i2) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (i2 > 0) {
            processPartial(this.bufBlock, 0, i2, bArr, i);
        }
        long j = this.atLength;
        int i4 = this.atBlockPos;
        this.atLength = j + i4;
        if (this.atLength > this.atLengthPre) {
            if (i4 > 0) {
                gHASHPartial(this.S_at, this.atBlock, 0, i4);
            }
            if (this.atLengthPre > 0) {
                GCMUtil.xor(this.S_at, this.S_atPre);
            }
            long j2 = ((this.totalLength * 8) + 127) >>> 7;
            byte[] bArr2 = new byte[16];
            if (this.exp == null) {
                this.exp = new BasicGCMExponentiator();
                this.exp.init(this.f26738H);
            }
            this.exp.exponentiateX(j2, bArr2);
            GCMUtil.multiply(this.S_at, bArr2);
            GCMUtil.xor(this.f26740S, this.S_at);
        }
        byte[] bArr3 = new byte[16];
        Pack.longToBigEndian(this.atLength * 8, bArr3, 0);
        Pack.longToBigEndian(this.totalLength * 8, bArr3, 8);
        gHASHBlock(this.f26740S, bArr3);
        byte[] bArr4 = new byte[16];
        this.cipher.processBlock(this.f26739J0, 0, bArr4, 0);
        GCMUtil.xor(bArr4, this.f26740S);
        int i5 = this.macSize;
        this.macBlock = new byte[i5];
        System.arraycopy(bArr4, 0, this.macBlock, 0, i5);
        if (this.forEncryption) {
            System.arraycopy(this.macBlock, 0, bArr, i + this.bufOff, this.macSize);
            i2 += this.macSize;
        } else {
            int i6 = this.macSize;
            byte[] bArr5 = new byte[i6];
            System.arraycopy(this.bufBlock, i2, bArr5, 0, i6);
            if (!Arrays.constantTimeAreEqual(this.macBlock, bArr5)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        reset(false);
        return i2;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCM";
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        byte[] bArr = this.macBlock;
        return bArr == null ? new byte[this.macSize] : Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        int i2 = i + this.bufOff;
        if (this.forEncryption) {
            return i2 + this.macSize;
        }
        int i3 = this.macSize;
        if (i2 < i3) {
            return 0;
        }
        return i2 - i3;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        int i2 = i + this.bufOff;
        if (!this.forEncryption) {
            int i3 = this.macSize;
            if (i2 < i3) {
                return 0;
            }
            i2 -= i3;
        }
        return i2 - (i2 % 16);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] iv;
        KeyParameter keyParameter;
        byte[] bArr;
        this.forEncryption = z;
        this.macBlock = null;
        this.initialised = true;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            iv = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 32 || macSize > 128 || macSize % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.macSize = macSize / 8;
            keyParameter = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to GCM");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            this.initialAssociatedText = null;
            this.macSize = 16;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
        }
        this.bufBlock = new byte[z ? 16 : this.macSize + 16];
        if (iv == null || iv.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (z && (bArr = this.nonce) != null && Arrays.areEqual(bArr, iv)) {
            if (keyParameter == null) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
            byte[] bArr2 = this.lastKey;
            if (bArr2 != null && Arrays.areEqual(bArr2, keyParameter.getKey())) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
        }
        this.nonce = iv;
        if (keyParameter != null) {
            this.lastKey = keyParameter.getKey();
        }
        if (keyParameter != null) {
            this.cipher.init(true, keyParameter);
            this.f26738H = new byte[16];
            BlockCipher blockCipher = this.cipher;
            byte[] bArr3 = this.f26738H;
            blockCipher.processBlock(bArr3, 0, bArr3, 0);
            this.multiplier.init(this.f26738H);
            this.exp = null;
        } else if (this.f26738H == null) {
            throw new IllegalArgumentException("Key must be specified in initial init");
        }
        this.f26739J0 = new byte[16];
        byte[] bArr4 = this.nonce;
        if (bArr4.length == 12) {
            System.arraycopy(bArr4, 0, this.f26739J0, 0, bArr4.length);
            this.f26739J0[15] = 1;
        } else {
            gHASH(this.f26739J0, bArr4, bArr4.length);
            byte[] bArr5 = new byte[16];
            Pack.longToBigEndian(this.nonce.length * 8, bArr5, 8);
            gHASHBlock(this.f26739J0, bArr5);
        }
        this.f26740S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0L;
        this.atLengthPre = 0L;
        this.counter = Arrays.clone(this.f26739J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0L;
        byte[] bArr6 = this.initialAssociatedText;
        if (bArr6 != null) {
            processAADBytes(bArr6, 0, bArr6.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        checkStatus();
        byte[] bArr = this.atBlock;
        int i = this.atBlockPos;
        bArr[i] = b;
        int i2 = i + 1;
        this.atBlockPos = i2;
        if (i2 == 16) {
            gHASHBlock(this.S_at, bArr);
            this.atBlockPos = 0;
            this.atLength += 16;
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        checkStatus();
        int i3 = this.atBlockPos;
        if (i3 > 0) {
            int i4 = 16 - i3;
            if (i2 < i4) {
                System.arraycopy(bArr, i, this.atBlock, i3, i2);
                this.atBlockPos += i2;
                return;
            }
            System.arraycopy(bArr, i, this.atBlock, i3, i4);
            gHASHBlock(this.S_at, this.atBlock);
            this.atLength += 16;
            i += i4;
            i2 -= i4;
        }
        int i5 = (i2 + i) - 16;
        while (i <= i5) {
            gHASHBlock(this.S_at, bArr, i);
            this.atLength += 16;
            i += 16;
        }
        this.atBlockPos = (i5 + 16) - i;
        System.arraycopy(bArr, i, this.atBlock, 0, this.atBlockPos);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        checkStatus();
        byte[] bArr2 = this.bufBlock;
        int i2 = this.bufOff;
        bArr2[i2] = b;
        int i3 = i2 + 1;
        this.bufOff = i3;
        if (i3 == bArr2.length) {
            if (this.forEncryption) {
                encryptBlock(bArr2, 0, bArr, i);
                this.bufOff = 0;
            } else {
                decryptBlock(bArr2, 0, bArr, i);
                byte[] bArr3 = this.bufBlock;
                System.arraycopy(bArr3, 16, bArr3, 0, this.macSize);
                this.bufOff = this.macSize;
            }
            return 16;
        }
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        int i4;
        int i5;
        checkStatus();
        if (bArr.length - i >= i2) {
            if (this.forEncryption) {
                int i6 = this.bufOff;
                if (i6 > 0) {
                    int i7 = 16 - i6;
                    if (i2 < i7) {
                        System.arraycopy(bArr, i, this.bufBlock, i6, i2);
                        this.bufOff += i2;
                        return 0;
                    }
                    System.arraycopy(bArr, i, this.bufBlock, i6, i7);
                    encryptBlock(this.bufBlock, 0, bArr2, i3);
                    i += i7;
                    i2 -= i7;
                    i5 = 16;
                } else {
                    i5 = 0;
                }
                int i8 = (i2 + i) - 16;
                while (i <= i8) {
                    encryptBlock(bArr, i, bArr2, i3 + i5);
                    i += 16;
                    i5 += 16;
                }
                this.bufOff = (i8 + 16) - i;
                System.arraycopy(bArr, i, this.bufBlock, 0, this.bufOff);
                return i5;
            }
            byte[] bArr3 = this.bufBlock;
            int length = bArr3.length;
            int i9 = this.bufOff;
            int i10 = length - i9;
            if (i2 < i10) {
                System.arraycopy(bArr, i, bArr3, i9, i2);
                this.bufOff += i2;
                return 0;
            }
            if (i9 >= 16) {
                decryptBlock(bArr3, 0, bArr2, i3);
                byte[] bArr4 = this.bufBlock;
                int i11 = this.bufOff - 16;
                this.bufOff = i11;
                System.arraycopy(bArr4, 16, bArr4, 0, i11);
                if (i2 < i10 + 16) {
                    System.arraycopy(bArr, i, this.bufBlock, this.bufOff, i2);
                    this.bufOff += i2;
                    return 16;
                }
                i4 = 16;
            } else {
                i4 = 0;
            }
            byte[] bArr5 = this.bufBlock;
            int length2 = (i2 + i) - bArr5.length;
            int i12 = this.bufOff;
            int i13 = 16 - i12;
            System.arraycopy(bArr, i, bArr5, i12, i13);
            decryptBlock(this.bufBlock, 0, bArr2, i3 + i4);
            int i14 = i + i13;
            i5 = i4 + 16;
            while (i14 <= length2) {
                decryptBlock(bArr, i14, bArr2, i3 + i5);
                i14 += 16;
                i5 += 16;
            }
            byte[] bArr6 = this.bufBlock;
            this.bufOff = (bArr6.length + length2) - i14;
            System.arraycopy(bArr, i14, bArr6, 0, this.bufOff);
            return i5;
        }
        throw new DataLengthException("Input buffer too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        reset(true);
    }
}
