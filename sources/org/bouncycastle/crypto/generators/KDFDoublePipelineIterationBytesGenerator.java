package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.MacDerivationFunction;
import org.bouncycastle.crypto.params.KDFDoublePipelineIterationParameters;
import org.bouncycastle.crypto.params.KeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KDFDoublePipelineIterationBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    /* renamed from: a */
    private byte[] f26677a;
    private byte[] fixedInputData;
    private int generatedBytes;

    /* renamed from: h */
    private final int f26678h;
    private byte[] ios;

    /* renamed from: k */
    private byte[] f26679k;
    private int maxSizeExcl;
    private final Mac prf;
    private boolean useCounter;

    public KDFDoublePipelineIterationBytesGenerator(Mac mac) {
        this.prf = mac;
        this.f26678h = mac.getMacSize();
        int i = this.f26678h;
        this.f26677a = new byte[i];
        this.f26679k = new byte[i];
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void generateNext() {
        Mac mac;
        byte[] bArr;
        int length;
        if (this.generatedBytes == 0) {
            mac = this.prf;
            bArr = this.fixedInputData;
            length = bArr.length;
        } else {
            mac = this.prf;
            bArr = this.f26677a;
            length = bArr.length;
        }
        mac.update(bArr, 0, length);
        this.prf.doFinal(this.f26677a, 0);
        Mac mac2 = this.prf;
        byte[] bArr2 = this.f26677a;
        mac2.update(bArr2, 0, bArr2.length);
        if (this.useCounter) {
            int i = (this.generatedBytes / this.f26678h) + 1;
            byte[] bArr3 = this.ios;
            switch (bArr3.length) {
                case 1:
                    break;
                case 2:
                    byte[] bArr4 = this.ios;
                    bArr4[bArr4.length - 2] = (byte) (i >>> 8);
                    break;
                case 3:
                    byte[] bArr5 = this.ios;
                    bArr5[bArr5.length - 3] = (byte) (i >>> 16);
                    byte[] bArr42 = this.ios;
                    bArr42[bArr42.length - 2] = (byte) (i >>> 8);
                    break;
                case 4:
                    bArr3[0] = (byte) (i >>> 24);
                    byte[] bArr52 = this.ios;
                    bArr52[bArr52.length - 3] = (byte) (i >>> 16);
                    byte[] bArr422 = this.ios;
                    bArr422[bArr422.length - 2] = (byte) (i >>> 8);
                    break;
                default:
                    throw new IllegalStateException("Unsupported size of counter i");
            }
            byte[] bArr6 = this.ios;
            bArr6[bArr6.length - 1] = (byte) i;
            this.prf.update(bArr6, 0, bArr6.length);
        }
        Mac mac3 = this.prf;
        byte[] bArr7 = this.fixedInputData;
        mac3.update(bArr7, 0, bArr7.length);
        this.prf.doFinal(this.f26679k, 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        int i3 = this.generatedBytes;
        int i4 = i3 + i2;
        if (i4 < 0 || i4 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (i3 % this.f26678h == 0) {
            generateNext();
        }
        int i5 = this.generatedBytes;
        int i6 = this.f26678h;
        int i7 = i5 % i6;
        int min = Math.min(i6 - (i5 % i6), i2);
        System.arraycopy(this.f26679k, i7, bArr, i, min);
        this.generatedBytes += min;
        int i8 = i2 - min;
        while (true) {
            i += min;
            if (i8 <= 0) {
                return i2;
            }
            generateNext();
            min = Math.min(this.f26678h, i8);
            System.arraycopy(this.f26679k, 0, bArr, i, min);
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
        if (!(derivationParameters instanceof KDFDoublePipelineIterationParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFDoublePipelineIterationParameters kDFDoublePipelineIterationParameters = (KDFDoublePipelineIterationParameters) derivationParameters;
        this.prf.init(new KeyParameter(kDFDoublePipelineIterationParameters.getKI()));
        this.fixedInputData = kDFDoublePipelineIterationParameters.getFixedInputData();
        int r = kDFDoublePipelineIterationParameters.getR();
        this.ios = new byte[r / 8];
        int i = Integer.MAX_VALUE;
        if (kDFDoublePipelineIterationParameters.useCounter()) {
            BigInteger multiply = TWO.pow(r).multiply(BigInteger.valueOf(this.f26678h));
            if (multiply.compareTo(INTEGER_MAX) != 1) {
                i = multiply.intValue();
            }
        }
        this.maxSizeExcl = i;
        this.useCounter = kDFDoublePipelineIterationParameters.useCounter();
        this.generatedBytes = 0;
    }
}
