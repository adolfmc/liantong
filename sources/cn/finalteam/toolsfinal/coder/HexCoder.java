package cn.finalteam.toolsfinal.coder;

import java.nio.charset.Charset;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class HexCoder {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final Charset charset;

    public static byte[] decodeHex(char[] cArr) {
        int length = cArr.length;
        if ((length & 1) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] bArr = new byte[length >> 1];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            i = i3 + 1;
            bArr[i2] = (byte) (((toDigit(cArr[i], i) << 4) | toDigit(cArr[i3], i3)) & 255);
            i2++;
        }
        return bArr;
    }

    public static char[] encodeHex(byte[] bArr) {
        return encodeHex(bArr, true);
    }

    public static char[] encodeHex(byte[] bArr, boolean z) {
        return encodeHex(bArr, z ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] bArr, char[] cArr) {
        int length = bArr.length;
        char[] cArr2 = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    public static String encodeHexString(byte[] bArr) {
        return new String(encodeHex(bArr));
    }

    protected static int toDigit(char c, int i) throws RuntimeException {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new RuntimeException("Illegal hexadecimal character " + c + " at index " + i);
    }

    public HexCoder() {
        this.charset = DEFAULT_CHARSET;
    }

    public HexCoder(Charset charset) {
        this.charset = charset;
    }

    public HexCoder(String str) {
        this(Charset.forName(str));
    }

    public byte[] decode(byte[] bArr) {
        return decodeHex(new String(bArr, getCharset()).toCharArray());
    }

    public Object decode(Object obj) {
        try {
            return decodeHex(obj instanceof String ? ((String) obj).toCharArray() : (char[]) obj);
        } catch (ClassCastException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public byte[] encode(byte[] bArr) {
        return encodeHexString(bArr).getBytes(getCharset());
    }

    public Object encode(Object obj) {
        try {
            return encodeHex(obj instanceof String ? ((String) obj).getBytes(getCharset()) : (byte[]) obj);
        } catch (ClassCastException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getCharsetName() {
        return this.charset.name();
    }

    public String toString() {
        return super.toString() + "[charsetName=" + this.charset + "]";
    }
}
