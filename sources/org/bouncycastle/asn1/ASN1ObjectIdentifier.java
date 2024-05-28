package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.bouncycastle.util.Arrays;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ASN1ObjectIdentifier extends ASN1Primitive {
    private static final long LONG_LIMIT = 72057594037927808L;
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1ObjectIdentifier.class, 6) { // from class: org.bouncycastle.asn1.ASN1ObjectIdentifier.1
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1ObjectIdentifier.createPrimitive(dEROctetString.getOctets(), false);
        }
    };
    private static final ConcurrentMap<OidHandle, ASN1ObjectIdentifier> pool = new ConcurrentHashMap();
    private byte[] contents;
    private final String identifier;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class OidHandle {
        private final byte[] contents;
        private final int key;

        OidHandle(byte[] bArr) {
            this.key = Arrays.hashCode(bArr);
            this.contents = bArr;
        }

        public boolean equals(Object obj) {
            if (obj instanceof OidHandle) {
                return Arrays.areEqual(this.contents, ((OidHandle) obj).contents);
            }
            return false;
        }

        public int hashCode() {
            return this.key;
        }
    }

    public ASN1ObjectIdentifier(String str) {
        if (str == null) {
            throw new NullPointerException("'identifier' cannot be null");
        }
        if (isValidIdentifier(str)) {
            this.identifier = str;
            return;
        }
        throw new IllegalArgumentException("string " + str + " not an OID");
    }

    ASN1ObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (!ASN1RelativeOID.isValidIdentifier(str, 0)) {
            throw new IllegalArgumentException("string " + str + " not a valid OID branch");
        }
        this.identifier = aSN1ObjectIdentifier.getId() + "." + str;
    }

    ASN1ObjectIdentifier(byte[] bArr, boolean z) {
        byte[] bArr2 = bArr;
        StringBuffer stringBuffer = new StringBuffer();
        boolean z2 = true;
        long j = 0;
        BigInteger bigInteger = null;
        for (int i = 0; i != bArr2.length; i++) {
            int i2 = bArr2[i] & 255;
            if (j <= C0164j1.f203x3) {
                long j2 = j + (i2 & 127);
                if ((i2 & 128) == 0) {
                    if (z2) {
                        if (j2 < 40) {
                            stringBuffer.append('0');
                        } else if (j2 < 80) {
                            stringBuffer.append('1');
                            j2 -= 40;
                        } else {
                            stringBuffer.append('2');
                            j2 -= 80;
                        }
                        z2 = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(j2);
                    j = 0;
                } else {
                    j = j2 << 7;
                }
            } else {
                BigInteger or = (bigInteger == null ? BigInteger.valueOf(j) : bigInteger).or(BigInteger.valueOf(i2 & 127));
                if ((i2 & 128) == 0) {
                    if (z2) {
                        stringBuffer.append('2');
                        or = or.subtract(BigInteger.valueOf(80L));
                        z2 = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(or);
                    j = 0;
                    bigInteger = null;
                } else {
                    bigInteger = or.shiftLeft(7);
                }
            }
        }
        this.identifier = stringBuffer.toString();
        this.contents = z ? Arrays.clone(bArr) : bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier createPrimitive(byte[] bArr, boolean z) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = pool.get(new OidHandle(bArr));
        return aSN1ObjectIdentifier == null ? new ASN1ObjectIdentifier(bArr, z) : aSN1ObjectIdentifier;
    }

    private void doOutput(ByteArrayOutputStream byteArrayOutputStream) {
        OIDTokenizer oIDTokenizer = new OIDTokenizer(this.identifier);
        int parseInt = Integer.parseInt(oIDTokenizer.nextToken()) * 40;
        String nextToken = oIDTokenizer.nextToken();
        if (nextToken.length() <= 18) {
            ASN1RelativeOID.writeField(byteArrayOutputStream, parseInt + Long.parseLong(nextToken));
        } else {
            ASN1RelativeOID.writeField(byteArrayOutputStream, new BigInteger(nextToken).add(BigInteger.valueOf(parseInt)));
        }
        while (oIDTokenizer.hasMoreTokens()) {
            String nextToken2 = oIDTokenizer.nextToken();
            if (nextToken2.length() <= 18) {
                ASN1RelativeOID.writeField(byteArrayOutputStream, Long.parseLong(nextToken2));
            } else {
                ASN1RelativeOID.writeField(byteArrayOutputStream, new BigInteger(nextToken2));
            }
        }
    }

    public static ASN1ObjectIdentifier fromContents(byte[] bArr) {
        return createPrimitive(bArr, true);
    }

    private synchronized byte[] getContents() {
        if (this.contents == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            doOutput(byteArrayOutputStream);
            this.contents = byteArrayOutputStream.toByteArray();
        }
        return this.contents;
    }

    public static ASN1ObjectIdentifier getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1ObjectIdentifier)) {
            return (ASN1ObjectIdentifier) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                return (ASN1ObjectIdentifier) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (ASN1ObjectIdentifier) TYPE.fromByteArray((byte[]) obj);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct object identifier from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1ObjectIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (!z && !aSN1TaggedObject.isParsed()) {
            ASN1Primitive object = aSN1TaggedObject.getObject();
            if (!(object instanceof ASN1ObjectIdentifier)) {
                return fromContents(ASN1OctetString.getInstance(object).getOctets());
            }
        }
        return (ASN1ObjectIdentifier) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    private static boolean isValidIdentifier(String str) {
        char charAt;
        if (str.length() < 3 || str.charAt(1) != '.' || (charAt = str.charAt(0)) < '0' || charAt > '2') {
            return false;
        }
        return ASN1RelativeOID.isValidIdentifier(str, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive == this) {
            return true;
        }
        if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
            return this.identifier.equals(((ASN1ObjectIdentifier) aSN1Primitive).identifier);
        }
        return false;
    }

    public ASN1ObjectIdentifier branch(String str) {
        return new ASN1ObjectIdentifier(this, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 6, getContents());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean encodeConstructed() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, getContents().length);
    }

    public String getId() {
        return this.identifier;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return this.identifier.hashCode();
    }

    public ASN1ObjectIdentifier intern() {
        OidHandle oidHandle = new OidHandle(getContents());
        ASN1ObjectIdentifier aSN1ObjectIdentifier = pool.get(oidHandle);
        if (aSN1ObjectIdentifier == null) {
            ASN1ObjectIdentifier putIfAbsent = pool.putIfAbsent(oidHandle, this);
            return putIfAbsent == null ? this : putIfAbsent;
        }
        return aSN1ObjectIdentifier;
    }

    /* renamed from: on */
    public boolean m366on(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String id = getId();
        String id2 = aSN1ObjectIdentifier.getId();
        return id.length() > id2.length() && id.charAt(id2.length()) == '.' && id.startsWith(id2);
    }

    public String toString() {
        return getId();
    }
}
