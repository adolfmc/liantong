package org.bouncycastle.crypto.p459io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.modes.AEADBlockCipher;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.crypto.io.CipherOutputStream */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CipherOutputStream extends FilterOutputStream {
    private AEADBlockCipher aeadBlockCipher;
    private byte[] buf;
    private BufferedBlockCipher bufferedBlockCipher;
    private final byte[] oneByte;
    private StreamCipher streamCipher;

    public CipherOutputStream(OutputStream outputStream, BufferedBlockCipher bufferedBlockCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.bufferedBlockCipher = bufferedBlockCipher;
    }

    public CipherOutputStream(OutputStream outputStream, StreamCipher streamCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.streamCipher = streamCipher;
    }

    public CipherOutputStream(OutputStream outputStream, AEADBlockCipher aEADBlockCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.aeadBlockCipher = aEADBlockCipher;
    }

    private void ensureCapacity(int i, boolean z) {
        if (z) {
            BufferedBlockCipher bufferedBlockCipher = this.bufferedBlockCipher;
            if (bufferedBlockCipher != null) {
                i = bufferedBlockCipher.getOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
                if (aEADBlockCipher != null) {
                    i = aEADBlockCipher.getOutputSize(i);
                }
            }
        } else {
            BufferedBlockCipher bufferedBlockCipher2 = this.bufferedBlockCipher;
            if (bufferedBlockCipher2 != null) {
                i = bufferedBlockCipher2.getUpdateOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher2 = this.aeadBlockCipher;
                if (aEADBlockCipher2 != null) {
                    i = aEADBlockCipher2.getUpdateOutputSize(i);
                }
            }
        }
        byte[] bArr = this.buf;
        if (bArr == null || bArr.length < i) {
            this.buf = new byte[i];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
        if (r1 != null) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005c  */
    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void close() throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            r4.ensureCapacity(r0, r1)
            org.bouncycastle.crypto.BufferedBlockCipher r1 = r4.bufferedBlockCipher     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            if (r1 == 0) goto L1b
            org.bouncycastle.crypto.BufferedBlockCipher r1 = r4.bufferedBlockCipher     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            byte[] r2 = r4.buf     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            int r1 = r1.doFinal(r2, r0)     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            if (r1 == 0) goto L37
            java.io.OutputStream r2 = r4.out     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            byte[] r3 = r4.buf     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
        L17:
            r2.write(r3, r0, r1)     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            goto L37
        L1b:
            org.bouncycastle.crypto.modes.AEADBlockCipher r1 = r4.aeadBlockCipher     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            if (r1 == 0) goto L2e
            org.bouncycastle.crypto.modes.AEADBlockCipher r1 = r4.aeadBlockCipher     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            byte[] r2 = r4.buf     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            int r1 = r1.doFinal(r2, r0)     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            if (r1 == 0) goto L37
            java.io.OutputStream r2 = r4.out     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            byte[] r3 = r4.buf     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            goto L17
        L2e:
            org.bouncycastle.crypto.StreamCipher r0 = r4.streamCipher     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            if (r0 == 0) goto L37
            org.bouncycastle.crypto.StreamCipher r0 = r4.streamCipher     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
            r0.reset()     // Catch: java.lang.Exception -> L3a org.bouncycastle.crypto.InvalidCipherTextException -> L43
        L37:
            r0 = 0
            r1 = r0
            goto L4b
        L3a:
            r0 = move-exception
            org.bouncycastle.crypto.io.CipherIOException r1 = new org.bouncycastle.crypto.io.CipherIOException
            java.lang.String r2 = "Error closing stream: "
            r1.<init>(r2, r0)
            goto L4b
        L43:
            r0 = move-exception
            org.bouncycastle.crypto.io.InvalidCipherTextIOException r1 = new org.bouncycastle.crypto.io.InvalidCipherTextIOException
            java.lang.String r2 = "Error finalising cipher data"
            r1.<init>(r2, r0)
        L4b:
            r4.flush()     // Catch: java.io.IOException -> L54
            java.io.OutputStream r0 = r4.out     // Catch: java.io.IOException -> L54
            r0.close()     // Catch: java.io.IOException -> L54
            goto L58
        L54:
            r0 = move-exception
            if (r1 != 0) goto L58
            goto L59
        L58:
            r0 = r1
        L59:
            if (r0 != 0) goto L5c
            return
        L5c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.p459io.CipherOutputStream.close():void");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        byte b = (byte) i;
        bArr[0] = b;
        if (this.streamCipher != null) {
            this.out.write(this.streamCipher.returnByte(b));
        } else {
            write(bArr, 0, 1);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int processBytes;
        ensureCapacity(i2, false);
        BufferedBlockCipher bufferedBlockCipher = this.bufferedBlockCipher;
        if (bufferedBlockCipher != null) {
            processBytes = bufferedBlockCipher.processBytes(bArr, i, i2, this.buf, 0);
            if (processBytes == 0) {
                return;
            }
        } else {
            AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
            if (aEADBlockCipher == null) {
                this.streamCipher.processBytes(bArr, i, i2, this.buf, 0);
                this.out.write(this.buf, 0, i2);
                return;
            }
            processBytes = aEADBlockCipher.processBytes(bArr, i, i2, this.buf, 0);
            if (processBytes == 0) {
                return;
            }
        }
        this.out.write(this.buf, 0, processBytes);
    }
}
