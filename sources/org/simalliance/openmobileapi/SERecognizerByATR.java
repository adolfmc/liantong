package org.simalliance.openmobileapi;

import java.util.Arrays;
import org.simalliance.openmobileapi.internal.ErrorStrings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SERecognizerByATR extends SERecognizer {
    public static final int ATR_MAX_LENGTH = 32;
    public static final int ATR_MIN_LENGTH = 2;
    public byte[] mAtr;
    public byte[] mMask;

    public SERecognizerByATR(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            throw new IllegalArgumentException(ErrorStrings.paramNull("atr"));
        }
        if (bArr2 != null) {
            if (bArr.length >= 2 && bArr.length <= 32) {
                if (bArr.length == bArr2.length) {
                    byte[] bArr3 = new byte[bArr.length];
                    this.mAtr = bArr3;
                    System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
                    byte[] bArr4 = new byte[bArr2.length];
                    this.mMask = bArr4;
                    System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
                    return;
                }
                throw new IllegalArgumentException("atr length and mask length must be equal.");
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("atr"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("mask"));
    }

    private byte[] maskAtr(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] & bArr2[i]);
        }
        return bArr3;
    }

    @Override // org.simalliance.openmobileapi.SERecognizer
    public boolean isMatching(Session session) {
        if (session != null) {
            byte[] atr = session.getATR();
            int length = atr.length;
            byte[] bArr = this.mAtr;
            if (length != bArr.length) {
                return false;
            }
            return Arrays.equals(maskAtr(bArr, this.mMask), maskAtr(atr, this.mMask));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("session"));
    }
}
