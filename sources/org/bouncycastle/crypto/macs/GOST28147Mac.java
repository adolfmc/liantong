package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class GOST28147Mac implements Mac {
    private static final int BLOCK_SIZE = 8;
    private static final int MAC_SIZE = 4;

    /* renamed from: S */
    private byte[] f26687S;
    private byte[] buf;
    private int bufOff;
    private boolean firstStep;
    private byte[] mac;
    private byte[] macIV;
    private final CryptoServicePurpose purpose;
    private int[] workingKey;

    public GOST28147Mac() {
        this(CryptoServicePurpose.AUTHENTICATION);
    }

    public GOST28147Mac(CryptoServicePurpose cryptoServicePurpose) {
        this.firstStep = true;
        this.workingKey = null;
        this.macIV = null;
        this.f26687S = new byte[]{9, 6, 3, 2, 8, 11, 1, 7, 10, 4, 14, 15, 12, 0, 13, 5, 3, 7, 14, 9, 8, 10, 15, 0, 5, 2, 6, 12, 11, 4, 13, 1, 14, 4, 6, 2, 11, 3, 13, 8, 12, 15, 5, 10, 0, 7, 1, 9, 14, 7, 10, 12, 13, 1, 3, 9, 0, 2, 11, 4, 15, 8, 5, 6, 11, 5, 1, 9, 8, 13, 15, 0, 14, 4, 2, 3, 12, 7, 10, 6, 3, 10, 13, 12, 1, 2, 0, 11, 7, 5, 9, 4, 8, 15, 14, 6, 1, 13, 2, 9, 7, 10, 6, 0, 8, 12, 4, 5, 15, 3, 11, 14, 11, 10, 15, 5, 0, 12, 14, 8, 6, 2, 3, 9, 1, 7, 13, 4};
        this.purpose = cryptoServicePurpose;
        this.mac = new byte[8];
        this.buf = new byte[8];
        this.bufOff = 0;
    }

    private static void CM5func(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        for (int i2 = 0; i2 < 8; i2++) {
            bArr3[i2] = (byte) (bArr[i + i2] ^ bArr2[i2]);
        }
    }

    private int[] generateWorkingKey(byte[] bArr) {
        if (bArr.length == 32) {
            int[] iArr = new int[8];
            for (int i = 0; i != 8; i++) {
                iArr[i] = Pack.littleEndianToInt(bArr, i * 4);
            }
            return iArr;
        }
        throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
    }

    private void gost28147MacFunc(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = littleEndianToInt2;
            int i5 = littleEndianToInt;
            int i6 = 0;
            while (i6 < 8) {
                int gost28147_mainStep = i4 ^ gost28147_mainStep(i5, iArr[i6]);
                i6++;
                i4 = i5;
                i5 = gost28147_mainStep;
            }
            i3++;
            littleEndianToInt = i5;
            littleEndianToInt2 = i4;
        }
        Pack.intToLittleEndian(littleEndianToInt, bArr2, i2);
        Pack.intToLittleEndian(littleEndianToInt2, bArr2, i2 + 4);
    }

    private int gost28147_mainStep(int i, int i2) {
        int i3 = i2 + i;
        byte[] bArr = this.f26687S;
        int i4 = (bArr[((i3 >> 0) & 15) + 0] << 0) + (bArr[((i3 >> 4) & 15) + 16] << 4) + (bArr[((i3 >> 8) & 15) + 32] << 8) + (bArr[((i3 >> 12) & 15) + 48] << 12) + (bArr[((i3 >> 16) & 15) + 64] << 16) + (bArr[((i3 >> 20) & 15) + 80] << 20) + (bArr[((i3 >> 24) & 15) + 96] << 24) + (bArr[((i3 >> 28) & 15) + 112] << 28);
        return (i4 << 11) | (i4 >>> 21);
    }

    private void recursiveInit(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters == null) {
            return;
        }
        CipherParameters cipherParameters2 = null;
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            System.arraycopy(parametersWithSBox.getSBox(), 0, this.f26687S, 0, parametersWithSBox.getSBox().length);
            cipherParameters2 = parametersWithSBox.getParameters();
        } else if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            byte[] bArr = this.mac;
            System.arraycopy(iv, 0, bArr, 0, bArr.length);
            this.macIV = parametersWithIV.getIV();
            cipherParameters2 = parametersWithIV.getParameters();
        }
        recursiveInit(cipherParameters2);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        while (true) {
            int i2 = this.bufOff;
            if (i2 >= 8) {
                break;
            }
            this.buf[i2] = 0;
            this.bufOff = i2 + 1;
        }
        byte[] bArr2 = this.buf;
        byte[] bArr3 = new byte[bArr2.length];
        if (this.firstStep) {
            this.firstStep = false;
            System.arraycopy(bArr2, 0, bArr3, 0, this.mac.length);
        } else {
            CM5func(bArr2, 0, this.mac, bArr3);
        }
        gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
        byte[] bArr4 = this.mac;
        System.arraycopy(bArr4, (bArr4.length / 2) - 4, bArr, i, 4);
        reset();
        return 4;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "GOST28147Mac";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 4;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        reset();
        this.buf = new byte[8];
        this.macIV = null;
        recursiveInit(cipherParameters);
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 178, cipherParameters, this.purpose));
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        int i = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i >= bArr.length) {
                this.bufOff = 0;
                this.firstStep = true;
                return;
            }
            bArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        byte[] bArr;
        int i = this.bufOff;
        byte[] bArr2 = this.buf;
        if (i == bArr2.length) {
            byte[] bArr3 = new byte[bArr2.length];
            if (this.firstStep) {
                this.firstStep = false;
                bArr = this.macIV;
                if (bArr == null) {
                    System.arraycopy(bArr2, 0, bArr3, 0, this.mac.length);
                    gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
                    this.bufOff = 0;
                }
            } else {
                bArr = this.mac;
            }
            CM5func(bArr2, 0, bArr, bArr3);
            gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr4 = this.buf;
        int i2 = this.bufOff;
        this.bufOff = i2 + 1;
        bArr4[i2] = b;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003d A[LOOP:0: B:14:0x003b->B:15:0x003d, LOOP_END] */
    @Override // org.bouncycastle.crypto.Mac
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void update(byte[] r12, int r13, int r14) throws org.bouncycastle.crypto.DataLengthException, java.lang.IllegalStateException {
        /*
            r11 = this;
            if (r14 < 0) goto L5f
            int r0 = r11.bufOff
            r1 = 8
            int r2 = 8 - r0
            if (r14 <= r2) goto L52
            byte[] r3 = r11.buf
            java.lang.System.arraycopy(r12, r13, r3, r0, r2)
            byte[] r0 = r11.buf
            int r3 = r0.length
            byte[] r3 = new byte[r3]
            boolean r4 = r11.firstStep
            r10 = 0
            if (r4 == 0) goto L27
            r11.firstStep = r10
            byte[] r4 = r11.macIV
            if (r4 == 0) goto L20
            goto L29
        L20:
            byte[] r4 = r11.mac
            int r4 = r4.length
            java.lang.System.arraycopy(r0, r10, r3, r10, r4)
            goto L2c
        L27:
            byte[] r4 = r11.mac
        L29:
            CM5func(r0, r10, r4, r3)
        L2c:
            int[] r5 = r11.workingKey
            r7 = 0
            byte[] r8 = r11.mac
            r9 = 0
            r4 = r11
            r6 = r3
            r4.gost28147MacFunc(r5, r6, r7, r8, r9)
            r11.bufOff = r10
            int r14 = r14 - r2
            int r13 = r13 + r2
        L3b:
            if (r14 <= r1) goto L52
            byte[] r0 = r11.mac
            CM5func(r12, r13, r0, r3)
            int[] r5 = r11.workingKey
            r7 = 0
            byte[] r8 = r11.mac
            r9 = 0
            r4 = r11
            r6 = r3
            r4.gost28147MacFunc(r5, r6, r7, r8, r9)
            int r14 = r14 + (-8)
            int r13 = r13 + 8
            goto L3b
        L52:
            byte[] r0 = r11.buf
            int r1 = r11.bufOff
            java.lang.System.arraycopy(r12, r13, r0, r1, r14)
            int r12 = r11.bufOff
            int r12 = r12 + r14
            r11.bufOff = r12
            return
        L5f:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Can't have a negative input length!"
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.macs.GOST28147Mac.update(byte[], int, int):void");
    }
}
