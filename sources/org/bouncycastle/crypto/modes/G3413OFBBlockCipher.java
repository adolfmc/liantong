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
public class G3413OFBBlockCipher extends StreamBlockCipher {

    /* renamed from: R */
    private byte[] f26734R;
    private byte[] R_init;

    /* renamed from: Y */
    private byte[] f26735Y;
    private int blockSize;
    private int byteCount;
    private BlockCipher cipher;
    private boolean initialized;

    /* renamed from: m */
    private int f26736m;

    public G3413OFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.initialized = false;
        this.blockSize = blockCipher.getBlockSize();
        this.cipher = blockCipher;
        this.f26735Y = new byte[this.blockSize];
    }

    private void generateR() {
        byte[] LSB = GOST3413CipherUtil.LSB(this.f26734R, this.f26736m - this.blockSize);
        System.arraycopy(LSB, 0, this.f26734R, 0, LSB.length);
        System.arraycopy(this.f26735Y, 0, this.f26734R, LSB.length, this.f26736m - LSB.length);
    }

    private void generateY() {
        this.cipher.processBlock(GOST3413CipherUtil.MSB(this.f26734R, this.blockSize), 0, this.f26735Y, 0);
    }

    private void initArrays() {
        int i = this.f26736m;
        this.f26734R = new byte[i];
        this.R_init = new byte[i];
    }

    private void setupDefaultParams() {
        this.f26736m = this.blockSize * 2;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) {
        if (this.byteCount == 0) {
            generateY();
        }
        byte[] bArr = this.f26735Y;
        int i = this.byteCount;
        byte b2 = (byte) (b ^ bArr[i]);
        this.byteCount = i + 1;
        if (this.byteCount == getBlockSize()) {
            this.byteCount = 0;
            generateR();
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OFB";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            setupDefaultParams();
            initArrays();
            byte[] bArr = this.R_init;
            System.arraycopy(bArr, 0, this.f26734R, 0, bArr.length);
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
        this.f26736m = iv.length;
        initArrays();
        this.R_init = Arrays.clone(iv);
        byte[] bArr2 = this.R_init;
        System.arraycopy(bArr2, 0, this.f26734R, 0, bArr2.length);
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(true, cipherParameters);
        }
        this.initialized = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.blockSize, bArr2, i2);
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        if (this.initialized) {
            byte[] bArr = this.R_init;
            System.arraycopy(bArr, 0, this.f26734R, 0, bArr.length);
            Arrays.clear(this.f26735Y);
            this.byteCount = 0;
            this.cipher.reset();
        }
    }
}
