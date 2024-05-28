package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ASN1UniversalString extends ASN1Primitive implements ASN1String {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1UniversalString.class, 28) { // from class: org.bouncycastle.asn1.ASN1UniversalString.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1UniversalString.createPrimitive(dEROctetString.getOctets());
        }
    };
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    final byte[] contents;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1UniversalString(byte[] bArr, boolean z) {
        this.contents = z ? Arrays.clone(bArr) : bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1UniversalString createPrimitive(byte[] bArr) {
        return new DERUniversalString(bArr, false);
    }

    private static void encodeHexByte(StringBuffer stringBuffer, int i) {
        stringBuffer.append(table[(i >>> 4) & 15]);
        stringBuffer.append(table[i & 15]);
    }

    private static void encodeHexDL(StringBuffer stringBuffer, int i) {
        if (i < 128) {
            encodeHexByte(stringBuffer, i);
            return;
        }
        int i2 = 5;
        byte[] bArr = new byte[5];
        do {
            i2--;
            bArr[i2] = (byte) i;
            i >>>= 8;
        } while (i != 0);
        int length = bArr.length - i2;
        int i3 = i2 - 1;
        bArr[i3] = (byte) (length | 128);
        while (true) {
            int i4 = i3 + 1;
            encodeHexByte(stringBuffer, bArr[i3]);
            if (i4 >= bArr.length) {
                return;
            }
            i3 = i4;
        }
    }

    public static ASN1UniversalString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1UniversalString)) {
            return (ASN1UniversalString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1UniversalString) {
                return (ASN1UniversalString) aSN1Primitive;
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1UniversalString) TYPE.fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error getInstance: " + e.toString());
        }
    }

    public static ASN1UniversalString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1UniversalString) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1UniversalString) {
            return Arrays.areEqual(this.contents, ((ASN1UniversalString) aSN1Primitive).contents);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 28, this.contents);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean encodeConstructed() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, this.contents.length);
    }

    public final byte[] getOctets() {
        return Arrays.clone(this.contents);
    }

    @Override // org.bouncycastle.asn1.ASN1String
    public final String getString() {
        int length = this.contents.length;
        StringBuffer stringBuffer = new StringBuffer(((ASN1OutputStream.getLengthOfDL(length) + length) * 2) + 3);
        stringBuffer.append("#1C");
        encodeHexDL(stringBuffer, length);
        for (int i = 0; i < length; i++) {
            encodeHexByte(stringBuffer, this.contents[i]);
        }
        return stringBuffer.toString();
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public final int hashCode() {
        return Arrays.hashCode(this.contents);
    }

    public String toString() {
        return getString();
    }
}
