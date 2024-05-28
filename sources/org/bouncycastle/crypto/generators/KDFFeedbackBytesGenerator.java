package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.MacDerivationFunction;
import org.bouncycastle.crypto.params.KDFFeedbackParameters;
import org.bouncycastle.crypto.params.KeyParameter;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class KDFFeedbackBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private byte[] fixedInputData;
    private int generatedBytes;

    /* renamed from: h */
    private final int f26680h;
    private byte[] ios;

    /* renamed from: iv */
    private byte[] f26681iv;

    /* renamed from: k */
    private byte[] f26682k;
    private int maxSizeExcl;
    private final Mac prf;
    private boolean useCounter;

    public KDFFeedbackBytesGenerator(Mac mac) {
        this.prf = mac;
        this.f26680h = mac.getMacSize();
        this.f26682k = new byte[this.f26680h];
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void generateNext() {
        Mac mac;
        byte[] bArr;
        int length;
        if (this.generatedBytes == 0) {
            mac = this.prf;
            bArr = this.f26681iv;
            length = bArr.length;
        } else {
            mac = this.prf;
            bArr = this.f26682k;
            length = bArr.length;
        }
        mac.update(bArr, 0, length);
        if (this.useCounter) {
            int i = (this.generatedBytes / this.f26680h) + 1;
            byte[] bArr2 = this.ios;
            switch (bArr2.length) {
                case 1:
                    break;
                case 2:
                    byte[] bArr3 = this.ios;
                    bArr3[bArr3.length - 2] = (byte) (i >>> 8);
                    break;
                case 3:
                    byte[] bArr4 = this.ios;
                    bArr4[bArr4.length - 3] = (byte) (i >>> 16);
                    byte[] bArr32 = this.ios;
                    bArr32[bArr32.length - 2] = (byte) (i >>> 8);
                    break;
                case 4:
                    bArr2[0] = (byte) (i >>> 24);
                    byte[] bArr42 = this.ios;
                    bArr42[bArr42.length - 3] = (byte) (i >>> 16);
                    byte[] bArr322 = this.ios;
                    bArr322[bArr322.length - 2] = (byte) (i >>> 8);
                    break;
                default:
                    throw new IllegalStateException("Unsupported size of counter i");
            }
            byte[] bArr5 = this.ios;
            bArr5[bArr5.length - 1] = (byte) i;
            this.prf.update(bArr5, 0, bArr5.length);
        }
        Mac mac2 = this.prf;
        byte[] bArr6 = this.fixedInputData;
        mac2.update(bArr6, 0, bArr6.length);
        this.prf.doFinal(this.f26682k, 0);
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        int i3 = this.generatedBytes;
        int i4 = i3 + i2;
        if (i4 < 0 || i4 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (i3 % this.f26680h == 0) {
            generateNext();
        }
        int i5 = this.generatedBytes;
        int i6 = this.f26680h;
        int i7 = i5 % i6;
        int min = Math.min(i6 - (i5 % i6), i2);
        System.arraycopy(this.f26682k, i7, bArr, i, min);
        this.generatedBytes += min;
        int i8 = i2 - min;
        while (true) {
            i += min;
            if (i8 <= 0) {
                return i2;
            }
            generateNext();
            min = Math.min(this.f26680h, i8);
            System.arraycopy(this.f26682k, 0, bArr, i, min);
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
        if (!(derivationParameters instanceof KDFFeedbackParameters)) {
            throw new IllegalArgumentException("Wrong type of arguments given");
        }
        KDFFeedbackParameters kDFFeedbackParameters = (KDFFeedbackParameters) derivationParameters;
        this.prf.init(new KeyParameter(kDFFeedbackParameters.getKI()));
        this.fixedInputData = kDFFeedbackParameters.getFixedInputData();
        int r = kDFFeedbackParameters.getR();
        this.ios = new byte[r / 8];
        int i = Integer.MAX_VALUE;
        if (kDFFeedbackParameters.useCounter()) {
            BigInteger multiply = TWO.pow(r).multiply(BigInteger.valueOf(this.f26680h));
            if (multiply.compareTo(INTEGER_MAX) != 1) {
                i = multiply.intValue();
            }
        }
        this.maxSizeExcl = i;
        this.f26681iv = kDFFeedbackParameters.getIV();
        this.useCounter = kDFFeedbackParameters.useCounter();
        this.generatedBytes = 0;
    }
}
