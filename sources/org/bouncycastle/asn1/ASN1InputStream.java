package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.p466io.Streams;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ASN1InputStream extends FilterInputStream implements BERTags {
    private final boolean lazyEvaluate;
    private final int limit;
    private final byte[][] tmpBuffers;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public ASN1InputStream(InputStream inputStream, int i) {
        this(inputStream, i, false);
    }

    public ASN1InputStream(InputStream inputStream, int i, boolean z) {
        this(inputStream, i, z, new byte[11]);
    }

    private ASN1InputStream(InputStream inputStream, int i, boolean z, byte[][] bArr) {
        super(inputStream);
        this.limit = i;
        this.lazyEvaluate = z;
        this.tmpBuffers = bArr;
    }

    public ASN1InputStream(InputStream inputStream, boolean z) {
        this(inputStream, StreamUtil.findLimit(inputStream), z);
    }

    public ASN1InputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Primitive createPrimitiveDERObject(int i, DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        switch (i) {
            case 1:
                return ASN1Boolean.createPrimitive(getBuffer(definiteLengthInputStream, bArr));
            case 2:
                return ASN1Integer.createPrimitive(definiteLengthInputStream.toByteArray());
            case 3:
                return ASN1BitString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 4:
                return ASN1OctetString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 5:
                return ASN1Null.createPrimitive(definiteLengthInputStream.toByteArray());
            case 6:
                return ASN1ObjectIdentifier.createPrimitive(getBuffer(definiteLengthInputStream, bArr), true);
            case 7:
                return ASN1ObjectDescriptor.createPrimitive(definiteLengthInputStream.toByteArray());
            case 8:
            case 9:
            case 11:
            case 14:
            case 15:
            case 16:
            case 17:
            case 29:
            default:
                throw new IOException("unknown tag " + i + " encountered");
            case 10:
                return ASN1Enumerated.createPrimitive(getBuffer(definiteLengthInputStream, bArr), true);
            case 12:
                return ASN1UTF8String.createPrimitive(definiteLengthInputStream.toByteArray());
            case 13:
                return ASN1RelativeOID.createPrimitive(definiteLengthInputStream.toByteArray(), false);
            case 18:
                return ASN1NumericString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 19:
                return ASN1PrintableString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 20:
                return ASN1T61String.createPrimitive(definiteLengthInputStream.toByteArray());
            case 21:
                return ASN1VideotexString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 22:
                return ASN1IA5String.createPrimitive(definiteLengthInputStream.toByteArray());
            case 23:
                return ASN1UTCTime.createPrimitive(definiteLengthInputStream.toByteArray());
            case 24:
                return ASN1GeneralizedTime.createPrimitive(definiteLengthInputStream.toByteArray());
            case 25:
                return ASN1GraphicString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 26:
                return ASN1VisibleString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 27:
                return ASN1GeneralString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 28:
                return ASN1UniversalString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 30:
                return ASN1BMPString.createPrimitive(getBMPCharBuffer(definiteLengthInputStream));
        }
    }

    private static char[] getBMPCharBuffer(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        int i;
        int remaining = definiteLengthInputStream.getRemaining();
        if ((remaining & 1) == 0) {
            char[] cArr = new char[remaining / 2];
            byte[] bArr = new byte[8];
            int i2 = 0;
            int i3 = 0;
            while (remaining >= 8) {
                if (Streams.readFully(definiteLengthInputStream, bArr, 0, 8) != 8) {
                    throw new EOFException("EOF encountered in middle of BMPString");
                }
                cArr[i3] = (char) ((bArr[0] << 8) | (bArr[1] & 255));
                cArr[i3 + 1] = (char) ((bArr[2] << 8) | (bArr[3] & 255));
                cArr[i3 + 2] = (char) ((bArr[4] << 8) | (bArr[5] & 255));
                cArr[i3 + 3] = (char) ((bArr[6] << 8) | (bArr[7] & 255));
                i3 += 4;
                remaining -= 8;
            }
            if (remaining > 0) {
                if (Streams.readFully(definiteLengthInputStream, bArr, 0, remaining) != remaining) {
                    throw new EOFException("EOF encountered in middle of BMPString");
                }
                while (true) {
                    int i4 = i2 + 1;
                    int i5 = i4 + 1;
                    i = i3 + 1;
                    cArr[i3] = (char) ((bArr[i2] << 8) | (bArr[i4] & 255));
                    if (i5 >= remaining) {
                        break;
                    }
                    i2 = i5;
                    i3 = i;
                }
                i3 = i;
            }
            if (definiteLengthInputStream.getRemaining() == 0 && cArr.length == i3) {
                return cArr;
            }
            throw new IllegalStateException();
        }
        throw new IOException("malformed BMPString encoding encountered");
    }

    private static byte[] getBuffer(DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        int remaining = definiteLengthInputStream.getRemaining();
        if (remaining >= bArr.length) {
            return definiteLengthInputStream.toByteArray();
        }
        byte[] bArr2 = bArr[remaining];
        if (bArr2 == null) {
            bArr2 = new byte[remaining];
            bArr[remaining] = bArr2;
        }
        definiteLengthInputStream.readAllIntoByteArray(bArr2);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readLength(InputStream inputStream, int i, boolean z) throws IOException {
        int read = inputStream.read();
        if ((read >>> 7) == 0) {
            return read;
        }
        if (128 == read) {
            return -1;
        }
        if (read >= 0) {
            if (255 != read) {
                int i2 = read & 127;
                int i3 = 0;
                int i4 = 0;
                do {
                    int read2 = inputStream.read();
                    if (read2 < 0) {
                        throw new EOFException("EOF found reading length");
                    }
                    if ((i3 >>> 23) != 0) {
                        throw new IOException("long form definite-length more than 31 bits");
                    }
                    i3 = (i3 << 8) + read2;
                    i4++;
                } while (i4 < i2);
                if (i3 < i || z) {
                    return i3;
                }
                throw new IOException("corrupted stream - out of bounds length found: " + i3 + " >= " + i);
            }
            throw new IOException("invalid long form definite-length 0xFF");
        }
        throw new EOFException("EOF found when length expected");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readTagNumber(InputStream inputStream, int i) throws IOException {
        int i2 = i & 31;
        if (i2 == 31) {
            int read = inputStream.read();
            if (read < 31) {
                if (read < 0) {
                    throw new EOFException("EOF found inside tag value.");
                }
                throw new IOException("corrupted stream - high tag number < 31 found");
            }
            int i3 = read & 127;
            if (i3 != 0) {
                while ((read & 128) != 0) {
                    if ((i3 >>> 24) != 0) {
                        throw new IOException("Tag number more than 31 bits");
                    }
                    int i4 = i3 << 7;
                    int read2 = inputStream.read();
                    if (read2 < 0) {
                        throw new EOFException("EOF found inside tag value.");
                    }
                    i3 = i4 | (read2 & 127);
                    read = read2;
                }
                return i3;
            }
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        return i2;
    }

    ASN1BitString buildConstructedBitString(ASN1EncodableVector aSN1EncodableVector) throws IOException {
        ASN1BitString[] aSN1BitStringArr = new ASN1BitString[aSN1EncodableVector.size()];
        for (int i = 0; i != aSN1BitStringArr.length; i++) {
            ASN1Encodable aSN1Encodable = aSN1EncodableVector.get(i);
            if (!(aSN1Encodable instanceof ASN1BitString)) {
                throw new ASN1Exception("unknown object encountered in constructed BIT STRING: " + aSN1Encodable.getClass());
            }
            aSN1BitStringArr[i] = (ASN1BitString) aSN1Encodable;
        }
        return new BERBitString(aSN1BitStringArr);
    }

    ASN1OctetString buildConstructedOctetString(ASN1EncodableVector aSN1EncodableVector) throws IOException {
        ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[aSN1EncodableVector.size()];
        for (int i = 0; i != aSN1OctetStringArr.length; i++) {
            ASN1Encodable aSN1Encodable = aSN1EncodableVector.get(i);
            if (!(aSN1Encodable instanceof ASN1OctetString)) {
                throw new ASN1Exception("unknown object encountered in constructed OCTET STRING: " + aSN1Encodable.getClass());
            }
            aSN1OctetStringArr[i] = (ASN1OctetString) aSN1Encodable;
        }
        return new BEROctetString(aSN1OctetStringArr);
    }

    protected ASN1Primitive buildObject(int i, int i2, int i3) throws IOException {
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, i3, this.limit);
        if ((i & 224) == 0) {
            return createPrimitiveDERObject(i2, definiteLengthInputStream, this.tmpBuffers);
        }
        int i4 = i & 192;
        if (i4 != 0) {
            return readTaggedObjectDL(i4, i2, (i & 32) != 0, definiteLengthInputStream);
        }
        switch (i2) {
            case 3:
                return buildConstructedBitString(readVector(definiteLengthInputStream));
            case 4:
                return buildConstructedOctetString(readVector(definiteLengthInputStream));
            case 8:
                return DLFactory.createSequence(readVector(definiteLengthInputStream)).toASN1External();
            case 16:
                return definiteLengthInputStream.getRemaining() < 1 ? DLFactory.EMPTY_SEQUENCE : this.lazyEvaluate ? new LazyEncodedSequence(definiteLengthInputStream.toByteArray()) : DLFactory.createSequence(readVector(definiteLengthInputStream));
            case 17:
                return DLFactory.createSet(readVector(definiteLengthInputStream));
            default:
                throw new IOException("unknown tag " + i2 + " encountered");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLimit() {
        return this.limit;
    }

    protected void readFully(byte[] bArr) throws IOException {
        if (Streams.readFully(this, bArr, 0, bArr.length) != bArr.length) {
            throw new EOFException("EOF encountered in middle of object");
        }
    }

    protected int readLength() throws IOException {
        return readLength(this, this.limit, false);
    }

    public ASN1Primitive readObject() throws IOException {
        int read = read();
        if (read <= 0) {
            if (read != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int readTagNumber = readTagNumber(this, read);
        int readLength = readLength();
        if (readLength >= 0) {
            try {
                return buildObject(read, readTagNumber, readLength);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception("corrupted stream detected", e);
            }
        } else if ((read & 32) != 0) {
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this, this.limit), this.limit, this.tmpBuffers);
            int i = read & 192;
            if (i != 0) {
                return aSN1StreamParser.loadTaggedIL(i, readTagNumber);
            }
            switch (readTagNumber) {
                case 3:
                    return BERBitStringParser.parse(aSN1StreamParser);
                case 4:
                    return BEROctetStringParser.parse(aSN1StreamParser);
                case 8:
                    return DERExternalParser.parse(aSN1StreamParser);
                case 16:
                    return BERSequenceParser.parse(aSN1StreamParser);
                case 17:
                    return BERSetParser.parse(aSN1StreamParser);
                default:
                    throw new IOException("unknown BER object encountered");
            }
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }

    ASN1Primitive readTaggedObjectDL(int i, int i2, boolean z, DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        return !z ? ASN1TaggedObject.createPrimitive(i, i2, definiteLengthInputStream.toByteArray()) : ASN1TaggedObject.createConstructedDL(i, i2, readVector(definiteLengthInputStream));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1EncodableVector readVector() throws IOException {
        ASN1Primitive readObject = readObject();
        if (readObject == null) {
            return new ASN1EncodableVector(0);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        do {
            aSN1EncodableVector.add(readObject);
            readObject = readObject();
        } while (readObject != null);
        return aSN1EncodableVector;
    }

    ASN1EncodableVector readVector(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        int remaining = definiteLengthInputStream.getRemaining();
        return remaining < 1 ? new ASN1EncodableVector(0) : new ASN1InputStream(definiteLengthInputStream, remaining, this.lazyEvaluate, this.tmpBuffers).readVector();
    }
}
