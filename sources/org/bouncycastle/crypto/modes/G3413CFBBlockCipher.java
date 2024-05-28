package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class G3413CFBBlockCipher extends StreamBlockCipher {

    /* renamed from: R */
    private byte[] f26729R;
    private byte[] R_init;
    private int blockSize;
    private int byteCount;
    private BlockCipher cipher;
    private boolean forEncryption;
    private byte[] gamma;
    private byte[] inBuf;
    private boolean initialized;

    /* renamed from: m */
    private int f26730m;

    /* renamed from: s */
    private final int f26731s;

    public G3413CFBBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public G3413CFBBlockCipher(BlockCipher blockCipher, int i) {
        super(blockCipher);
        this.initialized = false;
        if (i < 0 || i > blockCipher.getBlockSize() * 8) {
            throw new IllegalArgumentException("Parameter bitBlockSize must be in range 0 < bitBlockSize <= " + (blockCipher.getBlockSize() * 8));
        }
        this.blockSize = blockCipher.getBlockSize();
        this.cipher = blockCipher;
        this.f26731s = i / 8;
        this.inBuf = new byte[getBlockSize()];
    }

    private void initArrays() {
        int i = this.f26730m;
        this.f26729R = new byte[i];
        this.R_init = new byte[i];
    }

    private void setupDefaultParams() {
        this.f26730m = this.blockSize * 2;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) {
        if (this.byteCount == 0) {
            this.gamma = createGamma();
        }
        byte[] bArr = this.gamma;
        int i = this.byteCount;
        byte b2 = (byte) (bArr[i] ^ b);
        byte[] bArr2 = this.inBuf;
        this.byteCount = i + 1;
        if (this.forEncryption) {
            b = b2;
        }
        bArr2[i] = b;
        if (this.byteCount == getBlockSize()) {
            this.byteCount = 0;
            generateR(this.inBuf);
        }
        return b2;
    }

    byte[] createGamma() {
        byte[] MSB = GOST3413CipherUtil.MSB(this.f26729R, this.blockSize);
        byte[] bArr = new byte[MSB.length];
        this.cipher.processBlock(MSB, 0, bArr, 0);
        return GOST3413CipherUtil.MSB(bArr, this.f26731s);
    }

    void generateR(byte[] bArr) {
        byte[] LSB = GOST3413CipherUtil.LSB(this.f26729R, this.f26730m - this.f26731s);
        System.arraycopy(LSB, 0, this.f26729R, 0, LSB.length);
        System.arraycopy(bArr, 0, this.f26729R, LSB.length, this.f26730m - LSB.length);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CFB" + (this.blockSize * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f26731s;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.forEncryption = z;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            setupDefaultParams();
            initArrays();
            byte[] bArr = this.R_init;
            System.arraycopy(bArr, 0, this.f26729R, 0, bArr.length);
            if (cipherParameters != null) {
                blockCipher = this.cipher;
                blockCipher.init(true, cipherParameters);
            }
            this.initialized = true;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv.length < this.blockSize) {
            throw new IllegalArgumentException("Parameter m must blockSize <= m");
        }
        this.f26730m = iv.length;
        initArrays();
        this.R_init = Arrays.clone(iv);
        byte[] bArr2 = this.R_init;
        System.arraycopy(bArr2, 0, this.f26729R, 0, bArr2.length);
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(true, cipherParameters);
        }
        this.initialized = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, getBlockSize(), bArr2, i2);
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.byteCount = 0;
        Arrays.clear(this.inBuf);
        Arrays.clear(this.gamma);
        if (this.initialized) {
            byte[] bArr = this.R_init;
            System.arraycopy(bArr, 0, this.f26729R, 0, bArr.length);
            this.cipher.reset();
        }
    }
}
