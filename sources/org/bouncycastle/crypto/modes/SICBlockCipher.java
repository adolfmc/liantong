package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {

    /* renamed from: IV */
    private byte[] f26757IV;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    private byte[] counter;
    private byte[] counterOut;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cipher = blockCipher;
        this.blockSize = this.cipher.getBlockSize();
        int i = this.blockSize;
        this.f26757IV = new byte[i];
        this.counter = new byte[i];
        this.counterOut = new byte[i];
        this.byteCount = 0;
    }

    private void adjustCounter(long j) {
        long j2;
        long j3;
        int i = 5;
        if (j >= 0) {
            long j4 = (this.byteCount + j) / this.blockSize;
            if (j4 > 255) {
                j3 = j4;
                while (i >= 1) {
                    long j5 = 1 << (i * 8);
                    while (j3 >= j5) {
                        incrementCounterAt(i);
                        j3 -= j5;
                    }
                    i--;
                }
            } else {
                j3 = j4;
            }
            incrementCounter((int) j3);
            this.byteCount = (int) ((j + this.byteCount) - (this.blockSize * j4));
            return;
        }
        long j6 = ((-j) - this.byteCount) / this.blockSize;
        if (j6 > 255) {
            j2 = j6;
            while (i >= 1) {
                long j7 = 1 << (i * 8);
                while (j2 > j7) {
                    decrementCounterAt(i);
                    j2 -= j7;
                }
                i--;
            }
        } else {
            j2 = j6;
        }
        for (long j8 = 0; j8 != j2; j8++) {
            decrementCounterAt(0);
        }
        int i2 = (int) (this.byteCount + j + (this.blockSize * j6));
        if (i2 >= 0) {
            this.byteCount = 0;
            return;
        }
        decrementCounterAt(0);
        this.byteCount = this.blockSize + i2;
    }

    private void checkCounter() {
        if (this.f26757IV.length >= this.blockSize) {
            return;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.f26757IV;
            if (i == bArr.length) {
                return;
            }
            if (this.counter[i] != bArr[i]) {
                throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
            }
            i++;
        }
    }

    private void decrementCounterAt(int i) {
        byte[] bArr;
        byte b;
        int length = this.counter.length - i;
        do {
            length--;
            if (length < 0) {
                return;
            }
            b = (byte) (bArr[length] - 1);
            this.counter[length] = b;
        } while (b == -1);
    }

    private void incrementCounter(int i) {
        byte[] bArr = this.counter;
        byte b = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + i);
        if (b == 0 || bArr[bArr.length - 1] >= b) {
            return;
        }
        incrementCounterAt(1);
    }

    private void incrementCounterAt(int i) {
        byte b;
        int length = this.counter.length - i;
        do {
            length--;
            if (length < 0) {
                return;
            }
            byte[] bArr = this.counter;
            b = (byte) (bArr[length] + 1);
            bArr[length] = b;
        } while (b == 0);
    }

    private void incrementCounterChecked() {
        byte b;
        int length = this.counter.length;
        do {
            length--;
            if (length < 0) {
                break;
            }
            byte[] bArr = this.counter;
            b = (byte) (bArr[length] + 1);
            bArr[length] = b;
        } while (b == 0);
        byte[] bArr2 = this.f26757IV;
        if (length < bArr2.length && bArr2.length < this.blockSize) {
            throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
        }
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) throws DataLengthException, IllegalStateException {
        int i = this.byteCount;
        if (i == 0) {
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            byte[] bArr = this.counterOut;
            int i2 = this.byteCount;
            this.byteCount = i2 + 1;
            return (byte) (b ^ bArr[i2]);
        }
        byte[] bArr2 = this.counterOut;
        this.byteCount = i + 1;
        byte b2 = (byte) (b ^ bArr2[i]);
        if (this.byteCount == this.counter.length) {
            this.byteCount = 0;
            incrementCounterChecked();
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        byte[] bArr = this.counter;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        int length = bArr2.length - 1;
        while (length >= 1) {
            byte[] bArr3 = this.f26757IV;
            int i = length < bArr3.length ? (bArr2[length] & 255) - (bArr3[length] & 255) : bArr2[length] & 255;
            if (i < 0) {
                int i2 = length - 1;
                bArr2[i2] = (byte) (bArr2[i2] - 1);
                i += 256;
            }
            bArr2[length] = (byte) i;
            length--;
        }
        return (Pack.bigEndianToLong(bArr2, bArr2.length - 8) * this.blockSize) + this.byteCount;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        this.f26757IV = Arrays.clone(parametersWithIV.getIV());
        int i = this.blockSize;
        if (i < this.f26757IV.length) {
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.blockSize + " bytes.");
        }
        int i2 = 8 > i / 2 ? i / 2 : 8;
        if (this.blockSize - this.f26757IV.length <= i2) {
            if (parametersWithIV.getParameters() != null) {
                this.cipher.init(true, parametersWithIV.getParameters());
            }
            reset();
            return;
        }
        throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.blockSize - i2) + " bytes.");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.byteCount != 0) {
            processBytes(bArr, i, this.blockSize, bArr2, i2);
        } else {
            int i3 = this.blockSize;
            if (i + i3 > bArr.length) {
                throw new DataLengthException("input buffer too small");
            }
            if (i3 + i2 > bArr2.length) {
                throw new OutputLengthException("output buffer too short");
            }
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            for (int i4 = 0; i4 < this.blockSize; i4++) {
                bArr2[i2 + i4] = (byte) (bArr[i + i4] ^ this.counterOut[i4]);
            }
            incrementCounterChecked();
        }
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher, org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        byte b;
        if (i + i2 <= bArr.length) {
            if (i3 + i2 <= bArr2.length) {
                for (int i4 = 0; i4 < i2; i4++) {
                    int i5 = this.byteCount;
                    if (i5 == 0) {
                        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
                        byte b2 = bArr[i + i4];
                        byte[] bArr3 = this.counterOut;
                        int i6 = this.byteCount;
                        this.byteCount = i6 + 1;
                        b = (byte) (b2 ^ bArr3[i6]);
                    } else {
                        byte b3 = bArr[i + i4];
                        byte[] bArr4 = this.counterOut;
                        this.byteCount = i5 + 1;
                        b = (byte) (bArr4[i5] ^ b3);
                        if (this.byteCount == this.counter.length) {
                            this.byteCount = 0;
                            incrementCounterChecked();
                        }
                    }
                    bArr2[i3 + i4] = b;
                }
                return i2;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too small");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.counter, (byte) 0);
        byte[] bArr = this.f26757IV;
        System.arraycopy(bArr, 0, this.counter, 0, bArr.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long skip(long j) {
        adjustCounter(j);
        checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return j;
    }
}
