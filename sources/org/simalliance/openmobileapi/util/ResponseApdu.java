package org.simalliance.openmobileapi.util;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ResponseApdu {
    public byte[] mData;
    public byte[] mSw;

    public ResponseApdu(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length >= 2 && bArr.length <= 65538) {
                if (bArr.length > 2) {
                    byte[] bArr2 = new byte[bArr.length - 2];
                    this.mData = bArr2;
                    System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
                }
                byte[] bArr3 = new byte[2];
                this.mSw = bArr3;
                System.arraycopy(bArr, bArr.length - 2, bArr3, 0, 2);
                return;
            }
            throw new IllegalArgumentException("Invalid response length (" + bArr.length + ").");
        }
        throw new IllegalArgumentException("Response must not be null.");
    }

    public byte[] getData() {
        return this.mData;
    }

    public byte[] getSw() {
        return this.mSw;
    }

    public int getSw1Value() {
        return this.mSw[0] & 255;
    }

    public int getSw2Value() {
        return this.mSw[1] & 255;
    }

    public int getSwValue() {
        byte[] bArr = this.mSw;
        return ((bArr[0] & 255) << 8) + (bArr[1] & 255);
    }

    public boolean isSuccess() {
        return getSwValue() == 36864;
    }

    public boolean isWarning() {
        byte[] bArr = this.mSw;
        return bArr[0] == 98 || bArr[0] == 99;
    }
}
