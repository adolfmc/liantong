package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.MacDerivationFunction;
import org.bouncycastle.crypto.params.KDFCounterParameters;
import org.bouncycastle.crypto.params.KeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KDFCounterBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private byte[] fixedInputDataCtrPrefix;
    private byte[] fixedInputData_afterCtr;
    private int generatedBytes;

    /* renamed from: h */
    private final int f26675h;
    private byte[] ios;

    /* renamed from: k */
    private byte[] f26676k;
    private int maxSizeExcl;
    private final Mac prf;

    public KDFCounterBytesGenerator(Mac mac) {
        this.prf = mac;
        this.f26675h = mac.getMacSize();
        this.f26676k = new byte[this.f26675h];
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void generateNext() {
        int i = (this.generatedBytes / this.f26675h) + 1;
        byte[] bArr = this.ios;
        switch (bArr.length) {
            case 1:
                break;
            case 2:
                byte[] bArr2 = this.ios;
                bArr2[bArr2.length - 2] = (byte) (i >>> 8);
                break;
            case 3:
                byte[] bArr3 = this.ios;
                bArr3[bArr3.length - 3] = (byte) (i >>> 16);
                byte[] bArr22 = this.ios;
                bArr22[bArr22.length - 2] = (byte) (i >>> 8);
                break;
            case 4:
                bArr[0] = (byte) (i >>> 24);
                byte[] bArr32 = this.ios;
                bArr32[bArr32.length - 3] = (byte) (i >>> 16);
                byte[] bArr222 = this.ios;
                bArr222[bArr222.length - 2] = (byte) (i >>> 8);
                break;
            default:
                throw new IllegalStateException("Unsupported size of counter i");
        }
        byte[] bArr4 = this.ios;
        bArr4[bArr4.length - 1] = (byte) i;
        Mac mac = this.prf;
        byte[] bArr5 = this.fixedInputDataCtrPrefix;
        mac.update(bArr5, 0, bArr5.length);
        Mac mac2 = this.prf;
        byte[] bArr6 = this.ios;
        mac2.update(bArr6, 0, bArr6.length);
        Mac mac3 = this.prf;
        byte[] bArr7 = this.fixedInputData_afterCtr;
        mac3.update(bArr7, 0, bArr7.length);
        this.prf.doFinal(this.f26676k, 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        int i3 = this.generatedBytes;
        int i4 = i3 + i2;
        if (i4 < 0 || i4 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (i3 % this.f26675h == 0) {
            generateNext();
        }
        int i5 = this.generatedBytes;
        int i6 = this.f26675h;
        int i7 = i5 % i6;
        int min = Math.min(i6 - (i5 % i6), i2);
        System.arraycopy(this.f26676k, i7, bArr, i, min);
        this.generatedBytes += min;
        int i8 = i2 - min;
        while (true) {
            i += min;
            if (i8 <= 0) {
                return i2;
            }
            generateNext();
            min = Math.min(this.f26675h, i8);
            System.arraycopy(this.f26676k, 0, bArr, i, min);
            this.generatedBytes += min;
            i8 -= min;
        }
    }

    @Override // org.bouncycastle.crypto.MacDerivationFunction
    public Mac getMac() {
        return this.prf;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (!(derivationParameters instanceof KDFCounterParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFCounterParameters kDFCounterParameters = (KDFCounterParameters) derivationParameters;
        this.prf.init(new KeyParameter(kDFCounterParameters.getKI()));
        this.fixedInputDataCtrPrefix = kDFCounterParameters.getFixedInputDataCounterPrefix();
        this.fixedInputData_afterCtr = kDFCounterParameters.getFixedInputDataCounterSuffix();
        int r = kDFCounterParameters.getR();
        this.ios = new byte[r / 8];
        BigInteger multiply = TWO.pow(r).multiply(BigInteger.valueOf(this.f26675h));
        this.maxSizeExcl = multiply.compareTo(INTEGER_MAX) == 1 ? Integer.MAX_VALUE : multiply.intValue();
        this.generatedBytes = 0;
    }
}
