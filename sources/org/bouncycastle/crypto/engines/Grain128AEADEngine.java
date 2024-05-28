package org.bouncycastle.crypto.engines;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.modes.AEADCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Grain128AEADEngine implements AEADCipher {
    private static final int STATE_SIZE = 4;
    private int[] authAcc;
    private int[] authSr;
    private int[] lfsr;
    private byte[] mac;
    private int[] nfsr;
    private int output;
    private byte[] workingIV;
    private byte[] workingKey;
    private boolean initialised = false;
    private boolean isEven = true;
    private boolean aadFinished = false;
    private ErasableOutputStream aadData = new ErasableOutputStream();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class ErasableOutputStream extends ByteArrayOutputStream {
        public void erase() {
            Arrays.fill(this.buf, (byte) 0);
            reset();
        }

        public byte[] getBuf() {
            return this.buf;
        }
    }

    private void accumulate() {
        int[] iArr = this.authAcc;
        int i = iArr[0];
        int[] iArr2 = this.authSr;
        iArr[0] = i ^ iArr2[0];
        iArr[1] = iArr[1] ^ iArr2[1];
    }

    private void authShift(int i) {
        int[] iArr = this.authSr;
        iArr[0] = (iArr[0] >>> 1) | (iArr[1] << 31);
        iArr[1] = (i << 31) | (iArr[1] >>> 1);
    }

    private void doProcessAADBytes(byte[] bArr, int i, int i2) {
        int i3;
        byte[] bArr2;
        if (i2 < 128) {
            bArr2 = new byte[i2 + 1];
            bArr2[0] = (byte) reverseByte(i2);
            i3 = 0;
        } else {
            int len_length = len_length(i2);
            byte[] bArr3 = new byte[len_length + 1 + i2];
            bArr3[0] = (byte) reverseByte(len_length | 128);
            int i4 = i2;
            int i5 = 0;
            while (i5 < len_length) {
                i5++;
                bArr3[i5] = (byte) reverseByte(i4 & 255);
                i4 >>>= 8;
            }
            i3 = len_length;
            bArr2 = bArr3;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            bArr2[1 + i3 + i6] = (byte) reverseByte(bArr[i + i6]);
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < bArr2.length) {
            int i9 = i8;
            for (int i10 = 0; i10 < 16; i10++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                if ((i10 & 1) == 1) {
                    if (((byte) (bArr2[i9 >> 3] & (1 << (7 - (i9 & 7))))) != 0) {
                        accumulate();
                    }
                    authShift(this.output);
                    i9++;
                }
            }
            i7++;
            i8 = i9;
        }
    }

    private byte[] getKeyStream(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        byte[] bArr3 = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr3[i4] = (byte) reverseByte(bArr[i + i4]);
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i5 < i2) {
            int i9 = i6;
            int i10 = i8;
            int i11 = i7;
            byte b = 0;
            for (int i12 = 0; i12 < 16; i12++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                if (this.isEven) {
                    b = (byte) (b | ((((bArr3[i9 >> 3] >>> (7 - (i9 & 7))) & 1) ^ this.output) << (i10 & 7)));
                    i9++;
                    i10++;
                    this.isEven = false;
                } else {
                    if ((bArr3[i11 >> 3] & (1 << (7 - (i11 & 7)))) != 0) {
                        accumulate();
                    }
                    authShift(this.output);
                    i11++;
                    this.isEven = true;
                }
            }
            bArr2[i3 + i5] = b;
            i5++;
            i7 = i11;
            i6 = i9;
            i8 = i10;
        }
        return bArr2;
    }

    private int getOutput() {
        int[] iArr = this.nfsr;
        int i = iArr[0] >>> 12;
        int i2 = iArr[2];
        int i3 = iArr[2] >>> 31;
        int[] iArr2 = this.lfsr;
        int i4 = (iArr2[0] >>> 20) & (iArr2[0] >>> 13);
        return (((((((((((i3 & i) & (iArr2[2] >>> 30)) ^ (((i4 ^ (i & (iArr2[0] >>> 8))) ^ (i3 & (iArr2[1] >>> 10))) ^ ((iArr2[1] >>> 28) & (iArr2[2] >>> 15)))) ^ (iArr2[2] >>> 29)) ^ (iArr[0] >>> 2)) ^ (iArr[0] >>> 15)) ^ (iArr[1] >>> 4)) ^ (iArr[1] >>> 13)) ^ i2) ^ (iArr[2] >>> 9)) ^ (iArr[2] >>> 25)) & 1;
    }

    private int getOutputLFSR() {
        int[] iArr = this.lfsr;
        return (iArr[3] ^ (((((iArr[0] >>> 7) ^ iArr[0]) ^ (iArr[1] >>> 6)) ^ (iArr[2] >>> 6)) ^ (iArr[2] >>> 17))) & 1;
    }

    private int getOutputNFSR() {
        int[] iArr = this.nfsr;
        return (((((((((((iArr[3] ^ (((iArr[0] ^ (iArr[0] >>> 26)) ^ (iArr[1] >>> 24)) ^ (iArr[2] >>> 27))) ^ ((iArr[0] >>> 3) & (iArr[2] >>> 3))) ^ ((iArr[0] >>> 11) & (iArr[0] >>> 13))) ^ ((iArr[0] >>> 17) & (iArr[0] >>> 18))) ^ ((iArr[0] >>> 27) & (iArr[1] >>> 27))) ^ ((iArr[1] >>> 8) & (iArr[1] >>> 16))) ^ ((iArr[1] >>> 29) & (iArr[2] >>> 1))) ^ ((iArr[2] >>> 4) & (iArr[2] >>> 20))) ^ (((iArr[0] >>> 22) & (iArr[0] >>> 24)) & (iArr[0] >>> 25))) ^ (((iArr[2] >>> 6) & (iArr[2] >>> 14)) & (iArr[2] >>> 18))) ^ ((((iArr[2] >>> 24) & (iArr[2] >>> 28)) & (iArr[2] >>> 29)) & (iArr[2] >>> 31))) & 1;
    }

    private void initGrain() {
        for (int i = 0; i < 320; i++) {
            this.output = getOutput();
            this.nfsr = shift(this.nfsr, ((getOutputNFSR() ^ this.lfsr[0]) ^ this.output) & 1);
            this.lfsr = shift(this.lfsr, 1 & (getOutputLFSR() ^ this.output));
        }
        for (int i2 = 0; i2 < 8; i2++) {
            for (int i3 = 0; i3 < 8; i3++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (((getOutputNFSR() ^ this.lfsr[0]) ^ this.output) ^ (this.workingKey[i2] >> i3)) & 1);
                this.lfsr = shift(this.lfsr, ((getOutputLFSR() ^ this.output) ^ (this.workingKey[i2 + 8] >> i3)) & 1);
            }
        }
        for (int i4 = 0; i4 < 2; i4++) {
            for (int i5 = 0; i5 < 32; i5++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                int[] iArr = this.authAcc;
                iArr[i4] = iArr[i4] | (this.output << i5);
            }
        }
        for (int i6 = 0; i6 < 2; i6++) {
            for (int i7 = 0; i7 < 32; i7++) {
                this.output = getOutput();
                this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
                this.lfsr = shift(this.lfsr, getOutputLFSR() & 1);
                int[] iArr2 = this.authSr;
                iArr2[i6] = iArr2[i6] | (this.output << i7);
            }
        }
        this.initialised = true;
    }

    private int len_length(int i) {
        if ((i & 255) == i) {
            return 1;
        }
        if ((65535 & i) == i) {
            return 2;
        }
        return (16777215 & i) == i ? 3 : 4;
    }

    private int reverseByte(int i) {
        int i2 = (((i & 170) >>> 1) | ((i & 85) << 1)) & 255;
        int i3 = (((i2 & 204) >>> 2) | ((i2 & 51) << 2)) & 255;
        return (((i3 & 240) >>> 4) | ((i3 & 15) << 4)) & 255;
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        bArr2[12] = -1;
        bArr2[13] = -1;
        bArr2[14] = -1;
        bArr2[15] = Byte.MAX_VALUE;
        this.workingKey = bArr;
        this.workingIV = bArr2;
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.nfsr;
            if (i >= iArr.length) {
                return;
            }
            byte[] bArr3 = this.workingKey;
            int i3 = i2 + 3;
            int i4 = i2 + 2;
            int i5 = i2 + 1;
            iArr[i] = (bArr3[i2] & 255) | (bArr3[i3] << 24) | ((bArr3[i4] << 16) & 16711680) | ((bArr3[i5] << 8) & 65280);
            int[] iArr2 = this.lfsr;
            byte[] bArr4 = this.workingIV;
            iArr2[i] = (bArr4[i2] & 255) | (bArr4[i3] << 24) | ((bArr4[i4] << 16) & 16711680) | ((bArr4[i5] << 8) & 65280);
            i2 += 4;
            i++;
        }
    }

    private int[] shift(int[] iArr, int i) {
        iArr[0] = (iArr[0] >>> 1) | (iArr[1] << 31);
        iArr[1] = (iArr[1] >>> 1) | (iArr[2] << 31);
        iArr[2] = (iArr[2] >>> 1) | (iArr[3] << 31);
        iArr[3] = (i << 31) | (iArr[3] >>> 1);
        return iArr;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        if (!this.aadFinished) {
            doProcessAADBytes(this.aadData.getBuf(), 0, this.aadData.size());
            this.aadFinished = true;
        }
        this.mac = new byte[8];
        this.output = getOutput();
        this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) & 1);
        this.lfsr = shift(this.lfsr, 1 & getOutputLFSR());
        accumulate();
        int i2 = 0;
        int i3 = 0;
        while (i2 < 2) {
            int i4 = i3;
            int i5 = 0;
            while (i5 < 4) {
                this.mac[i4] = (byte) ((this.authAcc[i2] >>> (i5 << 3)) & 255);
                i5++;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        byte[] bArr2 = this.mac;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        try {
            return this.mac.length;
        } finally {
            reset();
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return "Grain-128AEAD";
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        return this.mac;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        return i + 8;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        return i;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Grain-128AEAD init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv == null || iv.length != 12) {
            throw new IllegalArgumentException("Grain-128AEAD requires exactly 12 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("Grain-128AEAD init parameters must include a key");
        }
        byte[] key = ((KeyParameter) parametersWithIV.getParameters()).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("Grain-128AEAD key must be 128 bits long");
        }
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
        this.workingIV = new byte[16];
        this.workingKey = new byte[16];
        this.lfsr = new int[4];
        this.nfsr = new int[4];
        this.authAcc = new int[2];
        this.authSr = new int[2];
        System.arraycopy(iv, 0, this.workingIV, 0, iv.length);
        System.arraycopy(key, 0, this.workingKey, 0, key.length);
        reset();
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        if (this.aadFinished) {
            throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
        }
        this.aadData.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i, int i2) {
        if (this.aadFinished) {
            throw new IllegalStateException("associated data must be added before plaintext/ciphertext");
        }
        this.aadData.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return processBytes(new byte[]{b}, 0, 1, bArr, i);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (!this.aadFinished) {
            doProcessAADBytes(this.aadData.getBuf(), 0, this.aadData.size());
            this.aadFinished = true;
        }
        if (i + i2 <= bArr.length) {
            if (i3 + i2 <= bArr2.length) {
                getKeyStream(bArr, i, i2, bArr2, i3);
                return i2;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        this.isEven = true;
        this.mac = null;
        this.aadData.reset();
        this.aadFinished = false;
        setKey(this.workingKey, this.workingIV);
        initGrain();
    }
}
