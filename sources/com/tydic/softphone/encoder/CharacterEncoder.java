package com.tydic.softphone.encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class CharacterEncoder {
    protected PrintStream pStream;

    protected abstract int bytesPerAtom();

    protected abstract int bytesPerLine();

    protected abstract void encodeAtom(OutputStream outputStream, byte[] bArr, int i, int i2) throws IOException;

    protected void encodeBufferSuffix(OutputStream outputStream) throws IOException {
    }

    protected void encodeLinePrefix(OutputStream outputStream, int i) throws IOException {
    }

    protected void encodeBufferPrefix(OutputStream outputStream) throws IOException {
        this.pStream = new PrintStream(outputStream);
    }

    protected void encodeLineSuffix(OutputStream outputStream) throws IOException {
        this.pStream.println();
    }

    protected int readFully(InputStream inputStream, byte[] bArr) throws IOException {
        for (int i = 0; i < bArr.length; i++) {
            int read = inputStream.read();
            if (read == -1) {
                return i;
            }
            bArr[i] = (byte) read;
        }
        return bArr.length;
    }

    public void encode(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[bytesPerLine()];
        encodeBufferPrefix(outputStream);
        while (true) {
            int readFully = readFully(inputStream, bArr);
            if (readFully == 0) {
                break;
            }
            encodeLinePrefix(outputStream, readFully);
            int i = 0;
            while (i < readFully) {
                if (bytesPerAtom() + i <= readFully) {
                    encodeAtom(outputStream, bArr, i, bytesPerAtom());
                } else {
                    encodeAtom(outputStream, bArr, i, readFully - i);
                }
                i += bytesPerAtom();
            }
            if (readFully < bytesPerLine()) {
                break;
            }
            encodeLineSuffix(outputStream);
        }
        encodeBufferSuffix(outputStream);
    }

    public void encode(byte[] bArr, OutputStream outputStream) throws IOException {
        encode(new ByteArrayInputStream(bArr), outputStream);
    }

    public String encode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encode(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            return byteArrayOutputStream.toString("8859_1");
        } catch (Exception unused) {
            throw new Error("CharacterEncoder.encode internal error");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] getBytes(java.nio.ByteBuffer r4) {
        /*
            r3 = this;
            boolean r0 = r4.hasArray()
            if (r0 == 0) goto L20
            byte[] r0 = r4.array()
            int r1 = r0.length
            int r2 = r4.capacity()
            if (r1 != r2) goto L20
            int r1 = r0.length
            int r2 = r4.remaining()
            if (r1 != r2) goto L20
            int r1 = r4.limit()
            r4.position(r1)
            goto L21
        L20:
            r0 = 0
        L21:
            if (r0 != 0) goto L2c
            int r0 = r4.remaining()
            byte[] r0 = new byte[r0]
            r4.get(r0)
        L2c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tydic.softphone.encoder.CharacterEncoder.getBytes(java.nio.ByteBuffer):byte[]");
    }

    public void encode(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        encode(getBytes(byteBuffer), outputStream);
    }

    public String encode(ByteBuffer byteBuffer) {
        return encode(getBytes(byteBuffer));
    }

    public void encodeBuffer(InputStream inputStream, OutputStream outputStream) throws IOException {
        int readFully;
        byte[] bArr = new byte[bytesPerLine()];
        encodeBufferPrefix(outputStream);
        do {
            readFully = readFully(inputStream, bArr);
            if (readFully == 0) {
                break;
            }
            encodeLinePrefix(outputStream, readFully);
            int i = 0;
            while (i < readFully) {
                if (bytesPerAtom() + i <= readFully) {
                    encodeAtom(outputStream, bArr, i, bytesPerAtom());
                } else {
                    encodeAtom(outputStream, bArr, i, readFully - i);
                }
                i += bytesPerAtom();
            }
            encodeLineSuffix(outputStream);
        } while (readFully >= bytesPerLine());
        encodeBufferSuffix(outputStream);
    }

    public void encodeBuffer(byte[] bArr, OutputStream outputStream) throws IOException {
        encodeBuffer(new ByteArrayInputStream(bArr), outputStream);
    }

    public String encodeBuffer(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encodeBuffer(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            return byteArrayOutputStream.toString();
        } catch (Exception unused) {
            throw new Error("CharacterEncoder.encodeBuffer internal error");
        }
    }

    public void encodeBuffer(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        encodeBuffer(getBytes(byteBuffer), outputStream);
    }

    public String encodeBuffer(ByteBuffer byteBuffer) {
        return encodeBuffer(getBytes(byteBuffer));
    }
}
