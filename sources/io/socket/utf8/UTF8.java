package io.socket.utf8;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class UTF8 {
    private static final String INVALID_CONTINUATION_BYTE = "Invalid continuation byte";
    private static int[] byteArray;
    private static int byteCount;
    private static int byteIndex;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Options {
        public boolean strict = true;
    }

    private UTF8() {
    }

    public static String encode(String str) throws UTF8Exception {
        return encode(str, new Options());
    }

    public static String encode(String str, Options options) throws UTF8Exception {
        boolean z = options.strict;
        int[] ucs2decode = ucs2decode(str);
        int length = ucs2decode.length;
        StringBuilder sb = new StringBuilder();
        int i = -1;
        while (true) {
            i++;
            if (i < length) {
                sb.append(encodeCodePoint(ucs2decode[i], z));
            } else {
                return sb.toString();
            }
        }
    }

    public static String decode(String str) throws UTF8Exception {
        return decode(str, new Options());
    }

    public static String decode(String str, Options options) throws UTF8Exception {
        boolean z = options.strict;
        byteArray = ucs2decode(str);
        byteCount = byteArray.length;
        byteIndex = 0;
        ArrayList arrayList = new ArrayList();
        while (true) {
            int decodeSymbol = decodeSymbol(z);
            if (decodeSymbol != -1) {
                arrayList.add(Integer.valueOf(decodeSymbol));
            } else {
                return ucs2encode(listToArray(arrayList));
            }
        }
    }

    private static int[] ucs2decode(String str) {
        int length = str.length();
        int i = 0;
        int[] iArr = new int[str.codePointCount(0, length)];
        int i2 = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            iArr[i2] = codePointAt;
            i += Character.charCount(codePointAt);
            i2++;
        }
        return iArr;
    }

    private static String encodeCodePoint(int i, boolean z) throws UTF8Exception {
        StringBuilder sb = new StringBuilder();
        if ((i & (-128)) == 0) {
            sb.append(Character.toChars(i));
            return sb.toString();
        }
        if ((i & (-2048)) == 0) {
            sb.append(Character.toChars(((i >> 6) & 31) | 192));
        } else if (((-65536) & i) == 0) {
            if (!checkScalarValue(i, z)) {
                i = 65533;
            }
            sb.append(Character.toChars(((i >> 12) & 15) | 224));
            sb.append(createByte(i, 6));
        } else if (((-2097152) & i) == 0) {
            sb.append(Character.toChars(((i >> 18) & 7) | 240));
            sb.append(createByte(i, 12));
            sb.append(createByte(i, 6));
        }
        sb.append(Character.toChars((i & 63) | 128));
        return sb.toString();
    }

    private static char[] createByte(int i, int i2) {
        return Character.toChars(((i >> i2) & 63) | 128);
    }

    private static int decodeSymbol(boolean z) throws UTF8Exception {
        int i = byteIndex;
        int i2 = byteCount;
        if (i <= i2) {
            if (i == i2) {
                return -1;
            }
            int i3 = byteArray[i] & 255;
            byteIndex = i + 1;
            if ((i3 & 128) == 0) {
                return i3;
            }
            if ((i3 & 224) == 192) {
                int readContinuationByte = readContinuationByte() | ((i3 & 31) << 6);
                if (readContinuationByte >= 128) {
                    return readContinuationByte;
                }
                throw new UTF8Exception("Invalid continuation byte");
            } else if ((i3 & 240) == 224) {
                int readContinuationByte2 = (readContinuationByte() << 6) | ((i3 & 15) << 12) | readContinuationByte();
                if (readContinuationByte2 >= 2048) {
                    if (checkScalarValue(readContinuationByte2, z)) {
                        return readContinuationByte2;
                    }
                    return 65533;
                }
                throw new UTF8Exception("Invalid continuation byte");
            } else {
                if ((i3 & 248) == 240) {
                    int readContinuationByte3 = readContinuationByte();
                    int i4 = readContinuationByte3 << 12;
                    int readContinuationByte4 = i4 | ((i3 & 15) << 18) | (readContinuationByte() << 6) | readContinuationByte();
                    if (readContinuationByte4 >= 65536 && readContinuationByte4 <= 1114111) {
                        return readContinuationByte4;
                    }
                }
                throw new UTF8Exception("Invalid continuation byte");
            }
        }
        throw new UTF8Exception("Invalid byte index");
    }

    private static int readContinuationByte() throws UTF8Exception {
        int i = byteIndex;
        if (i >= byteCount) {
            throw new UTF8Exception("Invalid byte index");
        }
        int i2 = byteArray[i] & 255;
        byteIndex = i + 1;
        if ((i2 & 192) == 128) {
            return i2 & 63;
        }
        throw new UTF8Exception("Invalid continuation byte");
    }

    private static String ucs2encode(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.appendCodePoint(i);
        }
        return sb.toString();
    }

    private static boolean checkScalarValue(int i, boolean z) throws UTF8Exception {
        if (i < 55296 || i > 57343) {
            return true;
        }
        if (z) {
            throw new UTF8Exception("Lone surrogate U+" + Integer.toHexString(i).toUpperCase() + " is not a scalar value");
        }
        return false;
    }

    private static int[] listToArray(List<Integer> list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }
}
