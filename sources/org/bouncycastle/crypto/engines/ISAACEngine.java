package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ISAACEngine implements StreamCipher {
    private final int sizeL = 8;
    private final int stateArraySize = 256;
    private int[] engineState = null;
    private int[] results = null;

    /* renamed from: a */
    private int f26623a = 0;

    /* renamed from: b */
    private int f26624b = 0;

    /* renamed from: c */
    private int f26625c = 0;
    private int index = 0;
    private byte[] keyStream = new byte[1024];
    private byte[] workingKey = null;
    private boolean initialised = false;

    private void isaac() {
        int i;
        int i2;
        int i3 = this.f26624b;
        int i4 = this.f26625c + 1;
        this.f26625c = i4;
        this.f26624b = i3 + i4;
        for (int i5 = 0; i5 < 256; i5++) {
            int i6 = this.engineState[i5];
            switch (i5 & 3) {
                case 0:
                    i = this.f26623a;
                    i2 = i << 13;
                    break;
                case 1:
                    i = this.f26623a;
                    i2 = i >>> 6;
                    break;
                case 2:
                    i = this.f26623a;
                    i2 = i << 2;
                    break;
                case 3:
                    i = this.f26623a;
                    i2 = i >>> 16;
                    break;
                default:
                    int i7 = this.f26623a;
                    int[] iArr = this.engineState;
                    this.f26623a = i7 + iArr[(i5 + 128) & 255];
                    int i8 = iArr[(i6 >>> 2) & 255] + this.f26623a + this.f26624b;
                    iArr[i5] = i8;
                    int[] iArr2 = this.results;
                    int i9 = iArr[(i8 >>> 10) & 255] + i6;
                    this.f26624b = i9;
                    iArr2[i5] = i9;
            }
            this.f26623a = i ^ i2;
            int i72 = this.f26623a;
            int[] iArr3 = this.engineState;
            this.f26623a = i72 + iArr3[(i5 + 128) & 255];
            int i82 = iArr3[(i6 >>> 2) & 255] + this.f26623a + this.f26624b;
            iArr3[i5] = i82;
            int[] iArr22 = this.results;
            int i92 = iArr3[(i82 >>> 10) & 255] + i6;
            this.f26624b = i92;
            iArr22[i5] = i92;
        }
    }

    private void mix(int[] iArr) {
        iArr[0] = iArr[0] ^ (iArr[1] << 11);
        iArr[3] = iArr[3] + iArr[0];
        iArr[1] = iArr[1] + iArr[2];
        iArr[1] = iArr[1] ^ (iArr[2] >>> 2);
        iArr[4] = iArr[4] + iArr[1];
        iArr[2] = iArr[2] + iArr[3];
        iArr[2] = iArr[2] ^ (iArr[3] << 8);
        iArr[5] = iArr[5] + iArr[2];
        iArr[3] = iArr[3] + iArr[4];
        iArr[3] = iArr[3] ^ (iArr[4] >>> 16);
        iArr[6] = iArr[6] + iArr[3];
        iArr[4] = iArr[4] + iArr[5];
        iArr[4] = iArr[4] ^ (iArr[5] << 10);
        iArr[7] = iArr[7] + iArr[4];
        iArr[5] = iArr[5] + iArr[6];
        iArr[5] = (iArr[6] >>> 4) ^ iArr[5];
        iArr[0] = iArr[0] + iArr[5];
        iArr[6] = iArr[6] + iArr[7];
        iArr[6] = iArr[6] ^ (iArr[7] << 8);
        iArr[1] = iArr[1] + iArr[6];
        iArr[7] = iArr[7] + iArr[0];
        iArr[7] = iArr[7] ^ (iArr[0] >>> 9);
        iArr[2] = iArr[2] + iArr[7];
        iArr[0] = iArr[0] + iArr[1];
    }

    private void setKey(byte[] bArr) {
        this.workingKey = bArr;
        if (this.engineState == null) {
            this.engineState = new int[256];
        }
        if (this.results == null) {
            this.results = new int[256];
        }
        for (int i = 0; i < 256; i++) {
            int[] iArr = this.engineState;
            this.results[i] = 0;
            iArr[i] = 0;
        }
        this.f26625c = 0;
        this.f26624b = 0;
        this.f26623a = 0;
        this.index = 0;
        byte[] bArr2 = new byte[bArr.length + (bArr.length & 3)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int i2 = 0; i2 < bArr2.length; i2 += 4) {
            this.results[i2 >>> 2] = Pack.littleEndianToInt(bArr2, i2);
        }
        int[] iArr2 = new int[8];
        for (int i3 = 0; i3 < 8; i3++) {
            iArr2[i3] = -1640531527;
        }
        for (int i4 = 0; i4 < 4; i4++) {
            mix(iArr2);
        }
        int i5 = 0;
        while (i5 < 2) {
            for (int i6 = 0; i6 < 256; i6 += 8) {
                for (int i7 = 0; i7 < 8; i7++) {
                    iArr2[i7] = iArr2[i7] + (i5 < 1 ? this.results[i6 + i7] : this.engineState[i6 + i7]);
                }
                mix(iArr2);
                for (int i8 = 0; i8 < 8; i8++) {
                    this.engineState[i6 + i8] = iArr2[i8];
                }
            }
            i5++;
        }
        isaac();
        this.initialised = true;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "ISAAC";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            byte[] key = ((KeyParameter) cipherParameters).getKey();
            setKey(key);
            CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), key.length < 32 ? key.length * 8 : 256, cipherParameters, Utils.getPurpose(z)));
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ISAAC init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + i2 <= bArr.length) {
            if (i3 + i2 <= bArr2.length) {
                for (int i4 = 0; i4 < i2; i4++) {
                    if (this.index == 0) {
                        isaac();
                        this.keyStream = Pack.intToBigEndian(this.results);
                    }
                    byte[] bArr3 = this.keyStream;
                    int i5 = this.index;
                    bArr2[i4 + i3] = (byte) (bArr3[i5] ^ bArr[i4 + i]);
                    this.index = (i5 + 1) & 1023;
                }
                return i2;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        setKey(this.workingKey);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.index == 0) {
            isaac();
            this.keyStream = Pack.intToBigEndian(this.results);
        }
        byte[] bArr = this.keyStream;
        int i = this.index;
        byte b2 = (byte) (b ^ bArr[i]);
        this.index = (i + 1) & 1023;
        return b2;
    }
}
