package org.simalliance.openmobileapi.internal;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TlvEntryWrapper {
    public byte[] mTag;
    public int mTotalLength;
    public byte[] mValue;

    public TlvEntryWrapper(byte[] bArr, int i, TlvParser tlvParser) {
        byte[] tagBytes = tlvParser.getTagBytes(bArr, i);
        this.mTag = tagBytes;
        int length = i + tagBytes.length;
        byte[] lengthBytes = tlvParser.getLengthBytes(bArr, length);
        byte[] bArr2 = new byte[tlvParser.getLengthValue(lengthBytes)];
        this.mValue = bArr2;
        System.arraycopy(bArr, length + lengthBytes.length, bArr2, 0, bArr2.length);
        this.mTotalLength = this.mTag.length + lengthBytes.length + this.mValue.length;
    }

    public byte[] encode() {
        byte[] bArr = new byte[this.mTotalLength];
        byte[] bArr2 = this.mTag;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        byte[] encodeLength = DerTlvCoder.encodeLength(this.mValue.length);
        byte[] bArr3 = this.mTag;
        System.arraycopy(encodeLength, bArr3.length, bArr, bArr3.length, encodeLength.length);
        byte[] bArr4 = this.mValue;
        System.arraycopy(bArr4, 0, bArr, this.mTag.length + encodeLength.length, bArr4.length);
        return bArr;
    }

    public byte[] getTag() {
        return this.mTag;
    }

    public int getTotalLength() {
        return this.mTotalLength;
    }

    public byte[] getValue() {
        return this.mValue;
    }
}
