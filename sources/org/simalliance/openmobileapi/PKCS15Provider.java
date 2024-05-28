package org.simalliance.openmobileapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.simalliance.openmobileapi.FileViewProvider;
import org.simalliance.openmobileapi.internal.ByteArrayConverter;
import org.simalliance.openmobileapi.internal.DerTlvCoder;
import org.simalliance.openmobileapi.internal.DerTlvParser;
import org.simalliance.openmobileapi.internal.ErrorStrings;
import org.simalliance.openmobileapi.internal.OidParser;
import org.simalliance.openmobileapi.internal.TlvEntryWrapper;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PKCS15Provider extends Provider {
    public static final int FID_EF_DIR = 12032;
    public static final int FID_EF_ODF = 20529;
    public static final int FID_EF_TOKEN_INFO = 20530;
    public FileViewProvider mFileViewProvider;
    public byte[] mOdfContent;
    public byte[] mTokenInfoContent;
    public static final byte[] AID_PKCS15 = {-96, 0, 0, 0, 99, 80, 75, 67, 83, 45, 49, 53};
    public static final byte[] TLV_TAG_PKCS15_APP_TEMPLATE = {97};
    public static final byte[] TLV_TAG_PKCS15_PATH = {81};
    public static final byte[] TLV_TAG_OID = {6};
    public static final byte[] TLV_TAG_SEQUENCE = {48};
    public static final byte[] TLV_TAG_PRIVATE_KEY = {-96};
    public static final byte[] TLV_TAG_PUBLIC_KEY = {-95};
    public static final byte[] TLV_TAG_PUBLIC_KEY_TRUSTED = {-94};
    public static final byte[] TLV_TAG_SECRET_KEY = {-93};
    public static final byte[] TLV_TAG_CERTIFICATE = {-92};
    public static final byte[] TLV_TAG_CERTIFICATE_TRUSTED = {-91};
    public static final byte[] TLV_TAG_CERTIFICATE_USEFUL = {-90};
    public static final byte[] TLV_TAG_DATA_OBJECT = {-89};
    public static final byte[] TLV_TAG_AUTHENTICATE_OBJECT = {-88};

    public PKCS15Provider(Channel channel) {
        super(channel);
        this.mFileViewProvider = new FileViewProvider(channel);
        if (currentDirIsPkcs15FileStructure()) {
            this.mOdfContent = readOdf();
            this.mTokenInfoContent = readTokenInfo();
            return;
        }
        try {
            int numberOfRecords = this.mFileViewProvider.selectByPath(Integer.toHexString(12032), false).getNumberOfRecords();
            int i = 0;
            while (i < numberOfRecords) {
                i++;
                if (recordContainsValidPath(this.mFileViewProvider.readRecord(0, i).getData())) {
                    this.mOdfContent = readOdf();
                    this.mTokenInfoContent = readTokenInfo();
                    return;
                }
            }
            throw new IOException("No PKCS#15 file structure found.");
        } catch (IllegalArgumentException unused) {
            throw new IOException("No PKCS#15 file structure found.");
        }
    }

    private boolean currentDirIsPkcs15FileStructure() {
        try {
            this.mFileViewProvider.selectByFID(20529);
            this.mFileViewProvider.selectByFID(20530);
            return true;
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isAuthenticationObjectTag(byte[] bArr) {
        return Arrays.equals(bArr, TLV_TAG_AUTHENTICATE_OBJECT);
    }

    private boolean isCertificateTag(byte[] bArr) {
        return Arrays.equals(bArr, TLV_TAG_CERTIFICATE) || Arrays.equals(bArr, TLV_TAG_CERTIFICATE_TRUSTED) || Arrays.equals(bArr, TLV_TAG_CERTIFICATE_USEFUL);
    }

    private boolean isDataObjectTag(byte[] bArr) {
        return Arrays.equals(bArr, TLV_TAG_DATA_OBJECT);
    }

    private boolean isPrivateKeyTag(byte[] bArr) {
        return Arrays.equals(bArr, TLV_TAG_PRIVATE_KEY);
    }

    private boolean isPublicKeyTag(byte[] bArr) {
        return Arrays.equals(bArr, TLV_TAG_PUBLIC_KEY) || Arrays.equals(bArr, TLV_TAG_PUBLIC_KEY_TRUSTED);
    }

    private byte[] readOdf() {
        this.mFileViewProvider.selectByFID(20529);
        byte[] readBinary = this.mFileViewProvider.readBinary(0, 0, 0);
        if (readBinary != null) {
            return readBinary;
        }
        throw new IOException("Error reading EF(ODF): returned null.");
    }

    private byte[] readTokenInfo() {
        this.mFileViewProvider.selectByFID(20530);
        byte[] readBinary = this.mFileViewProvider.readBinary(0, 0, 0);
        if (readBinary != null) {
            return readBinary;
        }
        throw new IOException("Error reading EF(TokenInfo): returned null.");
    }

    private boolean recordContainsValidPath(byte[] bArr) {
        DerTlvParser derTlvParser = new DerTlvParser();
        try {
            int searchTag = derTlvParser.searchTag(bArr, TLV_TAG_PKCS15_APP_TEMPLATE, 0);
            byte[] value = new TlvEntryWrapper(bArr, searchTag, derTlvParser).getValue();
            this.mFileViewProvider.selectByPath(ByteArrayConverter.byteArrayToPathString(new TlvEntryWrapper(value, derTlvParser.searchTag(value, TLV_TAG_PKCS15_PATH, searchTag), derTlvParser).getValue()), false);
            return currentDirIsPkcs15FileStructure();
        } catch (IllegalArgumentException | Exception unused) {
            return false;
        }
    }

    public Path decodePath(byte[] bArr) {
        DerTlvParser derTlvParser = new DerTlvParser();
        TlvEntryWrapper tlvEntryWrapper = new TlvEntryWrapper(bArr, 0, derTlvParser);
        if (Arrays.equals(tlvEntryWrapper.getTag(), DerTlvCoder.TAG_SEQUENCE)) {
            byte[] value = tlvEntryWrapper.getValue();
            TlvEntryWrapper tlvEntryWrapper2 = new TlvEntryWrapper(value, 0, derTlvParser);
            if (Arrays.equals(tlvEntryWrapper2.getTag(), DerTlvCoder.TAG_OCTET_STRING)) {
                int totalLength = tlvEntryWrapper2.getTotalLength() + 0;
                if (totalLength >= value.length) {
                    return new Path(tlvEntryWrapper2.getValue());
                }
                TlvEntryWrapper tlvEntryWrapper3 = new TlvEntryWrapper(value, totalLength, derTlvParser);
                if (Arrays.equals(tlvEntryWrapper3.getTag(), DerTlvCoder.TAG_INTEGER)) {
                    int byteArrayToInt = ByteArrayConverter.byteArrayToInt(tlvEntryWrapper3.getValue());
                    TlvEntryWrapper tlvEntryWrapper4 = new TlvEntryWrapper(value, totalLength + tlvEntryWrapper3.getTotalLength(), derTlvParser);
                    if (Arrays.equals(tlvEntryWrapper4.getTag(), DerTlvCoder.TAG_INTEGER)) {
                        return new Path(tlvEntryWrapper2.getValue(), byteArrayToInt, ByteArrayConverter.byteArrayToInt(tlvEntryWrapper4.getValue()));
                    }
                    throw new IllegalArgumentException("Unexpected tag.");
                }
                throw new IllegalArgumentException("Unexpected tag.");
            }
            throw new IllegalArgumentException("Unexpected tag.");
        }
        throw new IllegalArgumentException("Unexpected tag.");
    }

    public Path[] getAuthObjPaths() {
        ArrayList arrayList = new ArrayList();
        DerTlvParser derTlvParser = new DerTlvParser();
        byte[] validTlvData = derTlvParser.getValidTlvData(getODF());
        int i = 0;
        while (i < validTlvData.length) {
            TlvEntryWrapper tlvEntryWrapper = new TlvEntryWrapper(validTlvData, i, derTlvParser);
            if (isAuthenticationObjectTag(tlvEntryWrapper.getTag())) {
                try {
                    arrayList.add(decodePath(tlvEntryWrapper.getValue()));
                } catch (IllegalArgumentException unused) {
                }
            }
            i += tlvEntryWrapper.getTotalLength();
        }
        if (arrayList.size() > 0) {
            return (Path[]) arrayList.toArray(new Path[arrayList.size()]);
        }
        return null;
    }

    public Path[] getCertificatePaths() {
        ArrayList arrayList = new ArrayList();
        DerTlvParser derTlvParser = new DerTlvParser();
        byte[] validTlvData = derTlvParser.getValidTlvData(getODF());
        int i = 0;
        while (i < validTlvData.length) {
            TlvEntryWrapper tlvEntryWrapper = new TlvEntryWrapper(validTlvData, i, derTlvParser);
            if (isCertificateTag(tlvEntryWrapper.getTag())) {
                try {
                    arrayList.add(decodePath(tlvEntryWrapper.getValue()));
                } catch (IllegalArgumentException unused) {
                }
            }
            i += tlvEntryWrapper.getTotalLength();
        }
        if (arrayList.size() > 0) {
            return (Path[]) arrayList.toArray(new Path[arrayList.size()]);
        }
        return null;
    }

    public Path[] getDataObjPaths() {
        ArrayList arrayList = new ArrayList();
        DerTlvParser derTlvParser = new DerTlvParser();
        byte[] validTlvData = derTlvParser.getValidTlvData(getODF());
        int i = 0;
        while (i < validTlvData.length) {
            TlvEntryWrapper tlvEntryWrapper = new TlvEntryWrapper(validTlvData, i, derTlvParser);
            if (isDataObjectTag(tlvEntryWrapper.getTag())) {
                try {
                    arrayList.add(decodePath(tlvEntryWrapper.getValue()));
                } catch (IllegalArgumentException unused) {
                }
            }
            i += tlvEntryWrapper.getTotalLength();
        }
        if (arrayList.size() > 0) {
            return (Path[]) arrayList.toArray(new Path[arrayList.size()]);
        }
        return null;
    }

    public byte[] getODF() {
        return this.mOdfContent;
    }

    public Path[] getPrivateKeyPaths() {
        ArrayList arrayList = new ArrayList();
        DerTlvParser derTlvParser = new DerTlvParser();
        byte[] validTlvData = derTlvParser.getValidTlvData(getODF());
        int i = 0;
        while (i < validTlvData.length) {
            TlvEntryWrapper tlvEntryWrapper = new TlvEntryWrapper(validTlvData, i, derTlvParser);
            if (isPrivateKeyTag(tlvEntryWrapper.getTag())) {
                try {
                    arrayList.add(decodePath(tlvEntryWrapper.getValue()));
                } catch (IllegalArgumentException unused) {
                }
            }
            i += tlvEntryWrapper.getTotalLength();
        }
        if (arrayList.size() > 0) {
            return (Path[]) arrayList.toArray(new Path[arrayList.size()]);
        }
        return null;
    }

    public Path[] getPublicKeyPaths() {
        ArrayList arrayList = new ArrayList();
        DerTlvParser derTlvParser = new DerTlvParser();
        byte[] validTlvData = derTlvParser.getValidTlvData(getODF());
        int i = 0;
        while (i < validTlvData.length) {
            TlvEntryWrapper tlvEntryWrapper = new TlvEntryWrapper(validTlvData, i, derTlvParser);
            if (isPublicKeyTag(tlvEntryWrapper.getTag())) {
                try {
                    arrayList.add(decodePath(tlvEntryWrapper.getValue()));
                } catch (IllegalArgumentException unused) {
                }
            }
            i += tlvEntryWrapper.getTotalLength();
        }
        if (arrayList.size() > 0) {
            return (Path[]) arrayList.toArray(new Path[arrayList.size()]);
        }
        return null;
    }

    public byte[] getTokenInfo() {
        return this.mTokenInfoContent;
    }

    public byte[] readFile(Path path) {
        int i;
        int i2;
        FileViewProvider.FCP selectByPath = this.mFileViewProvider.selectByPath(ByteArrayConverter.byteArrayToPathString(path.getPath()), true);
        if (selectByPath.getFileType() == 1) {
            if (selectByPath.getFileStructure() == 1) {
                if (path.hasIndexLength()) {
                    i2 = path.getIndex();
                    i = path.getLength();
                } else {
                    i = 0;
                    i2 = 0;
                }
                return this.mFileViewProvider.readBinary(0, i2, i);
            } else if (path.hasIndexLength()) {
                return this.mFileViewProvider.readRecord(0, path.getIndex()).getData();
            } else {
                throw new IllegalArgumentException("Could not read file: index not specified in a record-based file.");
            }
        }
        throw new IllegalArgumentException("Could not read file: not an EF.");
    }

    public byte[] searchOID(byte[] bArr, String str) {
        TlvEntryWrapper tlvEntryWrapper;
        byte[] encodeOid = OidParser.encodeOid(str);
        DerTlvParser derTlvParser = new DerTlvParser();
        try {
            tlvEntryWrapper = new TlvEntryWrapper(bArr, 0, derTlvParser);
        } catch (Exception unused) {
        }
        if (Arrays.equals(tlvEntryWrapper.getTag(), TLV_TAG_DATA_OBJECT)) {
            byte[] value = tlvEntryWrapper.getValue();
            int i = 0;
            while (i < value.length) {
                TlvEntryWrapper tlvEntryWrapper2 = new TlvEntryWrapper(value, i, derTlvParser);
                if (Arrays.equals(tlvEntryWrapper2.getTag(), TLV_TAG_SEQUENCE)) {
                    byte[] value2 = tlvEntryWrapper2.getValue();
                    try {
                        TlvEntryWrapper tlvEntryWrapper3 = new TlvEntryWrapper(value2, 0, derTlvParser);
                        if (Arrays.equals(tlvEntryWrapper3.getTag(), TLV_TAG_OID) && Arrays.equals(encodeOid, tlvEntryWrapper3.getValue())) {
                            try {
                                return new TlvEntryWrapper(value2, tlvEntryWrapper3.getTotalLength() + 0, derTlvParser).getValue();
                            } catch (Exception unused2) {
                                i += tlvEntryWrapper2.getTotalLength();
                            }
                        }
                    } catch (Exception unused3) {
                        i += tlvEntryWrapper2.getTotalLength();
                    }
                }
                i += tlvEntryWrapper2.getTotalLength();
            }
            return null;
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class Path {
        public static final int VALUE_UNDEFINED = -1;
        public Integer mIndex;
        public Integer mLength;
        public byte[] mPath;

        public Path(byte[] bArr) {
            if (bArr != null) {
                byte[] bArr2 = new byte[bArr.length];
                this.mPath = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                this.mIndex = null;
                this.mLength = null;
                return;
            }
            throw new IllegalArgumentException(ErrorStrings.paramNull("path"));
        }

        public byte[] encode() {
            byte[] bArr;
            byte[] bArr2;
            byte[] encodeOctetString = DerTlvCoder.encodeOctetString(this.mPath);
            if (hasIndexLength()) {
                bArr = DerTlvCoder.encodeInteger(this.mIndex.intValue());
                bArr2 = DerTlvCoder.encodeInteger(this.mLength.intValue());
            } else {
                bArr = new byte[0];
                bArr2 = new byte[0];
            }
            byte[] bArr3 = new byte[encodeOctetString.length + bArr.length + bArr2.length];
            System.arraycopy(encodeOctetString, 0, bArr3, 0, encodeOctetString.length);
            System.arraycopy(bArr, 0, bArr3, encodeOctetString.length, bArr.length);
            System.arraycopy(bArr2, 0, bArr3, encodeOctetString.length + bArr.length, bArr2.length);
            return DerTlvCoder.encodeSequence(bArr3);
        }

        public int getIndex() {
            if (hasIndexLength()) {
                return this.mIndex.intValue();
            }
            return -1;
        }

        public int getLength() {
            if (hasIndexLength()) {
                return this.mLength.intValue();
            }
            return -1;
        }

        public byte[] getPath() {
            return this.mPath;
        }

        public boolean hasIndexLength() {
            return (this.mIndex == null || this.mLength == null) ? false : true;
        }

        public Path(byte[] bArr, int i, int i2) {
            if (bArr == null) {
                throw new IllegalArgumentException(ErrorStrings.paramNull("path"));
            }
            if (i < 1) {
                throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("index"));
            }
            if (i2 >= 1) {
                byte[] bArr2 = new byte[bArr.length];
                this.mPath = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                this.mIndex = Integer.valueOf(i);
                this.mLength = Integer.valueOf(i2);
                return;
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("length"));
        }
    }
}
