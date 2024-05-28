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
public class G3413CTRBlockCipher extends StreamBlockCipher {
    private byte[] CTR;

    /* renamed from: IV */
    private byte[] f26732IV;
    private final int blockSize;
    private byte[] buf;
    private int byteCount;
    private final BlockCipher cipher;
    private boolean initialized;

    /* renamed from: s */
    private final int f26733s;

    public G3413CTRBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public G3413CTRBlockCipher(BlockCipher blockCipher, int i) {
        super(blockCipher);
        this.byteCount = 0;
        if (i < 0 || i > blockCipher.getBlockSize() * 8) {
            throw new IllegalArgumentException("Parameter bitBlockSize must be in range 0 < bitBlockSize <= " + (blockCipher.getBlockSize() * 8));
        }
        this.cipher = blockCipher;
        this.blockSize = blockCipher.getBlockSize();
        this.f26733s = i / 8;
        this.CTR = new byte[this.blockSize];
    }

    private byte[] generateBuf() {
        byte[] bArr = this.CTR;
        byte[] bArr2 = new byte[bArr.length];
        this.cipher.processBlock(bArr, 0, bArr2, 0);
        return GOST3413CipherUtil.MSB(bArr2, this.f26733s);
    }

    private void generateCRT() {
        byte[] bArr = this.CTR;
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + 1);
    }

    private void initArrays() {
        int i = this.blockSize;
        this.f26732IV = new byte[i / 2];
        this.CTR = new byte[i];
        this.buf = new byte[this.f26733s];
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) {
        if (this.byteCount == 0) {
            this.buf = generateBuf();
        }
        byte[] bArr = this.buf;
        int i = this.byteCount;
        byte b2 = (byte) (b ^ bArr[i]);
        this.byteCount = i + 1;
        if (this.byteCount == this.f26733s) {
            this.byteCount = 0;
            generateCRT();
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCTR";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f26733s;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            initArrays();
            if (cipherParameters != null) {
                blockCipher = this.cipher;
                blockCipher.init(true, cipherParameters);
            }
            this.initialized = true;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        initArrays();
        this.f26732IV = Arrays.clone(parametersWithIV.getIV());
        byte[] bArr = this.f26732IV;
        if (bArr.length != this.blockSize / 2) {
            throw new IllegalArgumentException("Parameter IV length must be == blockSize/2");
        }
        System.arraycopy(bArr, 0, this.CTR, 0, bArr.length);
        for (int length = this.f26732IV.length; length < this.blockSize; length++) {
            this.CTR[length] = 0;
        }
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(true, cipherParameters);
        }
        this.initialized = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.f26733s, bArr2, i2);
        return this.f26733s;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        if (this.initialized) {
            byte[] bArr = this.f26732IV;
            System.arraycopy(bArr, 0, this.CTR, 0, bArr.length);
            for (int length = this.f26732IV.length; length < this.blockSize; length++) {
                this.CTR[length] = 0;
            }
            this.byteCount = 0;
            this.cipher.reset();
        }
    }
}
