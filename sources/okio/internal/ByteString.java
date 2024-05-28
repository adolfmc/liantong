package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Base64;
import okio.Buffer;
import okio.Platform;
import okio.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0002\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0080\b\u001a\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\r\u0010\u000f\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a\r\u0010\u0011\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a\u0015\u0010\u0012\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0080\b\u001a\u000f\u0010\u0014\u001a\u0004\u0018\u00010\n*\u00020\u0010H\u0080\b\u001a\r\u0010\u0015\u001a\u00020\n*\u00020\u0010H\u0080\b\u001a\r\u0010\u0016\u001a\u00020\n*\u00020\u0010H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u0018*\u00020\n2\u0006\u0010\u0019\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u0018*\u00020\n2\u0006\u0010\u0019\u001a\u00020\nH\u0080\b\u001a\u0017\u0010\u001a\u001a\u00020\u0018*\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u001bH\u0080\b\u001a\u0015\u0010\u001c\u001a\u00020\u001d*\u00020\n2\u0006\u0010\u001e\u001a\u00020\u0005H\u0080\b\u001a\r\u0010\u001f\u001a\u00020\u0005*\u00020\nH\u0080\b\u001a\r\u0010 \u001a\u00020\u0005*\u00020\nH\u0080\b\u001a\r\u0010!\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a\u001d\u0010\"\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a\r\u0010$\u001a\u00020\u0007*\u00020\nH\u0080\b\u001a\u001d\u0010%\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010%\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a-\u0010&\u001a\u00020\u0018*\u00020\n2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0080\b\u001a-\u0010&\u001a\u00020\u0018*\u00020\n2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010*\u001a\u00020\u0018*\u00020\n2\u0006\u0010+\u001a\u00020\u0007H\u0080\b\u001a\u0015\u0010*\u001a\u00020\u0018*\u00020\n2\u0006\u0010+\u001a\u00020\nH\u0080\b\u001a\u001d\u0010,\u001a\u00020\n*\u00020\n2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0080\b\u001a\r\u0010/\u001a\u00020\n*\u00020\nH\u0080\b\u001a\r\u00100\u001a\u00020\n*\u00020\nH\u0080\b\u001a\r\u00101\u001a\u00020\u0007*\u00020\nH\u0080\b\u001a\u001d\u00102\u001a\u00020\n*\u00020\u00072\u0006\u0010'\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0080\b\u001a\r\u00103\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a\r\u00104\u001a\u00020\u0010*\u00020\nH\u0080\b\u001a$\u00105\u001a\u000206*\u00020\n2\u0006\u00107\u001a\u0002082\u0006\u0010'\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u00069"}, m1890d2 = {"HEX_DIGIT_CHARS", "", "getHEX_DIGIT_CHARS", "()[C", "codePointIndexToCharIndex", "", "s", "", "codePointCount", "commonOf", "Lokio/ByteString;", "data", "decodeHexDigit", "c", "", "commonBase64", "", "commonBase64Url", "commonCompareTo", "other", "commonDecodeBase64", "commonDecodeHex", "commonEncodeUtf8", "commonEndsWith", "", "suffix", "commonEquals", "", "commonGetByte", "", "pos", "commonGetSize", "commonHashCode", "commonHex", "commonIndexOf", "fromIndex", "commonInternalArray", "commonLastIndexOf", "commonRangeEquals", "offset", "otherOffset", "byteCount", "commonStartsWith", "prefix", "commonSubstring", "beginIndex", "endIndex", "commonToAsciiLowercase", "commonToAsciiUppercase", "commonToByteArray", "commonToByteString", "commonToString", "commonUtf8", "commonWrite", "", "buffer", "Lokio/Buffer;", "okio"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: okio.internal.ByteStringKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ByteString {
    @NotNull
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final /* synthetic */ int access$codePointIndexToCharIndex(byte[] bArr, int i) {
        return codePointIndexToCharIndex(bArr, i);
    }

    public static final /* synthetic */ int access$decodeHexDigit(char c) {
        return decodeHexDigit(c);
    }

    @NotNull
    public static final String commonUtf8(@NotNull okio.ByteString commonUtf8) {
        Intrinsics.checkParameterIsNotNull(commonUtf8, "$this$commonUtf8");
        String utf8$okio = commonUtf8.getUtf8$okio();
        if (utf8$okio == null) {
            String utf8String = Platform.toUtf8String(commonUtf8.internalArray$okio());
            commonUtf8.setUtf8$okio(utf8String);
            return utf8String;
        }
        return utf8$okio;
    }

    @NotNull
    public static final String commonBase64(@NotNull okio.ByteString commonBase64) {
        Intrinsics.checkParameterIsNotNull(commonBase64, "$this$commonBase64");
        return Base64.encodeBase64$default(commonBase64.getData$okio(), null, 1, null);
    }

    @NotNull
    public static final String commonBase64Url(@NotNull okio.ByteString commonBase64Url) {
        Intrinsics.checkParameterIsNotNull(commonBase64Url, "$this$commonBase64Url");
        return Base64.encodeBase64(commonBase64Url.getData$okio(), Base64.getBASE64_URL_SAFE());
    }

    @NotNull
    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }

    @NotNull
    public static final String commonHex(@NotNull okio.ByteString commonHex) {
        byte[] data$okio;
        Intrinsics.checkParameterIsNotNull(commonHex, "$this$commonHex");
        char[] cArr = new char[commonHex.getData$okio().length * 2];
        int i = 0;
        for (byte b : commonHex.getData$okio()) {
            int i2 = i + 1;
            cArr[i] = getHEX_DIGIT_CHARS()[(b >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = getHEX_DIGIT_CHARS()[b & 15];
        }
        return new String(cArr);
    }

    @NotNull
    public static final okio.ByteString commonToAsciiLowercase(@NotNull okio.ByteString commonToAsciiLowercase) {
        byte b;
        Intrinsics.checkParameterIsNotNull(commonToAsciiLowercase, "$this$commonToAsciiLowercase");
        for (int i = 0; i < commonToAsciiLowercase.getData$okio().length; i++) {
            byte b2 = commonToAsciiLowercase.getData$okio()[i];
            byte b3 = (byte) 65;
            if (b2 >= b3 && b2 <= (b = (byte) 90)) {
                byte[] data$okio = commonToAsciiLowercase.getData$okio();
                byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                int i2 = i + 1;
                copyOf[i] = (byte) (b2 + 32);
                while (i2 < copyOf.length) {
                    byte b4 = copyOf[i2];
                    if (b4 < b3 || b4 > b) {
                        i2++;
                    } else {
                        copyOf[i2] = (byte) (b4 + 32);
                        i2++;
                    }
                }
                return new okio.ByteString(copyOf);
            }
        }
        return commonToAsciiLowercase;
    }

    @NotNull
    public static final okio.ByteString commonToAsciiUppercase(@NotNull okio.ByteString commonToAsciiUppercase) {
        byte b;
        Intrinsics.checkParameterIsNotNull(commonToAsciiUppercase, "$this$commonToAsciiUppercase");
        for (int i = 0; i < commonToAsciiUppercase.getData$okio().length; i++) {
            byte b2 = commonToAsciiUppercase.getData$okio()[i];
            byte b3 = (byte) 97;
            if (b2 >= b3 && b2 <= (b = (byte) 122)) {
                byte[] data$okio = commonToAsciiUppercase.getData$okio();
                byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                int i2 = i + 1;
                copyOf[i] = (byte) (b2 - 32);
                while (i2 < copyOf.length) {
                    byte b4 = copyOf[i2];
                    if (b4 < b3 || b4 > b) {
                        i2++;
                    } else {
                        copyOf[i2] = (byte) (b4 - 32);
                        i2++;
                    }
                }
                return new okio.ByteString(copyOf);
            }
        }
        return commonToAsciiUppercase;
    }

    @NotNull
    public static final okio.ByteString commonSubstring(@NotNull okio.ByteString commonSubstring, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonSubstring, "$this$commonSubstring");
        if (!(i >= 0)) {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        }
        if (i2 <= commonSubstring.getData$okio().length) {
            if (i2 - i >= 0) {
                return (i == 0 && i2 == commonSubstring.getData$okio().length) ? commonSubstring : new okio.ByteString(ArraysKt.copyOfRange(commonSubstring.getData$okio(), i, i2));
            }
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        throw new IllegalArgumentException(("endIndex > length(" + commonSubstring.getData$okio().length + ')').toString());
    }

    public static final byte commonGetByte(@NotNull okio.ByteString commonGetByte, int i) {
        Intrinsics.checkParameterIsNotNull(commonGetByte, "$this$commonGetByte");
        return commonGetByte.getData$okio()[i];
    }

    public static final int commonGetSize(@NotNull okio.ByteString commonGetSize) {
        Intrinsics.checkParameterIsNotNull(commonGetSize, "$this$commonGetSize");
        return commonGetSize.getData$okio().length;
    }

    @NotNull
    public static final byte[] commonToByteArray(@NotNull okio.ByteString commonToByteArray) {
        Intrinsics.checkParameterIsNotNull(commonToByteArray, "$this$commonToByteArray");
        byte[] data$okio = commonToByteArray.getData$okio();
        byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @NotNull
    public static final byte[] commonInternalArray(@NotNull okio.ByteString commonInternalArray) {
        Intrinsics.checkParameterIsNotNull(commonInternalArray, "$this$commonInternalArray");
        return commonInternalArray.getData$okio();
    }

    public static final boolean commonRangeEquals(@NotNull okio.ByteString commonRangeEquals, int i, @NotNull okio.ByteString other, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return other.rangeEquals(i2, commonRangeEquals.getData$okio(), i, i3);
    }

    public static final boolean commonRangeEquals(@NotNull okio.ByteString commonRangeEquals, int i, @NotNull byte[] other, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return i >= 0 && i <= commonRangeEquals.getData$okio().length - i3 && i2 >= 0 && i2 <= other.length - i3 && Util.arrayRangeEquals(commonRangeEquals.getData$okio(), i, other, i2, i3);
    }

    public static final boolean commonStartsWith(@NotNull okio.ByteString commonStartsWith, @NotNull okio.ByteString prefix) {
        Intrinsics.checkParameterIsNotNull(commonStartsWith, "$this$commonStartsWith");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        return commonStartsWith.rangeEquals(0, prefix, 0, prefix.size());
    }

    public static final boolean commonStartsWith(@NotNull okio.ByteString commonStartsWith, @NotNull byte[] prefix) {
        Intrinsics.checkParameterIsNotNull(commonStartsWith, "$this$commonStartsWith");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        return commonStartsWith.rangeEquals(0, prefix, 0, prefix.length);
    }

    public static final boolean commonEndsWith(@NotNull okio.ByteString commonEndsWith, @NotNull okio.ByteString suffix) {
        Intrinsics.checkParameterIsNotNull(commonEndsWith, "$this$commonEndsWith");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        return commonEndsWith.rangeEquals(commonEndsWith.size() - suffix.size(), suffix, 0, suffix.size());
    }

    public static final boolean commonEndsWith(@NotNull okio.ByteString commonEndsWith, @NotNull byte[] suffix) {
        Intrinsics.checkParameterIsNotNull(commonEndsWith, "$this$commonEndsWith");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        return commonEndsWith.rangeEquals(commonEndsWith.size() - suffix.length, suffix, 0, suffix.length);
    }

    public static final int commonIndexOf(@NotNull okio.ByteString commonIndexOf, @NotNull byte[] other, int i) {
        Intrinsics.checkParameterIsNotNull(commonIndexOf, "$this$commonIndexOf");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int length = commonIndexOf.getData$okio().length - other.length;
        int max = Math.max(i, 0);
        if (max <= length) {
            while (!Util.arrayRangeEquals(commonIndexOf.getData$okio(), max, other, 0, other.length)) {
                if (max == length) {
                    return -1;
                }
                max++;
            }
            return max;
        }
        return -1;
    }

    public static final int commonLastIndexOf(@NotNull okio.ByteString commonLastIndexOf, @NotNull okio.ByteString other, int i) {
        Intrinsics.checkParameterIsNotNull(commonLastIndexOf, "$this$commonLastIndexOf");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return commonLastIndexOf.lastIndexOf(other.internalArray$okio(), i);
    }

    public static final int commonLastIndexOf(@NotNull okio.ByteString commonLastIndexOf, @NotNull byte[] other, int i) {
        Intrinsics.checkParameterIsNotNull(commonLastIndexOf, "$this$commonLastIndexOf");
        Intrinsics.checkParameterIsNotNull(other, "other");
        for (int min = Math.min(i, commonLastIndexOf.getData$okio().length - other.length); min >= 0; min--) {
            if (Util.arrayRangeEquals(commonLastIndexOf.getData$okio(), min, other, 0, other.length)) {
                return min;
            }
        }
        return -1;
    }

    public static final boolean commonEquals(@NotNull okio.ByteString commonEquals, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(commonEquals, "$this$commonEquals");
        if (obj == commonEquals) {
            return true;
        }
        if (obj instanceof okio.ByteString) {
            okio.ByteString byteString = (okio.ByteString) obj;
            return byteString.size() == commonEquals.getData$okio().length && byteString.rangeEquals(0, commonEquals.getData$okio(), 0, commonEquals.getData$okio().length);
        }
        return false;
    }

    public static final int commonHashCode(@NotNull okio.ByteString commonHashCode) {
        Intrinsics.checkParameterIsNotNull(commonHashCode, "$this$commonHashCode");
        int hashCode$okio = commonHashCode.getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int hashCode = Arrays.hashCode(commonHashCode.getData$okio());
        commonHashCode.setHashCode$okio(hashCode);
        return hashCode;
    }

    public static final int commonCompareTo(@NotNull okio.ByteString commonCompareTo, @NotNull okio.ByteString other) {
        Intrinsics.checkParameterIsNotNull(commonCompareTo, "$this$commonCompareTo");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int size = commonCompareTo.size();
        int size2 = other.size();
        int min = Math.min(size, size2);
        for (int i = 0; i < min; i++) {
            int i2 = commonCompareTo.getByte(i) & 255;
            int i3 = other.getByte(i) & 255;
            if (i2 != i3) {
                return i2 < i3 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    @NotNull
    public static final okio.ByteString commonOf(@NotNull byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        byte[] copyOf = Arrays.copyOf(data, data.length);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        return new okio.ByteString(copyOf);
    }

    @NotNull
    public static final okio.ByteString commonToByteString(@NotNull byte[] commonToByteString, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonToByteString, "$this$commonToByteString");
        Util.checkOffsetAndCount(commonToByteString.length, i, i2);
        return new okio.ByteString(ArraysKt.copyOfRange(commonToByteString, i, i2 + i));
    }

    @NotNull
    public static final okio.ByteString commonEncodeUtf8(@NotNull String commonEncodeUtf8) {
        Intrinsics.checkParameterIsNotNull(commonEncodeUtf8, "$this$commonEncodeUtf8");
        okio.ByteString byteString = new okio.ByteString(Platform.asUtf8ToByteArray(commonEncodeUtf8));
        byteString.setUtf8$okio(commonEncodeUtf8);
        return byteString;
    }

    @Nullable
    public static final okio.ByteString commonDecodeBase64(@NotNull String commonDecodeBase64) {
        Intrinsics.checkParameterIsNotNull(commonDecodeBase64, "$this$commonDecodeBase64");
        byte[] decodeBase64ToArray = Base64.decodeBase64ToArray(commonDecodeBase64);
        if (decodeBase64ToArray != null) {
            return new okio.ByteString(decodeBase64ToArray);
        }
        return null;
    }

    @NotNull
    public static final okio.ByteString commonDecodeHex(@NotNull String commonDecodeHex) {
        Intrinsics.checkParameterIsNotNull(commonDecodeHex, "$this$commonDecodeHex");
        if (!(commonDecodeHex.length() % 2 == 0)) {
            throw new IllegalArgumentException(("Unexpected hex string: " + commonDecodeHex).toString());
        }
        byte[] bArr = new byte[commonDecodeHex.length() / 2];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((decodeHexDigit(commonDecodeHex.charAt(i2)) << 4) + decodeHexDigit(commonDecodeHex.charAt(i2 + 1)));
        }
        return new okio.ByteString(bArr);
    }

    public static final void commonWrite(@NotNull okio.ByteString commonWrite, @NotNull Buffer buffer, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(buffer, "buffer");
        buffer.write(commonWrite.getData$okio(), i, i2);
    }

    public static final int decodeHexDigit(char c) {
        if ('0' <= c && '9' >= c) {
            return c - '0';
        }
        if ('a' <= c && 'f' >= c) {
            return (c - 'a') + 10;
        }
        if ('A' > c || 'F' < c) {
            throw new IllegalArgumentException("Unexpected hex digit: " + c);
        }
        return (c - 'A') + 10;
    }

    @NotNull
    public static final String commonToString(@NotNull okio.ByteString byteString) {
        okio.ByteString commonToString = byteString;
        Intrinsics.checkParameterIsNotNull(commonToString, "$this$commonToString");
        if (byteString.getData$okio().length == 0) {
            return "[size=0]";
        }
        int codePointIndexToCharIndex = codePointIndexToCharIndex(byteString.getData$okio(), 64);
        if (codePointIndexToCharIndex == -1) {
            if (byteString.getData$okio().length <= 64) {
                return "[hex=" + byteString.hex() + ']';
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[size=");
            sb.append(byteString.getData$okio().length);
            sb.append(" hex=");
            if (!(64 <= byteString.getData$okio().length)) {
                throw new IllegalArgumentException(("endIndex > length(" + byteString.getData$okio().length + ')').toString());
            }
            if (64 != byteString.getData$okio().length) {
                commonToString = new okio.ByteString(ArraysKt.copyOfRange(byteString.getData$okio(), 0, 64));
            }
            sb.append(commonToString.hex());
            sb.append("…]");
            return sb.toString();
        }
        String utf8 = byteString.utf8();
        if (utf8 != null) {
            String substring = utf8.substring(0, codePointIndexToCharIndex);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(substring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
            if (codePointIndexToCharIndex < utf8.length()) {
                return "[size=" + byteString.getData$okio().length + " text=" + replace$default + "…]";
            }
            return "[text=" + replace$default + ']';
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public static final int codePointIndexToCharIndex(byte[] bArr, int i) {
        int i2;
        int i3;
        int length = bArr.length;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < length) {
            byte b = bArr[i4];
            if (b >= 0) {
                int i7 = i6 + 1;
                if (i6 == i) {
                    return i5;
                }
                if (b != 10 && b != 13) {
                    if ((b >= 0 && 31 >= b) || (Byte.MAX_VALUE <= b && 159 >= b)) {
                        return -1;
                    }
                }
                if (b == 65533) {
                    return -1;
                }
                i4++;
                int i8 = i5 + (b < 65536 ? 1 : 2);
                int i9 = i7;
                while (i4 < length && bArr[i4] >= 0) {
                    int i10 = i4 + 1;
                    byte b2 = bArr[i4];
                    int i11 = i9 + 1;
                    if (i9 == i) {
                        return i8;
                    }
                    if (b2 != 10 && b2 != 13) {
                        if ((b2 >= 0 && 31 >= b2) || (Byte.MAX_VALUE <= b2 && 159 >= b2)) {
                            return -1;
                        }
                    }
                    if (b2 == 65533) {
                        return -1;
                    }
                    i8 += b2 < 65536 ? 1 : 2;
                    i4 = i10;
                    i9 = i11;
                }
                int i12 = i8;
                i6 = i9;
                i5 = i12;
            } else if ((b >> 5) == -2) {
                int i13 = i4 + 1;
                if (length <= i13) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                byte b3 = bArr[i4];
                byte b4 = bArr[i13];
                if (!((b4 & 192) == 128)) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                int i14 = (b4 ^ 3968) ^ (b3 << 6);
                if (i14 < 128) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                int i15 = i6 + 1;
                if (i6 == i) {
                    return i5;
                }
                if (i14 != 10 && i14 != 13) {
                    if ((i14 >= 0 && 31 >= i14) || (127 <= i14 && 159 >= i14)) {
                        return -1;
                    }
                }
                if (i14 == 65533) {
                    return -1;
                }
                i5 += i14 >= 65536 ? 2 : 1;
                i4 += 2;
                i6 = i15;
            } else if ((b >> 4) == -2) {
                int i16 = i4 + 2;
                if (length <= i16) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                byte b5 = bArr[i4];
                byte b6 = bArr[i4 + 1];
                if (!((b6 & 192) == 128)) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                byte b7 = bArr[i16];
                if (!((b7 & 192) == 128)) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                int i17 = ((b7 ^ (-123008)) ^ (b6 << 6)) ^ (b5 << 12);
                if (i17 < 2048) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                } else if (55296 <= i17 && 57343 >= i17) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                } else {
                    int i18 = i6 + 1;
                    if (i6 == i) {
                        return i5;
                    }
                    if (i17 == 10 || i17 == 13) {
                        i2 = 65533;
                    } else {
                        if ((i17 >= 0 && 31 >= i17) || (127 <= i17 && 159 >= i17)) {
                            return -1;
                        }
                        i2 = 65533;
                    }
                    if (i17 == i2) {
                        return -1;
                    }
                    i5 += i17 >= 65536 ? 2 : 1;
                    i4 += 3;
                    i6 = i18;
                }
            } else if ((b >> 3) != -2) {
                if (i6 == i) {
                    return i5;
                }
                return -1;
            } else {
                int i19 = i4 + 3;
                if (length <= i19) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                byte b8 = bArr[i4];
                byte b9 = bArr[i4 + 1];
                if (!((b9 & 192) == 128)) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                byte b10 = bArr[i4 + 2];
                if (!((b10 & 192) == 128)) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                byte b11 = bArr[i19];
                if (!((b11 & 192) == 128)) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                }
                int i20 = (((b11 ^ 3678080) ^ (b10 << 6)) ^ (b9 << 12)) ^ (b8 << 18);
                if (i20 > 1114111) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                } else if (55296 <= i20 && 57343 >= i20) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                } else if (i20 < 65536) {
                    if (i6 == i) {
                        return i5;
                    }
                    return -1;
                } else {
                    int i21 = i6 + 1;
                    if (i6 == i) {
                        return i5;
                    }
                    if (i20 == 10 || i20 == 13) {
                        i3 = 65533;
                    } else {
                        if ((i20 >= 0 && 31 >= i20) || (127 <= i20 && 159 >= i20)) {
                            return -1;
                        }
                        i3 = 65533;
                    }
                    if (i20 == i3) {
                        return -1;
                    }
                    i5 += i20 >= 65536 ? 2 : 1;
                    i4 += 4;
                    i6 = i21;
                }
            }
        }
        return i5;
    }
}
