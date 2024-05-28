package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Date;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DERGeneralizedTime extends ASN1GeneralizedTime {
    public DERGeneralizedTime(String str) {
        super(str);
    }

    public DERGeneralizedTime(Date date) {
        super(date);
    }

    public DERGeneralizedTime(byte[] bArr) {
        super(bArr);
    }

    private byte[] getDERTime() {
        byte[] bArr;
        byte[] byteArray;
        int length;
        int i;
        if (this.contents[this.contents.length - 1] == 90) {
            if (!hasMinutes()) {
                bArr = new byte[this.contents.length + 4];
                System.arraycopy(this.contents, 0, bArr, 0, this.contents.length - 1);
                byteArray = Strings.toByteArray("0000Z");
                length = this.contents.length - 1;
                i = 5;
            } else if (hasSeconds()) {
                if (hasFractionalSeconds()) {
                    int length2 = this.contents.length - 2;
                    while (length2 > 0 && this.contents[length2] == 48) {
                        length2--;
                    }
                    if (this.contents[length2] == 46) {
                        byte[] bArr2 = new byte[length2 + 1];
                        System.arraycopy(this.contents, 0, bArr2, 0, length2);
                        bArr2[length2] = 90;
                        return bArr2;
                    }
                    byte[] bArr3 = new byte[length2 + 2];
                    int i2 = length2 + 1;
                    System.arraycopy(this.contents, 0, bArr3, 0, i2);
                    bArr3[i2] = 90;
                    return bArr3;
                }
                return this.contents;
            } else {
                bArr = new byte[this.contents.length + 2];
                System.arraycopy(this.contents, 0, bArr, 0, this.contents.length - 1);
                byteArray = Strings.toByteArray("00Z");
                length = this.contents.length - 1;
                i = 3;
            }
            System.arraycopy(byteArray, 0, bArr, length, i);
            return bArr;
        }
        return this.contents;
    }

    @Override // org.bouncycastle.asn1.ASN1GeneralizedTime, org.bouncycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 24, getDERTime());
    }

    @Override // org.bouncycastle.asn1.ASN1GeneralizedTime, org.bouncycastle.asn1.ASN1Primitive
    int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, getDERTime().length);
    }

    @Override // org.bouncycastle.asn1.ASN1GeneralizedTime, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDERObject() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return this;
    }
}
