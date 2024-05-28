package org.codehaus.jackson.p467io;

import java.lang.ref.SoftReference;
import org.codehaus.jackson.util.BufferRecycler;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.CharTypes;
import org.codehaus.jackson.util.TextBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.codehaus.jackson.io.JsonStringEncoder */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class JsonStringEncoder {
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected ByteArrayBuilder _byteBuilder;
    protected final char[] _quoteBuffer = new char[6];
    protected TextBuffer _textBuffer;
    private static final char[] HEX_CHARS = CharTypes.copyHexChars();
    private static final byte[] HEX_BYTES = CharTypes.copyHexBytes();
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();

    public JsonStringEncoder() {
        char[] cArr = this._quoteBuffer;
        cArr[0] = '\\';
        cArr[2] = '0';
        cArr[3] = '0';
    }

    public static JsonStringEncoder getInstance() {
        SoftReference<JsonStringEncoder> softReference = _threadEncoder.get();
        JsonStringEncoder jsonStringEncoder = softReference == null ? null : softReference.get();
        if (jsonStringEncoder == null) {
            JsonStringEncoder jsonStringEncoder2 = new JsonStringEncoder();
            _threadEncoder.set(new SoftReference<>(jsonStringEncoder2));
            return jsonStringEncoder2;
        }
        return jsonStringEncoder;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        r8 = r1 + 1;
        r1 = _appendSingleEscape(r2[r12.charAt(r1)], r11._quoteBuffer);
        r9 = r7 + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003a, code lost:
        if (r9 <= r6.length) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
        r9 = r6.length - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003e, code lost:
        if (r9 <= 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r6, r7, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
        r6 = r0.finishCurrentSegment();
        r1 = r1 - r9;
        java.lang.System.arraycopy(r11._quoteBuffer, r9, r6, 0, r1);
        r7 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r6, r7, r1);
        r7 = r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public char[] quoteAsString(java.lang.String r12) {
        /*
            r11 = this;
            org.codehaus.jackson.util.TextBuffer r0 = r11._textBuffer
            if (r0 != 0) goto Lc
            org.codehaus.jackson.util.TextBuffer r0 = new org.codehaus.jackson.util.TextBuffer
            r1 = 0
            r0.<init>(r1)
            r11._textBuffer = r0
        Lc:
            char[] r1 = r0.emptyAndGetCurrentSegment()
            int[] r2 = org.codehaus.jackson.util.CharTypes.get7BitOutputEscapes()
            int r3 = r2.length
            int r4 = r12.length()
            r5 = 0
            r6 = r1
            r1 = r5
            r7 = r1
        L1d:
            if (r1 >= r4) goto L6d
        L1f:
            char r8 = r12.charAt(r1)
            if (r8 >= r3) goto L59
            r9 = r2[r8]
            if (r9 == 0) goto L59
            int r8 = r1 + 1
            char r1 = r12.charAt(r1)
            r1 = r2[r1]
            char[] r9 = r11._quoteBuffer
            int r1 = r11._appendSingleEscape(r1, r9)
            int r9 = r7 + r1
            int r10 = r6.length
            if (r9 <= r10) goto L51
            int r9 = r6.length
            int r9 = r9 - r7
            if (r9 <= 0) goto L45
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r5, r6, r7, r9)
        L45:
            char[] r6 = r0.finishCurrentSegment()
            int r1 = r1 - r9
            char[] r7 = r11._quoteBuffer
            java.lang.System.arraycopy(r7, r9, r6, r5, r1)
            r7 = r1
            goto L57
        L51:
            char[] r10 = r11._quoteBuffer
            java.lang.System.arraycopy(r10, r5, r6, r7, r1)
            r7 = r9
        L57:
            r1 = r8
            goto L1d
        L59:
            int r9 = r6.length
            if (r7 < r9) goto L61
            char[] r6 = r0.finishCurrentSegment()
            r7 = r5
        L61:
            int r9 = r7 + 1
            r6[r7] = r8
            int r1 = r1 + 1
            if (r1 < r4) goto L6b
            r7 = r9
            goto L6d
        L6b:
            r7 = r9
            goto L1f
        L6d:
            r0.setCurrentLength(r7)
            char[] r12 = r0.contentsAsArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.p467io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0042, code lost:
        if (r5 < r4.length) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:
        r4 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0049, code lost:
        r7 = r2 + 1;
        r2 = r11.charAt(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004f, code lost:
        if (r2 > 127) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0051, code lost:
        r5 = _appendByteEscape(r2, r6[r2], r0, r5);
        r4 = r0.getCurrentSegment();
        r2 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005f, code lost:
        if (r2 > 2047) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0061, code lost:
        r4[r5] = (byte) ((r2 >> 6) | 192);
        r2 = (r2 & '?') | 128;
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0074, code lost:
        if (r2 < 55296) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0079, code lost:
        if (r2 <= 57343) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007f, code lost:
        if (r2 <= 56319) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0081, code lost:
        _throwIllegalSurrogate(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0084, code lost:
        if (r7 < r1) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0086, code lost:
        _throwIllegalSurrogate(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0089, code lost:
        r6 = r7 + 1;
        r2 = _convertSurrogate(r2, r11.charAt(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0096, code lost:
        if (r2 <= 1114111) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0098, code lost:
        _throwIllegalSurrogate(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x009b, code lost:
        r7 = r5 + 1;
        r4[r5] = (byte) ((r2 >> 18) | 240);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a5, code lost:
        if (r7 < r4.length) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a7, code lost:
        r4 = r0.finishCurrentSegment();
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ac, code lost:
        r5 = r7 + 1;
        r4[r7] = (byte) (((r2 >> 12) & 63) | 128);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b8, code lost:
        if (r5 < r4.length) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ba, code lost:
        r4 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00bf, code lost:
        r4[r5] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & 63) | 128;
        r5 = r5 + 1;
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d1, code lost:
        r6 = r5 + 1;
        r4[r5] = (byte) ((r2 >> '\f') | 224);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00db, code lost:
        if (r6 < r4.length) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00dd, code lost:
        r4 = r0.finishCurrentSegment();
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e2, code lost:
        r5 = r6 + 1;
        r4[r6] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & '?') | 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00f2, code lost:
        if (r5 < r4.length) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00f4, code lost:
        r4 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f9, code lost:
        r4[r5] = (byte) r2;
        r5 = r5 + 1;
        r2 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] quoteAsUTF8(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.p467io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._byteBuilder = byteArrayBuilder;
        }
        int length = str.length();
        byte[] resetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int length2 = resetAndGetFirstSegment.length;
        byte[] bArr = resetAndGetFirstSegment;
        int i2 = 0;
        int i3 = 0;
        loop0: while (true) {
            if (i2 >= length) {
                break;
            }
            int i4 = i2 + 1;
            char charAt = str.charAt(i2);
            while (charAt <= 127) {
                if (i3 >= length2) {
                    byte[] finishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i3 = 0;
                    bArr = finishCurrentSegment;
                    length2 = finishCurrentSegment.length;
                }
                int i5 = i3 + 1;
                bArr[i3] = (byte) charAt;
                if (i4 >= length) {
                    i3 = i5;
                    break loop0;
                }
                char charAt2 = str.charAt(i4);
                i4++;
                charAt = charAt2;
                i3 = i5;
            }
            if (i3 >= length2) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr.length;
                i3 = 0;
            }
            if (charAt < 2048) {
                bArr[i3] = (byte) ((charAt >> 6) | 192);
                i = i3 + 1;
            } else if (charAt < 55296 || charAt > 57343) {
                int i6 = i3 + 1;
                bArr[i3] = (byte) ((charAt >> 12) | 224);
                if (i6 >= length2) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr.length;
                    i6 = 0;
                }
                i = i6 + 1;
                bArr[i6] = (byte) (((charAt >> 6) & 63) | 128);
            } else {
                if (charAt > 56319) {
                    _throwIllegalSurrogate(charAt);
                }
                if (i4 >= length) {
                    _throwIllegalSurrogate(charAt);
                }
                int i7 = i4 + 1;
                charAt = _convertSurrogate(charAt, str.charAt(i4));
                if (charAt > 1114111) {
                    _throwIllegalSurrogate(charAt);
                }
                int i8 = i3 + 1;
                bArr[i3] = (byte) ((charAt >> 18) | 240);
                if (i8 >= length2) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr.length;
                    i8 = 0;
                }
                int i9 = i8 + 1;
                bArr[i8] = (byte) (((charAt >> 12) & 63) | 128);
                if (i9 >= length2) {
                    byte[] finishCurrentSegment2 = byteArrayBuilder.finishCurrentSegment();
                    i9 = 0;
                    bArr = finishCurrentSegment2;
                    length2 = finishCurrentSegment2.length;
                }
                bArr[i9] = (byte) (((charAt >> 6) & 63) | 128);
                i = i9 + 1;
                i4 = i7;
            }
            if (i >= length2) {
                byte[] finishCurrentSegment3 = byteArrayBuilder.finishCurrentSegment();
                i = 0;
                bArr = finishCurrentSegment3;
                length2 = finishCurrentSegment3.length;
            }
            bArr[i] = (byte) ((charAt & 63) | 128);
            i2 = i4;
            i3 = i + 1;
        }
        return this._byteBuilder.completeAndCoalesce(i3);
    }

    private int _appendSingleEscape(int i, char[] cArr) {
        if (i < 0) {
            int i2 = -(i + 1);
            cArr[1] = 'u';
            char[] cArr2 = HEX_CHARS;
            cArr[4] = cArr2[i2 >> 4];
            cArr[5] = cArr2[i2 & 15];
            return 6;
        }
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByteEscape(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HEX_BYTES[i4 >> 4]);
                byteArrayBuilder.append(HEX_BYTES[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HEX_BYTES[i >> 4]);
            byteArrayBuilder.append(HEX_BYTES[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _convertSurrogate(int i, int i2) {
        if (i2 < 56320 || i2 > 57343) {
            throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
        }
        return ((i - 55296) << 10) + 65536 + (i2 - 56320);
    }

    private void _throwIllegalSurrogate(int i) {
        if (i > 1114111) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        } else if (i < 55296) {
            throw new IllegalArgumentException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        } else if (i <= 56319) {
            throw new IllegalArgumentException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        } else {
            throw new IllegalArgumentException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
    }
}
