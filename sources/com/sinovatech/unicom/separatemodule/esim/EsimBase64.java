package com.sinovatech.unicom.separatemodule.esim;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class EsimBase64 {
    private EsimBase64() {
    }

    public static Encoder getEncoder() {
        return Encoder.RFC4648;
    }

    public static Encoder getUrlEncoder() {
        return Encoder.RFC4648_URLSAFE;
    }

    public static Encoder getMimeEncoder() {
        return Encoder.RFC2045;
    }

    public static Encoder getMimeEncoder(int i, byte[] bArr) {
        Objects.requireNonNull(bArr);
        int[] iArr = Decoder.fromBase64;
        for (byte b : bArr) {
            if (iArr[b & 255] != -1) {
                throw new IllegalArgumentException("Illegal base64 line separator character 0x" + Integer.toString(b, 16));
            }
        }
        if (i <= 0) {
            return Encoder.RFC4648;
        }
        return new Encoder(false, bArr, (i >> 2) << 2, true);
    }

    public static Decoder getDecoder() {
        return Decoder.RFC4648;
    }

    public static Decoder getUrlDecoder() {
        return Decoder.RFC4648_URLSAFE;
    }

    public static Decoder getMimeDecoder() {
        return Decoder.RFC2045;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Encoder {
        private static final int MIMELINEMAX = 76;
        private final boolean doPadding;
        private final boolean isURL;
        private final int linemax;
        private final byte[] newline;
        private static final char[] toBase64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static final char[] toBase64URL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        private static final byte[] CRLF = {13, 10};
        static final Encoder RFC4648 = new Encoder(false, null, -1, true);
        static final Encoder RFC4648_URLSAFE = new Encoder(true, null, -1, true);
        static final Encoder RFC2045 = new Encoder(false, CRLF, 76, true);

        private Encoder(boolean z, byte[] bArr, int i, boolean z2) {
            this.isURL = z;
            this.newline = bArr;
            this.linemax = i;
            this.doPadding = z2;
        }

        private final int outLength(int i) {
            int i2;
            if (this.doPadding) {
                i2 = ((i + 2) / 3) * 4;
            } else {
                int i3 = i % 3;
                i2 = ((i / 3) * 4) + (i3 == 0 ? 0 : i3 + 1);
            }
            int i4 = this.linemax;
            return i4 > 0 ? i2 + (((i2 - 1) / i4) * this.newline.length) : i2;
        }

        public byte[] encode(byte[] bArr) {
            byte[] bArr2 = new byte[outLength(bArr.length)];
            int encode0 = encode0(bArr, 0, bArr.length, bArr2);
            return encode0 != bArr2.length ? Arrays.copyOf(bArr2, encode0) : bArr2;
        }

        public int encode(byte[] bArr, byte[] bArr2) {
            if (bArr2.length < outLength(bArr.length)) {
                throw new IllegalArgumentException("Output byte array is too small for encoding all input bytes");
            }
            return encode0(bArr, 0, bArr.length, bArr2);
        }

        public String encodeToString(byte[] bArr) {
            byte[] encode = encode(bArr);
            return new String(encode, 0, 0, encode.length);
        }

        public ByteBuffer encode(ByteBuffer byteBuffer) {
            int encode0;
            byte[] bArr = new byte[outLength(byteBuffer.remaining())];
            if (byteBuffer.hasArray()) {
                encode0 = encode0(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.arrayOffset() + byteBuffer.limit(), bArr);
                byteBuffer.position(byteBuffer.limit());
            } else {
                byte[] bArr2 = new byte[byteBuffer.remaining()];
                byteBuffer.get(bArr2);
                encode0 = encode0(bArr2, 0, bArr2.length, bArr);
            }
            if (encode0 != bArr.length) {
                bArr = Arrays.copyOf(bArr, encode0);
            }
            return ByteBuffer.wrap(bArr);
        }

        public OutputStream wrap(OutputStream outputStream) {
            Objects.requireNonNull(outputStream);
            return new EncOutputStream(outputStream, this.isURL ? toBase64URL : toBase64, this.newline, this.linemax, this.doPadding);
        }

        public Encoder withoutPadding() {
            return !this.doPadding ? this : new Encoder(this.isURL, this.newline, this.linemax, false);
        }

        private int encode0(byte[] bArr, int i, int i2, byte[] bArr2) {
            char[] cArr = this.isURL ? toBase64URL : toBase64;
            int i3 = ((i2 - i) / 3) * 3;
            int i4 = i + i3;
            int i5 = this.linemax;
            if (i5 > 0 && i3 > (i5 / 4) * 3) {
                i3 = (i5 / 4) * 3;
            }
            int i6 = 0;
            while (i < i4) {
                int min = Math.min(i + i3, i4);
                int i7 = i;
                int i8 = i6;
                while (i7 < min) {
                    int i9 = i7 + 1;
                    int i10 = i9 + 1;
                    int i11 = ((bArr[i7] & 255) << 16) | ((bArr[i9] & 255) << 8);
                    int i12 = i10 + 1;
                    int i13 = i11 | (bArr[i10] & 255);
                    int i14 = i8 + 1;
                    bArr2[i8] = (byte) cArr[(i13 >>> 18) & 63];
                    int i15 = i14 + 1;
                    bArr2[i14] = (byte) cArr[(i13 >>> 12) & 63];
                    int i16 = i15 + 1;
                    bArr2[i15] = (byte) cArr[(i13 >>> 6) & 63];
                    i8 = i16 + 1;
                    bArr2[i16] = (byte) cArr[i13 & 63];
                    i7 = i12;
                }
                int i17 = ((min - i) / 3) * 4;
                i6 += i17;
                if (i17 == this.linemax && min < i2) {
                    byte[] bArr3 = this.newline;
                    int length = bArr3.length;
                    int i18 = i6;
                    int i19 = 0;
                    while (i19 < length) {
                        bArr2[i18] = bArr3[i19];
                        i19++;
                        i18++;
                    }
                    i6 = i18;
                }
                i = min;
            }
            if (i < i2) {
                int i20 = i + 1;
                int i21 = bArr[i] & 255;
                int i22 = i6 + 1;
                bArr2[i6] = (byte) cArr[i21 >> 2];
                if (i20 == i2) {
                    int i23 = i22 + 1;
                    bArr2[i22] = (byte) cArr[(i21 << 4) & 63];
                    if (this.doPadding) {
                        int i24 = i23 + 1;
                        bArr2[i23] = 61;
                        int i25 = i24 + 1;
                        bArr2[i24] = 61;
                        return i25;
                    }
                    return i23;
                }
                int i26 = bArr[i20] & 255;
                int i27 = i22 + 1;
                bArr2[i22] = (byte) cArr[((i21 << 4) & 63) | (i26 >> 4)];
                int i28 = i27 + 1;
                bArr2[i27] = (byte) cArr[(i26 << 2) & 63];
                if (this.doPadding) {
                    int i29 = i28 + 1;
                    bArr2[i28] = 61;
                    return i29;
                }
                return i28;
            }
            return i6;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Decoder {
        static final Decoder RFC2045;
        static final Decoder RFC4648;
        static final Decoder RFC4648_URLSAFE;
        private static final int[] fromBase64 = new int[256];
        private static final int[] fromBase64URL;
        private final boolean isMIME;
        private final boolean isURL;

        private Decoder(boolean z, boolean z2) {
            this.isURL = z;
            this.isMIME = z2;
        }

        static {
            Arrays.fill(fromBase64, -1);
            for (int i = 0; i < Encoder.toBase64.length; i++) {
                fromBase64[Encoder.toBase64[i]] = i;
            }
            fromBase64[61] = -2;
            fromBase64URL = new int[256];
            Arrays.fill(fromBase64URL, -1);
            for (int i2 = 0; i2 < Encoder.toBase64URL.length; i2++) {
                fromBase64URL[Encoder.toBase64URL[i2]] = i2;
            }
            fromBase64URL[61] = -2;
            RFC4648 = new Decoder(false, false);
            RFC4648_URLSAFE = new Decoder(true, false);
            RFC2045 = new Decoder(false, true);
        }

        public byte[] decode(byte[] bArr) {
            byte[] bArr2 = new byte[outLength(bArr, 0, bArr.length)];
            int decode0 = decode0(bArr, 0, bArr.length, bArr2);
            return decode0 != bArr2.length ? Arrays.copyOf(bArr2, decode0) : bArr2;
        }

        public byte[] decode(String str) {
            return decode(str.getBytes(StandardCharsets.ISO_8859_1));
        }

        public int decode(byte[] bArr, byte[] bArr2) {
            if (bArr2.length < outLength(bArr, 0, bArr.length)) {
                throw new IllegalArgumentException("Output byte array is too small for decoding all input bytes");
            }
            return decode0(bArr, 0, bArr.length, bArr2);
        }

        public ByteBuffer decode(ByteBuffer byteBuffer) {
            byte[] bArr;
            int length;
            int i;
            int position = byteBuffer.position();
            try {
                if (byteBuffer.hasArray()) {
                    bArr = byteBuffer.array();
                    i = byteBuffer.arrayOffset() + byteBuffer.position();
                    length = byteBuffer.arrayOffset() + byteBuffer.limit();
                    byteBuffer.position(byteBuffer.limit());
                } else {
                    bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    length = bArr.length;
                    i = 0;
                }
                byte[] bArr2 = new byte[outLength(bArr, i, length)];
                return ByteBuffer.wrap(bArr2, 0, decode0(bArr, i, length, bArr2));
            } catch (IllegalArgumentException e) {
                byteBuffer.position(position);
                throw e;
            }
        }

        public InputStream wrap(InputStream inputStream) {
            Objects.requireNonNull(inputStream);
            return new DecInputStream(inputStream, this.isURL ? fromBase64URL : fromBase64, this.isMIME);
        }

        private int outLength(byte[] bArr, int i, int i2) {
            int i3;
            int[] iArr = this.isURL ? fromBase64URL : fromBase64;
            int i4 = i2 - i;
            int i5 = 0;
            if (i4 == 0) {
                return 0;
            }
            if (i4 < 2) {
                if (this.isMIME && iArr[0] == -1) {
                    return 0;
                }
                throw new IllegalArgumentException("Input byte[] should at least have 2 bytes for base64 bytes");
            }
            if (this.isMIME) {
                int i6 = 0;
                while (true) {
                    if (i >= i2) {
                        break;
                    }
                    int i7 = i + 1;
                    int i8 = bArr[i] & 255;
                    if (i8 == 61) {
                        i4 -= (i2 - i7) + 1;
                        break;
                    }
                    if (iArr[i8] == -1) {
                        i6++;
                    }
                    i = i7;
                }
                i4 -= i6;
            } else if (bArr[i2 - 1] == 61) {
                i5 = bArr[i2 - 2] == 61 ? 2 : 1;
            }
            if (i5 == 0 && (i3 = i4 & 3) != 0) {
                i5 = 4 - i3;
            }
            return (((i4 + 3) / 4) * 3) - i5;
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
            if (r11[r8] == 61) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x002f, code lost:
            if (r4 != 18) goto L34;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private int decode0(byte[] r11, int r12, int r13, byte[] r14) {
            /*
                Method dump skipped, instructions count: 208
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.esim.EsimBase64.Decoder.decode0(byte[], int, int, byte[]):int");
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static class EncOutputStream extends FilterOutputStream {

        /* renamed from: b0 */
        private int f18520b0;

        /* renamed from: b1 */
        private int f18521b1;

        /* renamed from: b2 */
        private int f18522b2;
        private final char[] base64;
        private boolean closed;
        private final boolean doPadding;
        private int leftover;
        private final int linemax;
        private int linepos;
        private final byte[] newline;

        EncOutputStream(OutputStream outputStream, char[] cArr, byte[] bArr, int i, boolean z) {
            super(outputStream);
            this.leftover = 0;
            this.closed = false;
            this.linepos = 0;
            this.base64 = cArr;
            this.newline = bArr;
            this.linemax = i;
            this.doPadding = z;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(int i) throws IOException {
            write(new byte[]{(byte) (i & 255)}, 0, 1);
        }

        private void checkNewline() throws IOException {
            if (this.linepos == this.linemax) {
                this.out.write(this.newline);
                this.linepos = 0;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.closed) {
                throw new IOException("Stream is closed");
            }
            if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return;
            }
            int i3 = this.leftover;
            if (i3 != 0) {
                if (i3 == 1) {
                    int i4 = i + 1;
                    this.f18521b1 = bArr[i] & 255;
                    i2--;
                    if (i2 == 0) {
                        this.leftover = i3 + 1;
                        return;
                    }
                    i = i4;
                }
                this.f18522b2 = bArr[i] & 255;
                i2--;
                checkNewline();
                this.out.write(this.base64[this.f18520b0 >> 2]);
                this.out.write(this.base64[((this.f18520b0 << 4) & 63) | (this.f18521b1 >> 4)]);
                this.out.write(this.base64[((this.f18521b1 << 2) & 63) | (this.f18522b2 >> 6)]);
                this.out.write(this.base64[this.f18522b2 & 63]);
                this.linepos += 4;
                i++;
            }
            int i5 = i2 / 3;
            this.leftover = i2 - (i5 * 3);
            while (true) {
                int i6 = i5 - 1;
                if (i5 <= 0) {
                    break;
                }
                checkNewline();
                int i7 = i + 1;
                int i8 = i7 + 1;
                int i9 = ((bArr[i] & 255) << 16) | ((bArr[i7] & 255) << 8) | (bArr[i8] & 255);
                this.out.write(this.base64[(i9 >>> 18) & 63]);
                this.out.write(this.base64[(i9 >>> 12) & 63]);
                this.out.write(this.base64[(i9 >>> 6) & 63]);
                this.out.write(this.base64[i9 & 63]);
                this.linepos += 4;
                i = i8 + 1;
                i5 = i6;
            }
            int i10 = this.leftover;
            if (i10 == 1) {
                this.f18520b0 = bArr[i] & 255;
            } else if (i10 == 2) {
                this.f18520b0 = bArr[i] & 255;
                this.f18521b1 = bArr[i + 1] & 255;
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            int i = this.leftover;
            if (i == 1) {
                checkNewline();
                this.out.write(this.base64[this.f18520b0 >> 2]);
                this.out.write(this.base64[(this.f18520b0 << 4) & 63]);
                if (this.doPadding) {
                    this.out.write(61);
                    this.out.write(61);
                }
            } else if (i == 2) {
                checkNewline();
                this.out.write(this.base64[this.f18520b0 >> 2]);
                this.out.write(this.base64[((this.f18520b0 << 4) & 63) | (this.f18521b1 >> 4)]);
                this.out.write(this.base64[(this.f18521b1 << 2) & 63]);
                if (this.doPadding) {
                    this.out.write(61);
                }
            }
            this.leftover = 0;
            this.out.close();
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static class DecInputStream extends InputStream {
        private final int[] base64;

        /* renamed from: is */
        private final InputStream f18519is;
        private final boolean isMIME;
        private int bits = 0;
        private int nextin = 18;
        private int nextout = -8;
        private boolean eof = false;
        private boolean closed = false;
        private byte[] sbBuf = new byte[1];

        DecInputStream(InputStream inputStream, int[] iArr, boolean z) {
            this.f18519is = inputStream;
            this.base64 = iArr;
            this.isMIME = z;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (read(this.sbBuf, 0, 1) == -1) {
                return -1;
            }
            return this.sbBuf[0] & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            if (this.closed) {
                throw new IOException("Stream is closed");
            }
            if (!this.eof || this.nextout >= 0) {
                if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
                    throw new IndexOutOfBoundsException();
                }
                if (this.nextout >= 0) {
                    int i4 = i;
                    while (i2 != 0) {
                        i3 = i4 + 1;
                        int i5 = this.bits;
                        int i6 = this.nextout;
                        bArr[i4] = (byte) (i5 >> i6);
                        i2--;
                        this.nextout = i6 - 8;
                        if (this.nextout < 0) {
                            this.bits = 0;
                        } else {
                            i4 = i3;
                        }
                    }
                    return i4 - i;
                }
                i3 = i;
                while (true) {
                    if (i2 <= 0) {
                        break;
                    }
                    int read = this.f18519is.read();
                    if (read == -1) {
                        this.eof = true;
                        int i7 = this.nextin;
                        if (i7 != 18) {
                            if (i7 == 12) {
                                throw new IOException("Base64 stream has one un-decoded dangling byte.");
                            }
                            int i8 = i3 + 1;
                            int i9 = this.bits;
                            bArr[i3] = (byte) (i9 >> 16);
                            int i10 = i2 - 1;
                            if (i7 == 0) {
                                if (i10 == 0) {
                                    this.bits = i9 >> 8;
                                    this.nextout = 0;
                                } else {
                                    i3 = i8 + 1;
                                    bArr[i8] = (byte) (i9 >> 8);
                                }
                            }
                            i3 = i8;
                        }
                        if (i3 == i) {
                            return -1;
                        }
                        return i3 - i;
                    } else if (read == 61) {
                        int i11 = this.nextin;
                        if (i11 == 18 || i11 == 12 || (i11 == 6 && this.f18519is.read() != 61)) {
                            throw new IOException("Illegal base64 ending sequence:" + this.nextin);
                        }
                        int i12 = i3 + 1;
                        int i13 = this.bits;
                        bArr[i3] = (byte) (i13 >> 16);
                        int i14 = i2 - 1;
                        if (this.nextin == 0) {
                            if (i14 == 0) {
                                this.bits = i13 >> 8;
                                this.nextout = 0;
                            } else {
                                bArr[i12] = (byte) (i13 >> 8);
                                i3 = i12 + 1;
                                this.eof = true;
                            }
                        }
                        i3 = i12;
                        this.eof = true;
                    } else {
                        int i15 = this.base64[read];
                        if (i15 == -1) {
                            if (!this.isMIME) {
                                throw new IOException("Illegal base64 character " + Integer.toString(i15, 16));
                            }
                        } else {
                            int i16 = this.bits;
                            int i17 = this.nextin;
                            this.bits = (i15 << i17) | i16;
                            if (i17 == 0) {
                                this.nextin = 18;
                                this.nextout = 16;
                                while (true) {
                                    int i18 = this.nextout;
                                    if (i18 >= 0) {
                                        int i19 = i3 + 1;
                                        bArr[i3] = (byte) (this.bits >> i18);
                                        i2--;
                                        this.nextout = i18 - 8;
                                        if (i2 == 0 && this.nextout >= 0) {
                                            return i19 - i;
                                        }
                                        i3 = i19;
                                    } else {
                                        this.bits = 0;
                                        break;
                                    }
                                }
                            } else {
                                this.nextin = i17 - 6;
                            }
                        }
                    }
                }
                return i3 - i;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            if (this.closed) {
                throw new IOException("Stream is closed");
            }
            return this.f18519is.available();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            this.f18519is.close();
        }
    }
}
