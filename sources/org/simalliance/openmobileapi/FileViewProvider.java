package org.simalliance.openmobileapi;

import java.io.IOException;
import org.simalliance.openmobileapi.internal.BerTlvParser;
import org.simalliance.openmobileapi.internal.ByteArrayConverter;
import org.simalliance.openmobileapi.internal.ErrorStrings;
import org.simalliance.openmobileapi.internal.TlvEntryWrapper;
import org.simalliance.openmobileapi.util.CommandApdu;
import org.simalliance.openmobileapi.util.ResponseApdu;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FileViewProvider extends Provider {
    public static final int APPEND_RECORD = -1;
    public static final int CURRENT_FILE = 0;
    public static final int FID_LENGTH = 2;
    public static final int FID_MAX_VALUE = 65534;
    public static final int FID_MIN_VALUE = 1;
    public static final int FILE_ID_LENGTH = 4;
    public static final int INFO_NOT_AVAILABLE = -1;
    public static final int OFFSET_LONG_MAX_VALUE = 32767;
    public static final int OFFSET_SHORT_MAX_VALUE = 255;
    public static final int REC_NUMBER_MAX_VALUE = 254;
    public static final int REC_NUMBER_MIN_VALUE = 0;
    public static final int SFI_MAX_VALUE = 30;
    public static final int SFI_MIN_VALUE = 1;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class FCP {
        public static final byte FCPTAG_FCP_TEMPLATE = 98;
        public static final byte FCPTAG_FILE_DESCRIPTOR = -126;
        public static final byte FCPTAG_FILE_ID = -125;
        public static final byte FCPTAG_FILE_SIZE = Byte.MIN_VALUE;
        public static final byte FCPTAG_LCS = -118;
        public static final byte FCPTAG_SFI = -120;
        public static final byte FCPTAG_TOTAL_FILE_SIZE = -127;
        public static final int FILESTRUCTURE_CYCLIC = 4;
        public static final int FILESTRUCTURE_LINEAR_FIXED = 2;
        public static final int FILESTRUCTURE_LINEAR_VARIABLE = 3;
        public static final int FILESTRUCTURE_NO_EF = 0;
        public static final int FILESTRUCTURE_TRANSPARENT = 1;
        public static final byte FILETYPE_DF = 0;
        public static final byte FILETYPE_EF = 1;
        public static final int LCS_CREATION_STATE = 1;
        public static final int LCS_INITIALISATION_STATE = 2;
        public static final int LCS_NO_INFORMATION_GIVEN = 0;
        public static final int LCS_OPERATIONAL_STATE_ACTIVATED = 3;
        public static final int LCS_OPERATIONAL_STATE_DEACTIVATED = 4;
        public static final int LCS_TERMINATION_STATE = 5;
        public byte[] mRawFcpData;

        public FCP(byte[] bArr) {
            BerTlvParser berTlvParser = new BerTlvParser();
            this.mRawFcpData = new TlvEntryWrapper(bArr, berTlvParser.searchTag(bArr, new byte[]{98}, 0), berTlvParser).getValue();
        }

        public byte[] getFCP() {
            return this.mRawFcpData;
        }

        public int getFID() {
            BerTlvParser berTlvParser = new BerTlvParser();
            try {
                return ByteArrayConverter.byteArrayToInt(new TlvEntryWrapper(this.mRawFcpData, berTlvParser.searchTag(this.mRawFcpData, new byte[]{-125}, 0), berTlvParser).getValue());
            } catch (IllegalArgumentException unused) {
                return -1;
            }
        }

        public int getFileSize() {
            BerTlvParser berTlvParser = new BerTlvParser();
            try {
                return ByteArrayConverter.byteArrayToInt(new TlvEntryWrapper(this.mRawFcpData, berTlvParser.searchTag(this.mRawFcpData, new byte[]{Byte.MIN_VALUE}, 0), berTlvParser).getValue());
            } catch (IllegalArgumentException unused) {
                return -1;
            }
        }

        public int getFileStructure() {
            BerTlvParser berTlvParser = new BerTlvParser();
            try {
                switch (new TlvEntryWrapper(this.mRawFcpData, berTlvParser.searchTag(this.mRawFcpData, new byte[]{-126}, 0), berTlvParser).getValue()[0] & 7) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 3:
                        return 2;
                    case 4:
                    case 5:
                        return 3;
                    case 6:
                    case 7:
                        return 4;
                    default:
                        return -1;
                }
            } catch (IllegalArgumentException unused) {
                return -1;
            }
        }

        public int getFileType() {
            BerTlvParser berTlvParser = new BerTlvParser();
            try {
                return (new TlvEntryWrapper(this.mRawFcpData, berTlvParser.searchTag(this.mRawFcpData, new byte[]{-126}, 0), berTlvParser).getValue()[0] & 191) == 56 ? 0 : 1;
            } catch (IllegalArgumentException unused) {
                return -1;
            }
        }

        public int getLCS() {
            BerTlvParser berTlvParser = new BerTlvParser();
            try {
                switch (new TlvEntryWrapper(this.mRawFcpData, berTlvParser.searchTag(this.mRawFcpData, new byte[]{-118}, 0), berTlvParser).getValue()[0]) {
                    case 0:
                        return 0;
                    case 1:
                        return 1;
                    case 2:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    default:
                        return -1;
                    case 3:
                        return 2;
                    case 4:
                    case 6:
                        return 4;
                    case 5:
                    case 7:
                        return 3;
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        return 5;
                }
            } catch (IllegalArgumentException unused) {
                return -1;
            }
        }

        public int getMaxRecordSize() {
            byte[] bArr;
            BerTlvParser berTlvParser = new BerTlvParser();
            try {
                byte[] value = new TlvEntryWrapper(this.mRawFcpData, berTlvParser.searchTag(this.mRawFcpData, new byte[]{-126}, 0), berTlvParser).getValue();
                if (value.length < 3) {
                    return -1;
                }
                if (value.length == 3) {
                    bArr = new byte[1];
                } else {
                    bArr = new byte[2];
                }
                System.arraycopy(value, 2, bArr, 0, bArr.length);
                return ByteArrayConverter.byteArrayToInt(bArr);
            } catch (IllegalArgumentException unused) {
                return -1;
            }
        }

        public int getNumberOfRecords() {
            byte[] bArr;
            BerTlvParser berTlvParser = new BerTlvParser();
            try {
                byte[] value = new TlvEntryWrapper(this.mRawFcpData, berTlvParser.searchTag(this.mRawFcpData, new byte[]{-126}, 0), berTlvParser).getValue();
                if (value.length < 5) {
                    return -1;
                }
                if (value.length == 5) {
                    bArr = new byte[1];
                } else {
                    bArr = new byte[2];
                }
                System.arraycopy(value, 4, bArr, 0, bArr.length);
                return ByteArrayConverter.byteArrayToInt(bArr);
            } catch (IllegalArgumentException unused) {
                return -1;
            }
        }

        public int getSFI() {
            BerTlvParser berTlvParser = new BerTlvParser();
            try {
                byte[] value = new TlvEntryWrapper(this.mRawFcpData, berTlvParser.searchTag(this.mRawFcpData, new byte[]{-120}, 0), berTlvParser).getValue();
                if (value.length != 0 && value.length == 1 && (value[0] & 7) == 0) {
                    return value[0] >>> 3;
                }
            } catch (IllegalArgumentException unused) {
            }
            return -1;
        }

        public int getTotalFileSize() {
            BerTlvParser berTlvParser = new BerTlvParser();
            try {
                return ByteArrayConverter.byteArrayToInt(new TlvEntryWrapper(this.mRawFcpData, berTlvParser.searchTag(this.mRawFcpData, new byte[]{-127}, 0), berTlvParser).getValue());
            } catch (IllegalArgumentException unused) {
                return -1;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class Record {
        public byte[] mData;
        public int mNumber;

        public Record(int i, byte[] bArr) {
            if ((i < 0 || i > 254) && i != -1) {
                throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("number"));
            }
            if (bArr != null) {
                if (bArr.length <= 65535) {
                    this.mNumber = i;
                    this.mData = bArr;
                    return;
                }
                throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("data"));
            }
            throw new IllegalArgumentException(ErrorStrings.paramNull("data"));
        }

        public byte[] getData() {
            return this.mData;
        }

        public int getNumber() {
            return this.mNumber;
        }
    }

    public FileViewProvider(Channel channel) {
        super(channel);
    }

    public byte[] readBinary(int i, int i2, int i3) {
        byte b;
        byte b2;
        if ((i < 1 || i > 30) && i != 0) {
            throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("sfi"));
        }
        if (i2 >= 0) {
            if (i3 >= 0) {
                if (i == 0) {
                    if (i2 > 32767) {
                        throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("offset"));
                    }
                    if (i3 > 32767) {
                        throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("length"));
                    }
                    b = (byte) i2;
                    b2 = (byte) ((i2 >> 8) & 127);
                } else if (i2 > 255) {
                    throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("offset"));
                } else {
                    if (i3 > 255) {
                        throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("length"));
                    }
                    b = (byte) i2;
                    b2 = (byte) (i | 128);
                }
                ResponseApdu responseApdu = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) -80, b2, b, i3).toByteArray()));
                int swValue = responseApdu.getSwValue();
                switch (swValue) {
                    case 27009:
                        throw new IllegalStateException("The selected file is not transparent.");
                    case 27010:
                        throw new SecurityException("Security status not satisfied.");
                    case 27014:
                        throw new IllegalStateException("No file is currently selected.");
                    case 27265:
                        throw new UnsupportedOperationException();
                    case 27266:
                        if (i == 0) {
                            throw new IllegalStateException("File not found.");
                        }
                        throw new IllegalArgumentException("File not found.");
                    case 27904:
                        throw new UnsupportedOperationException();
                    case 36864:
                        byte[] data = responseApdu.getData();
                        return data == null ? new byte[0] : data;
                    default:
                        throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                }
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("length"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("offset"));
    }

    public Record readRecord(int i, int i2) {
        if ((i < 1 || i > 30) && i != 0) {
            throw new IllegalArgumentException("Invalid Short File ID. SFI must be between ");
        }
        if (i2 >= 0 && i2 <= 254) {
            ResponseApdu responseApdu = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) -78, (byte) i2, (byte) ((i << 3) | 4), 0).toByteArray()));
            int swValue = responseApdu.getSwValue();
            switch (swValue) {
                case 27009:
                    throw new IllegalStateException("The selected file is not record-based.");
                case 27010:
                    throw new SecurityException("Security status not satisfied.");
                case 27265:
                    throw new UnsupportedOperationException("This operation is not supported by the selected Applet.");
                case 27266:
                    if (i == 0) {
                        throw new IllegalStateException("File not found.");
                    }
                    throw new IllegalArgumentException("File not found.");
                case 27267:
                    return null;
                case 27904:
                    throw new UnsupportedOperationException("This operation is not supported by the selected Applet.");
                case 36864:
                    byte[] data = responseApdu.getData();
                    if (data == null) {
                        data = new byte[0];
                    }
                    return new Record(i2, data);
                default:
                    throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
            }
        }
        throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("recordNumber"));
    }

    public int[] searchRecord(int i, byte[] bArr) {
        if ((i < 1 || i > 30) && i != 0) {
            throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("sfi"));
        }
        if (bArr != null) {
            if (bArr.length != 0 && bArr.length <= 65535) {
                ResponseApdu responseApdu = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) -94, (byte) 1, (byte) ((i << 3) | 4), bArr, 0).toByteArray()));
                int swValue = responseApdu.getSwValue();
                switch (swValue) {
                    case 27009:
                        throw new IllegalStateException("The selected file is not record-based.");
                    case 27010:
                        throw new SecurityException("Security status not satisfied.");
                    case 27014:
                        throw new IllegalStateException("No file is currently selected.");
                    case 27265:
                        throw new UnsupportedOperationException("This operation is not supported by the selected Applet.");
                    case 27266:
                        throw new IllegalArgumentException("File not found.");
                    case 27904:
                        throw new UnsupportedOperationException("This operation is not supported by the selected Applet.");
                    case 36864:
                        byte[] data = responseApdu.getData();
                        if (data == null || data.length <= 0) {
                            return null;
                        }
                        int[] iArr = new int[data.length];
                        for (int i2 = 0; i2 < data.length; i2++) {
                            iArr[i2] = data[i2];
                        }
                        return iArr;
                    default:
                        throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                }
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("searchPattern"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("searchPattern"));
    }

    public FCP selectByFID(int i) {
        if (1 <= i && i <= 65534 && i != 16383) {
            byte[] bArr = new byte[2];
            System.arraycopy(ByteArrayConverter.intToByteArray(i), 2, bArr, 0, 2);
            ResponseApdu responseApdu = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) -92, (byte) 0, (byte) 4, bArr).toByteArray()));
            int swValue = responseApdu.getSwValue();
            if (swValue != 27010) {
                if (swValue != 27904) {
                    if (swValue == 36864) {
                        return new FCP(responseApdu.getData());
                    }
                    switch (swValue) {
                        case 27264:
                            throw new IllegalArgumentException("File not found.");
                        case 27265:
                            throw new UnsupportedOperationException();
                        case 27266:
                            throw new IllegalArgumentException("File not found.");
                        default:
                            throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                    }
                }
                throw new UnsupportedOperationException();
            }
            throw new SecurityException("Security status not satisfied.");
        }
        throw new IllegalArgumentException("Invalid File ID. File ID must be between ");
    }

    public FCP selectByPath(String str, boolean z) {
        if (str != null) {
            if (!str.isEmpty()) {
                String[] split = str.split(":");
                byte[] bArr = new byte[(split.length * 4) / 2];
                for (int i = 0; i < split.length; i++) {
                    if (split[i].length() == 4) {
                        int parseInt = Integer.parseInt(split[i], 16);
                        if (1 <= parseInt && parseInt <= 65534 && parseInt != 16383) {
                            System.arraycopy(ByteArrayConverter.hexStringToByteArray(split[i]), 0, bArr, (i * 4) / 2, 2);
                        } else {
                            throw new IllegalArgumentException("Invalid File ID. File ID must be between ");
                        }
                    } else {
                        throw new IllegalArgumentException("Path elements must be 4-characters long.");
                    }
                }
                ResponseApdu responseApdu = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) -92, z ? (byte) 9 : (byte) 8, (byte) 4, bArr).toByteArray()));
                int swValue = responseApdu.getSwValue();
                if (swValue != 27010) {
                    if (swValue != 27904) {
                        if (swValue == 36864) {
                            return new FCP(responseApdu.getData());
                        }
                        switch (swValue) {
                            case 27265:
                                throw new UnsupportedOperationException();
                            case 27266:
                                throw new IllegalArgumentException("File not found.");
                            default:
                                throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                        }
                    }
                    throw new UnsupportedOperationException();
                }
                throw new SecurityException("Security status not satisfied.");
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("path"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("path"));
    }

    public FCP selectParent() {
        ResponseApdu responseApdu = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) -92, (byte) 3, (byte) 4, 0).toByteArray()));
        int swValue = responseApdu.getSwValue();
        if (swValue != 27010) {
            if (swValue != 27266) {
                if (swValue != 27904) {
                    if (swValue == 36864) {
                        return new FCP(responseApdu.getData());
                    }
                    throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                }
                throw new UnsupportedOperationException();
            }
            throw new IllegalArgumentException("File not found.");
        }
        throw new SecurityException("Security status not satisfied.");
    }

    public void writeBinary(int i, byte[] bArr, int i2, int i3) {
        byte b;
        byte b2;
        if (bArr != null) {
            if (bArr.length == 0 || bArr.length > 255 || bArr.length != i3) {
                throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("data"));
            }
            if ((i < 1 || i > 30) && i != 0) {
                throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("sfi"));
            }
            if (i2 < 0) {
                throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("offset"));
            }
            if (i3 >= 0) {
                if (i == 0) {
                    if (i2 > 32767) {
                        throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("offset"));
                    }
                    b = (byte) i2;
                    b2 = (byte) ((i2 >> 8) & 127);
                } else if (i2 > 255) {
                    throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("offset"));
                } else {
                    b = (byte) i2;
                    b2 = (byte) ((i & 159) | 128);
                }
                int swValue = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) -42, b2, b, bArr).toByteArray())).getSwValue();
                switch (swValue) {
                    case 27009:
                        throw new IllegalStateException("The selected file is not transparent.");
                    case 27010:
                        throw new SecurityException("Security status not satisfied.");
                    case 27014:
                        throw new IllegalStateException("No file is currently selected.");
                    case 27265:
                        throw new UnsupportedOperationException();
                    case 27266:
                        if (i == 0) {
                            throw new IllegalStateException("File not found.");
                        }
                        throw new IllegalArgumentException("File not found.");
                    case 27392:
                        throw new IllegalArgumentException("Offset outside EF.");
                    case 27904:
                        throw new UnsupportedOperationException();
                    case 36864:
                        return;
                    default:
                        throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                }
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("length"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("data"));
    }

    public void writeRecord(int i, Record record) {
        CommandApdu commandApdu;
        if (record == null) {
            throw new IllegalArgumentException(ErrorStrings.paramNull("rec"));
        }
        if ((i >= 1 && i <= 30) || i == 0) {
            if (record.getNumber() == -1) {
                commandApdu = new CommandApdu((byte) 0, (byte) -30, (byte) 0, (byte) (i << 3), record.getData());
            } else {
                commandApdu = new CommandApdu((byte) 0, (byte) -36, (byte) record.getNumber(), (byte) ((i << 3) | 4), record.getData());
            }
            int swValue = new ResponseApdu(getChannel().transmit(commandApdu.toByteArray())).getSwValue();
            if (swValue == 25985) {
                throw new IllegalStateException("Memory failure.");
            }
            if (swValue == 26368) {
                throw new IllegalArgumentException("Wrong length.");
            }
            if (swValue == 27014) {
                throw new IllegalStateException("No file is currently selected.");
            }
            if (swValue == 27904) {
                throw new UnsupportedOperationException("This operation is not supported by the selected Applet.");
            }
            if (swValue != 36864) {
                switch (swValue) {
                    case 27009:
                        throw new IllegalStateException("The selected file is not record-based.");
                    case 27010:
                        throw new SecurityException("Security status not satisfied.");
                    default:
                        switch (swValue) {
                            case 27265:
                                throw new UnsupportedOperationException("This operation is not supported by the selected Applet.");
                            case 27266:
                                if (i == 0) {
                                    throw new IllegalStateException("No file is currently selected.");
                                }
                                throw new IllegalArgumentException("File not found.");
                            case 27267:
                                throw new IllegalArgumentException("Record not found.");
                            case 27268:
                                throw new IllegalStateException("Not enough memory space in the file.");
                            default:
                                if (25536 > swValue || swValue > 25551) {
                                    throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                                }
                                return;
                        }
                }
            }
            return;
        }
        throw new IllegalArgumentException("Invalid Short File ID. SFI must be between ");
    }
}
